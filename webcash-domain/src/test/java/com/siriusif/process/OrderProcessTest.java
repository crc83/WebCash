package com.siriusif.process;

import static com.siriusif.model.helpers.SaleBuiledr.money;
import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;

import com.siriusif.helper.AbstractSpringTest;
import com.siriusif.helper.Helper;
import com.siriusif.model.DinnerTable;
import com.siriusif.model.Order;
import com.siriusif.model.Workshift;
import com.siriusif.process.impl.OrderProcessImpl;
import com.siriusif.service.model.DinnerTableDao;
import com.siriusif.service.model.OrderDao;

public class OrderProcessTest extends AbstractSpringTest {

	@Mock
	private OrderDao orderDao;

	@Mock
	private DinnerTableDao tableDao;

	@Mock
	private WorkshiftProcess workshiftProcess;

	@InjectMocks
	private OrderProcessImpl orderProcess;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	/**
	 * When: new Order created Than: new Order not null; today's date equals
	 * date of new Order; total of order equals 0; new Order has only one
	 * Suborder
	 */
	@Test
	public void testNewOrder() {
		stub(workshiftProcess.getOpenWorkshiftNow()).toReturn(new Workshift());
		Order newOrder = orderProcess.newOrder(1l);

		Date today = new Date();

		verify(orderDao).add(newOrder);

		assertNotNull("New order not null", newOrder);
		assertNotNull("WorkShift not null", newOrder.getWorkShift());
		assertEquals(Helper.dateOnly(today),
				Helper.dateOnly(newOrder.getOpenDate()));

		assertTrue("We need to have total=0 for new order", newOrder.getTotal()
				.compareTo(money(0)) == 0);
		assertEquals("We need at least one suborder in new order", 1, newOrder
				.getSuborders().size());
	}

	/**
	 * When: stub conutDailyId 42 Than: DailyId of new Order return 43
	 */
	@Test
	public void testNewOrderWithCorrectDailyId() {
		// 42
		stub(orderDao.conutDailyId(any(Date.class))).toReturn(42);

		Order newOrder = orderProcess.newOrder(1l);
		assertEquals(43, newOrder.getDailyId());
	}

	/**
	 * When: current user add in Order Than: current user equals user of new
	 * Order
	 */
	@Test
	public void testAuthorForOrder() {
		Order newOrder = orderProcess.newOrder(1l);

		// TODO "admin" change to current user
		assertEquals("admin", newOrder.getAuthor());
	}

	/**
	 * When: new Suborder add in Order Than: Suborder add in open order and
	 * order only update
	 */
	@Test
	public void testAddNewSuborder() {
		Order order = new Order();
		stub(orderDao.find(any(Long.class))).toReturn(order);
		order = orderProcess.addSuborder(1l);

		verify(orderDao).update(order);
		assertNotNull("New Suborder not null", order);
	}

	/**
	 * When: Order to close Than: Order status is close
	 */
	@Test
	public void testCloseOrder() {
		Order order = new Order();
		stub(orderDao.find(any(Long.class))).toReturn(order);
		Order closeOrder = orderProcess.closeOrder(order.getId(), money(10.00));

		assertEquals(Order.STATUS_CLOSE_DATA, closeOrder.getStatus());
	}

	/**
	 * When: Table set in order Than: Table is not null in Order
	 */
	@Test
	public void testIfFindTableId() {
		DinnerTable table = new DinnerTable();
		table.setName("Tim");
		stub(tableDao.find(any(Long.class))).toReturn(table);
		Order newOrder = orderProcess.newOrder(1l);

		assertNotNull(newOrder.getTable());
		assertEquals("Tim", newOrder.getTable().getName());
		// TODO equals if tableId correctly
	}

	/**
	 * When: TableDao throws an exception Than: Error should be logged.
	 * orderProcess should return null instead of order
	 */
	@Test
	public void testIfTableDaoThrowsException() {
		stub(tableDao.find(any(Long.class))).toThrow(
				new org.hibernate.exception.SQLGrammarException(null, null));
		Order newOrder = orderProcess.newOrder(1l);
		assertNull(newOrder);
	}

}
