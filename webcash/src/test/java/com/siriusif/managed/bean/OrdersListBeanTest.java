package com.siriusif.managed.bean;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import com.siriusif.model.Order;
import com.siriusif.process.OrderProcess;
import static org.mockito.Mockito.*;

public class OrdersListBeanTest {
	
	
	private OrdersListBean ordersList = new OrdersListBean();
	private OrderProcess mockedOrderProcess;

	@Before
	public void setUp() throws Exception {
		mockedOrderProcess = mock(OrderProcess.class);
		ordersList.setOrderProcess(mockedOrderProcess);
	}

	/** 
	 * Given : After click on some table on Hall page I landed at OrderList
	 * When : There are opened orders for table
	 * Then : I will remain on this page
	 */
	@Test
	public void testIfThereAreOrdersForTable() {
		stub(mockedOrderProcess.countOpenedForTableId(32)).toReturn(42);
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
		stub(mockedOrderProcess.newOrder(1l)).toReturn(null);
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
		stub(mockedOrderProcess.newOrder(any(Long.class))).toReturn(order);
		stub(mockedOrderProcess.countOpenedForTableId(32)).toReturn(0);
		assertEquals("order.jsf?order_id=13", ordersList.urlToNewOrderIfNoOrdersForTable("32"));
	}

	/**
	 * When : I try to change orders for table through managed bean
	 * Then : I can't do it. This list is read only
	 */
	@Test
	public void testSetOrdersForTable() {	
		stub(mockedOrderProcess.listForTableId(any(Long.class))).toReturn(null);
		ordersList.setOrdersForTable(new ArrayList<Order>());
		assertNull("this property should be read only",ordersList.getOrdersForTable());
	}

}
