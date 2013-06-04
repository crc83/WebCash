package com.siriusif.ui;

import static com.codeborne.selenide.Condition.notPresent;
import static com.codeborne.selenide.Condition.present;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selectors.byValue;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.siriusif.ui.helper.SelenideJSFErrorChecker.assertNoErrors;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;

import com.siriusif.ui.helper.Login;
import com.siriusif.ui.helper.UITestBase;


public class CreateOrderPageTest extends UITestBase{
	

	/**
	 * Given : Opened hall with tables
	 * When  : I click on table without opened orders
	 * Than  : I see new order window
	 */
	@Test
	public void testOpensNewOrderIfNoOrdersForTable(){
	    open("/");
	    Login.asAdmin();
		$(withText("��� 6")).click();
		assertNoErrors();
		$(By.id("order")).exists();
		$(By.id("hall_use")).should(notPresent);

		$(withText("����")).click();
		$(withText("����")).followLink();
		$(withText("���� �� ����: 12.50")).should(present);
		$(withText("������")).click();
		$(By.id("hall_use")).exists();
	}

}
