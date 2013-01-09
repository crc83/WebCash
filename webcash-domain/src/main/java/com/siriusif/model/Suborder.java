package com.siriusif.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Администратор
 *
 */
/**
 * @author Администратор
 *
 */
/**
 * @author Администратор
 *
 */
/**
 * @author Администратор
 *
 */
/**
 * @author Администратор
 *
 */
public class Suborder {
	private int index;
	private List<Sale> sales;
	private Order order;

	public Suborder(){
		sales = new ArrayList<Sale>();
	}

	public Suborder(int index) {
		this();
		this.index = index;
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

//	public void setSales(List<Sale> sales) {
//		this.sales = sales;
//	}

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
	
	
	/**
	 * @return total suborder
	 */
	public double getTotal(){
		//TODO change from double to Currency
		double sum = 0;
		for(Sale s : sales){
		sum += s.getCalculatedSum();
		}
		return sum;
	}

}
