package com.siriusif.service.model;

import static org.junit.Assert.*;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.siriusif.helper.AbstractSpringTest;
import com.siriusif.helper.Helper;
import com.siriusif.model.TablesHall;
import com.siriusif.model.Workshift;

public class WorkshiftDaoImplTest extends AbstractSpringTest{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(WorkshiftDaoImplTest.class);

	@Autowired
	private WorkshiftDao workshiftDao;
	
	private static Workshift[] workshifts;

	@BeforeClass
	public static void setUp() throws JsonSyntaxException, JsonIOException, UnsupportedEncodingException{
		workshifts = Helper.fromJson("/workshift.json", Workshift[].class);
	}
	
	@Test
	public void testAdd() {
		int size = workshiftDao.list().size();
		workshiftDao.add(workshifts[0]);
		assertTrue (size < workshiftDao.list().size());
	}

	/**
	 * We can't set up close date during creation of workshift !!!
	 */
	@Test
	public void testEmptyCloseDateDuringInsert() {
		Workshift workshift =workshifts[1]; 
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
	
	/**
	 * Given : workshift with defined day sum
	 * When  : I read this workshift
	 * THen  : I should have correct sum
	 */
	@Test
	public void testSetDaySum() {
		int size = workshiftDao.list().size();
		
		Workshift workshift = workshifts[2];
//		workshift.setDaySum(BigDecimal.valueOf(13.51));
		workshiftDao.add(workshift);
		Workshift wsFroDB = workshiftDao.find(workshift.getId()); 
		// get this workshift from database
		assertEquals(BigDecimal.valueOf(1351, 2) , wsFroDB.getDaySum());
		assertTrue (size < workshiftDao.list().size());
	}


}
