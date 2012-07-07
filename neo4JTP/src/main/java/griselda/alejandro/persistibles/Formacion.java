package griselda.alejandro.persistibles;

import griselda.alejandro.BaseDeDatos;
import griselda.alejandro.Persistible;
import griselda.alejandro.Relacion;

import java.util.LinkedList;
import java.util.List;

import org.neo4j.graphdb.Node;
import org.neo4j.kernel.AbstractGraphDatabase;

public class Formacion implements Persistible {

	private Integer id;			
	private Boolean persistir;	
	private Equipo equipo;				//relacion-unica
	private List<Titular> titulares;		//relacion-multiple
	private List<Jugador> suplentes;		//relacion-multiple

    public Formacion() {
        this.id = null;
        this.equipo = null;
        this.titulares = new LinkedList<Titular>();
        this.suplentes = new LinkedList<Jugador>();
        this.persistir = false;
    }

    public Formacion(Equipo equipo) {
        this.id = null;
        this.equipo = equipo;
        this.titulares = new LinkedList<Titular>();
        this.suplentes = new LinkedList<Jugador>();
        this.persistir = false;
    }

    public Persistible completeObjectWithNode(Node nodo) {
        Integer idNodo = (Integer) nodo.getProperty("id");
        if (BaseDeDatos.existe(Equipo.class, idNodo)) {
            return BaseDeDatos.detectar(Equipo.class, idNodo);
        } else {
            this.id = idNodo;
            this.equipo = BaseDeDatos.getInstanciaDe(nodo, Relacion.FORMACION$EQUIPO, Equipo.class);
            this.titulares = BaseDeDatos.getColeccionInstanciasDe(nodo, Relacion.FORMACION$TITULAR, Titular.class);
            this.suplentes = BaseDeDatos.getColeccionInstanciasDe(nodo, Relacion.FORMACION$SUPLENTE, Jugador.class);
            BaseDeDatos.guardar(this);
        }
        return this;
    }

    public Node persistirEn(AbstractGraphDatabase instanciaDB) {
    	return BaseDeDatos.persistirObjeto(this, Formacion.class);
    }

	public void persistirSiEsNecesario(Node nodeSelf) {
		if (persistir) {
			BaseDeDatos.relacionarYPersistir(nodeSelf, this.equipo, Relacion.FORMACION$EQUIPO);
            BaseDeDatos.relacionarYPersistirColeccion(nodeSelf, this.titulares, Relacion.FORMACION$TITULAR);
            BaseDeDatos.relacionarYPersistirColeccion(nodeSelf, this.suplentes, Relacion.FORMACION$SUPLENTE);
        }
	}

    // access
    public Integer getId() {
        return id;
    }

	public void setId(Integer nuevoId) {
		this.id = nuevoId;		
	}	

    public Boolean getPersistir() {
		return persistir;
	}

	public void setPersistir(Boolean persistir) {
		this.persistir = persistir;
	}

	public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.persistir = true;
        this.equipo = equipo;
    }

    public List<Jugador> getSuplentes() {
        return suplentes;
    }
    
    public void setSuplentes(List<Jugador> suplentes) {
    	this.persistir = true;
    	this.suplentes = suplentes;
    }
    
    public List<Titular> getTitulares() {
    	return titulares;
    }

    public void setTitulares(List<Titular> titulares) {
        this.persistir = true;
        this.titulares = titulares;
    }

    public void addTitular(Titular unTitular) {
        this.persistir = true;
        this.titulares.add(unTitular);
    }

}
