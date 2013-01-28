package com.siriusif.service.model;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.esotericsoftware.yamlbeans.YamlException;
import com.esotericsoftware.yamlbeans.YamlReader;
import com.siriusif.helper.Helper;
import com.siriusif.model.Order;

public class OrderDaoImplTest extends AbstractDaoImplTest{
	
	@Autowired
	private OrderDao orderDao;

	@Test
	public void testAdd() {
		int size = orderDao.list().size();
		
		Order order = new Order();
		order.setAutor("admin");
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
		order.setAutor("admin");
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
		order.setAutor("admin");
		order.setPayed(BigDecimal.valueOf(13.51));
		order.setWorkShift(5l);
		order.setDailyId(size);
		orderDao.add(order);
		Order orFromDB = orderDao.find(order.getId());
		
		assertEquals(BigDecimal.valueOf(1525, 2), orFromDB.getSum());
		assertTrue (size < orderDao.list().size());
	}
	
	@Test
	public void testReadYaml() throws FileNotFoundException, YamlException{
		YamlReader reader = new YamlReader(new FileReader("order.yml"));
		Order order = reader.read(Order.class);
		System.out.println(order.getAutor());
	}

}
