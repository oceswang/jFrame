package com.ocellus.core.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ocellus.core.exception.BusinessException;
import com.ocellus.core.model.Resource;
import com.ocellus.core.model.ResponseData;
import com.ocellus.core.model.User;
import com.ocellus.core.service.ResourceService;
import com.ocellus.core.service.UserService;
import com.ocellus.core.util.AppConfigUtil;
import com.ocellus.core.util.Constants;
import com.ocellus.core.util.StringUtil;
import com.ocellus.core.util.WebUtil;

@Controller
public class MainController extends BaseController
{
	@Autowired
	private ResourceService resServ;
	@Autowired
	private UserService userService;
	@RequestMapping("/index")
	public ModelAndView index()
	{
		ModelAndView mv = new ModelAndView();
		try
		{
			mv.setViewName("index");
			List<Resource> resTree= resServ.search(new HashMap());
			String resTreeStr = new ObjectMapper().writeValueAsString(resTree);
			mv.addObject("resTree", resTreeStr);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return mv;
	}
	
	@RequestMapping("/login")
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("login");
		Cookie cookie = WebUtil.getCookie(request, "userName");
		if(cookie != null)
		{
			mv.addObject("userName", cookie.getValue());
			mv.addObject("remberMeChk", "checked");
		}
		return mv;
	}
	
	@RequestMapping("/auth")
	@ResponseBody
	public ResponseData auth(HttpServletRequest request,HttpServletResponse response)
	{
		ResponseData res = new ResponseData();
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String remberMe = request.getParameter("remberMe");
		try
		{
			User user = userService.login(userName, password);
			int validDays = 0;
			if(!StringUtil.isEmpty(remberMe))
			{
				validDays = AppConfigUtil.getInt("cookie.validDays", 15);
			}
			WebUtil.addCookie(response, "userName", userName, validDays*24*60*60);
			res.setStatusSuccess();
			request.getSession(true).setAttribute(Constants.SESSION_USER_KEY, user);
		} catch (BusinessException e)
		{
			res.setStatusFailer();
			res.setMessage(e.getMessage());
			logger.error(e.getMessage(), e);
		}
		return res;
	}
	
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request,HttpServletResponse response)
	{
		request.getSession(true).removeAttribute(Constants.SESSION_USER_KEY);
		return "ace-index";
	}
	
	@RequestMapping("/dashboard")
	public String dashboard()
	{
		return "ace-dashboard";
	}
}
