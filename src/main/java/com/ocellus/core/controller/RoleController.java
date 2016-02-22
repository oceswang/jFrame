package com.ocellus.core.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
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
import com.ocellus.core.exception.SystemException;
import com.ocellus.core.model.Permission;
import com.ocellus.core.model.Resource;
import com.ocellus.core.model.ResponseData;
import com.ocellus.core.model.Role;
import com.ocellus.core.plugins.paging.model.PageResponse;
import com.ocellus.core.service.ResourceService;
import com.ocellus.core.service.RoleService;

@Controller
@RequestMapping("/role")
public class RoleController extends BaseController
{
	private static final Logger logger = Logger.getLogger(RoleController.class);
	@Autowired
	private RoleService roleService;
	@Autowired
	private ResourceService resServ;
	
	@RequestMapping("/show")
	public ModelAndView show() throws BusinessException
	{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/role/role");
		return mv;
	}
	@ResponseBody
	@RequestMapping("/search")
	public PageResponse search(HttpServletRequest request) throws BusinessException
	{
		Map params = this.getParamMap(request);
		List<Role> list = roleService.search(params);
		return getPageResponse(list);
	}
	@RequestMapping("/add")
	public ModelAndView add()
	{
		ModelAndView mv = new ModelAndView();
		mv.addObject("command", new Role());
		mv.setViewName("/role/roleDetail");
		return mv;
	}
	
	@RequestMapping("/edit")
	public ModelAndView edit(@RequestParam String roleId) throws BusinessException
	{
		ModelAndView mv = new ModelAndView();
		Role role = roleService.getById(roleId);
		mv.addObject("command", role);
		mv.setViewName("/role/roleDetail");
		return mv;
	}
	@RequestMapping("/save")
	@ResponseBody
	public ResponseData save(@ModelAttribute Role roleVO)
	{
		ResponseData rep = new ResponseData();
		try
		{
			if(roleVO != null)
			{
				roleService.save(roleVO);
				rep.setStatusSuccess();
				rep.addUserData("role", roleVO);
			}
		} catch (BusinessException e)
		{
			rep.setStatusFailer();
			rep.setMessage(e.getMessage());
			logger.error(e);
		}
		return rep;
	}
	
	@RequestMapping("/showPermissionDialog")
	public ModelAndView showPermissionDialog(@RequestParam String roleId)
	{
		ModelAndView mv = new ModelAndView();
		try
		{
			mv.setViewName("/role/permission");
			mv.addObject("roleId", roleId);
			List<Resource> tree = new ArrayList<Resource>();
			Resource root = new Resource();
			root.setResourceCode("-1");
			root.setResourceName("所有资源");
			tree.add(root);
			tree.addAll(resServ.search(new HashMap()));
			ObjectMapper mapper =  new ObjectMapper();
			mv.addObject("resTree", mapper.writeValueAsString(tree));
			List<Permission> permissions = roleService.getRolePermission(roleId);
			mv.addObject("permissions", mapper.writeValueAsString(permissions));
		} catch (BusinessException e)
		{
			e.printStackTrace();
		} catch (JsonProcessingException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mv;
	}
	
	@RequestMapping("/savePermission")
	@ResponseBody
	public ResponseData savePermission(@RequestParam String roleId,@RequestParam("resourceIds[]") String[] resourceIds)
	{
		ResponseData status = new ResponseData();
		try
		{
			roleService.savePermission(roleId, resourceIds);
			status.setStatusSuccess();;
		}
		catch(BusinessException e)
		{
			String message = "保存失败";
			logger.error(message, e);
			status.setStatusFailer();;
			status.setMessage(message);
		}
		return status;
	}
}
