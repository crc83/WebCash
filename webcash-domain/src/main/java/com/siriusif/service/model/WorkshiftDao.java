package com.siriusif.service.model;

import java.util.Date;
import java.util.List;

import com.siriusif.model.Workshift;
import com.siriusif.service.GenericDao;

public interface WorkshiftDao extends GenericDao<Workshift, Long> {

	/**
	 * Return count of all workshifts for current date
	 * @param currentWorkshiftDate
	 * @return
	 */
	int countForDate(Date currentWorkshiftDate);

	/**
	 * Return list of workshifts which are not clossed (date doesn't matter)
	 * @return
	 */
	List<Workshift> getOpenedWorkshiftsList();

}
