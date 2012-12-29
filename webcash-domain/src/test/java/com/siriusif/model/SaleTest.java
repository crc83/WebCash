package com.siriusif.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class SaleTest {
	
	 /**
	  * Given : New empty good and sale
	  * When  : I set good to sale and set price, set Amount
	  * Than  : Expected result equals actual
	  **/
	@Test
	public void testPriceAmount(){
		Good good = new Good("Some name", 42);
		Sale sale = new Sale();
		
		sale.setSalesGood(good);
		good.setPrice(3.44);
		sale.setAmount(5.05);
		
		assertEquals(17.37, sale.getCalculatedSum(), 0.01);
	}
	

}
