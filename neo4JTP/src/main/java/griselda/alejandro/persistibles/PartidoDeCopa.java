package griselda.alejandro.persistibles;

import griselda.alejandro.BaseDeDatos;
import griselda.alejandro.Persistible;
import griselda.alejandro.Relacion;
import griselda.alejandro.nopersistibles.Partido;

import org.neo4j.graphdb.Node;
import org.neo4j.kernel.AbstractGraphDatabase;

public class PartidoDeCopa extends Partido {

    private Integer id;
	private PartidoSimple partidoSimple1;	//relacion-unica
    private PartidoSimple partidoSimple2;	//relacion-unica
    private Integer golesPenalesEquipo1;	//atributo
    private Integer golesPenalesEquipo2;	//atributo		
	private Boolean persistir;	

    public PartidoDeCopa() {
        super();
        this.id = null;
        this.partidoSimple1 = null;
        this.partidoSimple2 = null;
        this.golesPenalesEquipo1 = 0;
        this.golesPenalesEquipo2 = 0;
        this.persistir = false;
    }

    public PartidoDeCopa(PartidoSimple e1, PartidoSimple e2) {
        this.id = null;
        this.partidoSimple1 = e1;
        this.partidoSimple2 = e2;
        this.golesPenalesEquipo1 = 0;
        this.golesPenalesEquipo2 = 0;
        this.persistir = false;
    }

    public Node persistirEn(AbstractGraphDatabase instanciaDB) {
    	return BaseDeDatos.persistirObjeto(this, PartidoDeCopa.class);
    }
    
    public Persistible completeObjectWithNode(Node nodo) {
        Integer idNodo = (Integer) nodo.getProperty("id");
        if (BaseDeDatos.existe(Equipo.class, idNodo)) {
            return BaseDeDatos.detectar(Equipo.class, idNodo);
        } else {
            this.id = idNodo;
            this.golesPenalesEquipo1 = (Integer) nodo.getProperty("golesPenalesEquipo1");
            this.golesPenalesEquipo2 = (Integer) nodo.getProperty("golesPenalesEquipo2");
            this.partidoSimple1 = BaseDeDatos.getInstanciaDe(nodo, Relacion.P_COPA$P_SIMPLE1, PartidoSimple.class);
            this.partidoSimple2 = BaseDeDatos.getInstanciaDe(nodo, Relacion.P_COPA$P_SIMPLE2, PartidoSimple.class);
            
    		
            BaseDeDatos.guardar(this);
        }
        return this;
    }
    
    public void persistirSiEsNecesario(Node nodeSelf) {
		if (persistir) {
            nodeSelf.setProperty("golesPenalesEquipo1", this.golesPenalesEquipo1);            
            nodeSelf.setProperty("golesPenalesEquipo2", this.golesPenalesEquipo2);            
            BaseDeDatos.relacionarYPersistir(nodeSelf, this.partidoSimple1, Relacion.P_COPA$P_SIMPLE1);
            BaseDeDatos.relacionarYPersistir(nodeSelf, this.partidoSimple2, Relacion.P_COPA$P_SIMPLE2);
        }
	}
   
    //access    
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

    public PartidoSimple getPartidoSimple1() {
        return partidoSimple1;
    }

    public void setPartidoSimple1(PartidoSimple partidoSimple1) {
        this.persistir = true;
        this.partidoSimple1 = partidoSimple1;
    }

    public PartidoSimple getPartidoSimple2() {
        return partidoSimple2;
    }

    public void setPartidoSimple2(PartidoSimple partidoSimple2) {
        this.persistir = true;
        this.partidoSimple2 = partidoSimple2;
    }

    public int getGolesPenalesEquipo1() {
        return golesPenalesEquipo1;
    }

    public void setGolesPenalesEquipo1(Integer golesPenalesEquipo1) {
        this.persistir = true;
        this.golesPenalesEquipo1 = golesPenalesEquipo1;
    }

    public int getGolesPenalesEquipo2() {
        return golesPenalesEquipo2;
    }

    public void setGolesPenalesEquipo2(Integer golesPenalesEquipo2) {
        this.persistir = true;
        this.golesPenalesEquipo2 = golesPenalesEquipo2;
    }

    public void setGolesPenalesAcertadosEquipos(Integer golesPenalesEquipo1, Integer golesPenalesEquipo2) {
        this.persistir = true;
        this.golesPenalesEquipo1 = golesPenalesEquipo1;
        this.golesPenalesEquipo2 = golesPenalesEquipo2;
    }

    public Integer getGolesEquipo1() {
        return (partidoSimple1.getGolesEquipo1() + partidoSimple2.getGolesEquipo1());
    }

    public Integer getGolesEquipo2() {
        return (partidoSimple1.getGolesEquipo2() + partidoSimple2.getGolesEquipo2());
    }

    public boolean empateComplejo() {
        return ((partidoSimple1.getIntGanador() != partidoSimple2.getIntGanador()) & (getGolesEquipo1() == getGolesEquipo2()));
    }

    public boolean empateSimple() {
        return (partidoSimple1.getIntGanador() == 0 & partidoSimple2.getIntGanador() == 0);
    }

    public boolean empate() {
        return empateSimple() | empateComplejo();
    }

    public Equipo irAPenales() {
        if (golesPenalesEquipo1 > golesPenalesEquipo2)
            return getEquipo1();
        else
            return getEquipo2();
    }

    public Equipo getEquipo1() {
        return partidoSimple1.getEquipo1();
    }

    public Equipo getEquipo2() {
        return partidoSimple1.getEquipo2();
    }

    public Equipo getGanador() {
        if (empate()) {
            return irAPenales();
        } else if (partidoSimple1.getIntGanador() == (partidoSimple2.getIntGanador())) {
            return partidoSimple1.getGanador();
        } else if (partidoSimple1.getIntGanador() == 0) {
            return partidoSimple2.getGanador();
        } else if (partidoSimple2.getIntGanador() == 0) {
            return partidoSimple1.getGanador();
        } else if (getGolesEquipo1() < getGolesEquipo2()) {
            return getEquipo2();
        }
        return getEquipo1();// contar goles
    }

}
