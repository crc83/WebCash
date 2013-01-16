package com.siriusif.service.model.impl;

import org.springframework.stereotype.Repository;

import com.siriusif.model.Order;
import com.siriusif.service.HibernateDaoImpl;
import com.siriusif.service.model.OrderDao;

@Repository("orderDao")
public class OrderDaoImpl extends HibernateDaoImpl<Order, Long> implements OrderDao{

}

