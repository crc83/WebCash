package com.siriusif.ui;

import static org.junit.Assert.*;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class LoginPageTest {

	@Test
	public void testLoginPageOnRootPath() {
		WebDriver driver = new HtmlUnitDriver();
		//WebDriver driver = new ChromeDriver();
		//WebDriver driver = new FirefoxDriver();
		// Find the text input element by its name
		driver.get("http://localhost:8080/webcash");
		assertNotNull(driver.findElement(By.id("loginForm:username")));
		assertNotNull(driver.findElement(By.id("loginForm:password")));
		//Close the browser
        driver.quit();
	}

}
