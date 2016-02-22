package com.ocellus.core.webservices;

import javax.jws.WebService;

@WebService(endpointInterface = "com.ocellus.core.webservices.DataCenter")
public class DataCenterImpl implements DataCenter
{

	@Override
	public String process(String xml)
	{
		// TODO Auto-generated method stub
		return "This is the result.";
	}

}
