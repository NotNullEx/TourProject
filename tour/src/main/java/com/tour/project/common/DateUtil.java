package com.tour.project.common;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtil {

	/**
	 * 날짜포맷변경
	 * @param date
	 * @param format
	 * @return String
	 */
	public static String dateToString(Date date, String format) {
		if (date != null) {
			SimpleDateFormat simpleDate = new SimpleDateFormat(format);
			return simpleDate.format(date);
		} else {
			return null;
		}
	}
	
	public static Date strToDate(String str, String format) {
		if (format == null) format = "yyyyMMdd";
		
		DateFormat dateFormat = new SimpleDateFormat(format);
		try {
			return dateFormat.parse(str);
		} catch (ParseException e) {
			return null;
		}
	}
	
	/**
	 *  오늘부터 이전 일 구하기
	 *  @param format
	 *  @return
	 */
	public static String getPreviousDay(Date date, String format, Locale locale, int day){
		Calendar cal = Calendar.getInstance((locale != null) ? locale : Locale.getDefault());
		cal.setTime(date);

		cal.add(Calendar.DATE, -day);

		return dateToString(cal.getTime(), format);
	}
}
