package com.ellaapp.webapplication.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * @author Sam Jerusalem Utility class for generating hibernate sessions
 */
public class HibernateFactory {

    /**
     * Hibernate session factory
     */
    private static SessionFactory sessionFactory = null;

    /**
     * Hibernate configuration file path
     */
    public static final String HIBERNATE_CFG
            = "hibernate.cfg.xml";

    /**
     * In class hibernate session creation
     * @return session factory
     */
    private static SessionFactory createHibernateSession() {
        Configuration configuration = new Configuration();
        configuration.configure(HIBERNATE_CFG);

        ServiceRegistry registry = new StandardServiceRegistryBuilder().
                applySettings(configuration.getProperties()).build();

        SessionFactory sessionFactory = configuration.
                buildSessionFactory(registry);
        return sessionFactory;

    }

    /**
     * Public accessor to singleton sesson factory
     * @return Hibernate Session Factory
     */
    public static synchronized SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            sessionFactory = createHibernateSession();
        }
        return sessionFactory;
    }

}
