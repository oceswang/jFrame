package com.ocellus.core.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ocellus.core.dao.CodeListMapper;
import com.ocellus.core.exception.BusinessException;
import com.ocellus.core.model.CodeList;
import com.ocellus.core.util.StringUtil;

@Service
public class CodeListService extends GenericService<CodeList>
{
	private CodeListMapper codeListMapper;
	
	
	public int insert(CodeList codeList) throws BusinessException
	{
		int depth = 1;
		String parentId = codeList.getParentId();
		if(!StringUtil.isEmpty(parentId))
		{
			CodeList parent = getById(parentId);
			if(parent != null)
			{
				depth += 1;
			}
		}
		codeList.setDepth(depth);
		return super.insert(codeList);
	}
	public List<CodeList> getByGroupName(String groupName) throws BusinessException
	{
		return codeListMapper.getByGroupName(groupName);
	}
	@Autowired
	public void setCodeListMapper(CodeListMapper codeListMapper)
	{
		super.setBaseMapper(codeListMapper);
		this.codeListMapper = codeListMapper;
	}
}
