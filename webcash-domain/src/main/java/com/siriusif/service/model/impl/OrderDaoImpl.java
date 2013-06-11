package com.siriusif.service.model.impl;

import java.util.Collections;
import java.util.List;

import java.util.Date;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.siriusif.model.DinnerTable;
import com.siriusif.model.Order;
import com.siriusif.service.HibernateDaoImpl;
import com.siriusif.service.model.DinnerTableDao;
import com.siriusif.service.model.OrderDao;

/**
 * @author colyas
 * 
 */
@Repository("orderDao")
public class OrderDaoImpl extends HibernateDaoImpl<Order, Long> implements
		OrderDao {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.siriusif.service.HibernateDaoImpl#find(java.io.Serializable)
	 */
	@Override
	@Transactional
	public Order find(Long key) {
		Order order = super.find(key);
		initializeCollection(order.getSuborders());
		return order;
	}

	@Override
	public int conutDailyId(Date workingDate) {
		// TODO CS : Implement it later
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.siriusif.service.model.OrderDao#listForTableId(long)
	 */
	@Override
	public List<Order> listForTableId(DinnerTable table) {
		@SuppressWarnings("rawtypes")
		List rawOrders = currentSession()
				.createQuery(
						"SELECT o FROM Order o WHERE o.status=:status AND o.table = :table")
				.setParameter("status", Order.STATUS_OPEN_DATA)
				.setParameter("table", table).list();
		@SuppressWarnings("unchecked")
		List<Order> orders = Collections.checkedList(rawOrders, Order.class);

		return orders;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.siriusif.service.model.OrderDao#countOpenedForTableId(long)
	 */
	public int countOpenedForTable(DinnerTable table) {
		return currentSession()
				.createQuery(
						"SELECT o FROM Order o WHERE o.status=:status AND o.table = :table")
				.setParameter("status", Order.STATUS_OPEN_DATA)
				.setParameter("table", table).list().size();
	}

	/*
	 * SQL query: SELECT order_id, COUNT(order_id) FROM suborder GROUP BY
	 * order_id HAVING
	 * order_id=163840
	 * 
	 * SELECT o, COUNT(s) FROM Order o JOIN o.suborders s GROUP BY o HAVING id:
	 * orderId
	 */
	@Override
	public int countOfSuborders(long orderId) {
		return currentSession()
				.createQuery(
						"SELECT o, s FROM Order o JOIN o.suborders s WHERE o.id = :orderId")
				.setParameter("orderId", orderId).list().size();
	}

}
