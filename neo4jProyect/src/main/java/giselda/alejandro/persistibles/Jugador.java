package giselda.alejandro.persistibles;

import griselda.alejandro.BaseDeDatos;
import griselda.alejandro.BaseDeDatos.Relacion;
import griselda.alejandro.Persistible;
import griselda.alejandro.nopersistibles.Posicion;

import java.util.LinkedList;
import java.util.List;

import org.neo4j.graphdb.Node;
import org.neo4j.kernel.AbstractGraphDatabase;

public class Jugador implements Persistible {

    private Boolean persistir;

    private String nombre;

    private String apellido;

    private Integer nroCamiseta;

    private Integer id;

    private List<Habilidad> habilidades = new LinkedList<Habilidad>();

    public Jugador(String nombre, String apellido, Integer nroCamiseta) {
        this.id = null;
        this.nombre = nombre;
        this.apellido = apellido;
        this.nroCamiseta = nroCamiseta;
        this.persistir = false;
    }

    public Jugador() {
        super();
        this.id = null;
        this.nombre = null;
        this.apellido = null;
        this.nroCamiseta = null;
        this.persistir = false;
    }

    @Override
    public Persistible createObjectOfNode(Node nodo) {
        return null;
        // TODO

    }

    @Override
    public Node persistirEn(AbstractGraphDatabase instancia) {
        Node nodeSelf;
        if (this.id == null) {
            this.persistir = true;
            this.id = BaseDeDatos.getNewIdEntidad(instancia, BaseDeDatos.JUGADORES);
            nodeSelf = instancia.createNode();

            nodeSelf.setProperty("id", this.id);
            Node nodeJugadores = BaseDeDatos.getNodoEntidad(instancia, BaseDeDatos.JUGADORES);
            nodeJugadores.createRelationshipTo(nodeSelf, Relacion.ENTIDAD);

        } else {
            nodeSelf = BaseDeDatos.getInstanciaFromEntidad(instancia, BaseDeDatos.JUGADORES, this.id);

        }
        if (this.persistir) {
            for (Habilidad habilidad : this.habilidades) {
                BaseDeDatos.crearRelacionEntre(nodeSelf, habilidad.persistirEn(instancia), Relacion.JUGADORHABILIDAD);
            }

            nodeSelf.setProperty("nombre", this.nombre);
            nodeSelf.setProperty("apellido", this.apellido);
            nodeSelf.setProperty("nroCamiseta", this.nroCamiseta);
        }
        this.persistir = false;
        return nodeSelf;
    }

    public String getNombre() {
        return nombre;
    }

    public int getValorHabilidad(Posicion posicion) {
        int valor = 0;
        for (Habilidad i : this.habilidades) {
            if (i.getValor(posicion) > valor) {
                valor = i.getValor(posicion);
            }
        }
        return valor;
    }

    public void addHabilidad(Habilidad habilidad) {
        this.persistir = true;
        this.habilidades.add(habilidad);
    }

    // Access

    public void setNombre(String nombre) {
        this.persistir = true;
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.persistir = true;
        this.apellido = apellido;
    }

    public Integer getNroCamiseta() {
        return nroCamiseta;
    }

    public void setNroCamiseta(Integer nroCamiseta) {
        this.persistir = true;
        this.nroCamiseta = nroCamiseta;
    }

    public List<Habilidad> getHabilidades() {
        return habilidades;
    }

    public void setHabilidades(List<Habilidad> habilidades) {
        this.persistir = true;
        this.habilidades = habilidades;
    }

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
