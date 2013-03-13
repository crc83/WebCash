package com.siriusif.service.model;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.siriusif.model.Sale;
import com.siriusif.model.Suborder;

public class SuborderDaoImplTest extends AbstractDaoImplTest{
	
	@Autowired
	private SuborderDao suborderDao;

	@Test
	public void testAdd() {
		int size = suborderDao.list().size();
		
		Suborder suborder = new Suborder();
		suborder.setIndex(3);
		suborderDao.add(suborder);
		
		assertTrue (size < suborderDao.list().size());
	}
	
	@Test
	public void testOneToManySuborderSale(){
		int size = suborderDao.list().size();
		Suborder suborder = new Suborder(5);
		
		Sale sale = new Sale();
		sale.setAmount(BigDecimal.valueOf(15.25));
		suborder.addSale(sale);
		
		Sale sale1 = new Sale();
		sale1.setAmount(BigDecimal.valueOf(16.25));
		suborder.addSale(sale1);
		suborderDao.add(suborder);
		
		assertTrue (size < suborderDao.list().size());
		Suborder suborderFromDB = suborderDao.find(suborder.getId());
		assertEquals(5, suborderFromDB.getIndex());
		assertEquals(2, suborderFromDB.getSales().size());
	}
	
}
