package com.siriusif.service.model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.siriusif.model.Hall;
import com.siriusif.model.TablesHall;

public class HallDaoImplTest extends AbstractDaoImplTest{
	
	@Autowired
	private HallDao hallDao;
	
	@Autowired
	private TablesDao tablesDao;

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

		hall.addTables(new TablesHall("Nic", "Nikky", 50, 50));
		hall.addTables(new TablesHall("Tom", "Tommy", 100, 100));
		hall.addTables(new TablesHall("Tok", "Tokky", 150, 150));
		hall.addTables(new TablesHall("Nock", "Nokky", 200, 200));
		hall.addTables(new TablesHall("Tik", "Tikky", 250, 250));
		hall.setName("bancket");
		
		hallDao.add(hall);
		for (TablesHall table : hall.getTables()){
			tablesDao.add(table);
		}
	
		assertTrue (size < hallDao.list().size());
		Hall haFromDb = hallDao.find(hall.getId());
		assertEquals("bancket", haFromDb.getName());
		assertEquals(size, haFromDb.getTables().size());
	}

}