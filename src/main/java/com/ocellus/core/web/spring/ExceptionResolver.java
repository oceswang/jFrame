package com.ocellus.core.web.spring;

import java.io.PrintWriter;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import com.ocellus.core.model.ResponseData;
import com.ocellus.core.util.DateUtil;
import com.ocellus.core.util.StringUtil;
import com.ocellus.core.util.WebUtil;

public class ExceptionResolver extends SimpleMappingExceptionResolver
{
	private static Logger logger = Logger.getLogger(ExceptionResolver.class);
	@Override
	protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex)
	{
		if(request.getParameter("isload")==null && request.getHeader("x-requested-with") != null)
		{
			try
			{
				ResponseData data = new ResponseData();
				data.setStatusError();
				data.setMessage(ex.getMessage());
				PrintWriter writer = response.getWriter();
				writer.print(StringUtil.toJsonString(data));
				writer.flush();
			} catch (Exception e)
			{
				logger.error(e.getMessage(), e);
			} 
			return null;
		}
		// TODO Auto-generated method stub
		return super.doResolveException(request, response, handler, ex);
	}

	@Override
	protected void logException(Exception ex, HttpServletRequest request)
	{
		logger.error(buildLogMessage(ex, request), ex);
		
	}

	@Override
	protected String buildLogMessage(Exception ex, HttpServletRequest request)
	{
		StringBuffer msg = new StringBuffer("\n");
		msg.append("异常发生时间：").append(DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss")).append("\n");
		msg.append("用户：").append(WebUtil.getLoginUserName()).append("\n");
		msg.append("访问路径：").append(request.getRequestURI()).append("\n");
		msg.append("其他信息：").append("\n");
		Enumeration names = request.getParameterNames();
		if(names != null)
		{
			while(names.hasMoreElements())
			{
				String name = names.nextElement().toString();
				Object value = request.getParameter(name);
				msg.append(name).append("=").append(value).append(";");
			}
		}
		return msg.toString();
	}
	
}
