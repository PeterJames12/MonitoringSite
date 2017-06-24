package monitoring.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.File;

/**
 * @author Igor Gnes on 4/8/17.
 */
public class HibernateUtil {

    private static final SessionFactory SESSION_FACTORY = buildSessionFactory();

    /**
     * Return SessionFactory configuration.
     */
    private static SessionFactory buildSessionFactory() {
        try {
            return new Configuration().configure(
                    new File("src/main/java/monitoring/res/hibernate.xml")).buildSessionFactory();

        } catch (Throwable e) {
            System.err.println("Initial SessionFactory creation failed." + e);
            throw new ExceptionInInitializerError(e);
        }
    }

    public static SessionFactory getSessionFactory() {
        return SESSION_FACTORY;
    }

    public static void shutdown() {
        getSessionFactory().close();
    }
}
