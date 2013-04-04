package com.siriusif.service.model.impl;

import java.util.List;

import java.util.Date;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.siriusif.model.Order;
import com.siriusif.service.HibernateDaoImpl;
import com.siriusif.service.model.OrderDao;

/**
 * @author Администратор
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
		Order o = super.find(key);
		initializeCollection(o.getSuborders());
		return o;
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
	public List<Order> listForTableId(long tableId) {
		// TODO SB : Implement
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.siriusif.service.model.OrderDao#countOpenedForTableId(long)
	 */
	@Override
	public int countOpenedForTableId(long tableId) {
		// TODO SB : Implement
		return 0;
	}

	/*
	 * SQL query: SELECT order_id, COUNT(order_id) FROM suborder GROUP BY order_id HAVING
	 * order_id=163840
	 * 
	 * SELECT o, COUNT(s) FROM Order o JOIN o.suborders s GROUP BY o HAVING id:
	 * orderId
	 */
	@Override
	public int countOfSuborders(long orderId) {
		return currentSession()
				.createQuery(
						"SELECT o, COUNT(s) FROM Order o JOIN o.suborders s GROUP BY o HAVING o.id = :orderId")
				.setParameter("orderId", orderId).list().size()+1;
	}

}
