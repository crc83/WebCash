package com.siriusif.process;

import java.math.BigDecimal;
import java.util.List;

import com.siriusif.model.Order;

public interface OrderProcess {
	
	int countOpenedForTableId(long TableId);

	Order newOrder(Long idTable);
	
	List<Order> listForTableId(long tableId);

	Order getOrder(long orderId);

	Order addGoodsToOrder(Long goodId, Long orderId, Long suborderId);

	Order addSuborder(long orderId);

	int countOfSuborders(long orderId);

	void uptadeSale(long saleId, BigDecimal newAmount);

	Order closeOrder(Long orderId, BigDecimal paid);

}
