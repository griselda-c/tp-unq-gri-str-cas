package griselda.alejandro;

import griselda.alejandro.BaseDeDatos.Relacion;

import org.neo4j.graphdb.Node;
import org.neo4j.kernel.AbstractGraphDatabase;

public class PartidoSimple extends Partido {

    Integer id;

    Equipo equipo1;

    Equipo equipo2;

    public PartidoSimple(Equipo eq1, Equipo eq2) {
        this.equipo1 = eq1;
        this.equipo2 = eq2;
    }

    public PartidoSimple() {
        super();
    }

    @Override
    public Node persistirEn(AbstractGraphDatabase instancia) {
        Node nodeSelf;
        if (this.id == null) {
            this.id = BaseDeDatos.getNewIdEntidad(instancia, BaseDeDatos.PARTIDOSSIMPLES);
            nodeSelf = instancia.createNode();
            nodeSelf.setProperty("id", this.id);
            Node nodePartidosSimples = BaseDeDatos.getNodoEntidad(instancia, BaseDeDatos.PARTIDOSSIMPLES);
            nodePartidosSimples.createRelationshipTo(nodeSelf, Relacion.ENTIDAD);
        } else {
            nodeSelf = BaseDeDatos.getInstanciaFromEntidad(instancia, BaseDeDatos.PARTIDOSSIMPLES, this.id);

        }

        Node equipoNode = equipo1.persistirEn(instancia);
        if (!BaseDeDatos.existeRelacion(nodeSelf, equipoNode, Relacion.PARTIDOEQUIPO1)) {
            nodeSelf.createRelationshipTo(equipoNode, Relacion.PARTIDOEQUIPO1);
        }

        Node equipoNode2 = equipo2.persistirEn(instancia);
        if (!BaseDeDatos.existeRelacion(nodeSelf, equipoNode2, Relacion.PARTIDOEQUIPO2)) {
            nodeSelf.createRelationshipTo(equipoNode2, Relacion.PARTIDOEQUIPO2);
        }

        return nodeSelf;
    }

    @Override
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Equipo getEquipo1() {
        return equipo1;
    }

    public void setEquipo1(Equipo equipo1) {
        this.equipo1 = equipo1;
    }

    public Equipo getEquipo2() {
        return equipo2;
    }

    public void setEquipo2(Equipo equipo2) {
        this.equipo2 = equipo2;
    }

}
