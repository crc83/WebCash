package com.siriusif.ui;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class AbstractWebDriverTest {
	
	public static WebDriver browser;
	
	@Before
	public void setUpBrowser(){
		browser = new HtmlUnitDriver();
		//WebDriver driver = new ChromeDriver();
		//WebDriver driver = new FirefoxDriver();
	}
	
	@After
	public void closeBrowser(){
		browser.quit();
	}
	
	/**
	 * Open related to the root applications link
	 * @param relatedAddress related address 
	 * (i.e. /pages/menu.jsf opens http://localhost:8080/webcash/pages/menu.jsf)
	 */
	public void get(String relatedAddress) {
		browser.get("http://localhost:8080/webcash"+relatedAddress);
		
	}


}
