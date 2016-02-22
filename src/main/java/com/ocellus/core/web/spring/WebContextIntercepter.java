package com.ocellus.core.web.spring;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class WebContextIntercepter extends HandlerInterceptorAdapter
{

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception
	{
		if(modelAndView != null)
		{
			modelAndView.addObject("basePath", request.getContextPath());
		}
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
	{
		// TODO Auto-generated method stub
		return super.preHandle(request, response, handler);
	}

}
