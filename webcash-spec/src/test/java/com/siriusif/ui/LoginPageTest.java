package com.siriusif.ui;

import static org.junit.Assert.*;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.internal.seleniumemulation.WaitForPageToLoad;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

// SB : I need this in order to change web driver
@SuppressWarnings("unused")
public class LoginPageTest extends AbstractWebDriverTest{

	
	@Test
	public void testLoginPageOnRootPath() {
		get(""); //open root
		assertIfLoginPage();
	}
	
	@Test
	public void testSuccessfulLogin() {
		get("");
		String loginURL = browser.getCurrentUrl();
		browser.findElement(By.id("loginForm:username")).clear();
		browser.findElement(By.id("loginForm:username")).sendKeys("admin");
		browser.findElement(By.id("loginForm:password")).clear();
		browser.findElement(By.id("loginForm:password")).sendKeys("admin");
		
		assertFalse(loginURL.equals(browser.getCurrentUrl()));
	}

	private void assertIfLoginPage() {
		assertNotNull(browser.findElement(By.id("loginForm:username")));
		assertNotNull(browser.findElement(By.id("loginForm:password")));
	}
	
	// SB : Temporary solution : Uncomment and fix this test
 	@Test
	public void testAllOtherPagesAccessibleThroughLogin() {
		String[] pages = { 
			    "/pages/hall.jsf",
			    "/pages/menu.jsf"
			};
		for (String page : pages) {
			get(page);
			assertIfLoginPage();
		}
	}
}
