package giselda.alejandro.persistibles;

import griselda.alejandro.BaseDeDatos;
import griselda.alejandro.BaseDeDatos.Relacion;
import griselda.alejandro.DAO;
import griselda.alejandro.Persistible;
import griselda.alejandro.nopersistibles.Posicion;

import java.util.LinkedList;
import java.util.List;

import org.neo4j.graphdb.Node;
import org.neo4j.kernel.AbstractGraphDatabase;

/**
 * TODO: description
 */
public class Equipo implements Persistible {

    private String nombre;

    private Integer id;

    private Boolean persistir;

    private List<Jugador> jugadores;

    private List<Posicion> posiciones;

    private Tecnico tecnico;

    private Formacion formacion;

    public Equipo() {
        super();
        this.id = null;
        this.nombre = null;
        this.tecnico = null;
        this.formacion = null;
        this.jugadores = new LinkedList<Jugador>();
        this.posiciones = new LinkedList<Posicion>();
        this.persistir = false;
    }

    public Equipo(String nombre) {
        super();
        this.id = null;
        this.nombre = nombre;
        this.tecnico = null;
        this.formacion = null;
        this.jugadores = new LinkedList<Jugador>();
        this.posiciones = new LinkedList<Posicion>();
        this.persistir = false;
    }

    @Override
    public Persistible completeObjectWithNode(Node nodo) {
        Integer idNodo = (Integer) nodo.getProperty("id");
        if (BaseDeDatos.existe(Equipo.class, idNodo)) {
            return BaseDeDatos.detectar(Equipo.class, idNodo);
        } else {
            this.id = idNodo;
            this.nombre = (String) nodo.getProperty("nombre");
            this.formacion = (Formacion) new Formacion().completeObjectWithNode(nodo);
            this.jugadores = DAO.getJugadoresFrom(nodo, Relacion.EQUIPOJUGADOR);
            this.posiciones = DAO.getPosicionesFrom((String[]) nodo.getProperty("posiciones"));
            this.tecnico = (Tecnico) new Tecnico().completeObjectWithNode(nodo);
            BaseDeDatos.guardar(this);
        }
        return this;
    }

    @Override
    public Node persistirEn(AbstractGraphDatabase instancia) {
        Node nodeSelf;
        if (this.id == null) {
            this.persistir = true;
            this.id = BaseDeDatos.getNewIdEntidad(instancia, BaseDeDatos.EQUIPOS);
            nodeSelf = instancia.createNode();
            nodeSelf.setProperty("id", this.id);
            Node nodeJugadores = BaseDeDatos.getNodoEntidad(instancia, BaseDeDatos.EQUIPOS);
            nodeJugadores.createRelationshipTo(nodeSelf, Relacion.ENTIDAD);
        } else {
            nodeSelf = BaseDeDatos.getInstanciaFromEntidad(instancia, BaseDeDatos.EQUIPOS, this.id);
        }

        if (persistir) {
            // self properties
            nodeSelf.setProperty("nombre", this.nombre);

            String posicionesArray[] = new String[this.posiciones.size()];

            Integer arraySlot = 0;
            for (Posicion posicion : this.posiciones) {
                posicionesArray[arraySlot] = posicion.toString();
                arraySlot++;
            }

            nodeSelf.setProperty("posiciones", posicionesArray);
            // many-to-one
            BaseDeDatos.crearRelacionEntre(nodeSelf, tecnico.persistirEn(instancia), Relacion.EQUIPOTECNICO);

            // many-to-one
            BaseDeDatos.crearRelacionEntre(nodeSelf, formacion.persistirEn(instancia), Relacion.EQUIPOFORMACION);

            // one-to-many
            for (Jugador jugador : this.jugadores) {
                BaseDeDatos.crearRelacionEntre(nodeSelf, jugador.persistirEn(instancia), Relacion.EQUIPOJUGADOR);
            }
        }
        this.persistir = false;
        return nodeSelf;
    }

    // Access

    public Formacion armarFormacion() {
        this.persistir = true;
        this.setFormacion(tecnico.armarFormacion(this));
        return this.getFormacion();
    }

    public void addPosicion(Posicion unPosicion) {
        this.persistir = true;
        this.posiciones.add(unPosicion);
    }

    public void agregarJugador(Jugador j) {
        this.persistir = true;
        this.jugadores.add(j);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.persistir = true;
        this.nombre = nombre;
    }

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(List<Jugador> jugadores) {
        this.persistir = true;
        this.jugadores = jugadores;
    }

    public List<Posicion> getPosiciones() {
        return posiciones;
    }

    public void setPosiciones(List<Posicion> posiciones) {
        this.persistir = true;
        this.posiciones = posiciones;
    }

    public Tecnico getTecnico() {
        return tecnico;
    }

    public void setTecnico(Tecnico tecnico) {
        this.persistir = true;
        this.tecnico = tecnico;
    }

    public Formacion getFormacion() {
        return formacion;
    }

    public void setFormacion(Formacion formacion) {
        this.persistir = true;
        this.formacion = formacion;
    }

}
