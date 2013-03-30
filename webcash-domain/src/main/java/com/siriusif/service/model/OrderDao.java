package com.siriusif.service.model;

import java.util.Date;

import com.siriusif.model.Order;
import com.siriusif.service.GenericDao;

public interface OrderDao extends GenericDao<Order, Long> {

	int conutDailyId(Date workingDate);

}
