package com.siriusif.service.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.siriusif.helper.AbstractSpringTest;
import com.siriusif.helper.ModelFactory;
import com.siriusif.model.DinnerTable;
import com.siriusif.model.Order;

public class OrderDinnerTableDaoImplTest extends AbstractSpringTest {

	@Autowired
	private OrderDao orderDao;
	
	@Autowired
	private DinnerTableDao tableDao;
	
	/**
	 * When: we have 4 opened orders for specific tables
	 * Than: we get result of four opened orders
	 */
	@Test
	public void testCountOrdersForTableWhenOnlyOpened() {
		DinnerTable table = ModelFactory.getDinnerTableInstance();
		tableDao.add(table);
		for(int i=0; i<4; i++){
			Order order = new Order();
			order.setAuthor("author");
			order.setDailyId(42);
			order.setTable(table);
			order.setStatus(Order.STATUS_OPEN_DATA);
			orderDao.add(order);
		}
		
		assertEquals(4, orderDao.countOpenedForTable(table));
	}
	
	/**
	 * When: we have 4 _closed_ orders for specific tables
	 * Than: we get result of zero opened orders
	 */
	@Test
	public void testCountOrdersForTableWhenOnlyClosed() {
		DinnerTable table = ModelFactory.getDinnerTableInstance();
		tableDao.add(table);
		for(int i=0; i<4; i++){
			Order order = new Order();
			order.setAuthor("author");
			order.setDailyId(42);
			order.setTable(table);
			order.setStatus(Order.STATUS_CLOSE_DATA);
			orderDao.add(order);
		}
		
		assertEquals(0, orderDao.countOpenedForTable(table));
	}
	
	/**
	 * When: we have 4 _closed_ orders for specific tables
	 * Than: we get result of zero opened orders
	 */
	@Test
	public void testCountOrdersForTable() {
		DinnerTable table = ModelFactory.getDinnerTableInstance();
		tableDao.add(table);
		for(int i=0; i<4; i++){
			Order order = new Order();
			order.setAuthor("author");
			order.setDailyId(42);
			order.setTable(table);
			if (i>=2){
				order.setStatus(Order.STATUS_CLOSE_DATA);
			} else {
				order.setStatus(Order.STATUS_OPEN_DATA);
			}
			orderDao.add(order);
		}
		
		assertEquals(2, orderDao.countOpenedForTable(table));
	}

}
