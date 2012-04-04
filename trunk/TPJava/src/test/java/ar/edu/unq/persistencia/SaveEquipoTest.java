package ar.edu.unq.persistencia;

import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;

/**
 * TODO: description
 */
public class SaveEquipoTest extends AbstractHibernateTest {

    public void testSaveJugador() throws Exception {
        SessionFactory sessionFactory = this.getSessionFactory();
        Session session = sessionFactory.openSession();
        Jugador a = new Jugador("Diego", "Maradona", 10);
        Jugador b = new Jugador("Etson", "Do Nacimento", 10);

        session.saveOrUpdate(a);
        session.flush();

        Equipo e = new Equipo();
        e.agregarJugador(a);
        e.agregarJugador(b);

        session.saveOrUpdate(e);

        session.flush();
        session.close();
    }

}
