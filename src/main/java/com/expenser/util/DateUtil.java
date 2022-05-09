package com.expenser.util;

import java.util.Date;

public class DateUtil {
	
	public static boolean isFutureDate(Date targetDate) {
		return targetDate.after(new Date());
	}
}
