package com.siriusif.ui;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Configuration.browser;
import static com.codeborne.selenide.Configuration.timeout;
import static com.codeborne.selenide.Selectors.byText;
import static com.siriusif.ui.helper.SelenideJSFErrorChecker.assertNoErrors;
import static com.siriusif.ui.helper.SelenideJSFErrorChecker.errorReportsFolder;

import java.io.IOException;
import java.net.MalformedURLException;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import com.codeborne.selenide.Condition;
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
		$("#hall_use").shouldBe(Condition.hidden);
		$(byText("Стіл 1")).click();
		$("#order_list").shouldBe(Condition.hidden);
		assertNoErrors();
	}

}
