package com.ocellus.core.dao;

import org.springframework.stereotype.Repository;

import com.ocellus.core.model.Permission;

@Repository
public interface PermissionMapper extends GenericMapper<Permission, String>
{
	public void deleteByRoleId(String roleId);
}
