package com.siriusif.helper;

import java.io.UnsupportedEncodingException;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.siriusif.model.Hall;
import com.siriusif.service.model.HallDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AbstractContextLoader;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.support.GenericXmlContextLoader;

/**
 * This console app allow us to create database with initial data
 * for demonstration and testing
 *
 */
@ContextConfiguration(locations="/webcash-persistence-beans.xml")
public class PrepareDatabase
{
	private ApplicationContext ctx;
	
    private HallDao hallDao;
	
	public PrepareDatabase() throws Exception {
		ctx = new GenericXmlContextLoader().loadContext("/webcash-persistence-beans.xml");
		hallDao = (HallDao) ctx.getBean("hallDao");
	}
	
	public void loadHalls() throws JsonSyntaxException, JsonIOException, UnsupportedEncodingException{
    	Hall hall = Helper.fromJson("/hall.json", Hall.class);
		hallDao.add(hall);
	}

    public static void main( String[] args ) throws Exception
    {
    	
    	PrepareDatabase main = new PrepareDatabase();
    	System.out.println("Import started");
    	main.loadHalls();
		System.out.println("Import finished");
    }
}
