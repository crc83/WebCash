package com.siriusif.process;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.siriusif.model.DinnerTable;
import com.siriusif.model.Order;
import com.siriusif.model.Suborder;
import com.siriusif.service.model.DinnerTableDao;
import com.siriusif.service.model.OrderDao;

@Component
public class OrderProcess {
	
	private static Logger LOGGER = Logger.getLogger(OrderProcess.class);
	
	@Autowired
	private OrderDao orderDao;
	
	@Autowired
	private DinnerTableDao tableDao;
	
	public Order newOrder(Long idTable) {
		DinnerTable table = tableDao.find(idTable);
		Order newOrder = new Order();
		Date workingDate = new Date(); 
		newOrder.setOpenDate(workingDate);
		newOrder.setAuthor("admin");
		newOrder.setDailyId(orderDao.conutDailyId(workingDate)+1);
		newOrder.setStatus(Order.STATUS_OPEN_DATA);
		newOrder.setWorkShift(5l);
		newOrder.setTable(table);
		newOrder.addSuborder(new Suborder(1));
		
		orderDao.add(newOrder);
		return newOrder;
	}
	
	public Order addSuborder(Long orderId) {
		Order order = orderDao.find(orderId);
		Suborder suborder = new Suborder();
		suborder.setIndex(orderDao.countOfSuborders(orderId)+1);
		LOGGER.info("CS Order: " + order);
		LOGGER.info("CS Suborder: " + suborder);
		order.addSuborder(suborder);
		
		orderDao.update(order);
		return order;
	}
	
	public Order closeOrder(Long orderId, BigDecimal payed){
		Order closeOrder = orderDao.find(orderId);
		Date closeDate = new Date();
		closeOrder.setCloseDate(closeDate);
		closeOrder.setStatus(Order.STATUS_CLOSE_DATA);
		closeOrder.setPayed(payed);
		
		orderDao.update(closeOrder);
		return closeOrder;
	}

}
