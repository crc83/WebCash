package com.siriusif.ui;

import static org.junit.Assert.*;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.internal.seleniumemulation.WaitForPageToLoad;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

// SB : I need this in order to change web driver
@SuppressWarnings("unused")
public class HallUsePageTest extends AbstractWebDriverTest{

	
	@Test
	public void testOpenHallUse() {
		get("/login.jsf");
		browser.findElement(By.id("loginForm:username")).clear();
		browser.findElement(By.id("loginForm:username")).sendKeys("admin");
		browser.findElement(By.id("loginForm:password")).clear();
		browser.findElement(By.id("loginForm:password")).sendKeys("admin");
		browser.findElement(By.id("loginForm:loginButton")).click();
		System.out.println(browser.getCurrentUrl());
	}

}
