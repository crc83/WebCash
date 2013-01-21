package com.siriusif.helper;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Helper {
	
	 /**
	  * convert String to date
	 * @param date "dd/mm/yyyy"
	 * @return Date object
	 */
	public static Date stringToDate(String date){
	 DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	 Date today = null;
     try {
         today = df.parse(date);
     } catch (ParseException e) {
         e.printStackTrace();
     }
	return today;
	 }
}
