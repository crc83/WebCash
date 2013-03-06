package com.siriusif.service.model;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.siriusif.helper.Helper;
import com.siriusif.model.Order;
import com.siriusif.model.Suborder;

public class OrderDaoImplTest extends AbstractDaoImplTest{
	
	@Autowired
	private OrderDao orderDao;

	@Test
	public void testAdd() {
		int size = orderDao.list().size();
		
		Order order = new Order();
		order.setAuthor("admin");
		order.setCard(true);
		order.setDailyId(size);
		order.setDiscount(5);
		order.setNomerok(2);
		order.setPayed(BigDecimal.valueOf(13.51));
		order.setReadOnly(true);
		order.setSum(BigDecimal.valueOf(14,56));
		order.setTableNum(5);
		order.setType(false);
		order.setWorkShift(5l);
		orderDao.add(order);
		
		assertTrue (size < orderDao.list().size());
	}
	
	@Test
	public void testAddWorkshiftDate() {
		Order order = new Order();
		order.setAuthor("admin");
		order.setPayed(BigDecimal.valueOf(13.51));
		order.setSum(BigDecimal.valueOf(14,56));
		order.setWorkShift(5l);
		orderDao.add(order);
		Date workingDate = Helper.stringToDate("22/01/2013");
		order.setWorkingDate(workingDate);
		orderDao.update(order);
		
		Order orFromDB = orderDao.find(order.getId());
		assertEquals(workingDate, orFromDB.getWorkingDate());
	}
	
	@Test
	public void testSetSumma(){
		int size = orderDao.list().size();
		
		Order order = new Order();
		order.setSum(BigDecimal.valueOf(15.25));
		order.setAuthor("admin");
		order.setPayed(BigDecimal.valueOf(13.51));
		order.setWorkShift(5l);
		order.setDailyId(size);
		orderDao.add(order);
		Order orFromDB = orderDao.find(order.getId());
		
		assertEquals(BigDecimal.valueOf(1525, 2), orFromDB.getSum());
		assertTrue (size < orderDao.list().size());
	}
	
	@Test
	public void testOneToMenyOrderSuborders(){
		int size = orderDao.list().size();
		Order order = new Order();
		
		order.addSuborder(new Suborder(1));
		order.addSuborder(new Suborder(2));
		order.addSuborder(new Suborder(3));
		order.addSuborder(new Suborder(5));
		order.setTableNum(8);
		order.setSum(BigDecimal.valueOf(15.25));
		order.setAuthor("admin");
		order.setPayed(BigDecimal.valueOf(13.51));
		order.setWorkShift(5l);
		order.setDailyId(size);
		orderDao.add(order);
		
		assertTrue (size < orderDao.list().size());
		Order orFromDB = orderDao.find(order.getId());
		assertEquals(8, orFromDB.getTableNum());
		assertEquals(4, orFromDB.getSuborders().size());
	}

	
	private BufferedReader getCPFileReader(String fileName)
			throws UnsupportedEncodingException {
		InputStream in = this.getClass().getResourceAsStream(fileName);
		Reader reader = new InputStreamReader(in, "UTF-8");
		BufferedReader bufferedReader = new BufferedReader(reader);
		return bufferedReader;
	}

	@Test
	public void testReadJson() throws IOException{		
		Order order = new Gson().fromJson(getCPFileReader("/order.json"), Order.class);
		orderDao.add(order);
	}

}
