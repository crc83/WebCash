package com.siriusif.model;

import static com.siriusif.model.helpers.SaleBuiledr.buildSale;
import static org.junit.Assert.*;

import org.junit.Test;

public class OrderTest {

	@Test
	public void testValidationOrder() {
		Order order = new Order();

		order.setAutor("User");
		order.setTableNum(1);
		order.setWorkShift(1L);

		assertTrue(order.isValid());
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
		
		first.addSale(buildSale("Юшка грибна", 0.280, 12.50));
		first.addSale(buildSale("Салат домашній", 0.280, 12.00));
		first.addSale(buildSale("М'ясо по французьки", 0.200, 20.00));
		first.addSale(buildSale("Картопля молода з зеленню", 0.200, 8.00));
		order.addSuborder(first);
		
		Suborder second = new Suborder(2);
		second.addSale(buildSale("Смалець", 0.100, 8.00));
		second.addSale(buildSale("Сметана", 1, 4.00));
		second.addSale(buildSale("Фреш", 0.200, 16.00));
		order.addSuborder(second);

		Suborder third = new Suborder(3);
		third.addSale(buildSale("Хліб", 10, 0.50));
		third.addSale(buildSale("Кава Еспрессо", 0.040, 9.00));
		third.addSale(buildSale("Штрудель", 0.150, 14.00));
		order.addSuborder(third);
		
		assertEquals(27.92, order.getTotal(), 0.01);	
	}


}
