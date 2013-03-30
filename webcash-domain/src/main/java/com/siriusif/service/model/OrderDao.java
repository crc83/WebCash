package com.siriusif.service.model;

import java.util.List;

import com.siriusif.model.Order;
import com.siriusif.service.GenericDao;

public interface OrderDao extends GenericDao<Order, Long> {

	List<Order> listForTableId(long tableId);
	
	/**
	 * Counts opened orders for table id.<br>
	 * @param tableId id of table
	 * @return count of OPENED orders for table 
	 */
	int countOpenedForTableId(long tableId);

}
