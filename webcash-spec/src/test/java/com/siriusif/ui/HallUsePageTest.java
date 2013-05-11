package com.siriusif.ui;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Configuration.browser;
import static com.codeborne.selenide.Configuration.timeout;
import static com.codeborne.selenide.Selectors.byText;
import static com.siriusif.ui.helper.SelenideJSFErrorChecker.assertNoErrorss;
import static com.siriusif.ui.helper.SelenideJSFErrorChecker.errorReportsFolder;

import java.io.IOException;
import java.net.MalformedURLException;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import com.codeborne.selenide.Condition;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;

/**
 * Tests for "pages/hall_use.jsf" page
 * 
 * @author sbelei
 */
public class HallUsePageTest {
	
	public static Logger LOGGER = Logger.getLogger(HallUsePageTest.class);
	
	@Before
	public void setUp() {
	    timeout = 10000;
	    baseUrl = "http://localhost:8080/webcash";
	    browser = "htmlunit";
	    errorReportsFolder = "d:/temp";
	    open("/");
	    login();
	}

	private void login() {
		$(By.id("loginForm:username")).clear();
		$(By.id("loginForm:username")).sendKeys("admin");
		$(By.id("loginForm:password")).clear();
		$(By.id("loginForm:password")).sendKeys("admin");
		$(By.id("loginForm:loginButton")).click();
	}

	/**
	 * given : I'm on a root path 
	 * when : I provide "admin" credentials than : I
	 * will see page with halls and tables
	 */
//	@Test
	public void testOpenHallUse() {
//		get("");
//		assertTrue(isNoFatalErrors());
//		doLoginAsAdmin();
//		isElementPresent(By.id("hall_use"));
	}

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
		$("#hall_use").shouldBe(Condition.hidden);
		$(byText("Стіл 1")).click();
		$("#order_list").shouldBe(Condition.hidden);
		assertNoErrorss();
	}

}
