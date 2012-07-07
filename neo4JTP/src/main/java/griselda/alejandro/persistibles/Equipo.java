package griselda.alejandro.persistibles;

import griselda.alejandro.BaseDeDatos;
import griselda.alejandro.Persistible;
import griselda.alejandro.nopersistibles.Posicion;
import griselda.alejandro.Relacion;

import java.util.LinkedList;
import java.util.List;

import org.neo4j.graphdb.Node;
import org.neo4j.kernel.AbstractGraphDatabase;

public class Equipo implements Persistible {

	private Integer id;					
	private Boolean persistir;			
    private String nombre;				//atributo
    private Tecnico tecnico;			//relacion-unica
    private Formacion formacion;		//relacion-unica
    private List<Jugador> jugadores;	//relacion-multiple
    private List<Posicion> posiciones;	//relacion-multiple

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

    public Node persistirEn(AbstractGraphDatabase instanciaDB) {
    	return BaseDeDatos.persistirObjeto(this, Equipo.class);
    }
    
    public Persistible completeObjectWithNode(Node nodo) {
        Integer idNodo = (Integer) nodo.getProperty("id");
        if (BaseDeDatos.existe(Equipo.class, idNodo)) {
            return BaseDeDatos.detectar(Equipo.class, idNodo);
        } else {
            this.id = idNodo;
            this.nombre = (String) nodo.getProperty("nombre");
            this.formacion = BaseDeDatos.getInstanciaDe(nodo, Relacion.EQUIPO$FORMACION, Formacion.class);
            this.tecnico = BaseDeDatos.getInstanciaDe(nodo, Relacion.EQUIPO$TECNICO, Tecnico.class);
            this.jugadores = BaseDeDatos.getColeccionInstanciasDe(nodo, Relacion.EQUIPO$JUGADOR, Jugador.class);
            
            //this.posiciones = BaseDeDatos.getColeccionEnumerablesDe(nodo, "posiciones", Habilidad.class);
            List<Posicion> instanciasEnum = new LinkedList<Posicion>();
    		String[] nombresEnums = (String[]) nodo.getProperty("posiciones");
    		for (String nombreEnum : nombresEnums) {
    			instanciasEnum.add(Posicion.valueOf(nombreEnum));
    		}
    		this.posiciones = instanciasEnum;
    		
            BaseDeDatos.guardar(this);
        }
        return this;
    }

	public void persistirSiEsNecesario(Node nodeSelf) {
		if (persistir) {
            nodeSelf.setProperty("nombre", this.nombre);            
            BaseDeDatos.relacionarYPersistir(nodeSelf, this.tecnico, Relacion.EQUIPO$TECNICO);
            BaseDeDatos.relacionarYPersistir(nodeSelf, this.formacion, Relacion.EQUIPO$FORMACION);
            BaseDeDatos.relacionarYPersistirColeccionEnumerables(nodeSelf, this.posiciones, "posiciones");
            BaseDeDatos.relacionarYPersistirColeccion(nodeSelf, this.jugadores, Relacion.EQUIPO$JUGADOR);
        }
	}

    // Access
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }    

    public Boolean getPersistir() {
		return persistir;
	}

	public void setPersistir(Boolean persistir) {
		this.persistir = persistir;
	}

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
