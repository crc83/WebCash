package com.siriusif.model;

import static com.siriusif.model.helpers.TestHelper.buildSale;
import static com.siriusif.model.helpers.TestHelper.money;
import static com.siriusif.model.helpers.TestHelper.amount;
import static org.junit.Assert.*;

import org.junit.Test;

public class OrderTest {

	@Test
	public void testValidationOrder() {
		Order order = new Order();
		DinnerTable table = new DinnerTable();
		Workshift workshift = new Workshift();

		order.setAuthor("User");
		order.setWorkShift(workshift);
		order.setTable(table);

		assertTrue(order.isValid());
	}

	@Test
	public void testIsValidforClose() {
//		when will be load from JSON change this test
		Order order = new Order();

		order.setPaid(money(10.00));

		assertTrue(order.isValidForClose());
	}

	@Test
	public void testInvalidationOrder() {
		Order order = new Order();

		assertFalse(order.isValid());
	}

	@Test
	public void testSuborderAddToOrder() {
		Order order = new Order();
		Suborder suborder = new Suborder();

		order.addSuborder(suborder);

		assertEquals(1, order.getSuborders().size());
	}

	@Test
	public void testTotal() {
		Order order = new Order();

		Suborder first = new Suborder(1);

		first.addSale(buildSale("Юшка грибна", amount(0.280), money(12.50)));
		first.addSale(buildSale("Салат домашній", amount(0.280), money(12.00)));
		first.addSale(buildSale("М'ясо по французьки", amount(0.200), money(20.00)));
		first.addSale(buildSale("Картопля молода з зеленню", amount(0.200), money(8.00)));
		order.addSuborder(first);

		Suborder second = new Suborder(2);
		second.addSale(buildSale("Смалець", amount(0.100), money(8.00)));
		second.addSale(buildSale("Сметана", amount(1), money(4.00)));
		second.addSale(buildSale("Фреш", amount(0.200), money(16.00)));
		order.addSuborder(second);

		Suborder third = new Suborder(3);
		third.addSale(buildSale("Хліб", amount(10), money(0.50)));
		third.addSale(buildSale("Кава Еспрессо", amount(0.040), money(9.00)));
		third.addSale(buildSale("Штрудель", amount(0.150), money(14.00)));
		order.addSuborder(third);
		order.setPaid(money(28.00));

		assertTrue(order.getTotal().equals(money(27.92)));
		// TODO this test move to testIsValidforClose()
		assertTrue("payed more total", order.isValidForClose());
	}

}
