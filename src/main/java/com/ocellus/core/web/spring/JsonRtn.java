package com.ocellus.core.web.spring;

import java.util.HashMap;
import java.util.Map;


public class JsonRtn
{
	private String status;
	private String message;
	private Map userData = new HashMap();
	public String getStatus()
	{
		return status;
	}
	
	public String getMessage()
	{
		return message;
	}
	public void setMessage(String message)
	{
		this.message = message;
	}
	
	public void setStatusSuccess()
	{
		this.status = "1";
	}
	public void setStatusFailed()
	{
		this.status = "0";
	}
	public void setStatusTimeout()
	{
		this.status = "-1";
	}
	public void addUserData(String key, String value)
	{
		userData.put(key, value);
	}
	public void addUserData(Map data)
	{
		userData.putAll(data);
	}

	public Map getUserData()
	{
		return userData;
	}
	
	
	

}
