package com.siriusif.ui;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.*;
import static com.siriusif.ui.helper.SelenideJSFErrorChecker.assertNoErrors;

import org.junit.Test;
import org.openqa.selenium.By;

import com.siriusif.ui.helper.Login;
import com.siriusif.ui.helper.UITestBase;

/**
 * Integration tests for login page
 * 
 * @author sbelei
 */
public class LoginPageTest extends UITestBase {
	

	
	/**
	 * when : I open root path 
	 * than : I will see login page
	 */
	@Test
	public void testLoginPageOnRootPath() {
		open("/");
		Login.assertTrue();
		assertNoErrors();
	}

	/**
	 * given : I'm on a root path 
	 * when  : I login with correct credentials 
	 * than  : I will not be on a login page
	 */
	@Test
	public void testSuccessfulLogin() {
		open("/");
		Login.asAdmin();
		assertNoErrors();
		$(By.id("hall_use")).should(exist);
	}

	/**
	 * given : I'm on a root path 
	 * when : I try to open any page without credentials 
	 * than : I will see login page anyway
	 */
	@Test
	public void testAllOtherPagesAccessibleThroughLogin() {
		String[] pages = { "/pages/hall_use.jsf", "/pages/order.jsf", "/pages/orders_list.jsf" };
		for (String page : pages) {
			open(page);
			LOGGER.info("Opening page:"+page);
			Login.assertTrue();
			assertNoErrors();
		}
	}
}
