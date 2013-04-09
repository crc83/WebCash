package com.siriusif.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity
@Table(name = "`suborder`")
public class Suborder {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Long id;
	

	/**
	 * index of suborder in order
	 */
	@Column(name = "`index`", nullable = true)
	private int index;
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy = "suborder")
	private List<Sale> sales;
	
	@ManyToOne
	@JoinColumn(name = "`order_id`")
	private Order order;

	public Suborder() {
		sales = new ArrayList<Sale>();
	}

	public Suborder(int index) {
		this();
		this.index = index;
	}

	public void addSale(Sale sale) {
		sale.setSuborder(this);
		sales.add(sale);
	}

	/**
	 * Sum of sales (not including discount) in suborder
	 * @return total sum of suborder
	 */
	public BigDecimal getTotal() {
		// TODO change from double to Currency
		BigDecimal sum = BigDecimal.ZERO;
		for (Sale s : sales) {
			sum = sum.add(s.getCalculatedSum()).setScale(2, BigDecimal.ROUND_HALF_UP);
		}
		return sum;
	}

	/* autogenerated stuff */

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public List<Sale> getSales() {
		return sales;
	}

	// public void setSales(List<Sale> sales) {
	// this.sales = sales;
	// }

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Long getId() {
		return id;
}
	
	public void setId(Long id) {
		this.id = id;
	}
}
