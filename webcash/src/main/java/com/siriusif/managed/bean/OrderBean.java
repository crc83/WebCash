package com.siriusif.managed.bean;

import javax.faces.bean.ManagedBean;

import com.siriusif.model.Good;
import com.siriusif.model.Order;
import com.siriusif.model.Sale;
import com.siriusif.model.Suborder;
import static com.siriusif.model.helpers.SaleBuiledr.*;

@ManagedBean(name="orderBean")
public class OrderBean {
	private Order order;
	

	public OrderBean(){
		orderView();
	}

	private void orderView() {
		order = new Order();
		
		Suborder first = new Suborder(1);
		
		first.addSale(buildSale("Юшка грибна", 280, 12.50));
		first.addSale(buildSale("Салат домашній", 280, 12.00));
		first.addSale(buildSale("М'ясо по французьки", 200, 20.00));
		first.addSale(buildSale("Картопля молода з зеленню", 200, 8.00));
		order.addSuborder(first);

		Suborder second = new Suborder(2);
		second.addSale(buildSale("Смалець", 100, 8.00));
		second.addSale(buildSale("Сметана", 50, 4.00));
		second.addSale(buildSale("Фреш", 200, 16.00));
		order.addSuborder(second);

		Suborder third = new Suborder(3);
		third.addSale(buildSale("Хліб", 10, 0.50));
		third.addSale(buildSale("Кава Еспрессо", 40, 9.00));
		third.addSale(buildSale("Штрудель", 150, 14.00));
		order.addSuborder(third);
	}

	public Order getOrder() {
		return order;
	}

}
