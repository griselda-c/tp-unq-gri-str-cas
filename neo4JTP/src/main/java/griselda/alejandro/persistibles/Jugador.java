package griselda.alejandro.persistibles;

import griselda.alejandro.BaseDeDatos;
import griselda.alejandro.Persistible;
import griselda.alejandro.Relacion;
import griselda.alejandro.nopersistibles.Posicion;

import java.util.LinkedList;
import java.util.List;

import org.neo4j.graphdb.Node;
import org.neo4j.kernel.AbstractGraphDatabase;

public class Jugador implements Persistible {

	private Integer id;
    private Boolean persistir;
    private String nombre;  				//atributo
    private String apellido;				//atributo
    private Integer nroCamiseta;			//atributo
    private List<Habilidad> habilidades;    //relacion-multiple

    public Jugador(String nombre, String apellido, Integer nroCamiseta) {
        this.id = null;
        this.nombre = nombre;
        this.apellido = apellido;
        this.nroCamiseta = nroCamiseta;
        this.habilidades = new LinkedList<Habilidad>();
        this.persistir = false;
    }

    public Jugador() {
        this.id = null;
        this.nombre = null;
        this.apellido = null;
        this.nroCamiseta = null;
        this.habilidades = new LinkedList<Habilidad>();
        this.persistir = false;
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

    public Node persistirEn(AbstractGraphDatabase instanciaDB) {
        return BaseDeDatos.persistirObjeto(this, Jugador.class);
    }
    
    public Persistible completeObjectWithNode(Node nodo) {
        Integer idNodo = (Integer) nodo.getProperty("id");
        if (BaseDeDatos.existe(Jugador.class, idNodo)) {
            return BaseDeDatos.detectar(Jugador.class, idNodo);
        } else {
            this.id = idNodo;
            this.nombre = (String) nodo.getProperty("nombre");
            this.apellido = (String) nodo.getProperty("apellido");
            this.nroCamiseta = (Integer) nodo.getProperty("nroCamiseta");
            this.habilidades = BaseDeDatos.getColeccionInstanciasDe(nodo, Relacion.JUGADOR$HABILIDAD, Habilidad.class);
           
            BaseDeDatos.guardar(this);
        }
        return this;
    }

	public void persistirSiEsNecesario(Node nodeSelf) {
		if (persistir) {
            nodeSelf.setProperty("nombre", this.nombre); 
            nodeSelf.setProperty("apellido", this.apellido); 
            nodeSelf.setProperty("nroCamiseta", this.nroCamiseta);            
            BaseDeDatos.relacionarYPersistirColeccion(nodeSelf, this.habilidades, Relacion.JUGADOR$HABILIDAD);
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

	public void addHabilidad(Habilidad habilidad) {
        this.persistir = true;
        this.habilidades.add(habilidad);
    }
    
    public String getNombre() {
        return nombre;
    }

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

    public void getNroCamiseta(Integer nroC) {
        this.persistir = true;
        this.nroCamiseta = nroC;
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

}
