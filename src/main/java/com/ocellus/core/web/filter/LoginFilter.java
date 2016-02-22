package com.ocellus.core.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ocellus.core.model.ResponseData;
import com.ocellus.core.util.AppConfigUtil;
import com.ocellus.core.util.Constants;

public class LoginFilter implements Filter
{
	private String[] excludes = null;
	private String loginURL = null;
	@Override
	public void init(FilterConfig filterConfig) throws ServletException
	{
		String filterName = filterConfig.getFilterName();
		String excludesParam = AppConfigUtil.getProperty("app."+filterName+".excludeURL");
		if(excludesParam != null)
		{
			excludes = excludesParam.split(";");
		}
		loginURL = AppConfigUtil.getProperty("app.loginURL","login.do");

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
			ServletException
	{
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		HttpServletResponse httpResponse = (HttpServletResponse)response;
		
		String url = getURL(httpRequest);
		if(excludes != null && excludes.length>0)
		{
			for(String exclude : excludes)
			{
				if(url.equals(exclude))
				{
					chain.doFilter(httpRequest, httpResponse);
					return;
				}
			}
		}
		HttpSession session = httpRequest.getSession(true);
		if(session.getAttribute(Constants.SESSION_USER_KEY) == null)
		{
			if(isAjax(httpRequest))
			{
				ResponseData rep = new ResponseData();
				rep.setStatusTimeout();
				ObjectMapper mapper = new ObjectMapper();
				mapper.writeValue(httpResponse.getWriter(),rep);
			}
			else
			{
				httpResponse.sendRedirect(loginURL);
			}
		}
		else
		{
			chain.doFilter(httpRequest, httpResponse);
		}

	}

	@Override
	public void destroy()
	{
		// TODO Auto-generated method stub

	}
	
	public boolean isAjax(HttpServletRequest request)
	{
		return request.getHeader("x-requested-with") != null;
	}
	public String getURL(HttpServletRequest request)
	{
		String url = null;
		String contextPath = request.getContextPath();
		String uri = request.getRequestURI();
		url = uri.replace(contextPath, "");
		return url;
	}

}
