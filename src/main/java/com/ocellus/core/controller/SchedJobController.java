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
import com.ocellus.core.jobs.BaseJob;
import com.ocellus.core.model.CodeList;
import com.ocellus.core.model.ReferenceCache;
import com.ocellus.core.model.ResponseData;
import com.ocellus.core.model.SchedJob;
import com.ocellus.core.plugins.paging.model.PageResponse;
import com.ocellus.core.service.SchedJobService;

@Controller
@RequestMapping("/job")
public class SchedJobController extends BaseController
{
	@Autowired
	private SchedJobService schedJobService;
	@RequestMapping("/show")
	public ModelAndView showSched() throws BusinessException
	{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/job/job");
		mv.addObject("jobTriggerTypes", ReferenceCache.getCodeList("jobTriggerTypes"));
		return mv;
	}
	@RequestMapping("/search")
	@ResponseBody
	public PageResponse search(HttpServletRequest request) throws BusinessException
	{
		Map params = getParamMap(request);
		return getPageResponse(schedJobService.search(params));
	}
	@RequestMapping("/add")
	public ModelAndView add() throws BusinessException
	{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/job/jobDetail");
		List<CodeList> jobs = ReferenceCache.getCodeList("job");
		mv.addObject("jobs", jobs);
		mv.addObject("jobTriggerTypes", ReferenceCache.getCodeList("jobTriggerTypes"));
		mv.addObject("command", new SchedJob());
		return mv;
	}
	
	@RequestMapping("/edit")
	public ModelAndView edit(@RequestParam String schedJobId) throws BusinessException
	{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/job/jobDetail");
		SchedJob job = schedJobService.getById(schedJobId);
		mv.addObject("command", job);
		List<CodeList> jobs = ReferenceCache.getCodeList("job");
		mv.addObject("jobTriggerTypes", ReferenceCache.getCodeList("jobTriggerTypes"));
		mv.addObject("jobs", jobs);
		return mv;
	}
	@RequestMapping("/getConfigParams")
	@ResponseBody
	public ResponseData getConfigParams(@RequestParam String jobClass) 
	{
		ResponseData rtn = new ResponseData();
		try
		{
			Class clazz = Class.forName("com.ocellus.core.jobs."+jobClass);
			BaseJob job = (BaseJob)clazz.newInstance();
			rtn.setStatusSuccess();
			rtn.addUserData("configParams", job.getConfigParams());
		} catch (Exception e)
		{
			logger.error("获取参数错误", e);
			rtn.setStatusFailer();
		} 
		return rtn;
	}
	@RequestMapping("/save")
	@ResponseBody
	public ResponseData save(@ModelAttribute SchedJob schedJob)
	{
		ResponseData rtn = new ResponseData();
		try
		{
			schedJobService.save(schedJob);
			rtn.setStatusSuccess();
		} catch (BusinessException e)
		{
			logger.error("save failer", e);
			rtn.setStatusFailer();
			rtn.setMessage(e.getMessage());
		}
		return rtn;
	}
	
	
}
