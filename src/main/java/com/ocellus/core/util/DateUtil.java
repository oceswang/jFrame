package com.ocellus.core.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil
{
	public static final String FORMAT_DATETIME = "yyyy-MM-dd HH:mm:ss";
	public static String format(Date date, String format)
	{
		if (date == null)
		{
			return "";
		}
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
		return simpleDateFormat.format(date);
	}
	
	public static Date parse(String val, String format) throws ParseException
	{
		if(val != null && format != null)
		{
			SimpleDateFormat sf = new SimpleDateFormat(format);
			return sf.parse(val);
		}
		return null;
	}
}
