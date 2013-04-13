package com.siriusif;

import java.io.IOException;
import java.util.Properties;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.apache.log4j.Logger;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.siriusif.helper.Helper;
import com.siriusif.model.Group;
import com.siriusif.model.Hall;
import com.siriusif.model.User;

/**
 * This console app allow us to create database with initial data
 * for demonstration and testing
 *
 */
public class ImportDatabase {
	
	private static final Logger LOGGER = Logger.getLogger(ImportDatabase.class);
	
	public static void main(String[] args) throws JsonSyntaxException, JsonIOException, IOException {
		LOGGER.info("Import started.");
//		Order order = Helper.fromJson("/order.json", Order.class);
		
		String profile=null;
		//define profile as invocation parameter
		if (args.length>0) {
			profile = args[0];
			LOGGER.info("Using profile :"+profile);
		}
		
		GenericXmlApplicationContext context = initAppContext(profile);
		Session session = initHibernateSession(context);
		importHall(session);
		importGroups(session);
		importUsers(session);
		session.close();
		LOGGER.info("Import compleate.");
	}

	/**
	 * Import groups/subgroups and goods into database from grouplist.json
	 * @param session - ready to use hibernate session
	 */
	private static final void importGroups(Session session) {
		try {
			session.beginTransaction();
			Group[] groups = Helper.fromJsonGroup("/grouplist.json");
			for (Group group : groups){
				session.save(group);
			}
			session.getTransaction().commit();
		} catch(Exception E){
			LOGGER.error("Error during importing groups");
			LOGGER.error("Caused by:",E);
		}
	}

	/**
	 * Import Halls and Tables from "demo_hall.json" into database
	 * @param session - ready to use hibernate session
	 */
	private static final void importHall(Session session) {
		try {
			session.beginTransaction();
			Hall hall = Helper.fromJsonHall("/demo_hall.json");
			session.save(hall);
			session.getTransaction().commit();
		} catch(Exception E){
			LOGGER.error("Error during importing halls");
			LOGGER.error("Caused by:",E);
		}
	}

	private static final void importUsers(Session session) {
		try {
			session.beginTransaction();
			User[] users = Helper.fromJson("/demo_users.json", User[].class);
			for (User user : users){
				session.save(user);
			}
			session.getTransaction().commit();
		} catch(Exception E){
			LOGGER.error("Error during importing users");
			LOGGER.error("Caused by:",E);
		}
	}

	/**
	 * Instantiates hibernate session from preloaded Spring context.
	 * @param context - preloaded spring context
	 * @return ready to use Hibernate session object
	 */
	private static final Session initHibernateSession(
			GenericXmlApplicationContext context) {
		Properties hibernateProperties = context.getBean("hibernateProperties",Properties.class);
		hibernateProperties.put("hibernate.hbm2ddl.auto", "create-drop");
				
		SessionFactory sessionFactory = context.getBean("sessionFactory", SessionFactory.class);
		
		Session session = sessionFactory.openSession();
		return session;
	}

	/**
	 * Loads Spring context from <code>webcash-persistence-beans.xml</code>. 
	 * Sets active profile to <code>profile</code>, if defined. 
	 * @param profile - active profile. possible values "dev", "test", or <code>null</code>
	 * @return loaded Spring context
	 */
	private static final GenericXmlApplicationContext initAppContext(String profile) {
		GenericXmlApplicationContext context = new GenericXmlApplicationContext();
		if (profile != null) {
			context.getEnvironment().setActiveProfiles(profile);
		}
		context.load("webcash-persistence-beans.xml");
		context.refresh();
		return context;
	}

}
