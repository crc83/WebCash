package com.siriusif.service.model;

import java.util.List;

import java.util.Date;

import com.siriusif.model.DinnerTable;
import com.siriusif.model.Order;
import com.siriusif.service.GenericDao;

public interface OrderDao extends GenericDao<Order, Long> {

	int conutDailyId(Date workingDate);

	List<Order> listForTableId(DinnerTable table);
	
	/**
	 * Counts opened orders for table id.<br>
	 * @param tableId id of table
	 * @return count of OPENED orders for table 
	 */
	int countOpenedForTable(DinnerTable table);
	
	/**
	 * @param orderId of Order
	 * @return count suborders of order
	 */
	int countOfSuborders(long orderId);

}
