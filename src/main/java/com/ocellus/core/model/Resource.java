package com.ocellus.core.model;

import java.util.ArrayList;
import java.util.List;

public class Resource extends SuperModel
{
	private String resourceId;
	private String resourceCode;
	private String resourceName;
	private String resourceUrl;
	private String resourceTypeCode;
	private String parentResourceCode;
	private String projectCode;
	private Integer depth;
	private String resourceIcon;
	private String active;
	private List<Resource> children = new ArrayList<Resource>();
	public String getResourceId()
	{
		return resourceId;
	}
	public void setResourceId(String resourceId)
	{
		this.resourceId = resourceId;
	}
	public String getResourceCode()
	{
		return resourceCode;
	}
	public void setResourceCode(String resourceCode)
	{
		this.resourceCode = resourceCode;
	}
	public String getResourceName()
	{
		return resourceName;
	}
	public void setResourceName(String resourceName)
	{
		this.resourceName = resourceName;
	}
	public String getResourceUrl()
	{
		return resourceUrl;
	}
	public void setResourceUrl(String resourceUrl)
	{
		this.resourceUrl = resourceUrl;
	}
	public String getResourceTypeCode()
	{
		return resourceTypeCode;
	}
	public void setResourceTypeCode(String resourceTypeCode)
	{
		this.resourceTypeCode = resourceTypeCode;
	}
	public String getParentResourceCode()
	{
		return parentResourceCode;
	}
	public void setParentResourceCode(String parentResourceCode)
	{
		this.parentResourceCode = parentResourceCode;
	}
	public String getProjectCode()
	{
		return projectCode;
	}
	public void setProjectCode(String projectCode)
	{
		this.projectCode = projectCode;
	}
	public Integer getDepth()
	{
		return depth;
	}
	public void setDepth(Integer depth)
	{
		this.depth = depth;
	}
	public List<Resource> getChildren()
	{
		return children;
	}
	public void setChildren(List<Resource> children)
	{
		this.children = children;
	}
	public String getResourceIcon()
	{
		return resourceIcon;
	}
	public void setResourceIcon(String resourceIcon)
	{
		this.resourceIcon = resourceIcon;
	}
	public String getActive()
	{
		return active;
	}
	public void setActive(String active)
	{
		this.active = active;
	}
	@Override
	public void setId(String id)
	{
		setResourceId(id);
		
	}
	@Override
	public String getId()
	{
		// TODO Auto-generated method stub
		return getResourceId();
	}
	
	
	

}
