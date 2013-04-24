package com.siriusif.service.model;

import static com.siriusif.model.helpers.TestHelper.money;
import static org.junit.Assert.*;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.Date;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.siriusif.helper.AbstractSpringTest;
import com.siriusif.helper.Helper;
import com.siriusif.model.Order;
import com.siriusif.model.Workshift;

public class WorkshiftDaoImplTest extends AbstractSpringTest{
	
	@Autowired
	private WorkshiftDao workshiftDao;
	
	private static Workshift[] workshifts;

	@BeforeClass
	public static void globalSetUp() throws JsonSyntaxException, JsonIOException, UnsupportedEncodingException{
		workshifts = Helper.fromJson("/workshifts.json", Workshift[].class);
	}
	
	@Before
	public void setUp(){
		workshiftDao.clearAll();
	}
	
	@Test
	public final void testAdd() {
		int size = workshiftDao.list().size();
		workshiftDao.add(workshifts[0]);
		assertTrue (size < workshiftDao.list().size());
	}

	/**
	 * We can't set up close date during creation of workshift !!!
	 */
	@Test
	public void testEmptyCloseDateDuringInsert() {
		Workshift workshift =workshifts[1]; 
		workshiftDao.add(workshift);
		// get this workshift from database
		Workshift wsFroDB = workshiftDao.find(workshift.getId()); 
		assertNull(wsFroDB.getClosedAt());
	}

	@Test
	public void testSetCloseDate() {
		Workshift workshift = new Workshift();
		workshift.setDailyId(12);
		workshiftDao.add(workshift);
		
		// get this workshift from db
		Date closedAt = Helper.stringToDate("22/01/2013");
		workshift.setClosedAt(closedAt);

		// NOTE: Workshift open date is generated on the db side.
		// we can't update this value during update operations 
		assertNull(workshift.getOpenedAt());
		workshiftDao.update(workshift);
		
		// get this workshift from database
		Workshift wsFroDB = workshiftDao.find(workshift.getId()); 
		assertEquals(closedAt, wsFroDB.getClosedAt());
	}

	
	@Test
	public final void testSetOpenDateNotNull() {
		Workshift workshift = new Workshift();
		workshift.setDailyId(12);
		workshiftDao.add(workshift);
		// get this workshift from database
		Workshift wsFroDB = workshiftDao.find(workshift.getId()); 
		assertNotNull(wsFroDB.getOpenedAt());
	}
	
	/**
	 * Given : workshift with defined day sum
	 * When  : I read this workshift
	 * THen  : I should have correct sum
	 */
	@Test
	public final void testSetDaySum() {
		int size = workshiftDao.list().size();
		
		Workshift workshift = workshifts[2];
//		workshift.setDaySum(BigDecimal.valueOf(13.51));
		workshiftDao.add(workshift);
		Workshift wsFroDB = workshiftDao.find(workshift.getId()); 
		// get this workshift from database
		// TODO SB : Read day sum from JSON correctly
		assertEquals(BigDecimal.valueOf(1351, 2) , wsFroDB.getDaySum());
		assertTrue (size < workshiftDao.list().size());
	}
	
	@Test
	public final void testGetOpenedWorkshiftsList() {
		for (Workshift workshift : workshifts){
			workshiftDao.add(workshift);
			//because we ca't set closedAt during item creation
			workshiftDao.update(workshift);
		}
		assertEquals(2, workshiftDao.getOpenedWorkshiftsList().size());
	}

	@Test
	public final void testGetTodayWorkshiftsCount() {
		Date now = new Date();
		for (Workshift workshift : workshifts){
			workshiftDao.add(workshift);
			workshift.setOpenedAt(now);
			workshift.setClosedAt(now);
			workshiftDao.update(workshift);
		}
		assertEquals(5, workshiftDao.countForDate(now));
	}
	
	/**
	 * When: 3 Orders add in workshift
	 * Than: List of Orders in Workshift is 3
	 */
	@Test
	public void testOneToManyWrkshiftOrders(){
		int size = workshiftDao.list().size();
		Workshift workshift = new Workshift();

		Order order = new Order(); 
		order.setAuthor("admin");
		order.setPaid(money(13.51));
		workshift.addOrder(order);
		
		Order order2 = new Order();
		order2.setAuthor("sonya");
		order2.setPaid(money(15.55));
		workshift.addOrder(order2);
		
		Order order3 = new Order();
		order3.setAuthor("sanya");
		order3.setPaid(money(25.51));
		workshift.addOrder(order3);
		
		workshiftDao.add(workshift);
		
		assertTrue(size < workshiftDao.list().size());
		Workshift workshiftFromDB = workshiftDao.find(workshift.getId());
		assertEquals(3, workshiftFromDB.getOrders().size());
		assertEquals("sonya", workshiftFromDB.getOrders().get(1).getAuthor());
	}
}
