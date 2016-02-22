package com.ocellus.core.model;

public class Permission extends SuperModel
{
	private String permissionId;
	private String roleId;
	private String resourceId;
	
	@Override
	public void setId(String id)
	{
		// TODO Auto-generated method stub
		setPermissionId(id);
	}

	@Override
	public String getId()
	{
		// TODO Auto-generated method stub
		return getPermissionId();
	}

	public String getPermissionId()
	{
		return permissionId;
	}

	public void setPermissionId(String permissionId)
	{
		this.permissionId = permissionId;
	}

	public String getRoleId()
	{
		return roleId;
	}

	public void setRoleId(String roleId)
	{
		this.roleId = roleId;
	}

	public String getResourceId()
	{
		return resourceId;
	}

	public void setResourceId(String resourceId)
	{
		this.resourceId = resourceId;
	}

}
