package griselda.alejandro;

import griselda.alejandro.BaseDeDatos.Relacion;

import org.neo4j.graphdb.Node;
import org.neo4j.kernel.AbstractGraphDatabase;

/**
 * TODO: description
 */
public class Jugador implements Persistible {
    public String nombre;

    public Integer id;

    public Jugador(String nombre) {
        this.nombre = nombre;
        this.id = null;
    }

    public Jugador() {
        super();
    }

    @Override
    public void persistirEn(AbstractGraphDatabase instancia) {
        if (this.id == null) {
            Node nuevoJugador = instancia.createNode();
            nuevoJugador
            instancia.getReferenceNode().createRelationshipTo(nuevoJugador, Relacion.JUGADOR);
        } else {

        }
        System.out.println("Se guardó el Jugador con nombre " + this.nombre);
    }
}
