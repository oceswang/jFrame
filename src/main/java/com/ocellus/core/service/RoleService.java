package com.ocellus.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ocellus.core.dao.PermissionMapper;
import com.ocellus.core.dao.RoleMapper;
import com.ocellus.core.exception.BusinessException;
import com.ocellus.core.model.Permission;
import com.ocellus.core.model.Role;
import com.ocellus.core.util.ParamUtil;
import com.ocellus.core.util.StringUtil;

@Service
public class RoleService extends GenericService<Role>
{
	private RoleMapper mapper;
	@Autowired
	private PermissionMapper permissionMapper;
	@Autowired
	public void setMapper(RoleMapper mapper)
	{
		super.setBaseMapper(mapper);
		this.mapper = mapper;
	}
	
	public List<Permission> getRolePermission(String roleId)
	{
		return permissionMapper.search(ParamUtil.setParam("roleId", roleId));
	}
	
	public  void  savePermission(String roleId,String[] resourceIds) throws BusinessException
	{
		permissionMapper.deleteByRoleId(roleId);
		Permission permission = null;
		for(String resourceId : resourceIds)
		{
			permission = new Permission();
			permission.setPermissionId(StringUtil.getGUID());
			permission.setRoleId(roleId);
			permission.setResourceId(resourceId);
			permissionMapper.insert(permission);
		}
	}
}
