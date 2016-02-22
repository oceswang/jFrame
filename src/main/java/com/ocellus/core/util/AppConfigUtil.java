package com.ocellus.core.util;

import java.io.File;
import java.net.URLDecoder;
import java.util.Properties;

import org.apache.log4j.Logger;

public class AppConfigUtil
{
	private static Logger  logger = Logger.getLogger(AppConfigUtil.class);
	private static final String filePath = "conf/config.properties";
	private static Properties appConfig;
	private static long lastUpdate = 0L;
	
	public static String getProperty(String key)
	{
		init();
		return appConfig.getProperty(key);
	}
	public static String getProperty(String key, String defaultVal)
	{
		init();
		return appConfig.getProperty(key,defaultVal);
	}
	
	public static void setProperty(String key, String value){
		init();
		appConfig.setProperty(key, value);
	}
	
	public static boolean getBoolean(String key)
	{
		return "true".equalsIgnoreCase(getProperty(key));
	}
	
	public static int getInt(String key, int defaultVal)
	{
		String val = getProperty(key);
		if(NumberUtil.isNumber(val))
		{
			return NumberUtil.toInteger(val);
		}
		return defaultVal;
	}
	
	public static void main(String args[])
	{
		String key = "sms.enable";
		String value = getProperty(key);
		System.out.println(value);
	}
	
	private static void init() 
	{
		if(appConfig == null)
		{
			appConfig = new Properties();
		}
		try
		{
			String configPath = URLDecoder.decode(AppConfigUtil.class.getClassLoader().getResource(filePath).getPath(),"UTF-8");
			File configFile = new File(configPath);
			if(configFile.exists())
			{
				if(configFile.lastModified() != lastUpdate)
				{
					lastUpdate = configFile.lastModified();
					appConfig.load(AppConfigUtil.class.getClassLoader().getResourceAsStream(filePath));
				}
			}
		} catch (Exception e)
		{
			logger.error(e.getMessage(), e);
		}
	}
	
	
}
