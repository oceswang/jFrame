package com.ocellus.core.webservices;

import javax.jws.WebService;

@WebService
public interface DataCenter
{
	public String process(String xml);
}
