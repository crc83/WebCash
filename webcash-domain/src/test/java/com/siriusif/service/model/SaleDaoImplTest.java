package com.siriusif.service.model;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.siriusif.helper.AbstractSpringTest;
import com.siriusif.model.Sale;
import static com.siriusif.model.helpers.SaleBuiledr.amount;

public class SaleDaoImplTest extends AbstractSpringTest {
	
	@Autowired
	private SaleDao saleDao;

	/*
	 * When : I add table to db
	 * Then :
	 * 1) I should see that number of entities in DB increased 
	 * 2) I should read all fields from DB correctly
	 */
	//TODO SB : Check if sale read correctly
	@Test
	public void testAdd() {
		int size = saleDao.list().size();
		
		Sale sale = new Sale();
		sale.setAmount(amount(0.505));
		saleDao.add(sale);
		
		assertTrue(size < saleDao.list().size());
	}
	
}
