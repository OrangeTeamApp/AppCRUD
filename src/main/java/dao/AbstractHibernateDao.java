package dao;

import exception.HibernateSessionException;
import model.User;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;


abstract class AbstractHibernateDao {

    private static SessionFactory defaultSessionFactory;
    private static String defaultConfigFile = "hibernate.cfg.xml";

    static {
        try {
            Configuration configuration = new Configuration().configure(defaultConfigFile);
            configuration.addAnnotatedClass(User.class);
            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
            defaultSessionFactory = configuration.buildSessionFactory(builder.build());
        } catch (Exception e) {
            throw new HibernateSessionException(e.getMessage());
        }
    }


    public static SessionFactory getDefaultSessionFactory() {
        return defaultSessionFactory;
    }

    public Session createSession() {
        return defaultSessionFactory.openSession();
    }


}