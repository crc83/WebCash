package com.siriusif.process;

import com.siriusif.model.Workshift;

public interface WorkshiftProcess {
	
	/**
	 * Opens workshift for current working date.
	 * @return workshift object for new workshift
	 */
	Workshift openWorkshift();
	
	/**
	 * Currently opened workshift.
	 * Or null if there is no open workshift.
	 * @return open workshift today
	 */
	Workshift getOpenWorkshiftNow();

}
