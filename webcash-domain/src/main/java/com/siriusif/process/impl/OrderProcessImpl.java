package com.siriusif.process.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.siriusif.model.DinnerTable;
import com.siriusif.model.Good;
import com.siriusif.model.Order;
import com.siriusif.model.Sale;
import com.siriusif.model.Suborder;
import com.siriusif.process.OrderProcess;
import com.siriusif.process.WorkshiftProcess;
import com.siriusif.service.model.DinnerTableDao;
import com.siriusif.service.model.GoodDao;
import com.siriusif.service.model.OrderDao;
import com.siriusif.service.model.SaleDao;
import com.siriusif.service.model.SuborderDao;

import static com.siriusif.model.helpers.TestHelper.amount;

/**
 * @author csurudin
 * 
 */
@Component(value = "orderProcess")
public class OrderProcessImpl implements OrderProcess {

	private static Logger LOGGER = Logger.getLogger(OrderProcessImpl.class);

	@Autowired
	private OrderDao orderDao;

	@Autowired
	private SuborderDao suborderDao;

	@Autowired
	private SaleDao saleDao;

	@Autowired
	private DinnerTableDao tableDao;

	@Autowired
	private GoodDao goodDao;

	@Autowired
	private WorkshiftProcess workshiftProcess;

	/**
	 * @param idTable
	 * @return new Order with one Suborder
	 */
	@Override
	public Order newOrder(Long idTable) {
		// Workshift currentWorkshift = workshiftProcess.getOpenWorkshift();
		// if (currentWorkshift == null){
		// LOGGER.debug("We have some troubles with getting new workshift for some reason");
		// return null;
		// }
		DinnerTable table;
		try {
			table = tableDao.find(idTable);
		} catch (Exception e) {
			// we can't find table for some reason
			LOGGER.info("Can't find table by id=" + idTable);
			LOGGER.debug("Caused by:", e);
			return null;
		}
		Order newOrder = new Order();
		Date workingDate = new Date();
		newOrder.setOpenDate(workingDate);
		// TODO "admin" change to current user
		newOrder.setAuthor("admin");
		newOrder.setDailyId(orderDao.conutDailyId(workingDate) + 1);
		newOrder.setStatus(Order.STATUS_OPEN_DATA);
		// newOrder.setWorkShift(currentWorkshift);
		newOrder.setTable(table);
		newOrder.addSuborder(new Suborder(1));

		orderDao.add(newOrder);
		return newOrder;
	}

	/**
	 * @param orderId
	 * @return new suborder add in open order and doesn't create new Order
	 */
	@Override
	public Order addSuborder(long orderId) {
		Order order = orderDao.find(orderId);
		Suborder suborder = new Suborder();
		suborder.setIndex(orderDao.countOfSuborders(orderId) + 1);
		LOGGER.debug("Order: " + order);
		LOGGER.debug("Suborder: " + suborder);
		order.addSuborder(suborder);

		orderDao.update(order);
		return order;
	}

	/**
	 * @param orderId
	 * @param paid
	 * @return closing of open order
	 */
	public Order closeOrder(Long orderId, BigDecimal paid, boolean isCard) {
		Order closeOrder = orderDao.find(orderId);
		Date closeDate = new Date();
		closeOrder.setCloseDate(closeDate);
		closeOrder.setStatus(Order.STATUS_CLOSE_DATA);
		closeOrder.setPaid(paid);
		closeOrder.setCard(isCard);

		orderDao.update(closeOrder);
		return closeOrder;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.siriusif.process.OrderProcess#addGoodsToOrder(java.lang.Long,
	 * java.lang.Long)
	 * Add goods to Order
	 */
	@Override
	@Transactional
	public Order addGoodsToOrder(Long goodId, Long orderId, Long suborderId) {
		Sale sale = new Sale();
		Good good = goodDao.find(goodId);
		Suborder suborder = suborderDao.find(suborderId);
		Order order = orderDao.find(orderId);
		sale.setAmount(amount(1.00));
		sale.setSalesGood(good);
		order.getSuborders().get(suborder.getIndex() - 1).addSale(sale);

		orderDao.update(order);
		return order;
	}

	@Override
	public int countOpenedForTableId(long tableId) {
		int result = Integer.MAX_VALUE;
		try {
			DinnerTable table = tableDao.find(tableId);
			if (table != null) {
				result = orderDao.countOpenedForTable(table);
			}
		} catch (Exception e) {
			LOGGER.info("Error while counting orders for tableId=" + tableId);
			LOGGER.debug("Caused by", e);
		}
		return result;
	}

	@Override
	public void uptadeSale(long saleId, BigDecimal newAmount) {
		Sale sale = saleDao.find(saleId);
		sale.setAmount(newAmount);
		saleDao.update(sale);
	}

	@Override
	public void deleteSale(long saleId) {
		Sale sale = saleDao.find(saleId);
		saleDao.remove(sale);
	}

	@Override
	public List<Order> listForTableId(long tableId) {
		DinnerTable table = tableDao.find(tableId);
		return orderDao.listForTableId(table);
	}

	@Override
	public Order getOrder(long orderId) {
		return orderDao.find(orderId);
	}

	@Override
	public int countOfSuborders(long orderId) {
		return orderDao.countOfSuborders(orderId);
	}

}
