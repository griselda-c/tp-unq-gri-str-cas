package ar.edu.unq.persistencia;

import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;

/**
 * TODO: description
 */
public class SavePartidoTest extends AbstractPartidoHibernate {

    public void testSavePartidoSimple() throws Exception {
        SessionFactory sessionFactory = this.getSessionFactory();
        Session session = sessionFactory.openSession();
        PartidoSimple a = new PartidoSimple(this.equipo1, this.equipo2);
        a.setGolesEquipos(3, 1);
        session.saveOrUpdate(a);
        session.flush();
        session.close();
    }

    public void testSavePartidoCopa() throws Exception {
        SessionFactory sessionFactory = this.getSessionFactory();
        Session session = sessionFactory.openSession();
        PartidoSimple a = new PartidoSimple(equipo1, equipo2);
        a.setGolesEquipos(3, 1);
        PartidoSimple b = new PartidoSimple(equipo1, equipo2);
        b.setGolesEquipos(3, 5);
        PartidoDeCopa c = new PartidoDeCopa(a, b);
        c.setGolesPenalesAcertadosEquipos(2, 7);
        session.saveOrUpdate(c);
        session.flush();
        session.close();
    }

}
