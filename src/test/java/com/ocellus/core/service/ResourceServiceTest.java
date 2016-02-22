package com.ocellus.core.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ocellus.core.exception.BusinessException;
import com.ocellus.core.model.Resource;
import com.ocellus.core.util.Constants;

@ContextConfiguration(locations = { "classpath*:/spring/applicationContext.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class ResourceServiceTest
{
	@Autowired
	private ResourceService service;
	
	
	@Rollback(false)
	@Test
	public void testInsert() throws BusinessException
	{
		Resource res = null;
		
		res = new Resource();
		res.setResourceCode("1");
		res.setResourceName("所有资源");
		res.setResourceTypeCode(Constants.RESOURCE_TYPE_MENU);
		res.setProjectCode("001");
		res.setResourceUrl("");
		res.setParentResourceCode("-1");
		service.insert(res);
		
		res = new Resource();
		res.setResourceCode("permission");
		res.setResourceName("权限管理");
		res.setResourceTypeCode(Constants.RESOURCE_TYPE_SHORTCUT_MENU);
		res.setProjectCode("001");
		res.setResourceUrl("");
		res.setParentResourceCode("1");
		service.insert(res);
		
		res = new Resource();
		res.setResourceCode("res");
		res.setResourceName("资源管理");
		res.setResourceTypeCode(Constants.RESOURCE_TYPE_SHORTCUT_MENU);
		res.setProjectCode("001");
		res.setResourceUrl("");
		res.setParentResourceCode("permission");
		service.insert(res);
		
		res = new Resource();
		res.setResourceCode("user");
		res.setResourceName("用户管理");
		res.setResourceTypeCode(Constants.RESOURCE_TYPE_SHORTCUT_MENU);
		res.setProjectCode("001");
		res.setResourceUrl("");
		res.setParentResourceCode("permission");
		service.insert(res);
		
		res = new Resource();
		res.setResourceCode("role");
		res.setResourceName("角色管理");
		res.setResourceTypeCode(Constants.RESOURCE_TYPE_SHORTCUT_MENU);
		res.setProjectCode("001");
		res.setResourceUrl("");
		res.setParentResourceCode("permission");
		service.insert(res);
	}
	

}
