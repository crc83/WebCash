package com.siriusif.service.model;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.siriusif.helper.Helper;
import com.siriusif.model.Workshift;

public class WorkshiftDaoImplTest extends AbstractDaoImplTest{

	@Autowired
	private WorkshiftDao workshiftDao;
	
	@Test
	public void testAdd() {
		int size = workshiftDao.list().size();
		
		Workshift workshift = new Workshift();
		workshift.setDailyId(12);
		workshift.setClosedAt(Helper.stringToDate("22/01/2013"));
		workshiftDao.add(workshift);
		
		assertTrue (size < workshiftDao.list().size());
	}

	/**
	 * We can't set up close date during creation of workshift !!!
	 */
	@Test
	public void testEmptyCloseDateDuringInsert() {
		Workshift workshift = new Workshift();
		workshift.setDailyId(12);
		workshift.setClosedAt(Helper.stringToDate("22/01/2013"));
		workshiftDao.add(workshift);
		// get this workshift from database
		Workshift wsFroDB = workshiftDao.find(workshift.getId()); 
		assertNull(wsFroDB.getClosedAt());
	}

	@Test
	public void testSetCloseDate() {
		Workshift workshift = new Workshift();
		workshift.setDailyId(12);
		workshiftDao.add(workshift);
		// get this workshift from db
		Date closedAt = Helper.stringToDate("22/01/2013");
		workshift.setClosedAt(closedAt);

		// NOTE: Workshift open date is generated on the db side.
		// we can't update this value during update operations 
		assertNull(workshift.getOpenedAt());
		workshiftDao.update(workshift);
		
		// get this workshift from database
		Workshift wsFroDB = workshiftDao.find(workshift.getId()); 
		assertEquals(closedAt, wsFroDB.getClosedAt());
	}

	
	@Test
	public void testSetOpenDateNotNull() {
		Workshift workshift = new Workshift();
		workshift.setDailyId(12);
		workshiftDao.add(workshift);
		// get this workshift from database
		Workshift wsFroDB = workshiftDao.find(workshift.getId()); 
		assertNotNull(wsFroDB.getOpenedAt());
	}

}
