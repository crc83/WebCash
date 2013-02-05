package com.siriusif.helper;

import java.io.UnsupportedEncodingException;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.siriusif.model.Hall;
import com.siriusif.service.model.HallDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AbstractContextLoader;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.support.GenericXmlContextLoader;

/**
 * This console app allow us to create database with initial data
 * for demonstration and testing
 *
 */
@ContextConfiguration(locations = "/webcash-persistance-beans.xml")
public class PrepareDatabase
{
	@Autowired
    private HallDao hallDao;
	
	public PrepareDatabase() {
//		new GenericXmlContextLoader().loadContext("/persistence-beans.xml");		
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
