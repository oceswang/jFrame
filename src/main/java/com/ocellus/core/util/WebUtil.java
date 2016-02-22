package com.ocellus.core.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.ocellus.core.model.User;

public class WebUtil
{
	public static HttpServletRequest getRequest()
	{
		ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes(); 
		if(attrs != null)
		{
			return attrs.getRequest();
		}
		return null;
	}
	public static User getLoginUser()
	{
		ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes(); 
		if(attrs != null)
		{
			HttpServletRequest request = attrs.getRequest();
			if(request != null)
			{
				HttpSession session = request.getSession();
				return (User)session.getAttribute(Constants.SESSION_USER_KEY);
			}
		}
		return null;
	}
	
	public static String getLoginUserName()
	{
		User user = getLoginUser();
		if(user != null)
		{
			return user.getUserLogin();
		}
		return null;
	}
	
	public static boolean isAjax()
	{
		HttpServletRequest request = getRequest();
		if(request != null)
		{
			return request.getHeader("x-requested-with") != null;
		}
		
		return false;
	}
	
	public static void addCookie(HttpServletResponse response, String key, String value, int maxAge)
	{
		Cookie cookie = new Cookie(key,value);
		cookie.setPath("/");
		cookie.setMaxAge(maxAge);
		response.addCookie(cookie);
	}
	public static Cookie getCookie(HttpServletRequest request, String key)
	{
		Cookie cookie = null;
		Cookie[] cookies = request.getCookies();
		if(key != null && cookies != null && cookies.length > 0)
		{
			for(Cookie c : cookies)
			{
				if(key.equals(c.getName()))
				{
					cookie = c;
					break;
				}
			}
		}
		return cookie;
	}

}
