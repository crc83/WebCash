package com.siriusif.ui;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class AbstractWebDriverTest {
	
	public static WebDriver browser;
	
	@Before
	public void setUpBrowser(){
		browser = new HtmlUnitDriver();
		//initFirefox();
		//initChrome();
		//initIE();
	}

	
	//this still doesnt work
	private void initFirefox() {
		FirefoxProfile fp = new FirefoxProfile();
		fp.setPreference("webdriver.load.strategy", "unstable"); // As of 2.19. from 2.9 - 2.18 use 'fast'
		browser = new FirefoxDriver(fp);
	}

	
	private void initIE() {
		//		System.setProperty("webdriver.ie.driver","/path/to/IEDriverServer.exe");
		browser = new InternetExplorerDriver();
	}

	private void initChrome() {
		//		System.setProperty("webdriver.chrome.driver","/path/to/chromedriver.exe");
		browser = new ChromeDriver();
	}
	
	@After
	public void closeBrowser(){
//		browser.quit();
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
