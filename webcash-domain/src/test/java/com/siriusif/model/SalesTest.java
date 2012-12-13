package com.siriusif.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class SalesTest {
	
	 /**
	  * Given : New empty sale
	  * When  : I add good to sale
	  * Than  : Good will be add to sale
	  **/
	@Test
	public void testGetGroupGoodsToSales() {
		Sale sale = new Sale();
		
		sale.addGoodSales(sale);
		
		Good sales = sale.getSalesGood();
		assertEquals(sales, sale.getSalesGood());
	}
	
	 /**
	  * Given : New empty good and sale
	  * When  : I set good to sale and set price, set Amount
	  * Than  : Expected result equals actual
	  **/
	@Test
	public void testPriceAmount(){
		Good good = new Good();
		Sale sale = new Sale();
		
		sale.setSalesGood(good);
		good.setPrice(3.44);
		sale.setAmount(5.05);
		
		assertEquals(17.37, sale.endPrice(), 0.01);
	}
	

}
