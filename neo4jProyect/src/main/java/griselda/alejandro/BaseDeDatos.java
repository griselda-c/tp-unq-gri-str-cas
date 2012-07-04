package griselda.alejandro;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.RelationshipType;
import org.neo4j.graphdb.Transaction;
import org.neo4j.kernel.AbstractGraphDatabase;
import org.neo4j.kernel.EmbeddedGraphDatabase;

public class BaseDeDatos {

    public static final String EQUIPOS = "equipos";

    public static final String FORMACIONES = "formacion";

    public static final String HABILIDADES = "habilidades";

    public static final String JUGADORES = "jugadores";

    public static final String PARTIDOSCOPA = "partidosCopa";

    public static final String PARTIDOSSIMPLES = "partidosSimples";

    public static final String TECNICOS = "tecnicos";

    public static final String TITULARES = "titulares";

    public static Map<String, Persistible> identityMap = new HashMap<String, Persistible>();

    public static void abrirBase() {
        instancia = new EmbeddedGraphDatabase("base09");

        Transaction tx = instancia.beginTx();
        try {
            if (!instancia.getReferenceNode().hasProperty("iniciado")) {
                System.out.println("se creo la base");
                instancia.getReferenceNode().setProperty("iniciado", "iniciado");

                String entidades[] = { EQUIPOS, FORMACIONES, HABILIDADES, JUGADORES, PARTIDOSCOPA, PARTIDOSSIMPLES,
                        TECNICOS, TITULARES };

                for (int i = 0; i < entidades.length; i++) {
                    String entidad = entidades[i];
                    Node equiposNode = instancia.createNode();
                    equiposNode.setProperty("entidad", entidad);
                    equiposNode.setProperty("contadorId", 0);
                    instancia.getReferenceNode().createRelationshipTo(equiposNode, Relacion.CLASE);
                    System.out.println("Se crea la clase " + entidad);
                }
                tx.success();
            } else {
                // System.out.println("Existe una base en el directorio");
            }
        } finally {
            tx.finish();
        }
    }

    public static enum Relacion implements RelationshipType {
        CLASE, ENTIDAD, EQUIPOJUGADOR, PARTIDOEQUIPO2, PARTIDOEQUIPO1, EQUIPOTECNICO, EQUIPOFORMACION, FORMACIONEQUIPO, FORMACIONTITULAR, FORMACIONSUPLENTE, TITULARJUGADOR, TITULARPOSICION, JUGADORHABILIDAD, PARTIDOSIMPLE1, PARTIDOSIMPLE2
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
            System.out.println("se persiste " + object);
            tx.success();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            tx.finish();
        }
        cerrarBase();
    }

    public static void persistirColeccion(List<?> persistibles) {
        abrirBase();
        Transaction tx = instancia.beginTx();
        try {
            for (Object persistible : persistibles) {
                ((Persistible) persistible).persistirEn(instancia);
                System.out.println("se persiste " + persistible);
            }
            tx.success();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            tx.finish();
        }
        cerrarBase();
    }

    public static Node getNodoEntidad(AbstractGraphDatabase instanciaTemp, String tipoEntidad) {
        Iterable<Relationship> relaciones = instanciaTemp.getReferenceNode().getRelationships(Relacion.CLASE);
        if (!relaciones.iterator().hasNext()) {
            throw new RuntimeException("no exiten relaciones en este nodo para el tipoEntidad" + tipoEntidad);
        }
        for (Iterator<Relationship> iterator = relaciones.iterator(); iterator.hasNext();) {
            Relationship relacion = iterator.next();
            Node[] nodos = relacion.getNodes();

            for (int i = 0; i < nodos.length; i++) {
                if (nodos[i].hasProperty("entidad")) {
                    if (nodos[i].getProperty("entidad").equals(tipoEntidad)) {
                        return nodos[i];
                    }
                }
            }
        }
        throw new RuntimeException("no exiten nodo con ese tipoEntidad");
    }

    public synchronized static Integer getNewIdEntidad(AbstractGraphDatabase instanciaTemp, String tipoEntidad) {
        Node nodoEntidades = getNodoEntidad(instanciaTemp, tipoEntidad);
        Integer idEntidad = (Integer) nodoEntidades.getProperty("contadorId");
        nodoEntidades.setProperty("contadorId", (idEntidad + 1));
        return idEntidad;
    }

    public static Node getInstanciaFromEntidad(AbstractGraphDatabase instanciaTemp, String tipoEntidad,
            Integer idInstancia) {
        Node nodoEntidad = getNodoEntidad(instanciaTemp, tipoEntidad);
        Iterable<Relationship> relaciones = nodoEntidad.getRelationships(Relacion.ENTIDAD);
        if (!relaciones.iterator().hasNext()) {
            throw new RuntimeException("no exiten relaciones de tipo Entidad para este nodo");
        }
        for (Iterator<Relationship> iterator = relaciones.iterator(); iterator.hasNext();) {
            Relationship relacion = iterator.next();
            Node[] nodos = relacion.getNodes();
            for (int i = 0; i < nodos.length; i++) {
                if (nodos[i].hasProperty("id")) {
                    if (nodos[i].getProperty("id").equals(idInstancia)) {
                        return nodos[i];
                    }
                }
            }
        }
        throw new RuntimeException("no exiten nodo con ese tipoEntidad");

    }

    public static boolean existeRelacion(Node paternNode, Node childNode, Relacion relacionTipo) {
        Iterable<Relationship> relaciones = paternNode.getRelationships(relacionTipo);
        if (!relaciones.iterator().hasNext()) {
            return false;
        }
        for (Iterator<Relationship> iterator = relaciones.iterator(); iterator.hasNext();) {
            Relationship relacion = iterator.next();
            Node[] nodos = relacion.getNodes();
            for (int i = 0; i < nodos.length; i++) {
                if (nodos[i].equals(childNode)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void guardar(Persistible objeto) {
        identityMap.put(buildKey(objeto), objeto);
    }

    private static String buildKey(Persistible objeto) {
        return objeto.getClass().getCanonicalName() + "#" + objeto.getId();
    }

    private static String buildKey(Class<?> cl, Integer id) {
        return cl.getCanonicalName() + "#" + id;
    }

    @SuppressWarnings("unchecked")
    public static <T> T detectar(Class<T> cl, Integer idNodo) {
        return (T) identityMap.get(buildKey(cl, idNodo));
    }

    public static boolean existe(Class<?> cl, Integer idNodo) {
        return identityMap.containsKey(buildKey(cl, idNodo));
    }

    public static void crearRelacionEntre(Node nodeSelf, Node newNode, Relacion relacion) {
        if (!existeRelacion(nodeSelf, newNode, relacion)) {
            nodeSelf.createRelationshipTo(newNode, relacion);
        }
    }

}
