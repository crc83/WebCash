package com.siriusif.managed.bean;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import com.siriusif.model.Order;
import com.siriusif.process.OrderProcess;
import com.siriusif.service.model.OrderDao;
import static org.mockito.Mockito.*;

public class OrdersListBeanTest {
	
	
	private OrdersListBean ordersList = new OrdersListBean();
	private OrderDao mockedOrderDao;
	private OrderProcess mockedOrderProcess;

	@Before
	public void setUp() throws Exception {
		mockedOrderDao = mock(OrderDao.class);
		mockedOrderProcess = mock(OrderProcess.class);
		ordersList.setOrderDao(mockedOrderDao);
		ordersList.setOrderProcess(mockedOrderProcess);
	}

	/** 
	 * Given : After click on some table on Hall page I landed at OrderList
	 * When : There are opened orders for table
	 * Then : I will remain on this page
	 */
	@Test
	public void testIfThereAreOrdersForTable() {
		stub(mockedOrderDao.countOpenedForTableId(32)).toReturn(42);
		assertEquals("", ordersList.urlToNewOrderIfNoOrdersForTable("32"));
	}

	/** 
	 * Given : After click on some table on Hall page I landed at OrderList
	 * When : There are no opened orders for table but creation of order fails
	 * 		  and orderProcess returned "null" instead of new order
	 * Then : I will remain on this page
	 */
	@Test
	public void testIfCreationOfOrderFails() {
		stub(mockedOrderProcess.newOrder()).toReturn(null);
		assertEquals("", ordersList.urlToNewOrderIfNoOrdersForTable("32"));
	}
	
	/** 
	 * Given : After click on some table on Hall page I landed at OrderList
	 * When : There are no opened orders for table
	 * Then : I will be redireced to NewOrder page
	 */
	@Test
	public void testIfThereAreNoOrders() {
		Order order = new Order();
		order.setId(13l);
		stub(mockedOrderProcess.newOrder()).toReturn(order);
		stub(mockedOrderDao.countOpenedForTableId(32)).toReturn(0);
		assertEquals("order.jsf?order_id=13", ordersList.urlToNewOrderIfNoOrdersForTable("32"));
	}

	/**
	 * When : I try to change orders for table through managed bean
	 * Then : I can't do it. This list is read only
	 */
	@Test
	public void testSetOrdersForTable() {	
		stub(mockedOrderDao.listForTableId(any(Long.class))).toReturn(null);
		ordersList.setOrdersForTable(new ArrayList<Order>());
		assertNull("this property should be read only",ordersList.getOrdersForTable());
	}

}
