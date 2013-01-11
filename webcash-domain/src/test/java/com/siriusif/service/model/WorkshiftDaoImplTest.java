package com.siriusif.service.model;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.siriusif.model.Workshift;

public class WorkshiftDaoImplTest extends AbstractDaoImplTest{

	@Autowired
	private WorkshiftDao workshiftDao;
	
	@Test
	public void testAdd() {
		int size = workshiftDao.list().size();
		
		Workshift workshift = new Workshift();
		workshift.setDailyId(12);
		workshiftDao.add(workshift);
		
		assertTrue (size < workshiftDao.list().size());
	}

}
