package com.ocellus.core.jobs;

import com.ocellus.core.exception.JobExecuteException;

public class TestJob2 extends BaseJob
{
	public TestJob2()
	{
		defineConfigParm("Param3","参数3", ParamType.TEXT);
		defineConfigParm("Param4","参数4", ParamType.SELECT,"");
	}
	@Override
	protected void executeInteral() throws JobExecuteException
	{
		// TODO Auto-generated method stub

	}

}
