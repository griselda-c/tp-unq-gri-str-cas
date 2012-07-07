package griselda.alejandro.persistibles;

import griselda.alejandro.BaseDeDatos;
import griselda.alejandro.Persistible;
import griselda.alejandro.Relacion;
import griselda.alejandro.nopersistibles.Partido;

import org.neo4j.graphdb.Node;
import org.neo4j.kernel.AbstractGraphDatabase;

public class PartidoSimple extends Partido {

    private Integer id;
    private Equipo equipo1;			//relacion-unica
    private Equipo equipo2;			//relacion-unica
    private Integer golesEquipo1;	//atributo
    private Integer golesEquipo2;	//atributo
    private Boolean persistir;

    public PartidoSimple(Equipo eq1, Equipo eq2) {
        this.id = null;
        this.equipo1 = eq1;
        this.equipo2 = eq2;
        this.golesEquipo1 = 0;
        this.golesEquipo2 = 0;
        this.persistir = false;
    }

    public PartidoSimple() {
        this.id = null;
        this.equipo1 = null;
        this.equipo2 = null;
        this.golesEquipo1 = 0;
        this.golesEquipo2 = 0;
        this.persistir = false;
    }
    
    public Node persistirEn(AbstractGraphDatabase instanciaDB) {
    	return BaseDeDatos.persistirObjeto(this, PartidoSimple.class);
    }
    
    public Persistible completeObjectWithNode(Node nodo) {
        Integer idNodo = (Integer) nodo.getProperty("id");
        if (BaseDeDatos.existe(Equipo.class, idNodo)) {
            return BaseDeDatos.detectar(Equipo.class, idNodo);
        } else {
            this.id = idNodo;
            this.golesEquipo1 = (Integer) nodo.getProperty("golesEquipo1");
            this.golesEquipo2 = (Integer) nodo.getProperty("golesEquipo2");
            this.equipo1 = BaseDeDatos.getInstanciaDe(nodo, Relacion.PSIMPLE$EQUIPO1, Equipo.class);
            this.equipo2 = BaseDeDatos.getInstanciaDe(nodo, Relacion.PSIMPLE$EQUIPO2, Equipo.class);
            
    		
            BaseDeDatos.guardar(this);
        }
        return this;
    }
    
    public void persistirSiEsNecesario(Node nodeSelf) {
		if (persistir) {
            nodeSelf.setProperty("golesEquipo1", this.golesEquipo1);            
            nodeSelf.setProperty("golesEquipo2", this.golesEquipo2);            
            BaseDeDatos.relacionarYPersistir(nodeSelf, this.equipo1, Relacion.PSIMPLE$EQUIPO1);
            BaseDeDatos.relacionarYPersistir(nodeSelf, this.equipo2, Relacion.PSIMPLE$EQUIPO2);
        }
	}

    
    public Equipo getGanador() {
        if (getGolesEquipo1() == getGolesEquipo2()) {
            return null;
        } else if (getGolesEquipo1() > getGolesEquipo2()) {
            return getEquipo1();
        } else {
            return getEquipo2();
        }
    }

    public int getIntGanador() {
        if (getGolesEquipo1() == getGolesEquipo2()) {
            return 0;
        } else if (getGolesEquipo1() > getGolesEquipo2()) {
            return 1;
        } else {
            return 2;
        }
    }
    
    

    // Access    
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

    public void setGolesEquipos(int golesEquipo1, int golesEquipo2) {
        this.persistir = true;
        this.golesEquipo1 = golesEquipo1;
        this.golesEquipo2 = golesEquipo2;
    }

    public Equipo getEquipo1() {
        return equipo1;
    }

    public void setEquipo1(Equipo equipo1) {
        this.persistir = true;
        this.equipo1 = equipo1;
    }

    public Equipo getEquipo2() {
        return equipo2;
    }

    public void setEquipo2(Equipo equipo2) {
        this.persistir = true;
        this.equipo2 = equipo2;
    }

    public Integer getGolesEquipo2() {
        return golesEquipo2;
    }

    public Integer getGolesEquipo1() {
        return golesEquipo1;
    }

    public void setGolesEquipo1(Integer golesEquipo1) {
        this.persistir = true;
        this.golesEquipo1 = golesEquipo1;
    }

    public void setGolesEquipo2(Integer golesEquipo2) {
        this.persistir = true;
        this.golesEquipo2 = golesEquipo2;
    }

}
