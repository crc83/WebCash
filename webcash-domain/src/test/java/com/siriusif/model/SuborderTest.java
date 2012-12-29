package com.siriusif.model;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

public class SuborderTest {

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
		
		assertEquals(1, suborder.getSales().size());	
	}


}
