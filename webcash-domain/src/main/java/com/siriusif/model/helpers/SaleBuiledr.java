package com.siriusif.model.helpers;

import java.math.BigDecimal;

import com.siriusif.model.Good;
import com.siriusif.model.Sale;

public class SaleBuiledr {
	
	/**
	 * Helper method to build sale item for tests
	 * @param name
	 * @param amount
	 * @param price
	 * @return
	 */
	public static Sale buildSale(String name, BigDecimal amount, BigDecimal price) {
		Sale sale = new Sale();
		sale.setSalesGood(new Good(name, price));
		sale.setAmount(amount);
		return sale;
		
	}
}
