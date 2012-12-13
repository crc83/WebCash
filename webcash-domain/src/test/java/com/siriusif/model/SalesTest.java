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
		Sales sale = new Sales();
		
		sale.addGoodSales(sale);
		
		Goods sales = sale.getSalesGood();
		assertEquals(sales, sale.getSalesGood());
	}
	
	 /**
	  * Given : New empty good and sale
	  * When  : I set good to sale and set price, set Amount
	  * Than  : Expected result equals actual
	  **/
	@Test
	public void testPriceAmount(){
		Goods good = new Goods();
		Sales sale = new Sales();
		
		sale.setSalesGood(good);
		good.setPrice(3.44);
		sale.setAmount(5.05);
		
		assertEquals(17.37, sale.endPrice(), 0.01);
	}
	

}
