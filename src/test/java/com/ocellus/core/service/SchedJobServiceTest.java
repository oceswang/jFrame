package com.ocellus.core.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import com.ocellus.core.exception.BusinessException;
import com.ocellus.core.model.SchedJob;

@ContextConfiguration(locations = { "classpath*:/spring/applicationContext.xml" })
public class SchedJobServiceTest extends AbstractTransactionalJUnit4SpringContextTests
{
	@Autowired
	private SchedJobService schedJobService;
	
	@Test
	@Rollback(false)
	public void inserTest() throws BusinessException
	{
		
		Map params = new HashMap();
		params.put("Param1", "1111");
		params.put("Param2", "2222");
		SchedJob job = new SchedJob();
		job.setJobName("ttt");
		job.setJobClass("com.ocellus.core.schedule.TestJob1");
		job.setJobTriggerType("1");
		job.setJobTriggerValue("0 2 10 * * 2-4");
		job.setLatestRunTime(new Date());
		job.setNextRunTime(new Date());
		job.setJobParam(params);
		job.setJobStatus("1");
		job.setOnSuccess("onsuccess");
		job.setOnSuccessDelay(20);
		job.setOnFailed("onFailed");
		job.setOnFailedDelay(40);
		job.setOnSuccessWithError("onSuccessWithError");
		job.setOnSuccessWithErrorDelay(60);
		schedJobService.insert(job);
	}
}
