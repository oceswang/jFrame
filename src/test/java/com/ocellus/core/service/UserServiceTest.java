package com.ocellus.core.service;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ocellus.core.exception.BusinessException;
import com.ocellus.core.model.User;
import com.ocellus.core.util.EncryptUtil;

@ContextConfiguration(locations = { "classpath*:/spring/applicationContext.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceTest
{
	@Autowired
	private UserService userService;
	
	@Test
	@Rollback(false)
	public void insertTest() throws BusinessException
	{
		User user = null;
		
		for(int i=1;i<999;i++)
		{
			user = new User();
			user.setUserLogin("user"+i);
			user.setPassword(EncryptUtil.encryptAes("password"+i, user.getUserLogin()));
			user.setLatestLoginTime(new Date());
			user.setUserName("用户"+i);
			user.setUserStatus("1");
			userService.insert(user);
		}
	}
}
