package com.siriusif.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class CheckTest {

	@Test
	public void testValidationCheck() {
		Check check = new Check();
		
		check.setAutor("User");
		check.setTableNum(1);
		check.setWorkShift(1);
		
		assertTrue(check.isValid());
	}
	
	@Test
	public void testInvalidationCheck() {
		Check check = new Check();
		
		assertFalse(check.isValid());
	}

}
