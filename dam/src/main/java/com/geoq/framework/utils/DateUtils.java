package com.geoq.framework.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils
{
	public static final String DateFormat = "yyyy-MM-dd";
	public static final String TimeFormat = "HH:mm:ss";
	public static final String StrampFormat = "yyyy-MM-dd HH:mm:ss";

	public static String formatDate(Date date, String formatPatter)
	{
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatPatter);
		return simpleDateFormat.format(date);
	}

	public static Date parserDate(String date, String formatPatter)
	{
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatPatter);
		Date resultDate = new Date();
		try
		{
			resultDate = simpleDateFormat.parse(date);
		} catch (ParseException e)
		{
			e.printStackTrace();
		}
		return resultDate;
	}

	public static Date combineDate(Date dateSupport, Date timeSupport)
	{
		String dateStr = formatDate(dateSupport, DateFormat);
		String timeStr = formatDate(timeSupport, TimeFormat);
		return parserDate(dateStr + " " + timeStr, StrampFormat);
	}

}
