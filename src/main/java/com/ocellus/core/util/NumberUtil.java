package com.ocellus.core.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;

public class NumberUtil 
{
	public static boolean isNumber(String numStr)
	{
		try
		{
			if (numStr == null || numStr.trim().equals(""))
				return false;
			new BigDecimal(numStr);
			return true;
		}
		catch (NumberFormatException e) 
		{ 
			return false; 
		}
	}
	
	public static Integer toInteger(String str)
	{
		Integer integer = null;
		if(str != null && str.trim().length() > 0)
		{
			try
			{
				integer = new Integer(Integer.parseInt(str));
			}
			catch (Exception e) { e.printStackTrace(); }
		}
		return integer;
	}
	
	public static Integer toInteger(Object object)
	{
		Integer integer = null;
		if(object!=null)
		{
			if (object instanceof java.math.BigDecimal)
			{
				return ((java.math.BigDecimal) object).intValue();
			}
			
			String string = object.toString();			
			integer = toInteger(string);
		}
		return integer;
	}
	
	
	public static BigDecimal toBigDecimal(String value)
	{
		if(isNumber(value))
		{
			return new BigDecimal(value);
		}
		return null;
		
	}
	
	public static Double toDouble(String aString) 
	{
		try
		{
			double value = 0.00;
			if (aString == null || aString.trim().equals(""))
				return 0.00;
			value = Double.parseDouble(aString.trim());
			return value;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 百分比显示
	 * @param numerator 
	 * @param denominator
	 * @return
	 */
	public static String toProportion(BigDecimal numerator ,BigDecimal denominator) 	{
		  NumberFormat format = NumberFormat.getPercentInstance();// 获取格式化类实例
	      format.setMinimumFractionDigits(2);// 设置小数位
	      if(numerator != null && denominator != null && !BigDecimal.ZERO.equals(denominator)){
	    	  return format.format(numerator.divide(denominator,10,RoundingMode.HALF_DOWN));
	      }else{
	    	  return "0%";
	      }
	     
	}
	
	public static double round(double value, int scale, int roundingMode) 
	{  
		BigDecimal bd = BigDecimal.valueOf(value); 
        bd = bd.setScale(scale, roundingMode);  
        double d = bd.doubleValue();  
        return d;    
    }
	
}
