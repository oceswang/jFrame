package com.ocellus.core.model;


public class CodeList extends SuperModel
{
	private String codeListId;
	private String parentId;
	private String codeKey;
	private String codeValue;
	private String groupName;
	private Integer depth;
	private Integer sortOrder;
	private String active;
	@Override
	public void setId(String id)
	{
		setCodeListId(id);
	}

	@Override
	public String getId()
	{
		return getCodeListId();
	}

	public String getCodeListId()
	{
		return codeListId;
	}

	public void setCodeListId(String codeListId)
	{
		this.codeListId = codeListId;
	}

	public String getCodeKey()
	{
		return codeKey;
	}

	public void setCodeKey(String codeKey)
	{
		this.codeKey = codeKey;
	}

	public String getCodeValue()
	{
		return codeValue;
	}

	public void setCodeValue(String codeValue)
	{
		this.codeValue = codeValue;
	}

	public String getGroupName()
	{
		return groupName;
	}

	public void setGroupName(String groupName)
	{
		this.groupName = groupName;
	}

	public Integer getDepth()
	{
		return depth;
	}

	public void setDepth(Integer depth)
	{
		this.depth = depth;
	}

	public Integer getSortOrder()
	{
		return sortOrder;
	}

	public void setSortOrder(Integer sortOrder)
	{
		this.sortOrder = sortOrder;
	}

	public String getParentId()
	{
		return parentId;
	}

	public void setParentId(String parentId)
	{
		this.parentId = parentId;
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
