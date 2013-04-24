package com.siriusif.process;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.siriusif.helper.AbstractSpringTest;
import com.siriusif.helper.Helper;
import com.siriusif.model.Workshift;
import com.siriusif.process.impl.WorkshiftProcessImpl;
import com.siriusif.service.model.WorkshiftDao;

public class WorkshiftProcessTest extends AbstractSpringTest{

	@Mock
	private WorkshiftDao workshiftDao;
	
	@InjectMocks
	private WorkshiftProcess wsProcess = new WorkshiftProcessImpl(); 
	
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
		
		Date today = new Date();
		
		assertNotNull(newWorkshift);//workshift should be created
		assertEquals(Helper.dateOnly(today), 
					Helper.dateOnly(newWorkshift.getOpenedAt()));
		assertNull(newWorkshift.getClosedAt());
		assertNull(newWorkshift.getDaySum());
	}
	/*
	 * Given : We have three closed for today workshifts. And we have two not closed workshifts at all 
	 * When  : We open workshift
	 * Than  : We get it in initial state with dailyId=3
	 */
	@Test
	public void testOpenWorkshiftWhenWeHaveThreeClosed() throws JsonSyntaxException, JsonIOException, UnsupportedEncodingException {
		stub(workshiftDao.countForDate(any(Date.class))).toReturn(3);
		Workshift[] openedWorkshifts = Helper.fromJson("/opened_workshifts.json", Workshift[].class);
		stub(workshiftDao.getOpenedWorkshiftsList()).toReturn(Arrays.asList(openedWorkshifts));
		//TODO SB: add expectations of invocation WorkshiftDao.setCloseDate
		
		Date today =  Helper.dateOnly(new Date());
		Workshift newWorkshift = wsProcess.openWorkshift();
		
		assertNotNull(newWorkshift);//workshift should be created
		assertEquals(Helper.dateOnly(today), 
				Helper.dateOnly(newWorkshift.getOpenedAt()));
		assertNull(newWorkshift.getClosedAt());
		assertNull(newWorkshift.getDaySum());
		assertEquals(3, newWorkshift.getDailyId());
	}
	
	/*
	 * Given : There is no workshifts in the database
	 * Than  : I receive null as opened workshift 
	 */
	@Test
	public void testGetOpenedWorkshiftWhenDBEmpty() {
		assertNull(wsProcess.getOpenWorkshift());
	}

	/*
	 * Given : There is 3 closed and no opened workshifts in the database
	 * Than  : I receive null as opened workshift 
	 */
	@Test
	public void testGetOpenedWorkshiftWhenWorkshiftsClosed() throws JsonSyntaxException, JsonIOException, UnsupportedEncodingException {
		List<Workshift> listOfClosedWorkshifts = Arrays.asList(Helper.fromJson("/closed_workshifts.json", Workshift[].class));
		stub(workshiftDao.getOpenedWorkshiftsList()).toReturn(listOfClosedWorkshifts);
		assertNull(wsProcess.getOpenWorkshift());
	}
	
	/*
	 * Given : Error occurs while getting data from DB
	 * Than  : I receive null as opened workshift 
	 */
	@Test
	public void testGetOpenedWorkshiftWhenDBError() throws JsonSyntaxException, JsonIOException, UnsupportedEncodingException {
		stub(workshiftDao.getOpenedWorkshiftsList()).toThrow(new RuntimeException("Stub exception"));
		assertNull(wsProcess.getOpenWorkshift());
	}
	
	/*
	 * Given : (normal situation) There is only one opened workshift
	 * Than  : I receive this opened workshift 
	 */
	@Test
	public void testGetOpenedWorkshiftWhenOnlyOneOpenedWorkshift() throws JsonSyntaxException, JsonIOException, UnsupportedEncodingException {
		List<Workshift> oneOpenedWorkshift = Arrays.asList(Helper.fromJson("/one_opened_workshift.json", Workshift[].class));
		stub(workshiftDao.getOpenedWorkshiftsList()).toReturn(oneOpenedWorkshift);
		assertEquals(oneOpenedWorkshift.get(0), wsProcess.getOpenWorkshift());
	}
	
	/*
	 * Given : There is two opened workshifts
	 * Than  : I receive null 
	 */
	@Test
	public void testGetOpenedWorkshiftWhenTwoOpenedWorkshift() throws JsonSyntaxException, JsonIOException, UnsupportedEncodingException {
		List<Workshift> oneOpenedWorkshift = Arrays.asList(Helper.fromJson("/opened_workshifts.json", Workshift[].class));
		stub(workshiftDao.getOpenedWorkshiftsList()).toReturn(oneOpenedWorkshift);
		assertNull(wsProcess.getOpenWorkshift());
	}
	
	
	
}
