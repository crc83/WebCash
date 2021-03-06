package com.siriusif.model;

import static org.junit.Assert.*;
import static com.siriusif.model.helpers.SaleBuiledr.money;
import static com.siriusif.model.helpers.SaleBuiledr.amount;

import java.math.BigDecimal;

import org.apache.log4j.Logger;
import org.junit.Test;

public class SaleTest {
	private Logger LOGGER = Logger.getLogger(SaleTest.class);
	
	 /**
	  * Given : New empty good and sale
	  * When  : I set good to sale and set price, set Amount
	  * Than  : Expected result equals actual
	  **/
	@Test
	public void testPriceAmount(){
		Good good = new Good("Some name", new BigDecimal(42));
		Sale sale = new Sale();
		
		sale.setSalesGood(good);
		good.setPrice(money(35.44));
		sale.setAmount(amount(0.505));
		LOGGER.info(sale.getAmount());
		LOGGER.info(good.getPrice());
		LOGGER.info(sale.getCalculatedSum());
		assertEquals(money(17.90), sale.getCalculatedSum());
	}
	
	
	@Test
	public void testGetCalculateSumNotNull(){
		Sale sale = new Sale();	
		assertNotNull(sale.getCalculatedSum());
	}
}
