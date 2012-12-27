package com.siriusif.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class OrderTest {

	@Test
	public void testValidationOrder() {
		Order order = new Order();

		order.setAutor("User");
		order.setTableNum(1);
		order.setWorkShift(1);

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

}
