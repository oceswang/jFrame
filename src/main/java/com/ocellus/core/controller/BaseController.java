package com.ocellus.core.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.ocellus.core.plugins.paging.model.PageRequest;
import com.ocellus.core.plugins.paging.model.PageResponse;
import com.ocellus.core.util.NumberUtil;
import com.ocellus.core.util.StringUtil;

public class BaseController
{
	protected Logger logger = Logger.getLogger(getClass());
	protected PageResponse getPageResponse(Object rows){
		PageResponse rep = PageResponse.get();
		rep.setRows(rows);
		PageRequest.remove();
		PageResponse.remove();
		return rep;
	}
	protected Map<String, String> getParamMap(HttpServletRequest request) 
	{
		Map<String,String> paramMap = new HashMap<String,String>();
		try {
			Enumeration<?> paramNames = request.getParameterNames();
			while(paramNames.hasMoreElements())
			{
				String paramName = URLDecoder.decode(paramNames.nextElement().toString(), "UTF-8");
				String paramValue = URLDecoder.decode(request.getParameter(paramName), "UTF-8");
				paramMap.put(paramName, paramValue);
			}
			PageRequest.get();
			PageRequest.remove();
			PageResponse.get();
			PageResponse.remove();
			String page = paramMap.get("page");
			String rows = paramMap.get("rows");
			if(!StringUtil.isEmpty(page) && !StringUtil.isEmpty(rows))
			{
				PageRequest req = PageRequest.get();
				req.setPaging(true);
				req.setPage(NumberUtil.toInteger(paramMap.get("page")));
				req.setRows(NumberUtil.toInteger(paramMap.get("rows")));
				req.setSidx(paramMap.get("sidx") + "");
				req.setSord(paramMap.get("sord") + "");
			}
			
		} catch (UnsupportedEncodingException e) {
			logger.error(e.getStackTrace());
		}
		return paramMap;
	}
}
