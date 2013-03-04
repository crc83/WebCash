package com.siriusif.service.model;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

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
	
}
