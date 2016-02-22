package com.ocellus.core.model;

import java.util.HashMap;
import java.util.Map;

public class ResponseData
{
	
	private String status;
	private String message;
	private Map<String,Object> userData = new HashMap<String,Object>();
	public String getStatus()
	{
		return status;
	}
	public void setStatusSuccess()
	{
		this.status = "SUCCESS";
	}
	public void setStatusFailer()
	{
		this.status = "FAILER";
	}
	public void setStatusTimeout()
	{
		this.status = "TIMEOUT";
	}
	public void setStatusError()
	{
		this.status = "ERROR";
	}
	public String getMessage()
	{
		return message;
	}
	public void setMessage(String message)
	{
		this.message = message;
	}
	public Map<String, Object> getUserData()
	{
		return userData;
	}
	public void setUserData(Map<String, Object> userData)
	{
		this.userData = userData;
	}
	public void addUserData(String key, Object value)
	{
		userData.put(key, value);
	}
}
