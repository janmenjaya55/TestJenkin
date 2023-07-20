package com.bata.billpunch;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

public class TestAlt {
	
	public static void main(String[] args) throws ParseException {
		//14-27(25) 27-40(38)  40 -53
		String fromdate = "2022-04-01";
		String todate = "2022-06-30";
		
		String format = "yyyy-MM-dd";

		SimpleDateFormat df = new SimpleDateFormat(format);
		Date datefrom = df.parse(fromdate);
		Date dateto = df.parse(todate);

		Calendar fromcal = Calendar.getInstance();
		fromcal.setTime(datefrom);
		int yr = fromcal.get(Calendar.YEAR);
		int week = fromcal.get(Calendar.WEEK_OF_YEAR);
		int month = fromcal.get(Calendar.MONTH)+1;
		
		
		Calendar tocal = Calendar.getInstance();
		tocal.setTime(dateto);
		int toyr = tocal.get(Calendar.YEAR);
		int weekto = tocal.get(Calendar.WEEK_OF_YEAR);
		int monthto = tocal.get(Calendar.MONTH)+1;
		
		String fromyear=StringUtils.leftPad(String.valueOf(yr), 4, "0");
		String fromweek=StringUtils.leftPad(String.valueOf(week), 2, "0");
		String frommonth=StringUtils.leftPad(String.valueOf(month), 2, "0");

			
		
		String toyear=StringUtils.leftPad(String.valueOf(toyr), 4, "0");
		String toweek=StringUtils.leftPad(String.valueOf(weekto), 2, "0");
		String tomonth=StringUtils.leftPad(String.valueOf(monthto), 2, "0");
		
		System.out.println("fromyear"+fromyear);
		System.out.println("fromweek"+fromweek);
		System.out.println("toyear"+toyear);
		System.out.println("toweek"+toweek);
		System.out.println("frommonth"+frommonth);
		System.out.println("tomonth"+tomonth);
	} 

}
