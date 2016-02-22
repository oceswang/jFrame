package com.ocellus.core.controller;

import java.util.ArrayList;
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
import com.ocellus.core.exception.BusinessException;
import com.ocellus.core.model.ReferenceCache;
import com.ocellus.core.model.Resource;
import com.ocellus.core.model.ResponseData;
import com.ocellus.core.service.ResourceService;

@Controller
@RequestMapping("/resource")
public class ResourceController extends BaseController
{
	@Autowired
	private ResourceService resServ;
	@RequestMapping("/show")
	public ModelAndView show()
	{
		ModelAndView mv = new ModelAndView();
		try
		{
			mv.setViewName("/resource/resource");
			List<Resource> tree = new ArrayList<Resource>();
			Resource root = new Resource();
			root.setResourceCode("-1");
			root.setResourceName("所有资源");
			tree.add(root);
			tree.addAll(resServ.search(new HashMap()));
			String resTreeStr = new ObjectMapper().writeValueAsString(tree);
			mv.addObject("resTree", resTreeStr);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return mv;
	}
	
	@RequestMapping("/add")
	public ModelAndView addResource(@RequestParam String parentResourceCode)
	{
		ModelAndView mv = new ModelAndView();
		try
		{
			mv.setViewName("/resource/resourceDetail");
			Resource resource = new Resource();
			resource.setParentResourceCode(parentResourceCode);
			mv.addObject("resource", resource);
			mv.addObject("resourceTypeList", ReferenceCache.getCodeList("resource_type"));
			mv.addObject("projectList", ReferenceCache.getCodeList("project"));
		} catch (BusinessException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mv;
	}
	@RequestMapping("/edit")
	public ModelAndView editResource(@RequestParam String resourceCode)
	{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/resource/resourceDetail");
		try
		{
			Resource resource = resServ.getByCode(resourceCode);
			mv.addObject("resource", resource);
			mv.addObject("resourceTypeList", ReferenceCache.getCodeList("resource_type"));
			mv.addObject("projectList", ReferenceCache.getCodeList("project"));
		} catch (BusinessException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mv;
	}
	@RequestMapping("/save")
	@ResponseBody
	public ResponseData save(@ModelAttribute Resource resourceVO  )
	{
		ResponseData rep = new ResponseData();
		try
		{
			if(resourceVO != null)
			{
				resServ.save(resourceVO);
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
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public ResponseData delete(@RequestParam String resourceId)
	{
		ResponseData rtn = new ResponseData();
		try
		{
			resServ.delete(resourceId);
			rtn.setStatusSuccess();
		} catch (Exception e)
		{
			rtn.setStatusFailer();
			rtn.setMessage(e.getMessage());
			logger.error("删除失败", e);
		}
		return rtn;
	}
}
