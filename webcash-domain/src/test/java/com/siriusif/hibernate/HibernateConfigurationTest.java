package com.siriusif.hibernate;

import static org.junit.Assert.*;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

/*
 * SB :
 * For postgres we have to create database manually
 * here is explanation.
 * http://stackoverflow.com/questions/6245590/how-do-i-get-hibernate-to-execute-create-database-if-necessary-for-postgresql-wh
 */
// TODO SB : Do something wit CREATE DATABASE
@PropertySource("classpath*:test_db.properties")
@ContextConfiguration(locations = "/persistence-beans.xml")
public class HibernateConfigurationTest extends AbstractJUnit4SpringContextTests {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Test
	public void testHibernateConfiguration() {
		// Spring IOC container instantiated and prepared sessionFactory
		assertNotNull (sessionFactory); 
	}

}