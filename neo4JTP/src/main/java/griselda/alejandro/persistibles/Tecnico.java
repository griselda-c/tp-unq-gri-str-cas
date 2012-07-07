package griselda.alejandro.persistibles;

import griselda.alejandro.BaseDeDatos;
import griselda.alejandro.Persistible;
import griselda.alejandro.nopersistibles.FormacionStrategy;

import org.neo4j.graphdb.Node;
import org.neo4j.kernel.AbstractGraphDatabase;

public class Tecnico implements Persistible {

	public Integer id;
    public String nombre;		//atributo
    public String apellido;		//atributo
    private Boolean persistir;

    public Tecnico() {
        this.id = null;
        this.nombre = null;
        this.apellido = null;
        this.persistir = false;
    }

    public Tecnico(String nombre, String apellido) {
        this.id = null;
        this.nombre = nombre;
        this.apellido = apellido;
        this.persistir = false;
    }

    public Node persistirEn(AbstractGraphDatabase instanciaDB) {
    	return BaseDeDatos.persistirObjeto(this, Tecnico.class);
    }
    
    public Persistible completeObjectWithNode(Node nodo) {
        Integer idNodo = (Integer) nodo.getProperty("id");
        if (BaseDeDatos.existe(Equipo.class, idNodo)) {
            return BaseDeDatos.detectar(Equipo.class, idNodo);
        } else {
            this.id = idNodo;
            this.nombre = (String) nodo.getProperty("nombre");
            this.apellido = (String) nodo.getProperty("apellido");                		
            BaseDeDatos.guardar(this);
        }
        return this;
    }
    
    public void persistirSiEsNecesario(Node nodeSelf) {
		if (persistir) {
            nodeSelf.setProperty("nombre", this.nombre);            
            nodeSelf.setProperty("apellido", this.apellido);            
        }
	}
    
    
    

    // access
    public Integer getId() {
        return this.id;
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

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(final String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return this.apellido;
    }

    public void setApellido(final String apellido) {
        this.apellido = apellido;
    }

    public Formacion armarFormacion(Equipo unEquipo) {
        FormacionStrategy formacion = new FormacionStrategy();
        return formacion.armarFormacion(unEquipo);
    }

}
