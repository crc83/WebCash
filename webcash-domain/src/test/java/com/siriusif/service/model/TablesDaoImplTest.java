//package com.siriusif.service.model;
//
//import static org.junit.Assert.*;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.io.Reader;
//import java.io.UnsupportedEncodingException;
//
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import com.google.gson.Gson;
//import com.siriusif.model.TablesHall;
//
//public class TablesDaoImplTest extends AbstractDaoImplTest{
//	
//	@Autowired
//	private TablesDao tablesDao;
//
//	@Test
//	public void testAdd() {
//		int size = tablesDao.list().size();
//		
//		TablesHall tables = new TablesHall();
//		tables.setName("Bar");
//		tables.setDescription("first");
//		tables.setHeight(50);
//		tables.setLeft(50);
//		tables.setTop(50);
//		tables.setWidth(50);
//		tablesDao.add(tables);
//		
//		assertTrue (size < tablesDao.list().size());
//	}
//	
//	private BufferedReader getCPFileReader(String fileName)
//			throws UnsupportedEncodingException {
//		InputStream in = this.getClass().getResourceAsStream(fileName);
//		Reader reader = new InputStreamReader(in, "UTF-8");
//		BufferedReader bufferedReader = new BufferedReader(reader);
//		return bufferedReader;
//	}
//
//	@Test
//	public void testAddJsonToDb()throws IOException{		
//		TablesHall tablesHall = new Gson().fromJson(getCPFileReader("/tables.json"), TablesHall.class);
//		tablesDao.add(tablesHall);
//		
//	}
//
//}
