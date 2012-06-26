package griselda.alejandro;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.RelationshipType;
import org.neo4j.graphdb.Transaction;
import org.neo4j.kernel.AbstractGraphDatabase;
import org.neo4j.kernel.EmbeddedGraphDatabase;

public class BaseDeDatos {

    static String JUGADORES = "jugadores";

    static String EQUIPOS = "equipos";

    public static void abrirBase() {
        instancia = new EmbeddedGraphDatabase("base6");

        Transaction tx = instancia.beginTx();
        try {
            if (!instancia.getReferenceNode().hasProperty("iniciado")) {
                System.out.println("se creo la base");
                instancia.getReferenceNode().setProperty("iniciado", "iniciado");

                Node equiposNode = instancia.createNode();
                equiposNode.setProperty("entidad", EQUIPOS);
                equiposNode.setProperty("contadorId", 0);
                instancia.getReferenceNode().createRelationshipTo(equiposNode, Relacion.CLASE);

                Node jugadoresNode = instancia.createNode();
                jugadoresNode.setProperty("entidad", JUGADORES);
                jugadoresNode.setProperty("contadorId", 0);
                instancia.getReferenceNode().createRelationshipTo(jugadoresNode, Relacion.CLASE);
                tx.success();
            } else {
                // System.out.println("Existe una base en el directorio");
            }
        } finally {
            tx.finish();
        }
    }

    public static enum Relacion implements RelationshipType {
        CLASE, ENTIDAD, EQUIPOJUGADOR
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

    private static Map<String, Persistible> identityMap = new HashMap<String, Persistible>();

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

    public static List<Jugador> getJugadores() {
        List<Jugador> jugadores = new LinkedList<Jugador>();
        abrirBase();
        Transaction tx = instancia.beginTx();
        try {
            Node nodoEntidad = getNodoEntidad(instancia, JUGADORES);
            Iterable<Relationship> relaciones = nodoEntidad.getRelationships(Relacion.ENTIDAD);
            for (Iterator<Relationship> iterator = relaciones.iterator(); iterator.hasNext();) {
                Relationship relacion = iterator.next();
                Node[] nodos = relacion.getNodes();
                for (int i = 0; i < nodos.length; i++) {
                    if (!nodos[i].equals(nodoEntidad)) {
                        Jugador jugadorTemp;
                        Integer idNodo = (Integer) nodos[i].getProperty("id");
                        if (existe(Jugador.class, idNodo)) {
                            jugadorTemp = detectar(Jugador.class, idNodo);
                        } else {
                            jugadorTemp = new Jugador();
                            jugadorTemp.setNombre((String) nodos[i].getProperty("nombre"));
                            jugadorTemp.setId(idNodo);
                            guardar(jugadorTemp);
                        }
                        jugadores.add(jugadorTemp);
                    }
                }
            }
            tx.success();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            tx.finish();
        }
        cerrarBase();
        return jugadores;
    }

    public static List<Equipo> getEquipos() {
        List<Equipo> equipos = new LinkedList<Equipo>();
        abrirBase();
        Transaction tx = instancia.beginTx();
        try {
            Node nodoEntidad = getNodoEntidad(instancia, EQUIPOS);
            Iterable<Relationship> relaciones = nodoEntidad.getRelationships(Relacion.ENTIDAD);
            for (Iterator<Relationship> iterator = relaciones.iterator(); iterator.hasNext();) {
                Relationship relacion = iterator.next();
                Node[] nodos = relacion.getNodes();
                for (int i = 0; i < nodos.length; i++) {
                    if (!nodos[i].equals(nodoEntidad)) {
                        Equipo equipoTemp;
                        Integer idNodo = (Integer) nodos[i].getProperty("id");
                        if (existe(Equipo.class, idNodo)) {
                            equipoTemp = detectar(Equipo.class, idNodo);
                        } else {
                            equipoTemp = new Equipo();
                            equipoTemp.setNombre((String) nodos[i].getProperty("nombre"));
                            equipoTemp.setId(idNodo);
                            guardar(equipoTemp);
                        }
                        equipos.add(equipoTemp);
                        agregarJugadores(equipoTemp, nodos[i]);
                    }
                }
            }
            tx.success();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            tx.finish();
        }
        cerrarBase();
        return equipos;
    }

    public static void agregarJugadores(Equipo equipoTemp, Node equipoNode) {
        Iterable<Relationship> relaciones = equipoNode.getRelationships(Relacion.EQUIPOJUGADOR);
        for (Iterator<Relationship> iterator = relaciones.iterator(); iterator.hasNext();) {
            Relationship relacion = iterator.next();
            Node[] nodos = relacion.getNodes();
            for (int i = 0; i < nodos.length; i++) {
                if (!nodos[i].equals(equipoNode)) {

                    Integer idNodo = (Integer) nodos[i].getProperty("id");
                    if (existe(Equipo.class, idNodo)) {
                        equipoTemp.getJugadores().add(detectar(Jugador.class, idNodo));
                    } else {
                        Jugador jugadorTemp;
                        jugadorTemp = new Jugador();
                        jugadorTemp.setNombre((String) nodos[i].getProperty("nombre"));
                        jugadorTemp.setId(idNodo);
                        guardar(jugadorTemp);
                        equipoTemp.getJugadores().add(jugadorTemp);
                    }
                }
            }
        }
    }
}
