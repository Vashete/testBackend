package com.backend.test.util;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import com.backend.test.entities.Account;
import com.backend.test.entities.Transaction;

import lombok.extern.slf4j.Slf4j;


/**
 * Database configuration for H2 in memory database
 * @author Vashete
 * @version 20190515
 */
@Slf4j
public class HibernateUtil {
	private static SessionFactory sessionFactory;
	/**
	 * Returns the session factory object to do operations into the database
	 * @return
	 */
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();
                // Hibernate settings equivalent to hibernate.cfg.xml's properties
                Properties settings = new Properties();
                settings.put(Environment.DRIVER, "org.h2.Driver");
                settings.put(Environment.URL, "jdbc:h2:mem:testdb;INIT=runscript from 'classpath:init.sql';");
                settings.put(Environment.USER, "sa");
                settings.put(Environment.PASS, "");
                settings.put(Environment.DIALECT, "org.hibernate.dialect.H2Dialect");
                settings.put(Environment.SHOW_SQL, "true");
                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                settings.put(Environment.HBM2DDL_AUTO, "none");
                configuration.setProperties(settings);
                configuration.addAnnotatedClass(Account.class);
                configuration.addAnnotatedClass(Transaction.class);
                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                log.error("Error configuring datasource. Cause: {} Message: {}",e.getClass().getSimpleName(),e.getMessage());
            }
        }
        return sessionFactory;
    }
}

