package com.siriusif.model;

import static com.siriusif.model.helpers.SaleBuiledr.buildSaleOld;
import static com.siriusif.model.helpers.SaleBuiledr.money;
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

		first.addSale(buildSaleOld("Юшка грибна", 0.280, 12.50));
		first.addSale(buildSaleOld("Салат домашній", 0.280, 12.00));
		first.addSale(buildSaleOld("М'ясо по французьки", 0.200, 20.00));
		first.addSale(buildSaleOld("Картопля молода з зеленню", 0.200, 8.00));
		order.addSuborder(first);

		Suborder second = new Suborder(2);
		second.addSale(buildSaleOld("Смалець", 0.100, 8.00));
		second.addSale(buildSaleOld("Сметана", 1, 4.00));
		second.addSale(buildSaleOld("Фреш", 0.200, 16.00));
		order.addSuborder(second);

		Suborder third = new Suborder(3);
		third.addSale(buildSaleOld("Хліб", 10, 0.50));
		third.addSale(buildSaleOld("Кава Еспрессо", 0.040, 9.00));
		third.addSale(buildSaleOld("Штрудель", 0.150, 14.00));
		order.addSuborder(third);
		order.setPaid(money(28.00));

		assertTrue(order.getTotal().equals(money(27.92)));
		// TODO this test move to testIsValidforClose()
		assertTrue("payed more total", order.isValidForClose());
	}

}
