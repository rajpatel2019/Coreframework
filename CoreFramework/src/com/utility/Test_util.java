package com.utility;

import java.util.Calendar;

public class Test_util {
	
	public static String getSystemTime() {
		Calendar c = Calendar.getInstance();
		String  currenttime = "" + c.get(Calendar.YEAR) + 
				"-" + c.get(Calendar.MONTH) + 
				"-" + c.get(Calendar.DAY_OF_MONTH) +
				"-" + c.get(Calendar.HOUR_OF_DAY) +
				"-" + c.get(Calendar.MINUTE) +
				"-" + c.get(Calendar.SECOND);
		
		return currenttime;

	}
	
	

}
