package br.com.cvc.api.utils;

import java.util.Calendar;
import java.util.Date;

public class Utils {

	public static Date calculateDate(Integer dias) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DATE, dias);
		return cal.getTime();
	}
	
	public static boolean calculatingDate(Date transferDate, Integer days) {
		return transferDate.before(calculateDate(days));
	}

	public static Long gettingDayToDate(Integer dias) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DATE, dias);
		return Utils.gettingDayToDate(cal.getTime());
	}
	
	public static Long gettingDayToDate(Date data) {
		return data.getTime() / (1000 * 60 * 60 * 24);		
	}

}
