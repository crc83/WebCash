package com.siriusif.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class HallTest {

	@Test
	public void testTablesAddToHall() {
		Hall hall = new Hall();
		Tables tables = new Tables();
		
		hall.addTables(tables);
		
		assertEquals(1, hall.getTables().size());
	}

}
