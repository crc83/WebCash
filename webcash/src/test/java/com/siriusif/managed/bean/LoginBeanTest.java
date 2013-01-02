package com.siriusif.managed.bean;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Login bean testing
 * Date   : 21/12/2012 
 * Author : Sergiy.Beley@gmail.com
 */
//TODO : Mk mockup for user accounts
public class LoginBeanTest {
	
	LoginBean login;
	
	@Before
	public void setUp(){
		login = new LoginBean();
	}
	

	/**
	 * Given: user with credentials
	 * When : user has correct credentials
	 * Than : we get redirect to 'hall' page
	 */
	@Test
	public void testCorrectLoginResponce() {
		login.setUsername("admin");
		login.setPassword("admin");
		String responce = login.login();
		assertTrue(responce.contains("hall"));
	}

	/**
	 * Given: user with credentials
	 * When : user has wrong credentials
	 * Than : we get redirect back to 'login' page
	 */
	@Test
	public void testWrongLoginResponce() {
		login.setUsername("admin");
		login.setPassword("wrong_password");
		String responce = login.login();
		assertFalse(responce.contains("hall"));
		assertTrue(responce.contains("login"));
	}

}
