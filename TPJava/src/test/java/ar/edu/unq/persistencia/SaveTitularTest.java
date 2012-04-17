package ar.edu.unq.persistencia;

import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;

/**
 * TODO: description
 */
public class SaveTitularTest extends AbstractPartidoHibernate {

    public void testSaveTitular() throws Exception {
        SessionFactory sessionFactory = this.getSessionFactory();
        Session session = sessionFactory.openSession();
        Jugador a = new Jugador("Diego", "Maradona", 10);
        Jugador b = new Jugador("Etson", "Do Nacimento", 10);

        session.saveOrUpdate(new Titular(a, Posicion.Arquero));
        session.saveOrUpdate(new Titular(b, Posicion.Arquero));

        session.flush();

        session.flush();
        session.close();
    }

}
