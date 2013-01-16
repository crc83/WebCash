package com.siriusif.service.model;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.siriusif.model.Order;

public class OrderDaoImplTest extends AbstractDaoImplTest{
	
	@Autowired
	private OrderDao orderDao;

	@Test
	public void testAdd() {
		int size = orderDao.list().size();
		
		Order order = new Order();
		order.setAutor("admin");
		orderDao.add(order);
		
		assertTrue (size < orderDao.list().size());
	}

}
