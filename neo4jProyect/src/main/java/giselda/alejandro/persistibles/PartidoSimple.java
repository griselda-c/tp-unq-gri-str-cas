package giselda.alejandro.persistibles;

import griselda.alejandro.BaseDeDatos;
import griselda.alejandro.BaseDeDatos.Relacion;
import griselda.alejandro.Persistible;
import griselda.alejandro.nopersistibles.Partido;

import org.neo4j.graphdb.Node;
import org.neo4j.kernel.AbstractGraphDatabase;

public class PartidoSimple extends Partido {

    private Boolean persistir;

    private Equipo equipo1;

    private Equipo equipo2;

    private int golesEquipo1;

    private int golesEquipo2;

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

    @Override
    public Persistible createObjectOfNode(Node nodo) {
        return null;
        // TODO

    }

    @Override
    public Node persistirEn(AbstractGraphDatabase instancia) {
        Node nodeSelf;
        if (this.id == null) {
            System.out.println("se persiste " + this);
            this.persistir = true;
            this.id = BaseDeDatos.getNewIdEntidad(instancia, BaseDeDatos.PARTIDOSSIMPLES);
            nodeSelf = instancia.createNode();
            nodeSelf.setProperty("id", this.id);
            Node nodePartidosSimples = BaseDeDatos.getNodoEntidad(instancia, BaseDeDatos.PARTIDOSSIMPLES);
            nodePartidosSimples.createRelationshipTo(nodeSelf, Relacion.ENTIDAD);
        } else {
            nodeSelf = BaseDeDatos.getInstanciaFromEntidad(instancia, BaseDeDatos.PARTIDOSSIMPLES, this.id);

        }
        if (this.persistir) {
            nodeSelf.setProperty("golesEquipo1", this.golesEquipo1);
            nodeSelf.setProperty("golesEquipo2", this.golesEquipo2);

            BaseDeDatos.crearRelacionEntre(nodeSelf, equipo1.persistirEn(instancia), Relacion.PARTIDOEQUIPO1);
            BaseDeDatos.crearRelacionEntre(nodeSelf, equipo2.persistirEn(instancia), Relacion.PARTIDOEQUIPO2);
        }
        this.persistir = false;
        return nodeSelf;
    }

    @Override
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

    public void setGolesEquipos(int golesEquipo1, int golesEquipo2) {
        this.persistir = true;
        this.golesEquipo1 = golesEquipo1;
        this.golesEquipo2 = golesEquipo2;
    }

    @Override
    public Equipo getEquipo1() {
        return equipo1;
    }

    public void setEquipo1(Equipo equipo1) {
        this.persistir = true;
        this.equipo1 = equipo1;
    }

    @Override
    public Equipo getEquipo2() {
        return equipo2;
    }

    public void setEquipo2(Equipo equipo2) {
        this.persistir = true;
        this.equipo2 = equipo2;
    }

    @Override
    public int getGolesEquipo2() {
        return golesEquipo2;
    }

    @Override
    public int getGolesEquipo1() {
        return golesEquipo1;
    }

    public void setGolesEquipo1(int golesEquipo1) {
        this.persistir = true;
        this.golesEquipo1 = golesEquipo1;
    }

    public void setGolesEquipo2(int golesEquipo2) {
        this.persistir = true;
        this.golesEquipo2 = golesEquipo2;
    }

}
