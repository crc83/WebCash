package com.siriusif.ui;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class AbstractWebDriverTest {
	
	public static WebDriver browser;
	
	@BeforeClass
	public static void setUpBrowser(){
		browser = new HtmlUnitDriver();
		//WebDriver driver = new ChromeDriver();
		//WebDriver driver = new FirefoxDriver();
	}
	
	@AfterClass
	public static void closeBrowser(){
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
