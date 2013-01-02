package com.siriusif.model;

import java.util.ArrayList;
import java.util.List;

public class Suborder {
	private int index;
	private List<Sale> sales;
	private Order order;

	public Suborder(){
		sales = new ArrayList<Sale>();
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public List<Sale> getSales() {
		return sales;
	}

	public void setSales(List<Sale> sales) {
		this.sales = sales;
	}

	public void addSale(Sale sale) {
		sale.setSuborder(this);
		sales.add(sale);
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}



}
