package com.siriusif.model;

import static org.junit.Assert.*;
import org.junit.Test;

public class SuborderTest {

	@Test
	public void testSaleAddToSuborder() {
		Suborder suborder = new Suborder();
		Sale sale = new Sale();
		
		suborder.addSale(sale);
		
		assertEquals(1, suborder.getSales().size());	
	}

}