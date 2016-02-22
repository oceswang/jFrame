package com.ocellus.core.model;

import java.io.Serializable;
import java.util.Date;

public abstract class SuperModel implements Serializable
{
	private static final long serialVersionUID = 4492858928411049423L;
	private String addUser;
	private Date addDate;
	private String editUser;
	private Date editDate;
	public String getAddUser()
	{
		return addUser;
	}
	public void setAddUser(String addUser)
	{
		this.addUser = addUser;
	}
	public Date getAddDate()
	{
		return addDate;
	}
	public void setAddDate(Date addDate)
	{
		this.addDate = addDate;
	}
	public String getEditUser()
	{
		return editUser;
	}
	public void setEditUser(String editUser)
	{
		this.editUser = editUser;
	}
	public Date getEditDate()
	{
		return editDate;
	}
	public void setEditDate(Date editDate)
	{
		this.editDate = editDate;
	}

	public abstract void setId(String id);
	public abstract String getId();
}
