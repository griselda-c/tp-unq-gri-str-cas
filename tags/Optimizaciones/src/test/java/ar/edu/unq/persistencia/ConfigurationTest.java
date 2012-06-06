package ar.edu.unq.persistencia;


import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;

public class ConfigurationTest extends AbstractHibernateTest {
	public void testApp() throws Exception {
		SessionFactory sessionFactory = getSessionFactory();
		Session session = sessionFactory.openSession();

		session.close();
	}
}
