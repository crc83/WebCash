package com.siriusif.model;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.math.RoundingMode;

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
		good.setPrice(new BigDecimal(35.44).setScale(2, RoundingMode.HALF_UP));
		sale.setAmount(new BigDecimal(0.505).setScale(3, RoundingMode.HALF_UP));
		LOGGER.info(sale.getAmount());
		LOGGER.info(good.getPrice());
		LOGGER.info(sale.getCalculatedSum());
//		BigDecimal expected = new BigDecimal(17.37).setScale(2, RoundingMode.FLOOR);
//		LOGGER.info(expected);
//				assertTrue(expected.equals(sale.getCalculatedSum()));
		assertEquals(new BigDecimal(17.90).setScale(2, RoundingMode.HALF_UP), sale.getCalculatedSum());
	}
	

}
