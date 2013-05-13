package com.siriusif.ui.helper;

import static com.codeborne.selenide.Selenide.$;

import org.openqa.selenium.By;

public class Login {
	
	public static void asAdmin(){
		$(By.id("loginForm:username")).clear();
		$(By.id("loginForm:username")).sendKeys("admin");
		$(By.id("loginForm:password")).clear();
		$(By.id("loginForm:password")).sendKeys("admin");
		$(By.id("loginForm:loginButton")).click();
	}

}
