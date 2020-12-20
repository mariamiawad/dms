package com.sdm.employee.configurator;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.sdm.employee.entity.Employee;

public class HibernateConnector {
	private static HibernateConnector me;
	private Configuration cfg;
	private SessionFactory sessionFactory;
	private HibernateConnector() throws HibernateException {
		// build the config
		cfg = new Configuration(); /** * Connection Information.. */
		cfg.setProperty("hibernate.connection.driver_class", "org.h2.Driver");
		cfg.setProperty("hibernate.connection.url", "jdbc:h2:tcp://localhost:9092/~/test");
		cfg.setProperty("hibernate.connection.username", "sa");
		cfg.setProperty("hibernate.connection.password", "");
		cfg.setProperty("hibernate.show_sql", "true");
		cfg.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
		/** * Mapping Resources.. */
		cfg.addAnnotatedClass(Employee.class);
		cfg.setProperty("hibernate.hbm2ddl.auto", "update");
		sessionFactory = cfg.buildSessionFactory();
	}

	public static synchronized HibernateConnector getInstance() throws HibernateException {
		if (me == null) {
			me = new HibernateConnector();
		}
		return me;
	}

	public Session getSession() throws HibernateException {
		Session session = sessionFactory.openSession();
		if (!session.isConnected()) {
			this.reconnect();
		}
		return session;
	}

	private void reconnect() throws HibernateException {
		this.sessionFactory = cfg.buildSessionFactory();
	}

	public boolean stopSession() {

		sessionFactory.close();
		return true;
	}
}
