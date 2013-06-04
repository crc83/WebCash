package com.siriusif.managed.bean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.siriusif.model.Workshift;
import com.siriusif.process.WorkshiftProcess;

import static com.siriusif.helper.Helper.date;
import static org.mockito.Mockito.*;


public class WorkStartBeanTest {
	
	WorkStartBean workStartBean = new WorkStartBean();
	WorkshiftProcess wsProcess;
	
	@Before
	public void setUp() {
		wsProcess = mock(WorkshiftProcess.class);
		workStartBean.setWorkshiftProcess(wsProcess);
	}
	
	/**
	 * Given : There is one opened workshift
	 * When  : I start working day
	 * Then  : Application should use this workshift
	 */
	@Test
	public void testOneOpenedWorkhift(){
		Workshift openedWorkshift = new Workshift();
		openedWorkshift.setId(42L);
		openedWorkshift.setDailyId(37);
		openedWorkshift.setWorkingDate(date("12/01/2013"));
		
		when(wsProcess.getOpenWorkshift()).thenReturn(openedWorkshift);
		//method under test
		workStartBean.openWorkshiftIfNecessary();
		
		verify(wsProcess,never()).openWorkshift();
	}

	/**
	 * Given : There is no opened workshifts 
	 * When  : I start working day
	 * Then  : I recieve new workhift
	 */
	@Test
	public void testNoWorkhifts(){
		when(wsProcess.getOpenWorkshift()).thenReturn(null);
		//method under test
		workStartBean.openWorkshiftIfNecessary();
		
		verify(wsProcess,times(1)).openWorkshift();		
	}

}
