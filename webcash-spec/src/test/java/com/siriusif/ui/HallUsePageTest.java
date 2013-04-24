package com.siriusif.ui;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.By.ByXPath;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.Page;

/**
 * Tests for "pages/hall_use.jsf" page
 * 
 * @author sbelei
 */
public class HallUsePageTest extends AbstractWebDriverTest {

	/**
	 * given : I'm on a root path when : I provide "admin" credentials than : I
	 * will see page with halls and tables
	 */
	@Test
	public void testOpenHallUse() {
		get("");
		assertTrue(isNoFatalErrors());
		doLoginAsAdmin();
		System.out.println(browser.getCurrentUrl());
	}

	/**
	 * given : I'm on a halls page when : I click on table with opened orders
	 * than : I'll be redirected to page with list of orders
	 * 
	 * @throws IOException
	 * @throws MalformedURLException
	 * @throws FailingHttpStatusCodeException
	 */
//	@Test
	public void testSelectTableFromFirstHall()
			throws FailingHttpStatusCodeException, MalformedURLException,
			IOException {
		get("");
		doLoginAsAdmin();
		browser.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		browser.findElement(
				ByXPath.xpath("//img[@onclick=\"location.href='orders_list.jsf?table=32768'\"]"))
				.click();
		// http://htmlunit.sourceforge.net/faq.html#AJAXDoesNotWork

		debugPageSource();
		assertTrue(isNoFatalErrors());

	}

}
