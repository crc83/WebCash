package com.siriusif.service.model;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.siriusif.model.Sale;
import static com.siriusif.model.helpers.SaleBuiledr.amount;

public class SaleDaoImplTest extends AbstractDaoImplTest {
	
	@Autowired
	private SaleDao saleDao;

	@Test
	public void testAdd() {
		int size = saleDao.list().size();
		
		Sale sale = new Sale();
		sale.setAmount(amount(0.505));
		saleDao.add(sale);
		
		assertTrue(size < saleDao.list().size());
	}
	
}
