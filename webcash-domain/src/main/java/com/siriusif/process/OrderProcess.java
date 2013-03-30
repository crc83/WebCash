package com.siriusif.process;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.siriusif.model.Order;
import com.siriusif.model.Suborder;
import com.siriusif.service.model.OrderDao;

@Component
public class OrderProcess {
	
	@Autowired
	private OrderDao orderDao;
	
	public Order newOrder() {
		Order newOrder = new Order();
		Date workingDate = new Date(); 
		newOrder.setDate(workingDate);
		newOrder.setAuthor("admin");
		newOrder.setDailyId(orderDao.conutDailyId(workingDate)+1);
		newOrder.setWorkShift(5l);
		newOrder.addSuborder(new Suborder(1));
		
		orderDao.add(newOrder);
		return newOrder;
	}

}
