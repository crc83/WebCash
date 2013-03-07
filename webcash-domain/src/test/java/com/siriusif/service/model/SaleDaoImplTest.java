package com.siriusif.service.model;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.siriusif.model.Sale;

public class SaleDaoImplTest extends AbstractDaoImplTest {
	
	@Autowired
	private SaleDao saleDao;

	@Test
	public void testAdd() {
		int size = saleDao.list().size();
		
		Sale sale = new Sale();
		sale.setAmount(new BigDecimal(0.505).setScale(3, RoundingMode.HALF_UP));
		saleDao.add(sale);
		
		assertTrue(size < saleDao.list().size());
	}

}
