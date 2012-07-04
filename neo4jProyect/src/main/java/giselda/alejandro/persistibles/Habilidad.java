package giselda.alejandro.persistibles;

import griselda.alejandro.BaseDeDatos;
import griselda.alejandro.BaseDeDatos.Relacion;
import griselda.alejandro.Persistible;
import griselda.alejandro.nopersistibles.Posicion;

import org.neo4j.graphdb.Node;
import org.neo4j.kernel.AbstractGraphDatabase;

public class Habilidad implements Persistible {

    public Posicion posicion;

    public Integer id;

    public Integer valor;

    public Habilidad(Posicion posicion, Integer valor) {
        this.id = null;
        this.valor = valor;
        this.posicion = posicion;
    }

    public Habilidad() {
        this.id = null;
        this.valor = null;
        this.posicion = null;
    }

    @Override
    public Persistible createObjectOfNode(Node nodo) {
        return null;
        // TODO

    }

    public Integer getValor(Posicion posicion) {
        if (this.posicion.equals(posicion)) {
            return this.valor;
        } else {
            return 0;
        }
    }

    // acces
    public String getPosicionString() {
        return posicion.toString();
    }

    public Posicion getPosicion() {
        return posicion;
    }

    public void setPosicion(Posicion posicion) {
        this.posicion = posicion;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public Node persistirEn(AbstractGraphDatabase instancia) {
        Node nodeSelf;
        if (this.id == null) {
            this.id = BaseDeDatos.getNewIdEntidad(instancia, BaseDeDatos.HABILIDADES);
            nodeSelf = instancia.createNode();
            nodeSelf.setProperty("id", this.id);
            Node nodeJugadores = BaseDeDatos.getNodoEntidad(instancia, BaseDeDatos.HABILIDADES);
            nodeJugadores.createRelationshipTo(nodeSelf, Relacion.ENTIDAD);
        } else {
            nodeSelf = BaseDeDatos.getInstanciaFromEntidad(instancia, BaseDeDatos.HABILIDADES, this.id);
        }

        // self properties
        nodeSelf.setProperty("posicion", this.posicion.toString());
        nodeSelf.setProperty("valor", this.valor);

        return nodeSelf;
    }

}
