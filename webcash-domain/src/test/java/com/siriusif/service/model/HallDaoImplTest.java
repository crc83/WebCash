package com.siriusif.service.model;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.siriusif.model.Hall;

public class HallDaoImplTest extends AbstractDaoImplTest{
	
	@Autowired
	private HallDao hallDao;

	@Test
	public void testAdd() {
		int size = hallDao.list().size();
		
		Hall hall = new Hall();
		hall.setName("bar");
		hallDao.add(hall);
		
		assertTrue (size < hallDao.list().size());
	}

}
