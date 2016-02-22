package com.ocellus.core.model;

import java.util.Date;

public class User extends SuperModel
{
	private String userId;
	private String userLogin;
	private String password;
	private String userName;
	private String userStatus;
	private Date latestLoginTime;

	@Override
	public void setId(String id)
	{
		setUserId(id);
		
	}

	@Override
	public String getId()
	{
		return getUserId();
	}

	public String getUserId()
	{
		return userId;
	}

	public void setUserId(String userId)
	{
		this.userId = userId;
	}

	public String getUserLogin()
	{
		return userLogin;
	}

	public void setUserLogin(String userLogin)
	{
		this.userLogin = userLogin;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public Date getLatestLoginTime()
	{
		return latestLoginTime;
	}

	public void setLatestLoginTime(Date latestLoginTime)
	{
		this.latestLoginTime = latestLoginTime;
	}

	public String getUserStatus()
	{
		return userStatus;
	}

	public void setUserStatus(String userStatus)
	{
		this.userStatus = userStatus;
	}

	public String getUserName()
	{
		return userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}
	
	
	
}
