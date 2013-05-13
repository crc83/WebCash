package com.siriusif.ui.helper;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.$;
import static com.siriusif.ui.helper.SelenideJSFErrorChecker.assertNoErrors;

import org.openqa.selenium.By;

public class Login {
	
	public static void asAdmin(){
		$(By.id("loginForm:username")).clear();
		$(By.id("loginForm:username")).sendKeys("admin");
		$(By.id("loginForm:password")).clear();
		$(By.id("loginForm:password")).sendKeys("admin");
		$(By.id("loginForm:loginButton")).click();
	}

	/**
	 * Check if it's login page. (login and password inputs should present)
	 */
	public static void assertTrue() {
		$(By.id("login")).exists();
		$(By.id("loginForm:username")).should(exist);
		$(By.id("loginForm:password")).should(exist);
		assertNoErrors();
	}
}
