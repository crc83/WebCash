package com.siriusif.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.siriusif.helper.ModelFactory;

public class DinnerTableTest {

	@Test
	public void testGetBottom(){
		DinnerTable table = ModelFactory.getDinnerTableInstance();
		assertEquals(47+37, table.getBottom());
	}
	
	@Test
	public void testGetRight(){
		DinnerTable table = ModelFactory.getDinnerTableInstance();
		assertEquals(31+88, table.getRight());
	}
}
