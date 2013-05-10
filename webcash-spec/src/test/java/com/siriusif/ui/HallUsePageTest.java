package com.siriusif.ui;

import static org.junit.Assert.assertTrue;
import static com.codeborne.selenide.Selenide.*;

import java.io.IOException;
import java.net.MalformedURLException;
import org.junit.Test;
import org.openqa.selenium.By;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;

/**
 * Tests for "pages/hall_use.jsf" page
 * 
 * @author sbelei
 */
public class HallUsePageTest extends AbstractWebDriverTest {

	/**
	 * given : I'm on a root path 
	 * when : I provide "admin" credentials than : I
	 * will see page with halls and tables
	 */
//	@Test
	public void testOpenHallUse() {
		get("");
		assertTrue(isNoFatalErrors());
		doLoginAsAdmin();
		isElementPresent(By.id("hall_use"));
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
		get("");
		doLoginAsAdmin();
		isElementPresent(By.id("hall_use"));
		try{
			browser.findElement(By.xpath("//div[contains(text(), 'Стіл 1')]")).click();
		} catch(Exception e){
			//nop
		}
		// http://htmlunit.sourceforge.net/faq.html#AJAXDoesNotWork
		isElementPresent(By.id("order_list"));
		debugPageSource();
		assertTrue(isNoFatalErrors());

	}

}
