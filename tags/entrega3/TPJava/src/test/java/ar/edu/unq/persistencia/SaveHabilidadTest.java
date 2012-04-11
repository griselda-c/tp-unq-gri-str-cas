package ar.edu.unq.persistencia;

import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;

/**
 * TODO: description
 */
public class SaveHabilidadTest extends AbstractHibernateTest {

    public void testSaveHabilidad() throws Exception {
        SessionFactory sessionFactory = this.getSessionFactory();
        Session session = sessionFactory.openSession();
        Habilidad a = new Habilidad(Posicion.Arquero, 10);
        Habilidad b = new Habilidad(Posicion.Arquero, 8);
        Habilidad c = new Habilidad(Posicion.Lateral, 4);
        Habilidad d = new Habilidad(Posicion.Delantero, 2);
        session.saveOrUpdate(a);
        session.saveOrUpdate(b);
        session.saveOrUpdate(c);
        session.saveOrUpdate(d);
        session.flush();

        /*
         * 
         * Criteria q = session.createCriteria(Habilidad.class); q.add(Expression.eq("valor", "10")); List<Habilidad> l
         * = q.list();
         * 
         * for (Habilidad jd : l) { System.out.println(jd.getPosicion()); }
         */

        session.close();
    }
}
