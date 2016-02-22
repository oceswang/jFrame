package com.ocellus.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ocellus.core.dao.UserMapper;
import com.ocellus.core.exception.BusinessException;
import com.ocellus.core.model.User;
import com.ocellus.core.util.EncryptUtil;
import com.ocellus.core.util.StringUtil;

@Service
public class UserService extends GenericService<User>
{
	private UserMapper userMapper;
	@Autowired
	public void setUserMapper(UserMapper userMapper)
	{
		super.setBaseMapper(userMapper);
		this.userMapper = userMapper;
	}
	
	public User getByUserLogin(String userLogin)
	{
		return userMapper.getByUserLogin(userLogin);
	}
	
	public User login(String userLogin,String password) throws BusinessException
	{
		if(StringUtil.isEmpty(userLogin) || StringUtil.isEmpty(password))
		{
			throw new BusinessException("请输入用户名密码。");
		}
		User user = getByUserLogin(userLogin);
		if(user == null || !EncryptUtil.encryptAes(password, userLogin).equals(user.getPassword()))
		{
			throw new BusinessException("用户名或密码不正确。");
		}
		return user;
	}
	
}
