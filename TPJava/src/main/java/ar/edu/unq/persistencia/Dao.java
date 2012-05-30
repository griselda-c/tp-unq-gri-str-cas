package ar.edu.unq.persistencia;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class Dao {

    public static List<Jugador> getJugadores() {
        Session session = SessionManager.getSession();
        Criteria q = session.createCriteria(Jugador.class);
        List<Jugador> l = q.list();
        return l;
    }

    public static Jugador getJugadorPorNombre(String nombre) {
        Session session = SessionManager.getSession();
        Criteria q = session.createCriteria(Jugador.class);
        q.add(Restrictions.eq("nombre", nombre));
        List<Jugador> l = q.list();
        return l.get(0);
    }

    public static List<Partido> getPartidosSimples() {
        Session session = SessionManager.getSession();
        Criteria q = session.createCriteria(PartidoSimple.class);
        List<Partido> l = q.list();
        return l;
    }

    public static List<Partido> getPartidosSimplesEmpty() {
        Session session = SessionManager.getSession();
        Criteria q = session.createCriteria(PartidoSimple.class);
        q.setFetchMode("equipo1", FetchMode.SELECT);
        q.setFetchMode("equipo2", FetchMode.SELECT);
        List<Partido> l = q.list();
        return l;
    }

    public static List<Partido> getPartidosCopaDeEquipos() {
        Session session = SessionManager.getSession();
        Criteria q = session.createCriteria(PartidoDeCopa.class);
        List<Partido> l = q.list();
        return l;
    }

    public static List<Equipo> getEquipos() {
        Session session = SessionManager.getSession();
        Criteria q = session.createCriteria(Equipo.class);
        q.setFetchMode("formacion", FetchMode.SELECT);
        List<Equipo> l = q.list();
        return l;
    }

    public static List<Partido> getPartidosSimplesDeEquipos(Equipo eq1, Equipo eq2) {

        Session session = SessionManager.getSession();
        Criteria q = session.createCriteria(Partido.class);
        q.setCacheable(true);
        q.add(Restrictions
                .disjunction()
                .add(Restrictions.conjunction().add(Restrictions.eq("equipo1", eq1))
                        .add(Restrictions.eq("equipo2", eq2)))
                .add(Restrictions.conjunction().add(Restrictions.eq("equipo1", eq2))
                        .add(Restrictions.eq("equipo2", eq1))));

        Criteria criteriaEquipo1 = q.createCriteria("equipo1");
        criteriaEquipo1.setFetchMode("tecnico", FetchMode.SELECT);
        criteriaEquipo1.setFetchMode("formacion", FetchMode.SELECT);

        Criteria criteriaEquipo2 = q.createCriteria("equipo2");
        criteriaEquipo2.setFetchMode("tecnico", FetchMode.SELECT);
        criteriaEquipo2.setFetchMode("formacion", FetchMode.SELECT);

        return q.list();
    }

    public static Equipo getEquipoPorId(int nombre) {
        Session session = SessionManager.getSession();
        Criteria q = session.createCriteria(Equipo.class);
        q.add(Restrictions.eq("id", nombre));

        List<Equipo> l = q.list();
        return l.get(0);
    }

    public static List<Formacion> getFormaciones() {
        Session session = SessionManager.getSession();
        Criteria q = session.createCriteria(Formacion.class);
        List<Formacion> l = q.list();
        return l;
    }

}
