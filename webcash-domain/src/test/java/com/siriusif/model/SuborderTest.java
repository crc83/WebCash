package com.siriusif.model;

import static com.siriusif.model.helpers.SaleBuiledr.buildSaleOld;
import static com.siriusif.model.helpers.SaleBuiledr.money;
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
	
//	@Test
//	public void testGetCalculateSumNotNull(){
//		Sale sale = new Sale();
//		
//		assertNotNull(sale.getCalculatedSum());
//	}
	
	@Test
	public void testTotal() {
		Suborder first = new Suborder(1);
		
		
		first.addSale(buildSaleOld("Юшка грибна", 0.280, 12.50));
		first.addSale(buildSaleOld("Салат домашній", 0.280, 12.00));
		first.addSale(buildSaleOld("М'ясо по французьки", 0.200, 20.00));
		first.addSale(buildSaleOld("Картопля молода з зеленню", 0.200, 8.00));
		LOGGER.info(first.getSales().isEmpty());

		assertEquals(money(12.46), first.getTotal());	
	}


}
