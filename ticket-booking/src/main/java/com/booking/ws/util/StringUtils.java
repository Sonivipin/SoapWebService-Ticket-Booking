package com.booking.ws.util;

public class StringUtils {

	private StringUtils() {
	}

	public static boolean isNotEmpty(String value) {
		if ((value != null) && (value.trim().length() > 0)) {
			return true;
		} else {
			return false;
		}
	}
}
