package com.siriusif.service.model;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.siriusif.helper.AbstractSpringTest;
import com.siriusif.helper.Helper;
import com.siriusif.helper.ModelFactory;
import com.siriusif.model.DinnerTable;
import com.siriusif.model.Order;
import com.siriusif.model.Suborder;

import static com.siriusif.helper.Helper.date;
import static com.siriusif.model.helpers.TestHelper.money;

public class OrderDaoImplTest extends AbstractSpringTest {

	@Autowired
	private OrderDao orderDao;
	
	@Autowired
	private DinnerTableDao tableDao;

	/**
	 * When: add order 
	 * Than: list of orders in DB increase
	 */
	@Test
	public void testAdd() {
		int size = orderDao.list().size();

		Order order = new Order();
		order.setAuthor("admin");
		order.setCard(true);
		order.setDailyId(size);
		order.setDiscount(5);
		order.setNomerok(2);
		order.setPaid(money(13.51));
		order.setReadOnly(true);
		order.setType(false);
		orderDao.add(order);

		assertTrue(size < orderDao.list().size());
	}

	/**
	 * When: add static working Date 
	 * Than: working Date of order find in DB
	 */
	@Test
	public void testAddWorkshiftDate() {
		Order order = new Order();
		order.setAuthor("admin");
		order.setPaid(money(13.51));
		orderDao.add(order);
		Date workingDate = date("22/01/2013");
		order.setWorkingDate(workingDate);
		orderDao.update(order);

		Order orFromDB = orderDao.find(order.getId());
		assertEquals(workingDate, orFromDB.getWorkingDate());
	}

	/**
	 * When: add money in paid 
	 * Than: money add correctly in order
	 */
	@Test
	public void testSetSumma() {
		int size = orderDao.list().size();

		Order order = new Order();
		order.setAuthor("admin");
		order.setPaid(money(13.51));
		order.setDailyId(size);
		orderDao.add(order);

		assertTrue(size < orderDao.list().size());
	}

	/**
	 * When: 4 Suborders add in order 
	 * Than: 4 Suborders find in DB by Order Id
	 */
	@Test
	public void testOneToManyOrderSuborders() {
		int size = orderDao.list().size();
		Order order = new Order();

		order.addSuborder(new Suborder(1));
		order.addSuborder(new Suborder(2));
		order.addSuborder(new Suborder(3));
		order.addSuborder(new Suborder(5));
		order.setAuthor("admin");
		order.setPaid(money(13.51));
		order.setDailyId(size);
		orderDao.add(order);

		assertTrue(size < orderDao.list().size());
		Order orFromDB = orderDao.find(order.getId());
		assertEquals(4, orFromDB.getSuborders().size());
	}

	/**
	 * When: 4 Suborders add in Order 
	 * Than: count of Suborders in Order 4
	 */
	@Test
	public void tsetCountOfsubordersInOrder() {
		int size = orderDao.list().size();
		Order order = new Order();

		order.addSuborder(new Suborder(1));
		order.addSuborder(new Suborder(2));
		order.addSuborder(new Suborder(3));
		order.addSuborder(new Suborder(4));
		order.setAuthor("adminic");
		order.setPaid(money(13.51));
		order.setDailyId(size);
		orderDao.add(order);

		assertEquals(4, orderDao.countOfSuborders(order.getId()));
	}

	/**
	 * When: Suborder doesn't add in Order 
	 * Than: count of Suborders in Order 0
	 */
	@Test
	public void tsetCountOfsubordersInOrderIsZero() {
		int size = orderDao.list().size();
		Order order = new Order();

		order.setAuthor("adminic");
		order.setPaid(money(13.51));
		order.setDailyId(size);
		orderDao.add(order);

		assertEquals(0, orderDao.countOfSuborders(order.getId()));
	}

	/**
	 * When: updating of order 
	 * Than: open date doesn't update and set close date
	 */
	@Test
	public void testCloseDateOfOrder() {
		int size = orderDao.list().size();
		Order order = new Order();

		Date closeDate = date("22/01/2013");
		order.setCloseDate(closeDate);
		order.setAuthor("admin");
		order.setPaid(money(13.51));
		order.setDailyId(size);
		orderDao.add(order);

		assertNull(order.getOpenDate());
		orderDao.update(order);

		Order orFromDb = orderDao.find(order.getId());
		assertEquals(closeDate, orFromDb.getCloseDate());
	}
	
	@Test
	public void testListForTableId(){
		int size = orderDao.list().size();
		DinnerTable table = new DinnerTable();
		table.setName("bar");
		
		Order order = new Order();
		order.setAuthor("admin");
		order.setDailyId(size);
		order.setStatus(Order.STATUS_OPEN_DATA);
		table.addOrder(order);
		
		Order order1 = new Order();
		order1.setAuthor("admin");
		order1.setDailyId(size);
		order1.setStatus(Order.STATUS_CLOSE_DATA);
		table.addOrder(order1);
		
		Order order2 = new Order();
		order2.setAuthor("admin");
		order2.setDailyId(size);
		order2.setStatus(Order.STATUS_OPEN_DATA);
		table.addOrder(order2);
		tableDao.add(table);
		
		assertEquals(2, orderDao.listForTableId(table).size());
	}
}
