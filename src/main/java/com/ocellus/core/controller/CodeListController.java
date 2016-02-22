package com.ocellus.core.controller;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ocellus.core.exception.BusinessException;
import com.ocellus.core.model.CodeList;
import com.ocellus.core.model.ResponseData;
import com.ocellus.core.service.CodeListService;
import com.ocellus.core.util.StringUtil;

@Controller
@RequestMapping("/codelist")
public class CodeListController extends BaseController
{
	private static final Logger logger = Logger.getLogger(CodeListController.class);
	@Autowired
	private CodeListService codeListService;
	@RequestMapping("/show")
	public ModelAndView show()
	{
		ModelAndView mv = new ModelAndView();
		try
		{
			mv.setViewName("/codelist/codelist");
			List<CodeList> list = codeListService.search(new HashMap());
			mv.addObject("codedata", StringUtil.toJsonString(list));
		} catch (Exception e)
		{
			e.printStackTrace();
		}
				
		return mv;
	}
	@RequestMapping("/add")
	public ModelAndView add(@RequestParam String parentId)
	{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/codelist/codelistDetail");
		CodeList codeList = new CodeList();
		codeList.setParentId(parentId);
		mv.addObject("codeList", codeList);
		return mv;
	}
	
	@RequestMapping("/edit")
	public ModelAndView edit(@RequestParam String codeListId)
	{
		ModelAndView mv = new ModelAndView();
		try
		{
			mv.setViewName("/codelist/codelistDetail");
			CodeList codeList = codeListService.getById(codeListId);
			mv.addObject("codeList", codeList);
		} catch (BusinessException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mv;
	}
	@RequestMapping("/save")
	@ResponseBody
	public ResponseData save(@ModelAttribute CodeList codelist)
	{
		ResponseData rtn = new ResponseData();
		try
		{
			if(StringUtil.isEmpty(codelist.getCodeListId()))
			{
				codeListService.insert(codelist);
			}
			else
			{
				codeListService.update(codelist);
			}
			rtn.setStatusSuccess();rtn.addUserData("codeList", codelist);
		} catch (Exception e)
		{
			rtn.setStatusFailer();
			rtn.setMessage(e.getMessage());
			logger.error(e);
		}
		
		return rtn;
	}
	
	
	
}
