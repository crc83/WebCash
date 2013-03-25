package com.siriusif.process;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.siriusif.model.Order;
import com.siriusif.service.model.OrderDao;

@Component
public class OrderProcess {
	
	@Autowired
	private OrderDao orderDao;
	
	public Order newOrder() {
		Order order = new Order();
		
		order.setDate(new Date());
		return order;
	}

}
