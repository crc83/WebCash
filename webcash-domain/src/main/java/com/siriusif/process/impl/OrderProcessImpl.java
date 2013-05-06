package com.siriusif.process.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.siriusif.model.DinnerTable;
import com.siriusif.model.Good;
import com.siriusif.model.Order;
import com.siriusif.model.Sale;
import com.siriusif.model.Suborder;
import com.siriusif.model.Workshift;
import com.siriusif.process.OrderProcess;
import com.siriusif.process.WorkshiftProcess;
import com.siriusif.service.model.DinnerTableDao;
import com.siriusif.service.model.GoodDao;
import com.siriusif.service.model.OrderDao;
import com.siriusif.service.model.SaleDao;
import com.siriusif.service.model.SuborderDao;

import static com.siriusif.model.helpers.TestHelper.amount;

@Component(value="orderProcess")
public class OrderProcessImpl implements OrderProcess {

	private static Logger LOGGER = Logger.getLogger(OrderProcessImpl.class);

	@Autowired
	private OrderDao orderDao;

	@Autowired
	private DinnerTableDao tableDao;
	
	@Autowired
	private GoodDao goodDao;
	
	@Autowired
	private SaleDao saleDao;
	
	@Autowired
	private SuborderDao suborderDao;

	@Autowired
	private WorkshiftProcess workshiftProcess;

	/**
	 * @param idTable
	 * @return new Order with one Suborder
	 */
	@Override
	public Order newOrder(Long idTable) {
		Workshift currentWorkshift = workshiftProcess.getOpenWorkshift();
		if (currentWorkshift == null){
			LOGGER.debug("We have some troubles with getting new workshift for some reason");
			return null;
		}
		DinnerTable table;
		try {
			table = tableDao.find(idTable);
		} catch (Exception e) {
			// we can't find table for some reason
			LOGGER.info("We are waiting stackTrase here.");
			LOGGER.info("Can't find table in database", e);
			return null;
		}
		Order newOrder = new Order();
		Date workingDate = new Date();
		newOrder.setOpenDate(workingDate);
		// TODO "admin" change to current user
		newOrder.setAuthor("admin");
		newOrder.setDailyId(orderDao.conutDailyId(workingDate) + 1);
		newOrder.setStatus(Order.STATUS_OPEN_DATA);
		newOrder.setWorkShift(currentWorkshift);
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
		suborder.setIndex(orderDao.countOfSuborders(orderId) + 1);
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
	public Order closeOrder(Long orderId, BigDecimal paid) {
		Order closeOrder = orderDao.find(orderId);
		Date closeDate = new Date();
		closeOrder.setCloseDate(closeDate);
		closeOrder.setStatus(Order.STATUS_CLOSE_DATA);
		closeOrder.setPaid(paid);

		orderDao.update(closeOrder);
		return closeOrder;
	}
	
	@Override
	public Order addGoodsToOrder(Long goodId, Long orderId){
		Sale sale = new Sale();
		Good good = goodDao.find(goodId);
		Order order = orderDao.find(orderId);
		sale.setAmount(amount(1.00));
		sale.setSalesGood(good);
		order.getSuborders().get(0).addSale(sale);
		
		orderDao.update(order);
		return order;
	}

	@Override
	public int countOpenedForTableId(long tableId) {
		return orderDao.countOpenedForTableId(tableId);
	}

	@Override
	public List<Order> listForTableId(long tableId) {
		return orderDao.listForTableId(tableId);
	}

	@Override
	public Order getOrder(long orderId) {
		return orderDao.find(orderId);
	}

}
