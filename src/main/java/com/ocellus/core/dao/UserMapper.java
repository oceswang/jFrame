package com.ocellus.core.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ocellus.core.model.User;
import com.ocellus.core.model.UserRole;

@Repository
public interface UserMapper extends GenericMapper<User, String>
{
	public User getByUserLogin(String userLogin);
	public void insertUserRole(List<UserRole> list);
	public void deleteUserRole(String userId);
}
