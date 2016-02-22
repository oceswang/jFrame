package com.ocellus.core.service;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.ocellus.core.dao.GenericMapper;
import com.ocellus.core.exception.BusinessException;
import com.ocellus.core.model.SuperModel;
import com.ocellus.core.util.StringUtil;
import com.ocellus.core.util.WebUtil;


public abstract class GenericService<T extends SuperModel>
{
	private GenericMapper<T, String> baseMapper;

	public void setBaseMapper(GenericMapper<T, String> baseMapper)
	{
		this.baseMapper = baseMapper;
	}
	
	public int insert(T model) throws BusinessException
	{
		model.setId(StringUtil.getGUID());
		Date now = new Date();
		model.setAddDate(now);
		model.setAddUser(WebUtil.getLoginUserName());
		model.setEditDate(now);
		model.setEditUser(WebUtil.getLoginUserName());
		int rtn = baseMapper.insert(model);
		afterInsert(model);
		return rtn;
	}
	
	public int delete(String id) throws BusinessException
	{
		return baseMapper.delete(id);
	}
	
	public int update(T model) throws BusinessException
	{
		Date now = new Date();
		model.setEditDate(now);
		model.setEditUser(WebUtil.getLoginUserName());
		return baseMapper.update(model);
	}
	
	public List<T> search(Map params) throws BusinessException
	{
		return baseMapper.search(params);
	}
	
	public T getById(String id) throws BusinessException
	{
		return baseMapper.getById(id);
	}
	
	protected void afterInsert(T model) throws BusinessException{}
	
	
	public void save(T model) throws BusinessException
	{
		if(model != null)
		{
			if(StringUtil.isEmpty(model.getId()))
			{
				insert(model);
			}
			else
			{
				update(model);
			}
		}
	}

	public void insertBatch(List<T> list)
	{
		// TODO Auto-generated method stub
		
	}

	public void insertBatch(Map params)
	{
		// TODO Auto-generated method stub
		
	}

	public List<T> getPageList(Map params)
	{
		// TODO Auto-generated method stub
		return null;
	}
	
}
