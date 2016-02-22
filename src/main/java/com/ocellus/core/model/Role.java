package com.ocellus.core.model;

public class Role extends SuperModel
{

	private String roleId;
	private String roleCode;
	private String roleName;
	private String roleDesc;
	private String active;
	@Override
	public void setId(String id)
	{
		setRoleId(id);
	}

	@Override
	public String getId()
	{
		return getRoleId();
	}

	public String getRoleId()
	{
		return roleId;
	}

	public void setRoleId(String roleId)
	{
		this.roleId = roleId;
	}

	public String getRoleCode()
	{
		return roleCode;
	}

	public void setRoleCode(String roleCode)
	{
		this.roleCode = roleCode;
	}

	public String getRoleName()
	{
		return roleName;
	}

	public void setRoleName(String roleName)
	{
		this.roleName = roleName;
	}

	public String getRoleDesc()
	{
		return roleDesc;
	}

	public void setRoleDesc(String roleDesc)
	{
		this.roleDesc = roleDesc;
	}

	public String getActive()
	{
		return active;
	}

	public void setActive(String active)
	{
		this.active = active;
	}
	
	

}
