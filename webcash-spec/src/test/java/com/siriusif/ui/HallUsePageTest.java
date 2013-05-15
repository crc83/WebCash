package com.siriusif.ui;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selectors.withText;
import static com.siriusif.ui.helper.SelenideJSFErrorChecker.assertNoErrors;

import java.io.IOException;
import java.net.MalformedURLException;

import org.junit.Test;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.siriusif.ui.helper.Login;
import com.siriusif.ui.helper.UITestBase;

/**
 * Tests for "pages/hall_use.jsf" page
 * 
 * @author sbelei
 */
public class HallUsePageTest extends UITestBase{
	
	/**
	 * given : I'm on a halls page when : I click on table with opened orders
	 * than : I'll be redirected to page with list of orders
	 * 
	 * @throws IOException
	 * @throws MalformedURLException
	 * @throws FailingHttpStatusCodeException
	 */
	@Test
	public void testSelectTableFromFirstHall()
			throws FailingHttpStatusCodeException, MalformedURLException,
			IOException {
	    open("/");
	    Login.asAdmin();
		$("#hall_use").exists();;
		assertNoErrors();
		$(withText("Стіл 1")).click();
		$("#order").should(exist);
	}

}
