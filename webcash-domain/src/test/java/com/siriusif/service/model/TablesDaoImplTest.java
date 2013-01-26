package com.siriusif.service.model;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.siriusif.model.TablesHall;

public class TablesDaoImplTest extends AbstractDaoImplTest{
	
	@Autowired
	private TablesDao tablesDao;

	@Test
	public void testAdd() {
		int size = tablesDao.list().size();
		
		TablesHall tables = new TablesHall();
		tables.setName("Bar");
		tables.setDescription("first");
		tables.setHeight(50);
		tables.setLeft(50);
		tables.setTop(50);
		tables.setWidth(50);
		tablesDao.add(tables);
		
		assertTrue (size < tablesDao.list().size());
	}

}
