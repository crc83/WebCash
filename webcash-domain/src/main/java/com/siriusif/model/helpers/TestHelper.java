package com.siriusif.model.helpers;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.siriusif.model.Good;
import com.siriusif.model.Sale;
import com.siriusif.model.Workshift;
import com.siriusif.service.model.impl.WorkshiftDaoImpl;

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
	
	/**
	 * Helper method to build workshift for the tests
	 * @return
	 */
	public static final Workshift buildWorshift(){
		Workshift ws = new Workshift();
		return ws;
	}
		
	public static final BigDecimal money(double money){
		return new BigDecimal(money).setScale(2, RoundingMode.HALF_UP);	
	}

	public static final BigDecimal amount(double amount){
		return new BigDecimal(amount).setScale(3, RoundingMode.HALF_UP);	
	}
}
