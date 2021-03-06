package com.siriusif.process;

import static com.siriusif.model.helpers.SaleBuiledr.money;
import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.*;
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
	
//		Order order = orderDao.find(newOrder.getId());
		
		verify(orderDao).add(newOrder);
		
		assertNotNull("..",newOrder);
		assertEquals(Helper.dateOnly(today), 
				Helper.dateOnly(newOrder.getDate()));
		
		assertTrue("We need to have total=0 for new order", newOrder.getTotal().compareTo(money(0))==0);
		assertEquals("We need at least one suborder in new order" ,1, newOrder.getSuborders().size());		
	}
	
	@Test
	public void testNewOrderWithCorrectDailyId() {
		//42
		stub(orderDao.conutDailyId(any(Date.class))).toReturn(42);
		
		Order newOrder = orderProcess.newOrder();
		assertEquals(43,newOrder.getDailyId());
	}
	
	@Test
	public void testAuthorForOrder(){
		Order newOrder = orderProcess.newOrder();
		
//		TODO "admin" change to current user
		assertEquals("admin", newOrder.getAuthor());
	}

}
