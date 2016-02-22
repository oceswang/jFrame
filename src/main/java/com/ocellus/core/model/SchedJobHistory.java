package com.ocellus.core.model;

import java.util.Date;

public class SchedJobHistory extends SuperModel
{

	private String historyId;
	private String jobId;
	private Date jobRunTime;
	private Object jobData;
	
	@Override
	public void setId(String id)
	{
		historyId = id;
	}

	@Override
	public String getId()
	{
		return historyId;
	}

	public String getHistoryId()
	{
		return historyId;
	}

	public void setHistoryId(String historyId)
	{
		this.historyId = historyId;
	}

	public String getJobId()
	{
		return jobId;
	}

	public void setJobId(String jobId)
	{
		this.jobId = jobId;
	}

	public Date getJobRunTime()
	{
		return jobRunTime;
	}

	public void setJobRunTime(Date jobRunTime)
	{
		this.jobRunTime = jobRunTime;
	}

	public Object getJobData()
	{
		return jobData;
	}

	public void setJobData(Object jobData)
	{
		this.jobData = jobData;
	}

}
