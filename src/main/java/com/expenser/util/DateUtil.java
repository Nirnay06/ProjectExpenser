package com.expenser.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	
	public static boolean isFutureDate(Date targetDate) {
		return targetDate.after(new Date());
	}
	
	/**
	 * <p>The Function will convert the date and time strings to Java Date object</p>
	 * @param date -> String object date in the format yyyy-MM-dd
	 * @param time -> String object time in the format HH:mm
	 * @return Java Date object
	 * @throws ParseException
	 */
	public static Date getDateFromDateAndTimeString(String date, String time) throws ParseException{
		 Date dateTime = new SimpleDateFormat("MM/dd/yyyy'T'HH:mm")
                 .parse(date.concat("T").concat(time));
		return dateTime;
	}
	
	public static String getDateString(Date date) {
		String dateString = new SimpleDateFormat("yyyy-MM-dd").format(date);
		return dateString;
	}
	
	public static String getTimeString(Date date) {
		String timeString = new SimpleDateFormat("HH:mm").format(date);
		return timeString;
	}
}
