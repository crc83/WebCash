package com.siriusif.model;

import java.util.Date;
import org.junit.Test;
import com.siriusif.helper.Helper;
import static com.siriusif.model.helpers.TestHelper.money;
import static org.junit.Assert.assertNotNull;

public class WorkshiftTest {
	
	/**
	 * Given : New workshift object
	 * When  : I assign all it's fields
	 * Than  : I didn't receive any error during this
	 */
	@Test
	public void testWorkshiftInitialization() {
		Date workingDate = Helper.stringToDate("13/11/2013");
		
		Workshift workshift = new Workshift();
		workshift.setWorkingDate(workingDate);
		workshift.setId(12l);
		workshift.setDailyId(42);
		workshift.setDaySum(money(23.17));
		workshift.setOpenedAt(new Date());
		assertNotNull(workshift);
	}

}
