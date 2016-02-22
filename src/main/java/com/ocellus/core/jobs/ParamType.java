package com.ocellus.core.jobs;

public enum ParamType
{
	TEXT("text"),
	SELECT("select");
	
	private String val;
	ParamType(String val)
	{
		this.val = val;
	}
	public String getVal()
	{
		return val;
	}
	public void setVal(String val)
	{
		this.val = val;
	}
}
