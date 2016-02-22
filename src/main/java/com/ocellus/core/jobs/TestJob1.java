package com.ocellus.core.jobs;

import com.ocellus.core.exception.JobExecuteException;

public class TestJob1 extends BaseJob
{
	public TestJob1()
	{
		defineConfigParm("Param1","参数1", ParamType.TEXT);
		defineConfigParm("Param2","参数2", ParamType.SELECT,"");
	}
	@Override
	protected void executeInteral() throws JobExecuteException
	{
		// TODO Auto-generated method stub

	}

}
