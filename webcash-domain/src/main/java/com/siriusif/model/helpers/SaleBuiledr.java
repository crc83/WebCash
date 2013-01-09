package com.siriusif.model.helpers;

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
	public static Sale buildSale(String name, double amount, double price) {
		Sale sale = new Sale();
		sale.setSalesGood(new Good(name, price));
		sale.setAmount(amount);
		return sale;
		
	}
}
