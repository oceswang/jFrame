package com.ocellus.core.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ocellus.core.model.ReferenceCache;
import com.ocellus.core.model.Resource;
import com.ocellus.core.model.ResponseData;
import com.ocellus.core.service.ResourceService;
import com.ocellus.core.util.StringUtil;

@Controller

public class PermissionController extends BaseController
{
	@Autowired
	private ResourceService resServ;
	@RequestMapping("/permission")
	public ModelAndView show()
	{
		ModelAndView mv = new ModelAndView();
		try
		{
			mv.setViewName("/permission/permission");
			List<Resource> resTree= resServ.search(new HashMap());
			String resTreeStr = new ObjectMapper().writeValueAsString(resTree);
			mv.addObject("resTree", resTreeStr);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return mv;
	}/*
	@RequestMapping("/resource/add")
	public ModelAndView addResource(@RequestParam String parentResourceCode)
	{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/permission/resourceDetail");
		Resource resource = new Resource();
		resource.setParentResourceCode(parentResourceCode);
		mv.addObject("resource", resource);
		mv.addObject("resourceTypeList", ReferenceCache.getCodeList("resource_type"));
		mv.addObject("projectList", ReferenceCache.getCodeList("project"));
		return mv;
	}
	@RequestMapping("/resource/edit")
	public ModelAndView editResource(@RequestParam String resourceCode)
	{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/permission/resourceDetail");
		Resource resource = resServ.getByCode(resourceCode);
		mv.addObject("resource", resource);
		mv.addObject("resourceTypeList", ReferenceCache.getCodeList("resource_type"));
		mv.addObject("projectList", ReferenceCache.getCodeList("project"));
		return mv;
	}
	@RequestMapping("/resource/save")
	@ResponseBody
	public ResponseData save(@ModelAttribute Resource resourceVO  )
	{
		ResponseData rep = new ResponseData();
		try
		{
			if(resourceVO != null)
			{
				if(!StringUtil.isEmpty(resourceVO.getResourceId()))
				{
					resServ.update(resourceVO);
				}
				else
				{
					resServ.insert(resourceVO);
				}
				rep.setStatusSuccess();
				rep.addUserData("res", resourceVO);
			}
			else
			{
				
			}
		} catch (Exception e)
		{
			rep.setStatusFailer();
			rep.setMessage(e.getMessage());
			logger.error(e);
		}
		
		return rep;
	}*/
}
