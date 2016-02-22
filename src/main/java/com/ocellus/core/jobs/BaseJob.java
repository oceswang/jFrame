package com.ocellus.core.jobs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.ocellus.core.exception.JobExecuteException;

public abstract class BaseJob
{
	protected List<ConfigParam> configParams = new ArrayList<ConfigParam>();
	protected Map params = new HashMap();
	private StringBuffer log = new StringBuffer();
	private String jobStatus = null;
	public final void execute() throws JobExecuteException
	{
		log("===================="+this.getClass().getName()+" Start====================\n");
		if(params != null && params.size()>0)
		{
			Set keys = params.keySet();
			for(Object key : keys)
			{
				log(key+"="+params.get(key));
			}
			log("\n");
		}
		executeInteral();
		log("===================="+this.getClass().getName()+" End====================\n");
	}
	
	protected abstract void executeInteral() throws JobExecuteException;
	
	protected void defineConfigParm(String paramName,String paramLabel,ParamType paramType)
	{
		ConfigParam param = new ConfigParam();
		param.setParamName(paramName);
		param.setParamLabel(paramLabel);
		param.setParamType(paramType);
		configParams.add(param);
	}
	
	protected void defineConfigParm(String paramName,String paramLabel,ParamType paramType,String defaultVal)
	{
		ConfigParam param = new ConfigParam();
		param.setParamName(paramName);
		param.setParamType(paramType);
		param.setParamLabel(paramLabel);
		param.setDefaultVal(defaultVal);
		configParams.add(param);
	}
	
	protected void defineConfigParm(String paramName,String paramLabel,ParamType paramType,String defaultVal, String refKey)
	{
		ConfigParam param = new ConfigParam();
		param.setParamName(paramName);
		param.setParamType(paramType);
		param.setParamLabel(paramLabel);
		param.setDefaultVal(defaultVal);
		param.setRefKey(refKey);
		configParams.add(param);
	}
	

	public List<ConfigParam> getConfigParams()
	{
		return configParams;
	}

	public Map getParams()
	{
		return params;
	}

	public void setParams(Map params)
	{
		this.params = params;
	}
	/**
	 * append message to job history
	 * @param msg
	 */
	protected void log(String msg)
	{
		log.append(msg);
	}
	protected void setJobStatus(String jobStatus)
	{
		this.jobStatus = jobStatus;
	}
}
