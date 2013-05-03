package com.siriusif.ui;

import java.io.PrintWriter;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.gargoylesoftware.htmlunit.WebClient;
import com.google.common.base.Function;

public class AbstractWebDriverTest {
	private static final String BROWSER_TYPE = "test.browser.type";
	private static final String DAMP_FOLDER = "test.damp.folder";

	public static Logger LOGGER = Logger.getLogger(AbstractWebDriverTest.class);
	public static WebDriver browser;
	public static WebClient webClient;

	@Before
	public void setUpBrowser() {
		String browserType = "";// System.getenv(BROWSER_TYPE);
		if ("firefox".equals(browserType)) {
			initFirefox();
		} else if ("iexplorer".equals(browserType)) {
			initIE();
		} else if ("chrome".equals(browserType)) {
			initChrome();
		} else {
			initHtmlUnit();
		}
	}

	private void initHtmlUnit() {
		LOGGER.info("Starting HTMLUnit");
		browser = new HtmlUnitDriver(true);
	}

	// this still doesnt work
	private void initFirefox() {
		LOGGER.info("Starting Firefox");
		FirefoxProfile fp = new FirefoxProfile();
		// fp.setPreference("webdriver.load.strategy", "unstable"); // As of
		// 2.19. from 2.9 - 2.18 use 'fast'
		browser = new FirefoxDriver(fp);
	}

	private void initIE() {
		LOGGER.info("Starting Internet Explorer");
		browser = new InternetExplorerDriver();
	}

	private void initChrome() {
		LOGGER.info("Starting Chrome");
		browser = new ChromeDriver();
	}

	@After
	public void closeBrowser() {
		browser.quit();
	}

	/**
	 * Open related to the root applications link
	 * 
	 * @param relatedAddress
	 *            related address (i.e. /pages/menu.jsf opens
	 *            http://localhost:8080/webcash/pages/menu.jsf)
	 */
	public void get(String relatedAddress) {
		browser.get("http://localhost:8080/webcash" + relatedAddress);
	}

	/**
	 * Checks if element present
	 * 
	 * @param by
	 *            selector of element
	 * @return true if element present
	 */
	public boolean isElementPresent(By by) {
		boolean result = false;
		try {
			result = (browser.findElement(by) != null);
		} catch (Exception e) {
			// ok there is no such elements
		}
		return result;
	}

	public boolean isNoFatalErrors() {
		browser.getPageSource();
		boolean errorsPresent = isElementPresent(By.id("trace"));
		if (errorsPresent) {
			LOGGER.info("Error page detected at:" + getCaller(2));
			debugPageSource();
		}
		return !errorsPresent;
	}

	public void debugPageSource() {
		String html = browser.getPageSource();
		LOGGER.debug(html);
		saveToFile(html);
	}

	private void saveToFile(String html) {
		String dampFolder = System.getenv(DAMP_FOLDER);
		if (dampFolder == null || "".equals(dampFolder)) {
			return;
		}
		StackTraceElement caller = getCaller(3);
		try {
			String filename = caller.getClassName() + "#"
					+ caller.getMethodName() + ".html";
			LOGGER.info("Saving error page to file:" + filename);
			PrintWriter pw = new PrintWriter(dampFolder + filename);
			pw.write(html);
			pw.close();
			LOGGER.info("Page saved to file");
		} catch (Exception e) {
			LOGGER.info("Error saving file");
		}
	}

	/**
	 * Return the caller class and method.
	 * 
	 * @param depth
	 *            of caller
	 * @return caller
	 */
	public static StackTraceElement getCaller(int depth) {
		StackTraceElement[] stackTraceElements = Thread.currentThread()
				.getStackTrace();
		int correctedDepth = Math.min(stackTraceElements.length, depth + 1);
		// +1 to include this method in depth
		return stackTraceElements[correctedDepth];
	}

	public void doLoginAsAdmin() {
		browser.findElement(By.id("loginForm:username")).clear();
		browser.findElement(By.id("loginForm:username")).sendKeys("admin");
		browser.findElement(By.id("loginForm:password")).clear();
		browser.findElement(By.id("loginForm:password")).sendKeys("admin");
		browser.findElement(By.id("loginForm:loginButton")).click();
	}
}
