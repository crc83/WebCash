package com.siriusif.ui;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.openqa.selenium.By;

/**
 * Tests for "pages/hall_use.jsf" page
 * @author sbelei
 */
public class HallUsePageTest extends AbstractWebDriverTest{

	/**
	 * given : I'm on a root path
	 * when  : I provide "admin" credentials
	 * than  : I will see page with halls and tables
	 */
	@Test
	public void testOpenHallUse() {
		get("");
		assertTrue(isNoFatalErrors());
		doLoginAsAdmin();
		System.out.println(browser.getCurrentUrl());
	}



}
