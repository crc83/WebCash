package com.siriusif.process;

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
	
	@Autowired
	private WorkshiftDao workshiftDao;
	
	public Workshift openWorkshift(){
		return null;
	}
	
	public void closeWorkshift(){
		//NOP
	}
	
}
