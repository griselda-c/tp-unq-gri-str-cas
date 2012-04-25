package ar.edu.unq.persistencia;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionManager {

    private static SessionFactory sessionFactory;

    private static synchronized SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            Configuration cfg = new Configuration();
            cfg.configure();

            sessionFactory = cfg.buildSessionFactory();
        }

        return sessionFactory;
    }

    private static ThreadLocal<Session> sessionHolder = new ThreadLocal<Session>();

    public static Session getSession() {
        if (sessionHolder.get() == null) {
            Session s = getSessionFactory().openSession();
            sessionHolder.set(s);
            return s;
        }
        return sessionHolder.get();
    }

    public static void closeSession() {
        if (sessionHolder.get() != null) {
            sessionHolder.get().close();
            sessionHolder.set(null);
        }
    }

}
