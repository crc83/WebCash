package com.siriusif.service.model.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.siriusif.model.Order;
import com.siriusif.model.Suborder;
import com.siriusif.service.HibernateDaoImpl;
import com.siriusif.service.model.OrderDao;

@Repository("orderDao")
public class OrderDaoImpl extends HibernateDaoImpl<Order, Long> implements OrderDao{
	
	/* (non-Javadoc)
	 * @see com.siriusif.service.HibernateDaoImpl#find(java.io.Serializable)
	 */
	@Override
	@Transactional
	public Order find(Long key) {
		Order o = super.find(key); 
		initializeCollection(o.getSuborders());
		return o; 
	}
	

}

