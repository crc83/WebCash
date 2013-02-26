package com.siriusif.process;

import static org.junit.Assert.*;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.siriusif.helper.AbstractSpringTest;
import com.siriusif.helper.Helper;
import com.siriusif.model.Workshift;
import com.siriusif.service.model.WorkshiftDao;

public class WorkshiftProcessTest extends AbstractSpringTest{

	@Autowired
	private WorkshiftDao workshiftDao;
	
	@Autowired
	private WorkshiftProcess wsProcess; 
	
	/*
	 * Given : We have couple of workshifts 
	 * When  : We open workshift
	 * Than  : We get it in initial state
	 */
	@Test
	public void testOpenWorkshift() throws JsonSyntaxException, JsonIOException, UnsupportedEncodingException {
		Workshift[] worksifts = Helper.fromJson("/workshift.json", Workshift[].class);
		for (Workshift ws : worksifts){
			workshiftDao.add(ws);
			//because we cannot write close date during workshift creation
			workshiftDao.update(ws);
		}
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
	public void testOpenWorkshiftWhenWeHaveThreeClosed() throws JsonSyntaxException, JsonIOException, UnsupportedEncodingException {
		Workshift[] worksifts = Helper.fromJson("/workshift.json", Workshift[].class);
		Date today =  Helper.getDateOnly(new Date());
		for (Workshift ws : worksifts){
			ws.setOpenedAt(today);
			ws.setClosedAt(today);
			workshiftDao.add(ws);
			//because we cannot write close date during workshift creation
			workshiftDao.update(ws);
		}
		Workshift newWorkshift = wsProcess.openWorkshift();
		
		assertNotNull(newWorkshift);//workshift should be created
		
		assertEquals(today, Helper.getDateOnly(newWorkshift.getOpenedAt()));
		assertNull(newWorkshift.getClosedAt());
		assertNull(newWorkshift.getDaySum());
		assertEquals(3, newWorkshift.getDailyId());
	}

	
}
