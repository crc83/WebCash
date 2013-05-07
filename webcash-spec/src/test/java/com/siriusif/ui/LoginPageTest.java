package com.siriusif.ui;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;

/**
 * Integration tests for login page
 * 
 * @author sbelei
 */
public class LoginPageTest extends AbstractWebDriverTest {
	
	/**
	 * Check if it's login page. (login and password inputs should present)
	 */
	private void assertIfLoginPage() {
		assertTrue(isNoFatalErrors());
		assertNotNull(browser.findElement(By.id("loginForm:username")));
		assertNotNull(browser.findElement(By.id("loginForm:password")));
	}
	
	
	/**
	 * when : I open root path 
	 * than : I will see login page
	 */
	@Test
	public void testLoginPageOnRootPath() {
		get(""); // open root
		assertTrue(isNoFatalErrors());
		assertIfLoginPage();
	}

	/**
	 * given : I'm on a root path 
	 * when  : I login with correct credentials 
	 * than  : I will not be on a login page
	 */
	@Test
	public void testSuccessfulLogin() {
		get("");
		doLoginAsAdmin();
		assertTrue(isNoFatalErrors());
		isElementPresent(By.id("hall_use"));
	}

	/**
	 * given : I'm on a root path 
	 * when : I try to open any page without credentials 
	 * than : I will see login page anyway
	 */
	@Test
	public void testAllOtherPagesAccessibleThroughLogin() {
		String[] pages = { "/pages/hall.jsf", "/pages/hall_use.jsf", "/pages/order.jsf", "/pages/orders_list.jsf" };
		for (String page : pages) {
			get(page);
			assertTrue(isNoFatalErrors());
			assertIfLoginPage();
		}
	}
}
