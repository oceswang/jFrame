package com.ocellus.core.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;

import com.ocellus.core.dao.SchedJobMapper;
import com.ocellus.core.exception.BusinessException;
import com.ocellus.core.jobs.BaseJob;
import com.ocellus.core.jobs.JobWrapper;
import com.ocellus.core.model.SchedJob;
import com.ocellus.core.model.SchedJobHistory;
import com.ocellus.core.util.Constants;
import com.ocellus.core.util.DateUtil;
import com.ocellus.core.util.StringUtil;
@Service
public class SchedJobService extends GenericService<SchedJob> 
{
	private SchedJobMapper schedJobMapper;
	@Resource(name="jobScheduler")
	private ThreadPoolTaskScheduler jobScheduler;
	
	private  Map<String,ScheduledFuture> scheduledFurturs = new HashMap<String,ScheduledFuture>();
	@Autowired
	public void setSchedJobMapper(SchedJobMapper schedJobMapper)
	{
		super.setBaseMapper(schedJobMapper);
		this.schedJobMapper = schedJobMapper;
	}
	
	@Override
	public int insert(SchedJob model) throws BusinessException
	{
		try
		{
			schedule(model);
			return super.insert(model);
		} catch (Exception e)
		{
			throw new BusinessException(e);
		}
	}
	@Override
	public int delete(String id) throws BusinessException
	{
		// TODO Auto-generated method stub
		if(scheduledFurturs.containsKey(id))
		{
			scheduledFurturs.get(id).cancel(false);
		}
		return super.delete(id);
	}
	@Override
	public int update(SchedJob model) throws BusinessException
	{
		try
		{
			if(scheduledFurturs.containsKey(model.getJobId()))
			{
				scheduledFurturs.get(model.getJobId()).cancel(false);
			}
			schedule(model);
			return super.update(model);
		} catch (Exception e)
		{
			throw new BusinessException(e);
		}
	}
	
	private void schedule(SchedJob schedJob) throws Exception
	{
		BaseJob job = (BaseJob)Class.forName(schedJob.getJobClass()).newInstance();
		Map jobParam = schedJob.getJobParam();
		if(jobParam == null)
		{
			jobParam = new HashMap();
		}
		jobParam.put("jobId", schedJob.getJobId());
		job.setParams(jobParam);
		Runnable task = new JobWrapper(job);
		ScheduledFuture scheduledFuture = null;
		if(Constants.JOB_TRIGGER_TYPE_SIMPLE.equals(schedJob.getJobTriggerType()))
		{
			Date startTime = DateUtil.parse(schedJob.getJobTriggerValue(), DateUtil.FORMAT_DATETIME);
			scheduledFuture = jobScheduler.schedule(task, startTime);
		}
		else if(Constants.JOB_TRIGGER_TYPE_CRON.equals(schedJob.getJobTriggerType()))
		{
			CronTrigger trigger = new CronTrigger(schedJob.getJobTriggerValue());
			scheduledFuture = jobScheduler.schedule(task, trigger);
		}
		scheduledFurturs.put(schedJob.getJobId(), scheduledFuture);
	}
	
	
	public void insertHistory(SchedJobHistory history)
	{
		history.setId(StringUtil.getGUID());
		schedJobMapper.insertHistory(history);
	}
	public List<SchedJobHistory> searchHistory(Map params)
	{
		return schedJobMapper.searchHistory(params);
	}
	public SchedJobHistory getHistoryById(String historyId)
	{
		return schedJobMapper.getHistoryById(historyId);
	}
	
}
