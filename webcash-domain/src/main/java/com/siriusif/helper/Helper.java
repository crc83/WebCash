package com.siriusif.helper;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.siriusif.model.Group;
import com.siriusif.model.Hall;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Helper {
	private static DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

	/**
	 * convert String to date
	 * 
	 * @param date "dd/mm/yyyy"
	 * @return Date object
	 */
	public static Date date(String date) {
		Date today = null;
		try {
			today = dateFormat.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return today;
	}
	
	/**
	 * Cut's time part from java.util.Date value
	 * @param date
	 * @return date without time part
	 */
	public static Date dateOnly(Date date) {
		// Get Calendar object set to the date and time of the given Date object
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		// Set time fields to zero
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);

		// Put it back in the Date object
		return cal.getTime();
	}

	//TODO SB : Add javadoc
	public static BufferedReader getCPFileReader(String fileName)
			throws UnsupportedEncodingException {
		InputStream in = Helper.class.getResourceAsStream(fileName);
		Reader reader = new InputStreamReader(in, "UTF-8");
		BufferedReader bufferedReader = new BufferedReader(reader);
		return bufferedReader;
	}

	//TODO SB : Add javadoc
	public static <T> T fromJson(String fileName, Class<T> classOfT) throws JsonSyntaxException, JsonIOException, UnsupportedEncodingException {
		Gson gson = new GsonBuilder().setDateFormat("dd/mm/yyyy").create();
		return gson.fromJson(getCPFileReader(fileName), classOfT);
	}
	
	//TODO SB : Add javadoc
	public static Hall fromJsonHall(String fileName) throws JsonSyntaxException, JsonIOException, UnsupportedEncodingException{
		Hall hall = fromJson(fileName, Hall.class);
		//because links aren't set automatically
		hall.setTables(hall.getTables());			
		return hall;
	}	
	
	//TODO SB : Add javadoc
	public static Group[] fromJsonGroups(String fileName) throws JsonSyntaxException, JsonIOException, UnsupportedEncodingException{
		Group[] groups = fromJson(fileName, Group[].class);
		//because links aren't set automatically
		for(Group group : groups){
			group.fixReferencesToParentGroup();
		}
		return groups;
		
	}
}
