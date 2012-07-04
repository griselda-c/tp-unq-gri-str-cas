package giselda.alejandro.persistibles;

import griselda.alejandro.BaseDeDatos;
import griselda.alejandro.BaseDeDatos.Relacion;
import griselda.alejandro.Persistible;
import griselda.alejandro.nopersistibles.Posicion;

import org.neo4j.graphdb.Node;
import org.neo4j.kernel.AbstractGraphDatabase;

public class Titular implements Persistible {

    public Integer id;

    public Jugador jugador;

    public Posicion posicion;

    public Titular(Jugador jugador, Posicion posicion) {
        this.id = null;
        this.jugador = jugador;
        this.posicion = posicion;
    }

    public Titular() {
        this.id = null;
        this.jugador = null;
        this.posicion = null;
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
            this.id = BaseDeDatos.getNewIdEntidad(instancia, BaseDeDatos.TITULARES);
            nodeSelf = instancia.createNode();
            nodeSelf.setProperty("id", this.id);
            Node nodeJugadores = BaseDeDatos.getNodoEntidad(instancia, BaseDeDatos.TITULARES);
            nodeJugadores.createRelationshipTo(nodeSelf, Relacion.ENTIDAD);
        } else {
            nodeSelf = BaseDeDatos.getInstanciaFromEntidad(instancia, BaseDeDatos.TITULARES, this.id);
        }

        // self properties
        nodeSelf.setProperty("posicion", this.posicion.toString());

        // many-to-one
        BaseDeDatos.crearRelacionEntre(nodeSelf, jugador.persistirEn(instancia), Relacion.TITULARJUGADOR);

        return nodeSelf;
    }

    // access
    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public void setPosicion(Posicion posicion) {
        this.posicion = posicion;
    }

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public Posicion getPosicion() {
        return posicion;
    }

}
