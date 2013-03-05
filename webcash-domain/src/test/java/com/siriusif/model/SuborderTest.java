package com.siriusif.model;

import static com.siriusif.model.helpers.SaleBuiledr.buildSale;
import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.math.RoundingMode;

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
	public void testGetCalculateSumNotNull(){
		Sale sale = new Sale();
		
		assertNotNull(sale.getCalculatedSum());
	}
	
	@Test
	public void testTotal() {
		Suborder first = new Suborder(1);
		
		
		first.addSale(buildSale("Юшка грибна", new BigDecimal(0.280).setScale(3, RoundingMode.HALF_UP), new BigDecimal(12.50).setScale(2, RoundingMode.HALF_UP)));
		first.addSale(buildSale("Салат домашній", new BigDecimal(0.280).setScale(3, RoundingMode.HALF_UP), new BigDecimal(12.00).setScale(2, RoundingMode.HALF_UP)));
		first.addSale(buildSale("М'ясо по французьки", new BigDecimal(0.200).setScale(3, RoundingMode.HALF_UP), new BigDecimal(20.00).setScale(2, RoundingMode.HALF_UP)));
		first.addSale(buildSale("Картопля молода з зеленню", new BigDecimal(0.200).setScale(3, RoundingMode.HALF_UP), new BigDecimal(8.00).setScale(2, RoundingMode.HALF_UP)));
		LOGGER.info(first.getSales().isEmpty());

		assertEquals(new BigDecimal(12.46).setScale(2, RoundingMode.HALF_UP), first.getTotal());	
	}


}
