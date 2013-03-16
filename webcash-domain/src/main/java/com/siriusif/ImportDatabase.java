package com.siriusif;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.siriusif.helper.Helper;
import com.siriusif.model.Group;
import com.siriusif.model.Hall;
import com.siriusif.model.Order;

/**
 * This console app allow us to create database with initial data
 * for demonstration and testing
 *
 */
public class ImportDatabase {
	
	private static Logger LOGGER = Logger.getLogger(ImportDatabase.class);
	
	public static void main(String[] args) throws JsonSyntaxException, JsonIOException, IOException {
		LOGGER.info("Import started.");
		Hall hall = Helper.fromJsonHall("/demo_hall.json");
		Group group = Helper.fromJsonGroup("/demo_grouplist.json");
//		Order order = Helper.fromJson("/order.json", Order.class);
		
		String profile=null;
		//define profile as invocation parameter
		if (args.length>0) {
			profile = args[0];
			LOGGER.info("Using profile :"+profile);
		}
		GenericXmlApplicationContext context = initAppContext(profile);
		Session session = initHibernateSession(context);
		session.save(hall);
		session.save(group);
//		session.delete(order);
//		session.save(order);
		session.getTransaction().commit();
		session.close();
		LOGGER.info("Import compleate.");
	}

	/**
	 * Instantiates hibernate session from preloaded Spring context.
	 * @param context - preloaded spring context
	 * @return ready to use Hibernate session object
	 */
	private static Session initHibernateSession(
			GenericXmlApplicationContext context) {
		SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		return session;
	}

	/**
	 * Loads Spring context from <code>webcash-persistence-beans.xml</code>. 
	 * Sets active profile to <code>profile</code>, if defined. 
	 * @param profile - active profile. possible values "dev", "test", or <code>null</code>
	 * @return loaded Spring context
	 */
	private static GenericXmlApplicationContext initAppContext(String profile) {
		GenericXmlApplicationContext context = new GenericXmlApplicationContext();
		if (profile != null) {
			context.getEnvironment().setActiveProfiles(profile);
		}
		context.load("webcash-persistence-beans.xml");
		context.refresh();
		return context;
	}

}
