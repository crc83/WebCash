package com.siriusif.service.model;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.siriusif.helper.Helper;
import com.siriusif.model.Hall;
import com.siriusif.model.TablesHall;

public class HallDaoImplTest extends AbstractDaoImplTest{
	
	private static Logger LOGGER = Logger.getLogger(HallDaoImplTest.class);
	
	@Autowired
	private HallDao hallDao;
	
	@Test
	public void testAdd() {
		int size = hallDao.list().size();
		
		Hall hall = new Hall();
		hall.setName("bar");
		hallDao.add(hall);
		
		assertTrue (size < hallDao.list().size());
	}
	
	@Test
	public void testManyToOneHallTables(){
		int size = hallDao.list().size();
		Hall hall = new Hall();

		hall.addTable(new TablesHall("Nic", "Nikky", 50, 50));
		hall.addTable(new TablesHall("Tom", "Tommy", 100, 100));
		hall.addTable(new TablesHall("Tok", "Tokky", 150, 150));
		hall.addTable(new TablesHall("Nock", "Nokky", 200, 200));
		hall.addTable(new TablesHall("Tik", "Tikky", 250, 250));
		hall.setName("bancket");
		
		hallDao.add(hall);

		assertTrue (size < hallDao.list().size());
		Hall haFromDb = hallDao.find(hall.getId());
		assertEquals("bancket", haFromDb.getName());
		assertEquals(5, haFromDb.getTables().size());
	}
	
	/*
	 * Given : I add hall with three tables to DB
	 * When  : ...
	 * Then  : I should have three tables in hall
	 */
	@Test
	public void testAddHallWithTables()throws IOException{	
		LOGGER.info("Getting data from JSON");
		Hall hall = Helper.fromJsonHall("/hall.json");
		LOGGER.info("Saving data to DB");
		hallDao.add(hall);
		LOGGER.info("Getting data from DB");
		Hall hallFromDb = hallDao.find(hall.getId());
		assertEquals("Test hall", hallFromDb.getName());
		List<TablesHall> tablesFromDB = hallFromDb.getTables();
		assertEquals("Not all tables read for Hall", 3, tablesFromDB.size());
		//let's validate all fields for second table
		TablesHall table = tablesFromDB.get(1);
		assertEquals("Table2", table.getName());
		assertEquals("The second table", table.getDescription());
		assertEquals(200, table.getTop());
		assertEquals(341, table.getLeft());
		assertEquals(42, table.getHeight());
		assertEquals(37, table.getWidth());
	}

}
