package com.siriusif.ui.helper;


import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Configuration.browser;
import static com.codeborne.selenide.Configuration.timeout;
import static com.codeborne.selenide.Configuration.holdBrowserOpen;
import static com.siriusif.ui.helper.SelenideJSFErrorChecker.errorReportsFolder;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.BeforeClass;


public class UITestBase {

	public static Logger LOGGER = Logger.getLogger(UITestBase.class);

	@Before
	public void globalSetUp(){
	    timeout = 10000;
	    baseUrl = "http://localhost:8080/webcash";
	    browser = "htmlunit";
	    holdBrowserOpen = false;
	    errorReportsFolder = "d:/temp";
	}
}
