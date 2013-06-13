package com.siriusif.process.impl;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.siriusif.model.Workshift;
import com.siriusif.process.WorkshiftProcess;
import com.siriusif.service.model.WorkshiftDao;

/**
 * Performs operation with Workshifts
 * i.e. 
 * 
 * - openWorkshift
 * - closeWorkshift
 */
@Component(value="workshiftProcess")
public class WorkshiftProcessImpl implements WorkshiftProcess {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(WorkshiftProcessImpl.class);
	
	@Autowired
	private WorkshiftDao workshiftDao;
	
	/*
	 * (non-Javadoc)
	 * @see com.siriusif.process.WorkshiftProcess#openWorkshift()
	 */
	@Override
	public Workshift openWorkshift(){
		List<Workshift> openedWorkshifts = getOpenedWorkshifts();
		//if we have more than one opened session
		if (openedWorkshifts.size()>1) {
			//we should log the state and continue work
			LOGGER.warn("There is more than one opened workshift. Closing them all anyway.");
		}
		Workshift ws = null;
		return ws;
	}
	
	/**
	 * Returns count of closed workshifts for current working date
	 * @return count of closed workshifts today
	 */
	private int getTodayWorkshiftsCount() {
		return workshiftDao.countForDate(getCurrentWorkshiftDate());
	}

	/**
	 * Returns working date. By default it returns current date.
	 * But you can set up time, when working date should be changed.
	 * I.e. if time to change working date is 3:00 A.M. than 28-MAR 23:00 and 29-MAR 2:59 are still the same working date (28-MAR). 
	 * @return working date according to settings
	 */
	private Date getCurrentWorkshiftDate() {
		// TODO SB : Correct to work with real working dates
		return new Date();
	}

	/**
	 * Return list of all workshifts which are not closed
	 * before or equals current working date.
	 * @return list of unclosed workshifts
	 */
	private List<Workshift> getOpenedWorkshifts() {
		List<Workshift> result = workshiftDao.getOpenedWorkshiftsList();
		return result;
	}

	/**
	 * Closes workshift
	 * @param ws workshift to close
	 */
	public void closeWorkshift(Workshift ws){
		if ( ws != null ) {
			// TODO Auto-generated method stub
			ws.setClosedAt(new Date());
			LOGGER.debug("Closing workshift:"+ws.toString());
		} else {
			LOGGER.warn("closeWorkshift: workshift is null");
		}
	}

	// TODO SB: Cower with tests
	@Override
	public Workshift getOpenWorkshift() {
		List<Workshift> openedWorkshifts = null;
		try {
			openedWorkshifts = workshiftDao.getOpenedWorkshiftsList();
		} catch (Exception e){
			LOGGER.error("Error while getting opened workshift from DB. Caused by",e);
			return null;
		}
		
		if (openedWorkshifts == null || openedWorkshifts.size()==0){
			LOGGER.debug("There is no opened workshifts. Return null");
			return null;
		} else if (openedWorkshifts.size()>1){
			LOGGER.warn("There is more than one opened workshift. Fix it in workshifts view.");
			return null;
		} else {
			return openedWorkshifts.get(0);
		}
	}
	
}
