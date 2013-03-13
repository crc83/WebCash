package com.siriusif.helper;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.siriusif.model.Group;
import com.siriusif.model.GroupsList;
import com.siriusif.model.Hall;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Helper {

	/**
	 * convert String to date
	 * 
	 * @param date
	 *            "dd/mm/yyyy"
	 * @return Date object
	 */
	public static Date stringToDate(String date) {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Date today = null;
		try {
			today = df.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return today;
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
		return new Gson().fromJson(getCPFileReader(fileName), classOfT);
	}
	
	public static Hall fromJsonHall(String fileName) throws JsonSyntaxException, JsonIOException, UnsupportedEncodingException{
		Hall hall = fromJson(fileName, Hall.class);
		//because links aren't set automatically
		hall.setTables(hall.getTables());			
		return hall;
		
	}
	
	public static Group fromJsonGroup(String fileName) throws JsonSyntaxException, JsonIOException, UnsupportedEncodingException{
		Group group = fromJson(fileName, Group.class);
		//because links aren't set automatically
		group.setSubGroups(group.getSubGroups());
		group.setGoods(group.getGoods());
		return group;
		
	}
	
	public static GroupsList fromJsonGroupsList(String fileName) throws JsonSyntaxException, JsonIOException, UnsupportedEncodingException{
		GroupsList groupsList = fromJson(fileName, GroupsList.class);
		//because links aren't set automatically
		groupsList.setGroups(groupsList.getGroups());
		return groupsList;
		
	}
}
