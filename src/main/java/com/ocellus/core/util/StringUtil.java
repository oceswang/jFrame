package com.ocellus.core.util;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class StringUtil 
{
	public static boolean isEmpty(String val)
	{
		return val == null || val.trim().length()==0;
	}
	
	public static String defaultIfEmpty(String val, String defaultVal)
	{
		if(isEmpty(val))
		{
			return defaultVal;
		}
		return val;
	}
	
	public static boolean equals(String val1, String val2)
	{
		if(val1 == null && val2 == null)
			return true;
		if(!isEmpty(val1) && !isEmpty(val2) && val1.equals(val2))
		{
			return true;
		}
		return false;
	}
	
	// If string is less than aLength, pad on left.
	public static String lPad(String val, int length, String padChar)
	{
		if (val == null)
			return null;
		int len = val.length();
		StringBuffer pad = new StringBuffer("");
		if (length > len)
		{
			for (int i = 1; i <= length - len; i++)
				pad.append(padChar);
			return pad.toString() + val;
		}
		return val;
	}
	/**
	 * 去除字符；
	 * @param val
	 * @return
	 */
	public static String removeSymbol(String val)
	{
		if(!isEmpty(val))
		{
			String regEx="[-`~!@#$%^&*()+=|{}':;',//[//].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";   
			Pattern   p   =   Pattern.compile(regEx);      
			Matcher   m   =   p.matcher(val);      
			return m.replaceAll("").trim(); 
		}
		return val;
	}
	
	public static String subString(String val, int start, int end)
	{
		if(!isEmpty(val) && val.length()>=end)
		{
			return val.substring(start, end);
		}
		return val;
	}
	
	public static List<String> toList(String val, String separator)
	{
		List<String> list = new ArrayList<String>();
		if(!isEmpty(val) && val.indexOf(separator)>-1)
		{
			String[] arr = val.split(separator);
			for(String item : arr)
			{
				if(!isEmpty(item))
				{
					list.add(item);
				}
			}
		}
		return list;
	}
	
	public static String changeCharset(String val, String charset) throws UnsupportedEncodingException
	{
		if(!isEmpty(val))
		{
			byte[] bs = val.getBytes();
			return new String(bs, charset);
		}
		return null;
	}
	public static String getGUID()
	{
		return UUID.randomUUID().toString().toUpperCase().replaceAll("-", "");
	}
	public static String toJsonString(Object obj) throws JsonProcessingException
	{
		return new ObjectMapper().writeValueAsString(obj);
	}
	
	public static void main(String[] args) throws UnsupportedEncodingException
	{
		String str = "皖C-20578/挂HC77";
		System.out.println(changeCharset(str,"GBK"));
		//String rtn = removeSymbol(str);
		//System.out.println(subString(rtn,0, 7));
	}

}
