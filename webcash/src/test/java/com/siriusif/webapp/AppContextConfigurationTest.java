package com.siriusif.webapp;

import static org.junit.Assert.*;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;

import com.siriusif.security.AuthenticationService;

@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:webcash-application-context.xml"} )
public class AppContextConfigurationTest extends AbstractJUnit4SpringContextTests {
	
//	@Autowired
//	private SessionFactory sessionFactory;

	@Autowired
	private AuthenticationService authenticationService;
	
	@Test
	public void testAuthentificationConfiguration() {
		assertNotNull (authenticationService); 
	}

}