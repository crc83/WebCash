package com.siriusif.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TableTest {

	@Test
	public void testGetBottom(){
		TablesHall table = new TablesHall();
		table.setTop(42);
		table.setHeight(37);
		assertEquals(42+37, table.getBottom());
	}
	
	@Test
	public void testGetRight(){
		TablesHall table = new TablesHall();
		table.setLeft(31);
		table.setWidth(88);
		assertEquals(31+88, table.getRight());
	}
}
