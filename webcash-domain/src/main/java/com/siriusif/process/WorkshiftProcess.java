package com.siriusif.process;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.siriusif.model.Workshift;
import com.siriusif.service.model.WorkshiftDao;

/**
 * Performs operation with Workshifts
 * i.e. 
 * 
 * - openWorkshift
 * - closeWorkshift
 */
@Component
public class WorkshiftProcess {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(WorkshiftProcess.class);
	
	@Autowired
	private WorkshiftDao workshiftDao;
	
	/**
	 * Opens workshift for current working date.
	 * @return workshift object for new workshift
	 */
	public Workshift openWorkshift(){
		List<Workshift> openedWorkshifts = getOpenedWorkshifts();
		//if we have more than one opened session
		if (openedWorkshifts.size()>1) {
			//we should log the state and continue work
			LOGGER.warn("There is more than one opened session. Closing them all anyway.");
		}
		//we should close all opened workshifts
		for (Workshift ws : openedWorkshifts){
			closeWorkshift(ws);
		}
		//we shuld create new workshift
		Workshift ws = new Workshift();
		ws.setOpenedAt(getCurrentWorkshiftDate());
		ws.setDailyId(getTodayWorkshiftsCount());
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
			LOGGER.debug("Closing workshift:"+ws.toString());
		} else {
			LOGGER.warn("closeWorkshift: null instead of workshift");
		}
	}
	
}
