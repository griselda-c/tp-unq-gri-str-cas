package ar.edu.unq.persistencia;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Expression;

/**
 * TODO: description
 */
public class SaveJugadorTest extends AbstractPartidoHibernate {

    public void testSaveJugador() throws Exception {
        SessionFactory sessionFactory = this.getSessionFactory();
        Session session = sessionFactory.openSession();
        Jugador a = new Jugador("Diego", "Maradona", 10);
        a.addHabilidad(new Habilidad(Posicion.Central, 10));
        session.saveOrUpdate(a);
        session.flush();
        session.close();
    }

    public void testGetJugador() throws Exception {
        SessionFactory sessionFactory = this.getSessionFactory();
        Session session = sessionFactory.openSession();
        Criteria q = session.createCriteria(Jugador.class);
        q.add(Expression.eq("nombre", "Diego"));
        List<Jugador> l = q.list();
        for (Jugador jd : l) {
            System.out.println(jd.getNombre() + " - " + jd.getApellido());
        }
        session.flush();

        session.close();
    }

    public void testGetJugadores() throws Exception {
        SessionFactory sessionFactory = this.getSessionFactory();
        Session session = sessionFactory.openSession();
        Criteria q = session.createCriteria(Jugador.class);
        q.add(Expression.eq("nombre", "Diego"));
        List<Jugador> l = q.list();
        for (Jugador jd : l) {
            System.out.println(jd.getNombre() + " - " + jd.getApellido());
        }
        session.flush();

        session.close();
    }
}
