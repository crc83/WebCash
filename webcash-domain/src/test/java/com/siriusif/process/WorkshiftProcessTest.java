package com.siriusif.process;

import static org.junit.Assert.*;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.siriusif.helper.AbstractSpringTest;
import com.siriusif.helper.Helper;
import com.siriusif.model.Workshift;
import com.siriusif.service.model.WorkshiftDao;

public class WorkshiftProcessTest extends AbstractSpringTest{

	@Mock
	private WorkshiftDao workshiftDao;
	
	@InjectMocks
	private WorkshiftProcess wsProcess; 
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	/*
	 * Given : We have couple of workshifts 
	 * When  : We open workshift
	 * Than  : We get it in initial state
	 */
	@Test
	public void testOpenWorkshift() {
		
		Workshift newWorkshift = wsProcess.openWorkshift();
		
		assertNotNull(newWorkshift);//workshift should be created
		Date today = new Date();
		
		assertEquals(today, newWorkshift.getOpenedAt());
		assertNull(newWorkshift.getClosedAt());
		assertNull(newWorkshift.getDaySum());
	}
	/*
	 * Given : We have three closed for today workshifts 
	 * When  : We open workshift
	 * Than  : We get it in initial state with dailyId=3
	 */
	@Test
	public void testOpenWorkshiftWhenWeHaveThreeClosed() {
		Date today =  Helper.getDateOnly(new Date());
		Workshift newWorkshift = wsProcess.openWorkshift();
		
		assertNotNull(newWorkshift);//workshift should be created
		
		assertEquals(today, Helper.getDateOnly(newWorkshift.getOpenedAt()));
		assertNull(newWorkshift.getClosedAt());
		assertNull(newWorkshift.getDaySum());
		assertEquals(3, newWorkshift.getDailyId());
	}

	
}
