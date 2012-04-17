package ar.edu.unq.persistencia;

import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;

/**
 * TODO: description
 */
public class SaveFormacionTest extends AbstractPartidoHibernate {

    public void testSaveFormacion() throws Exception {
        SessionFactory sessionFactory = this.getSessionFactory();
        Session session = sessionFactory.openSession();
        Formacion formacionTem = this.equipo1.armarFormacion();

        session.saveOrUpdate(formacionTem);
        session.flush();

        session.close();
    }
}
