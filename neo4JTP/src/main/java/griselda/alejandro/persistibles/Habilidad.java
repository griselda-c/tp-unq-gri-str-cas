package griselda.alejandro.persistibles;

import griselda.alejandro.BaseDeDatos;
import griselda.alejandro.Persistible;
import griselda.alejandro.nopersistibles.Posicion;

import org.neo4j.graphdb.Node;
import org.neo4j.kernel.AbstractGraphDatabase;

public class Habilidad implements Persistible {

	private Integer id;
	private Boolean persistir;
	private Posicion posicion;	//atributo enum
	private Integer valor;		//atributo

    public Habilidad(Posicion posicion, Integer valor) {
        this.id = null;
        this.valor = valor;
        this.posicion = posicion;
        this.persistir = false;
    }

    public Habilidad() {
        this.id = null;
        this.valor = null;
        this.posicion = null;
        this.persistir = false;
    }

    public Integer getValor(Posicion posicion) {
        if (this.posicion.equals(posicion)) {
            return this.valor;
        } else {
            return 0;
        }
    }

    public Node persistirEn(AbstractGraphDatabase instanciaDB) {
    	return BaseDeDatos.persistirObjeto(this, Habilidad.class);
    }
    
    public Persistible completeObjectWithNode(Node nodo) {
        Integer idNodo = (Integer) nodo.getProperty("id");
        if (BaseDeDatos.existe(Habilidad.class, idNodo)) {
            return BaseDeDatos.detectar(Habilidad.class, idNodo);
        } else {
            this.id = idNodo;
            this.posicion = (Posicion) Posicion.valueOf((String) nodo.getProperty("posicion"));
            this.valor = (Integer) nodo.getProperty("valor");
            BaseDeDatos.guardar(this);
        }
        return this;
    }

	public void persistirSiEsNecesario(Node nodeSelf) {
		if (persistir) {
			nodeSelf.setProperty("posicion", this.posicion.toString()); 
			nodeSelf.setProperty("valor", this.valor); 
        }
	}

    // acces    
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

	public String getPosicionString() {
        return posicion.toString();
    }

    public Posicion getPosicion() {
        return posicion;
    }

    public void setPosicion(Posicion posicion) {
        this.persistir = true;
        this.posicion = posicion;
    }

    public Integer getValor() {
        return valor;
    }

    public void setValor(Integer valor) {
        this.persistir = true;
        this.valor = valor;
    }

}
