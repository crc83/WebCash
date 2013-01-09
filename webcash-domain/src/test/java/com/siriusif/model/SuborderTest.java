package com.siriusif.model;

import static com.siriusif.model.helpers.SaleBuiledr.buildSale;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

public class SuborderTest {

	@Test
	public void testSaleAddToSuborder() {
		Suborder suborder = new Suborder();
		Sale sale = new Sale();
		
		suborder.addSale(sale);
		
		assertEquals(1, suborder.getSales().size());	
	}
	
	@Test
	public void testSaleAddToSuborderWithIndex() {
		Suborder suborder = new Suborder(2);
		Sale sale = new Sale();
		
		suborder.addSale(sale);
		
		assertEquals(1, suborder.getSales().size());	
	}
	
	@Test
	public void testTotal() {
		Order order = new Order();
		
		Suborder first = new Suborder(1);
		
		first.addSale(buildSale("Юшка грибна", 0.280, 12.50));
		first.addSale(buildSale("Салат домашній", 0.280, 12.00));
		first.addSale(buildSale("М'ясо по французьки", 0.200, 20.00));
		first.addSale(buildSale("Картопля молода з зеленню", 0.200, 8.00));
		order.addSuborder(first);
		
		assertEquals(12.46, first.getTotal(), 0.01);	
	}


}
