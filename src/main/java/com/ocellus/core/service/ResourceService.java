package com.ocellus.core.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ocellus.core.dao.ResourceMapper;
import com.ocellus.core.exception.BusinessException;
import com.ocellus.core.model.Resource;
import com.ocellus.core.util.StringUtil;

@Service
public class ResourceService extends GenericService<Resource>
{
	private ResourceMapper resourceMapper;
	@Autowired
	public void setResourceMapper(ResourceMapper resourceMapper)
	{
		super.setBaseMapper(resourceMapper);
		this.resourceMapper = resourceMapper;
	}
	
	public Resource getByCode(String code) throws BusinessException
	{
		return resourceMapper.getByCode(code);
	}
	public List<Resource> getByParentCode(String code) throws BusinessException
	{
		return resourceMapper.getByParentCode(code);
	}
	
	public int insert(Resource resource) throws BusinessException
	{
		String parentCode = resource.getParentResourceCode();
		int depth = 1;
		if(!StringUtil.isEmpty(parentCode))
		{
			Resource parent = resourceMapper.getByCode(parentCode);
			if(parent != null)
			{
				depth = parent.getDepth()+1;
			}
		}
		resource.setDepth(depth);
		return super.insert(resource);
	}
	
	
	
}
