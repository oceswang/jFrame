package com.ocellus.core.model;

import java.util.Date;
import java.util.Map;

public class SchedJob extends SuperModel
{
	private String jobId;
	private String jobName;
	private String jobClass;
	private String jobTriggerType;
	private String jobTriggerValue;
	private Date latestRunTime;
	private Date nextRunTime;
	private Map jobParam;
	private String jobStatus;
	private String onSuccess;
	private Integer onSuccessDelay;
	private String onFailed;
	private Integer onFailedDelay;
	private String onSuccessWithError;
	private Integer onSuccessWithErrorDelay;
	
	
	
	@Override
	public void setId(String id)
	{
		this.jobId = id;
		
	}
	@Override
	public String getId()
	{
		return jobId;
	}
	public String getJobId()
	{
		return jobId;
	}
	public void setJobId(String jobId)
	{
		this.jobId = jobId;
	}
	public String getJobName()
	{
		return jobName;
	}
	public void setJobName(String jobName)
	{
		this.jobName = jobName;
	}
	public String getJobClass()
	{
		return jobClass;
	}
	public void setJobClass(String jobClass)
	{
		this.jobClass = jobClass;
	}
	public String getJobTriggerType()
	{
		return jobTriggerType;
	}
	public void setJobTriggerType(String jobTriggerType)
	{
		this.jobTriggerType = jobTriggerType;
	}
	public String getJobTriggerValue()
	{
		return jobTriggerValue;
	}
	public void setJobTriggerValue(String jobTriggerValue)
	{
		this.jobTriggerValue = jobTriggerValue;
	}
	public Date getLatestRunTime()
	{
		return latestRunTime;
	}
	public void setLatestRunTime(Date latestRunTime)
	{
		this.latestRunTime = latestRunTime;
	}
	public Date getNextRunTime()
	{
		return nextRunTime;
	}
	public void setNextRunTime(Date nextRunTime)
	{
		this.nextRunTime = nextRunTime;
	}
	public Map getJobParam()
	{
		return jobParam;
	}
	public void setJobParam(Map jobParam)
	{
		this.jobParam = jobParam;
	}
	public String getJobStatus()
	{
		return jobStatus;
	}
	public void setJobStatus(String jobStatus)
	{
		this.jobStatus = jobStatus;
	}
	public String getOnSuccess()
	{
		return onSuccess;
	}
	public void setOnSuccess(String onSuccess)
	{
		this.onSuccess = onSuccess;
	}
	public Integer getOnSuccessDelay()
	{
		return onSuccessDelay;
	}
	public void setOnSuccessDelay(Integer onSuccessDelay)
	{
		this.onSuccessDelay = onSuccessDelay;
	}
	public String getOnFailed()
	{
		return onFailed;
	}
	public void setOnFailed(String onFailed)
	{
		this.onFailed = onFailed;
	}
	public Integer getOnFailedDelay()
	{
		return onFailedDelay;
	}
	public void setOnFailedDelay(Integer onFailedDelay)
	{
		this.onFailedDelay = onFailedDelay;
	}
	public String getOnSuccessWithError()
	{
		return onSuccessWithError;
	}
	public void setOnSuccessWithError(String onSuccessWithError)
	{
		this.onSuccessWithError = onSuccessWithError;
	}
	public Integer getOnSuccessWithErrorDelay()
	{
		return onSuccessWithErrorDelay;
	}
	public void setOnSuccessWithErrorDelay(Integer onSuccessWithErrorDelay)
	{
		this.onSuccessWithErrorDelay = onSuccessWithErrorDelay;
	}

}
