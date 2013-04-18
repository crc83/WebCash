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
	
	/**
	 * @param idTable
	 * @return new Order with one Suborder
	 */
	public Order newOrder(Long idTable) {
		DinnerTable table;
		try {
			table = tableDao.find(idTable);
		} catch (Exception e){
			//we can't find table for some reason
			LOGGER.info("Can't find table in database",e);
			return null;
		}
		Order newOrder = new Order();
		Date workingDate = new Date(); 
		newOrder.setOpenDate(workingDate);
//		TODO "admin" change to current user
		newOrder.setAuthor("admin");
		newOrder.setDailyId(orderDao.conutDailyId(workingDate)+1);
		newOrder.setStatus(Order.STATUS_OPEN_DATA);
		newOrder.setWorkShift(5l);
		newOrder.setTable(table);
		newOrder.addSuborder(new Suborder(1));
		
		orderDao.add(newOrder);
		return newOrder;
	}
	
	/**
	 * @param orderId
	 * @return new suborder add in open order and doesn't create new Order
	 */
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
	
	/**
	 * @param orderId
	 * @param paid
	 * @return closing of open order
	 */
	public Order closeOrder(Long orderId, BigDecimal paid){
		Order closeOrder = orderDao.find(orderId);
		Date closeDate = new Date();
		closeOrder.setCloseDate(closeDate);
		closeOrder.setStatus(Order.STATUS_CLOSE_DATA);
		closeOrder.setPaid(paid);
		
		orderDao.update(closeOrder);
		return closeOrder;
	}

}
