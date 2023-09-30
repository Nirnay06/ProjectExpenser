package com.expenser.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.TemporalField;
import java.util.Date;

import org.springframework.data.util.Pair;

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
		String dateString = new SimpleDateFormat("MM/dd/yyyy").format(date);
		return dateString;
	}
	
	public static String getDateString(LocalDate localDate) {
		Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		String dateString = new SimpleDateFormat("MM/dd/yyyy").format(date);
		return dateString;
	}
	
	public static String getMonthFromDate(LocalDate date) {
		return date.getMonth().toString();
	}
	
	public static String getYearFromDate(LocalDate date) {
		return "" + date.getYear();
	}
	
	public static String getTimeString(Date date) {
		String timeString = new SimpleDateFormat("HH:mm").format(date);
		return timeString;
	}
	
	public static Pair<LocalDate, LocalDate> getStartAndEndDateOfWeek(LocalDate date){
		if(date!=null) {
			LocalDate startWeek = date.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
			LocalDate endWeek = date.with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY));
			
			return Pair.of(startWeek, endWeek);
		}
		throw new IllegalArgumentException();
	}

	public static Pair<LocalDate, LocalDate> getStartAndEndDateOfMonth(LocalDate date) {
		if(date!=null) {
			LocalDate startMonth = date.with(TemporalAdjusters.firstDayOfMonth());
			LocalDate endMonth = date.with(TemporalAdjusters.lastDayOfMonth());
			return Pair.of(startMonth, endMonth);
		}
		throw new IllegalArgumentException();
	}

	public static Pair<LocalDate, LocalDate> getStartAndEndDateOfQuarter(LocalDate date) {
		if(date!=null) {
			 LocalDate firstDateOfQuarter = date.with(date.getMonth().firstMonthOfQuarter())
                     .withDayOfMonth(1);
			 LocalDate lastDateOfQuarter = firstDateOfQuarter.plusMonths(2).withDayOfMonth(firstDateOfQuarter.plusMonths(2).lengthOfMonth());
			return Pair.of(firstDateOfQuarter, lastDateOfQuarter);
		}
		throw new IllegalArgumentException();
	}
	
	public static Pair<LocalDate, LocalDate> getStartAndEndDateOfYear(LocalDate date) {
		if(date!=null) {
			 LocalDate firstDateOfYear = date.withDayOfYear(1);
			 LocalDate lastDateOfYear = date.withMonth(Month.DECEMBER.getValue()).withDayOfMonth(31);;
			return Pair.of(firstDateOfYear, lastDateOfYear);
		}
		throw new IllegalArgumentException();
	}

	
}
