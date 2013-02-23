package com.siriusif.process;

import static org.junit.Assert.*;

import java.io.UnsupportedEncodingException;

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
		assertNull(newWorkshift.getClosedAt());
		assertNull(newWorkshift.getDaySum());
		fail("Not yet implemented");
	}

}
