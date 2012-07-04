package griselda.alejandro;

import giselda.alejandro.persistibles.Equipo;
import giselda.alejandro.persistibles.Habilidad;
import giselda.alejandro.persistibles.Jugador;
import giselda.alejandro.persistibles.PartidoSimple;
import giselda.alejandro.persistibles.Titular;
import griselda.alejandro.BaseDeDatos.Relacion;
import griselda.alejandro.nopersistibles.Posicion;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.Transaction;
import org.neo4j.kernel.AbstractGraphDatabase;

public class DAO {

    public static AbstractGraphDatabase instancia;

    public static void cerrarBase() {
        instancia.shutdown();
        instancia = null;
    }

    public static void abrirBase() {
        BaseDeDatos.abrirBase();
        instancia = BaseDeDatos.instancia;
    }

    public static void guardar(Persistible objeto) {
        BaseDeDatos.guardar(objeto);
    }

    @SuppressWarnings("unchecked")
    public static <T> T detectar(Class<T> cl, Integer idNodo) {
        return BaseDeDatos.detectar(cl, idNodo);
    }

    public static boolean existe(Class<?> cl, Integer idNodo) {
        return BaseDeDatos.existe(cl, idNodo);
    }

    /*** metodos para pedir de la base ***/

    public static List<Equipo> getEquipos() {
        List<Equipo> equipos = null;
        abrirBase();
        Transaction tx = instancia.beginTx();
        try {
            Node nodoEntidad = BaseDeDatos.getNodoEntidad(instancia, BaseDeDatos.EQUIPOS);
            equipos = getEquiposFrom(nodoEntidad, Relacion.ENTIDAD);
            tx.success();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            tx.finish();
        }
        cerrarBase();
        return equipos;
    }

    public static List<Jugador> getJugadores() {
        List<Jugador> jugadores = null;
        abrirBase();
        Transaction tx = instancia.beginTx();
        try {
            Node nodoEntidad = BaseDeDatos.getNodoEntidad(instancia, BaseDeDatos.JUGADORES);
            jugadores = getJugadoresFrom(nodoEntidad, Relacion.ENTIDAD);
            tx.success();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            tx.finish();
        }
        cerrarBase();
        return jugadores;
    }

    public static List<Jugador> getJugadoresFrom(Node nodoRelacion, Relacion relacionEnum) {
        List<Jugador> jugadores = new LinkedList<Jugador>();
        Iterable<Relationship> relaciones = nodoRelacion.getRelationships(relacionEnum);
        for (Iterator<Relationship> iterator = relaciones.iterator(); iterator.hasNext();) {
            Relationship relacion = iterator.next();
            Node[] nodos = relacion.getNodes();
            for (int i = 0; i < nodos.length; i++) {
                if (!nodos[i].equals(nodoRelacion)) {
                    Jugador jugadorTemp;
                    Integer idNodo = (Integer) nodos[i].getProperty("id");
                    if (existe(Jugador.class, idNodo)) {
                        jugadorTemp = detectar(Jugador.class, idNodo);
                    } else {
                        jugadorTemp = new Jugador();
                        jugadorTemp.setNombre((String) nodos[i].getProperty("nombre"));
                        jugadorTemp.setApellido((String) nodos[i].getProperty("apellido"));
                        jugadorTemp.setNroCamiseta((Integer) nodos[i].getProperty("nroCamiseta"));
                        jugadorTemp.setId(idNodo);
                        jugadorTemp.setHabilidades(getHabilidadesfrom(nodos[i], Relacion.JUGADORHABILIDAD));
                        guardar(jugadorTemp);
                    }
                    jugadores.add(jugadorTemp);
                }
            }
        }
        return jugadores;
    }

    public static List<Habilidad> getHabilidadesfrom(Node nodeHabilidad, Relacion relacionEnum) {
        List<Habilidad> habilidades = new LinkedList<Habilidad>();

        Iterable<Relationship> relaciones = nodeHabilidad.getRelationships(relacionEnum);
        for (Iterator<Relationship> iterator = relaciones.iterator(); iterator.hasNext();) {
            Relationship relacion = iterator.next();
            Node[] nodos = relacion.getNodes();
            for (int i = 0; i < nodos.length; i++) {
                if (!nodos[i].equals(nodeHabilidad)) {
                    Habilidad habilidad;
                    Integer idNodo = (Integer) nodos[i].getProperty("id");
                    if (existe(Jugador.class, idNodo)) {
                        habilidad = detectar(Habilidad.class, idNodo);
                    } else {
                        habilidad = new Habilidad();
                        Posicion posicion = Posicion.valueOf((String) nodos[i].getProperty("posicion"));
                        habilidad.setPosicion(posicion);
                        habilidad.setValor((Integer) nodos[i].getProperty("valor"));
                        habilidad.setId(idNodo);
                        guardar(habilidad);
                    }
                    habilidades.add(habilidad);
                }
            }
        }
        return habilidades;
    }

    private static List<Equipo> getEquiposFrom(Node nodoEntidad, Relacion relacionEnum) {
        List<Equipo> equipos = new LinkedList<Equipo>();
        Iterable<Relationship> relaciones = nodoEntidad.getRelationships(relacionEnum);
        for (Iterator<Relationship> iterator = relaciones.iterator(); iterator.hasNext();) {
            Relationship relacion = iterator.next();
            Node[] nodos = relacion.getNodes();
            for (int i = 0; i < nodos.length; i++) {
                if (!nodos[i].equals(nodoEntidad)) {
                    Equipo equipoTemp = getEquipo(nodos[i]);
                    equipos.add(equipoTemp);
                }
            }
        }
        return equipos;
    }

    private static Equipo getEquipo(Node nodo) {
        Equipo equipoTemp;
        Integer idNodo = (Integer) nodo.getProperty("id");
        if (existe(Equipo.class, idNodo)) {
            equipoTemp = detectar(Equipo.class, idNodo);
        } else {
            equipoTemp = new Equipo();
            equipoTemp.setId(idNodo);
            equipoTemp.setJugadores(getJugadoresFrom(nodo, Relacion.EQUIPOJUGADOR));
            equipoTemp.setNombre((String) nodo.getProperty("nombre"));
            // TODO
            // equipoTemp.setFormacion(formacion);
            // equipoTemp.setPosiciones(posiciones);
            // equipoTemp.setTecnico(tecnico);
            guardar(equipoTemp);
        }
        return equipoTemp;
    }

    public static List<PartidoSimple> getPartidosSimples() {
        List<PartidoSimple> partidosSimples = new LinkedList<PartidoSimple>();
        abrirBase();
        Transaction tx = instancia.beginTx();
        try {
            Node nodoEntidad = BaseDeDatos.getNodoEntidad(instancia, BaseDeDatos.PARTIDOSSIMPLES);
            Iterable<Relationship> relaciones = nodoEntidad.getRelationships(Relacion.ENTIDAD);
            for (Iterator<Relationship> iterator = relaciones.iterator(); iterator.hasNext();) {
                Relationship relacion = iterator.next();
                Node[] nodos = relacion.getNodes();
                for (int i = 0; i < nodos.length; i++) {
                    if (!nodos[i].equals(nodoEntidad)) {
                        PartidoSimple partidoTemp;
                        Integer idNodo = (Integer) nodos[i].getProperty("id");
                        if (existe(Equipo.class, idNodo)) {
                            partidoTemp = detectar(PartidoSimple.class, idNodo);
                        } else {
                            partidoTemp = new PartidoSimple();
                            partidoTemp.setId(idNodo);
                            guardar(partidoTemp);
                        }
                        partidosSimples.add(partidoTemp);

                        partidoTemp.setEquipo1(obtenerEquipo(partidoTemp, nodos[i], Relacion.PARTIDOEQUIPO1));
                        partidoTemp.setEquipo2(obtenerEquipo(partidoTemp, nodos[i], Relacion.PARTIDOEQUIPO2));
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
        return partidosSimples;
    }

    public static Equipo obtenerEquipo(PartidoSimple partidoTemp, Node partidoNode, Relacion partidoequipo) {
        Iterable<Relationship> relaciones = partidoNode.getRelationships(partidoequipo);
        for (Iterator<Relationship> iterator = relaciones.iterator(); iterator.hasNext();) {
            Relationship relacion = iterator.next();
            Node[] nodos = relacion.getNodes();
            for (int i = 0; i < nodos.length; i++) {
                if (!nodos[i].equals(partidoNode)) {

                    Integer idNodo = (Integer) nodos[i].getProperty("id");
                    if (existe(Equipo.class, idNodo)) {
                        return detectar(Equipo.class, idNodo);
                    } else {
                        Equipo equipoTemp;
                        equipoTemp = new Equipo();
                        equipoTemp.setNombre((String) nodos[i].getProperty("nombre"));
                        equipoTemp.setId(idNodo);
                        guardar(equipoTemp);
                        return equipoTemp;
                    }
                }
            }
        }
        return null;
    }

    public static List<Posicion> getPosicionesFrom(String[] posiciones) {
        List<Posicion> posicionesList = new LinkedList<Posicion>();
        for (int i = 0; i < posiciones.length; i++) {
            posicionesList.add(Posicion.valueOf(posiciones[i]));
        }
        return posicionesList;
    }

    public static List<Titular> getTitularesFrom(Node nodo, Relacion formaciontitular) {
        // TODO
        throw new UnsupportedOperationException();
    }

}
