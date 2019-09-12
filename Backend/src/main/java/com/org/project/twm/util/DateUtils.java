package com.org.project.twm.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This Class will be used for all the data specific things.
 *
 * @author abhishek.sisodiya
 */
public class DateUtils {

	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	/**
	 * Returns today's date as java.util.Date object
	 *
	 * @return today's date as java.util.Date object
	 */
	public static Date today() {
		return new Date();
	}

	/**
	 * Returns today's date as yyyy-MM-dd format
	 *
	 * @return today's date as yyyy-MM-dd format
	 */
	public static String todayStr() {
		return sdf.format(today());
	}

	/**
	 * Returns the formatted String date for the passed java.util.Date object
	 *
	 * @param date
	 * @return
	 */
	public static String formattedDate(Date date) {
		return date != null ? sdf.format(date) : todayStr();
	}

}
