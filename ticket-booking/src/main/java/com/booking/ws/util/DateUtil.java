package com.booking.ws.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	private static final String DATE_FORMAT = "MM/dd/yyyy HH:mm:ss";

	private DateUtil() {

	}

	/**
	 * The function converts and returns current date object to string object.
	 * 
	 * @return
	 */
	public static String currentDateToString() {
		DateFormat df = new SimpleDateFormat(DATE_FORMAT);
		Date today = Calendar.getInstance().getTime();
		return df.format(today);
	}

	/**
	 * The function converts and returns input date object to string object.
	 * 
	 * @param date
	 * @return
	 */
	public static String dateToString(final Date date) {
		if (date == null) {
			return null;
		}
		DateFormat df = new SimpleDateFormat(DATE_FORMAT);
		return df.format(date);
	}

	/**
	 * The function converts and returns input String object to date object.
	 * 
	 * @param date
	 * @return
	 */
	public static Date stringToDate(final String date) {
		DateFormat df = new SimpleDateFormat(DATE_FORMAT);
		Date d1 = null;
		try {
			d1 = df.parse(date);
		} catch (ParseException e) {
			System.out.println("Parsing exception " + e);
		}
		return d1;
	}
}
