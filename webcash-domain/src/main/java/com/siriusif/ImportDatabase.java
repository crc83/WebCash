package com.siriusif;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.siriusif.helper.Helper;
import com.siriusif.model.Hall;

/**
 * This console app allow us to create database with initial data
 * for demonstration and testing
 *
 */
public class ImportDatabase {
	
	public static void main(String[] args) throws JsonSyntaxException, JsonIOException, IOException {
		Hall hall = Helper.fromJson("/demo_hall.json", Hall.class);

		ApplicationContext context = new ClassPathXmlApplicationContext("webcash-persistence-beans.xml");
//		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
//		 ctx.getEnvironment().setActiveProfiles("dev");
//		 ctx.load("webcash-persistence-beans.xml");
//		 ctx.refresh();
		SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(hall);
		session.getTransaction().commit();
		session.close();
	}

}
