package com.siriusif.service.model;

import static org.junit.Assert.*;

import java.io.UnsupportedEncodingException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.siriusif.helper.Helper;
import com.siriusif.helper.AbstractSpringTest;
import com.siriusif.model.TablesHall;

public class TablesDaoImplTest extends AbstractSpringTest{
	
	@Autowired
	private TablesDao tablesDao;

	/*
	 * When : I add table to db
	 * Then :
	 * 1) I should see that number of entities in DB increased 
	 * 2) I should read all fields from DB correctly
	 */
	@Test
	public void testAdd() throws JsonSyntaxException, JsonIOException,
			UnsupportedEncodingException {
		int size = tablesDao.list().size();
		TablesHall table = Helper.fromJson("/tables.json", TablesHall.class);
		tablesDao.add(table);
		assertTrue (size < tablesDao.list().size());
		TablesHall tableFromDB = tablesDao.find(table.getId());

		assertEquals("administrator", tableFromDB.getName());
		assertEquals("leftTable", tableFromDB.getDescription());
		assertEquals(50, tableFromDB.getTop());
		assertEquals(60, tableFromDB.getLeft());
		assertEquals(123, tableFromDB.getHeight());
		assertEquals(321, tableFromDB.getWidth());
	}
	}
