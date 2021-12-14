package br.com.cvc.api.utils;

import java.util.Calendar;
import java.util.Date;

public class Utils {

	/**
	 * Method responsible for returning the date calculated from the current date plus the days passed by the parameter
	 * @param days
	 * @return
	 */
	public static Date calculateDate(Integer days) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DATE, days);
		return cal.getTime();
	}
	
	/**
	 * Method responsible for returning the time in milliseconds of a date calculated from the current date plus the days passed by the parameter
	 * @param days
	 * @return
	 */
	public static Long gettingDayToDate(Integer days) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DATE, days);
		return Utils.gettingDayToDate(cal.getTime());
	}
	
	/**
	 * Method responsible for returning the time in milliseconds of a date passed by the parameter
	 * @param date
	 * @return
	 */
	public static Long gettingDayToDate(Date date) {
		return date.getTime() / (1000 * 60 * 60 * 24);		
	}

}
