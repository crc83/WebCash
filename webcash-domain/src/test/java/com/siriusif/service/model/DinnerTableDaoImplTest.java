package com.siriusif.service.model;

import static org.junit.Assert.*;

import java.io.UnsupportedEncodingException;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.siriusif.helper.Helper;
import com.siriusif.helper.AbstractSpringTest;
import com.siriusif.model.DinnerTable;
import com.siriusif.model.Order;

import static com.siriusif.model.helpers.SaleBuiledr.money;

public class DinnerTableDaoImplTest extends AbstractSpringTest {

	@Autowired
	private DinnerTableDao tablesDao;

	/*
	 * When : I add table to db Then : 1) I should see that number of entities
	 * in DB increased 2) I should read all fields from DB correctly
	 */
	@Test
	public void testAdd() throws JsonSyntaxException, JsonIOException,
			UnsupportedEncodingException {
		int size = tablesDao.list().size();
		DinnerTable table = Helper.fromJson("/tables.json", DinnerTable.class);
		tablesDao.add(table);
		assertTrue(size < tablesDao.list().size());
		DinnerTable tableFromDB = tablesDao.find(table.getId());

		assertEquals("administrator", tableFromDB.getName());
		assertEquals("leftTable", tableFromDB.getDescription());
		assertEquals(50, tableFromDB.getTop());
		assertEquals(60, tableFromDB.getLeft());
		assertEquals(123, tableFromDB.getHeight());
		assertEquals(321, tableFromDB.getWidth());
	}
	
	@Test
	public void testManyToOneTableOrders(){
		int size = tablesDao.list().size();
		DinnerTable table = new DinnerTable();

		Order order = new Order(); 
		order.setAuthor("admin");
		order.setPaid(money(13.51));
		table.setName("table");
		table.addOrder(order);
		
		Order order2 = new Order();
		order2.setAuthor("sonya");
		order2.setPaid(money(15.55));
		table.addOrder(order2);
		
		Order order3 = new Order();
		order3.setAuthor("sanya");
		order3.setPaid(money(25.51));
		table.addOrder(order3);
		
		tablesDao.add(table);
		
		assertTrue(size < tablesDao.list().size());
		DinnerTable tableFromDb = tablesDao.find(table.getId());
		assertEquals(3, tableFromDb.getOrders().size());
		assertEquals("admin", tableFromDb.getOrders().get(0).getAuthor());
	}
}
