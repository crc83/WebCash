package com.siriusif.model.helpers;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.siriusif.model.Good;
import com.siriusif.model.Sale;

public final class TestHelper {
	
	/**
	 * Helper method to build sale item for tests
	 * @param name
	 * @param amount
	 * @param price
	 * @return
	 */
	public static final Sale buildSale(String name, BigDecimal amount, BigDecimal price) {
		Sale sale = new Sale();
		sale.setSalesGood(new Good(name, price));
		sale.setAmount(amount);
		return sale;		
	}
		
	public static final BigDecimal money(double money){
		return new BigDecimal(money).setScale(2, RoundingMode.HALF_UP);	
	}

	public static final BigDecimal amount(double amount){
		return new BigDecimal(amount).setScale(3, RoundingMode.HALF_UP);	
	}
}
