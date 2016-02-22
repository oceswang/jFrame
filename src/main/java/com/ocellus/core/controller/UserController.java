package com.ocellus.core.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ocellus.core.exception.BusinessException;
import com.ocellus.core.exception.SystemException;
import com.ocellus.core.model.ResponseData;
import com.ocellus.core.model.User;
import com.ocellus.core.plugins.paging.model.PageResponse;
import com.ocellus.core.service.UserService;
import com.ocellus.core.util.EncryptUtil;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController
{
	@Autowired
	private UserService userService;
	@RequestMapping("/show")
	public ModelAndView show()
	{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/user/user");
		return mv;
	}
	@ResponseBody
	@RequestMapping("/search")
	public PageResponse search(HttpServletRequest request) throws BusinessException
	{
		Map params = this.getParamMap(request);
		List<User> list = userService.search(params);
		return getPageResponse(list);
	}
	@RequestMapping("/add")
	public ModelAndView add()
	{
		ModelAndView mv = new ModelAndView();
		mv.addObject("command", new User());
		mv.setViewName("/user/userDetail");
		return mv;
	}
	
	@RequestMapping("/edit")
	public ModelAndView edit(@RequestParam String userId)
	{
		ModelAndView mv = new ModelAndView();
		try
		{
			User user = userService.getById(userId);
			mv.addObject("command", user);
			mv.setViewName("/user/userDetail");
		} catch (BusinessException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mv;
	}
	@RequestMapping("/save")
	@ResponseBody
	public ResponseData save(@ModelAttribute User userVO)
	{
		ResponseData rep = new ResponseData();
		try
		{
			if(userVO != null)
			{
				userVO.setPassword(EncryptUtil.encryptAes(userVO.getPassword(), userVO.getUserLogin()));
				userService.save(userVO);
				rep.setStatusSuccess();
				rep.addUserData("user", userVO);
			}
		} catch (Exception e)
		{
			rep.setStatusFailer();
			rep.setMessage(e.getMessage());
			logger.error(e);
		}
		return rep;
	}
	
	@RequestMapping("/showRoles")
	public ModelAndView showRoles(@RequestParam String userId)
	{
		ModelAndView mv = new ModelAndView();
		mv.addObject("userId", userId);
		mv.setViewName("/user/roles");
		return mv;
	}
}
