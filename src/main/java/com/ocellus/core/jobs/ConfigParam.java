package com.ocellus.core.jobs;

public class ConfigParam
{
	private String paramName;
	private String paramLabel;
	private ParamType paramType;
	private String refKey;
	private String defaultVal;
	
	
	public ConfigParam()
	{
	}
	
	public ConfigParam(String paramName, String paramLabel, ParamType paramType)
	{
		super();
		this.paramName = paramName;
		this.paramLabel = paramLabel;
		this.paramType = paramType;
	}

	public ConfigParam(String paramName, String paramLabel, ParamType paramType, String defaultVal)
	{
		super();
		this.paramName = paramName;
		this.paramLabel = paramLabel;
		this.paramType = paramType;
		this.defaultVal = defaultVal;
	}

	public ConfigParam(String paramName, String paramLabel, ParamType paramType, String refKey, String defaultVal)
	{
		super();
		this.paramName = paramName;
		this.paramLabel = paramLabel;
		this.paramType = paramType;
		this.refKey = refKey;
		this.defaultVal = defaultVal;
	}

	public String getParamName()
	{
		return paramName;
	}
	public void setParamName(String paramName)
	{
		this.paramName = paramName;
	}
	public String getParamLabel()
	{
		return paramLabel;
	}
	public void setParamLabel(String paramLabel)
	{
		this.paramLabel = paramLabel;
	}
	public ParamType getParamType()
	{
		return paramType;
	}
	public void setParamType(ParamType paramType)
	{
		this.paramType = paramType;
	}
	public String getRefKey()
	{
		return refKey;
	}
	public void setRefKey(String refKey)
	{
		this.refKey = refKey;
	}
	public String getDefaultVal()
	{
		return defaultVal;
	}
	public void setDefaultVal(String defaultVal)
	{
		this.defaultVal = defaultVal;
	}
	
	
	
	

}
