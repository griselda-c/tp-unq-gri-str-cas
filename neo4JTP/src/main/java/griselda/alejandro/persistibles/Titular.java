package griselda.alejandro.persistibles;

import griselda.alejandro.BaseDeDatos;
import griselda.alejandro.Persistible;
import griselda.alejandro.Relacion;
import griselda.alejandro.nopersistibles.Posicion;

import org.neo4j.graphdb.Node;
import org.neo4j.kernel.AbstractGraphDatabase;

public class Titular implements Persistible {

    public Integer id;
    public Jugador jugador;		//relacion-simple
    public Posicion posicion;	//atributo enum
	private Boolean persistir;

    public Titular(Jugador jugador, Posicion posicion) {
        this.id = null;
        this.jugador = jugador;
        this.posicion = posicion;
        this.persistir = false;
    }

    public Titular() {
        this.id = null;
        this.jugador = null;
        this.posicion = null;
        this.persistir = false;
    }

    public Node persistirEn(AbstractGraphDatabase instanciaDB) {
    	return BaseDeDatos.persistirObjeto(this, Titular.class);
    }
    
    public Persistible completeObjectWithNode(Node nodo) {
        Integer idNodo = (Integer) nodo.getProperty("id");
        if (BaseDeDatos.existe(Habilidad.class, idNodo)) {
            return BaseDeDatos.detectar(Habilidad.class, idNodo);
        } else {
            this.id = idNodo;
            this.posicion = (Posicion) Posicion.valueOf((String) nodo.getProperty("posicion"));
            this.jugador = BaseDeDatos.getInstanciaDe(nodo, Relacion.TITULAR$JUGADOR, Jugador.class);
            BaseDeDatos.guardar(this);
        }
        return this;
    }

	public void persistirSiEsNecesario(Node nodeSelf) {
		if (persistir) {
			nodeSelf.setProperty("posicion", this.posicion.toString()); 
			BaseDeDatos.relacionarYPersistir(nodeSelf, this.jugador, Relacion.TITULAR$JUGADOR);
        }
	}

    // access
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
	
    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public void setPosicion(Posicion posicion) {
        this.posicion = posicion;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public Posicion getPosicion() {
        return posicion;
    }

}
