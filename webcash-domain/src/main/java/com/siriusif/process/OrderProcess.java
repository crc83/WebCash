package com.siriusif.process;

import java.util.List;

import com.siriusif.model.Order;

public interface OrderProcess {
	
	int countOpenedForTableId(long TableId);

	Order newOrder(Long idTable);
	
	List<Order> listForTableId(long tableId);

	Order getOrder(long orderId);

}
