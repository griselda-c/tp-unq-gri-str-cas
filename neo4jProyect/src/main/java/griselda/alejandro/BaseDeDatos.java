package griselda.alejandro;

import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.RelationshipType;
import org.neo4j.graphdb.Transaction;
import org.neo4j.kernel.AbstractGraphDatabase;
import org.neo4j.kernel.EmbeddedGraphDatabase;

/**
 * TODO: description
 */
public class BaseDeDatos {

    public static void abrirBase() {
        instancia = new EmbeddedGraphDatabase("base");

        Transaction tx = instancia.beginTx();
        try {
            if (!instancia.getReferenceNode().hasProperty("iniciado")) {
                System.out.println("se creo la base");
                instancia.getReferenceNode().setProperty("iniciado", "iniciado");

                Node equiposNode = instancia.createNode();
                equiposNode.setProperty("entidad", "equipos");
                equiposNode.setProperty("contadorId", 0);
                instancia.getReferenceNode().createRelationshipTo(equiposNode, Relacion.ENTIDAD);

                Node jugadoresNode = instancia.createNode();
                jugadoresNode.setProperty("entidad", "jugadores");
                jugadoresNode.setProperty("contadorId", 0);
                instancia.getReferenceNode().createRelationshipTo(jugadoresNode, Relacion.ENTIDAD);
                tx.success();
            } else {
                System.out.println("Existe una base en el directorio");
            }
        } finally {
            tx.finish();
        }
    }

    public static enum Relacion implements RelationshipType {
        ENTIDAD, JUGADOR, EQUIPO
    }

    public static AbstractGraphDatabase instancia;

    public static void cerrarBase() {
        instancia.shutdown();
    }

    public static void persistir(Persistible object) {
        abrirBase();
        Transaction tx = instancia.beginTx();
        try {
            object.persistirEn(instancia);
        } finally {
            tx.finish();
        }
        cerrarBase();
    }
}
