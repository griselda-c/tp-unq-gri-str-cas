package ar.edu.unq.persistencia;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;

public class Dao {

    public static List<Jugador> getJugadores() {
        Session session = SessionManager.getSession();
        Criteria q = session.createCriteria(Jugador.class);
        List<Jugador> l = q.list();
        return l;
    }

    public static List<Equipo> getEquipos() {
        Session session = SessionManager.getSession();
        Criteria q = session.createCriteria(Equipo.class);
        List<Equipo> l = q.list();
        return l;
    }

    public static List<Formacion> getFormaciones() {
        Session session = SessionManager.getSession();
        Criteria q = session.createCriteria(Formacion.class);
        List<Formacion> l = q.list();
        return l;
    }

}
