package com.ocellus.core.jobs;

import com.ocellus.core.exception.JobExecuteException;

public class JobWrapper implements Runnable
{
	private final BaseJob job;
	
	public JobWrapper(BaseJob job)
	{
		this.job = job;
	}
	

	@Override
	public void run()
	{
		try
		{
			job.execute();
		} catch (JobExecuteException e)
		{
			e.printStackTrace();
		}

	}

}
