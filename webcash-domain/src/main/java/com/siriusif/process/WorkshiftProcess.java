package com.siriusif.process;

import com.siriusif.model.Workshift;

public interface WorkshiftProcess {
	
	/**
	 * @return open workshift today
	 */
	Workshift getOpenWorkshiftNow();

}
