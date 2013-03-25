package com.siriusif.process;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.siriusif.helper.AbstractSpringTest;
import com.siriusif.helper.Helper;
import com.siriusif.model.Order;
import com.siriusif.service.model.OrderDao;

public class OrderProcessTest extends AbstractSpringTest {
	
	@Mock
	private OrderDao orderDao;
	
	@InjectMocks
	private OrderProcess orderProcess; 
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testNewOrder() {
		Order newOrder = orderProcess.newOrder();
		
		Date today = new Date();
		
		assertNotNull(newOrder);
		assertEquals(Helper.dateOnly(today), 
				Helper.dateOnly(newOrder.getDate()));
	}

}
