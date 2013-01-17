package com.siriusif.service.model;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.siriusif.helper.Helper;
import com.siriusif.model.Order;

public class OrderDaoImplTest extends AbstractDaoImplTest{
	
	@Autowired
	private OrderDao orderDao;

	@Test
	public void testAdd() {
		int size = orderDao.list().size();
		
		Order order = new Order();
		order.setAutor("admin");
		order.setCard(true);
		order.setDailyId(size);
		order.setDate(Helper.stringToDate("22/01/2013"));
		order.setDiscount(5);
		order.setNomerok(2);
		order.setPayed(BigDecimal.valueOf(13.51));
		order.setReadOnly(true);
		order.setSum(BigDecimal.valueOf(14,56));
		order.setTableNum(5);
		order.setType(false);
		order.setWorkShift(5l);
		orderDao.add(order);
		
		assertTrue (size < orderDao.list().size());
	}

}
