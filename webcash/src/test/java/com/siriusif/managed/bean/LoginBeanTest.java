package com.siriusif.managed.bean;

import org.junit.Before;
import org.junit.Test;

import com.siriusif.security.AuthenticationService;
import com.siriusif.service.model.UserDao;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Tests for actions of LoginBean
 * @author sbelei
 */
public class LoginBeanTest {
	
	private LoginBean loginBean = new LoginBean();
	private AuthenticationService mockedAS;
	
	
	@Before
	public void setUp() {
		mockedAS = mock(AuthenticationService.class);
		stub(mockedAS.login(anyString(), anyString())).toReturn(false);
		stub(mockedAS.login("admin", "password")).toReturn(true);
		loginBean.setAuthenticationService(mockedAS);
	}
	
	/**
	 * When : I pass correct login and password
	 * Then : I recieve url to login page
	 */
	@Test
	public void testCorrectLogin(){
		loginBean.setUsername("admin");
		loginBean.setPassword("password");
		assertEquals(LoginBean.LOGIN_CORRECT_URL, loginBean.login());
	}
	
	/**
	 * When : I pass correct login and incorrect password
	 * Then : I recieve url to login page
	 */
	@Test
	public void testIncorrectPassword() {
		loginBean.setUsername("admin");
		loginBean.setPassword("pwd");
		assertEquals(LoginBean.LOGIN_WRONG_URL, loginBean.login());		 		
	}

	/**
	 * When : I pass incorrect login and correct password
	 * Then : I recieve url to login page
	 */
	@Test
	public void testIncorrectLogin() {
		loginBean.setUsername("an");
		loginBean.setPassword("password");
		assertEquals(LoginBean.LOGIN_WRONG_URL, loginBean.login());		
	}

	/**
	 * When : I pass empty password
	 * Then : I recieve url to login page
	 */
	@Test
	public void testEmptyPassword() {
		loginBean.setUsername("admin");
		loginBean.setPassword("");
		assertEquals(LoginBean.LOGIN_WRONG_URL, loginBean.login());		 		
	}

	/**
	 * When : I pass empty login
	 * Then : I recieve url to login page
	 */
	@Test
	public void testEmptyLogin() {
		loginBean.setUsername("");
		loginBean.setPassword("password");
		assertEquals(LoginBean.LOGIN_WRONG_URL, loginBean.login());		
	}

	
}
