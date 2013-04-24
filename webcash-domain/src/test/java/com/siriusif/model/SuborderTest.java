package com.siriusif.model;

import static com.siriusif.model.helpers.TestHelper.amount;
import static com.siriusif.model.helpers.TestHelper.buildSale;
import static com.siriusif.model.helpers.TestHelper.money;
import static org.junit.Assert.*;

import org.apache.log4j.Logger;
import org.junit.Test;

public class SuborderTest {
	private Logger LOGGER = Logger.getLogger(SuborderTest.class);

	@Test
	public void testSaleAddToSuborder() {
		Suborder suborder = new Suborder();
		Sale sale = new Sale();
		
		suborder.addSale(sale);
		
		assertEquals(1, suborder.getSales().size());	
	}
	
	@Test
	public void testSaleAddToSuborderWithIndex() {
		Suborder suborder = new Suborder(2);
		Sale sale = new Sale();
		
		suborder.addSale(sale);
		LOGGER.info(sale.getSuborder().getIndex());
		
		assertEquals(1, suborder.getSales().size());	
	}
	
	@Test
	public void testTotal() {
		Suborder first = new Suborder(1);
				
		first.addSale(buildSale("Юшка грибна", amount(0.280), money(12.50)));
		first.addSale(buildSale("Салат домашній", amount(0.280), money(12.00)));
		first.addSale(buildSale("М'ясо по французьки", amount(0.200), money(20.00)));
		first.addSale(buildSale("Картопля молода з зеленню", amount(0.200), money(8.00)));
		LOGGER.info(first.getSales().isEmpty());

		assertEquals(money(12.46), first.getTotal());	
	}


}
