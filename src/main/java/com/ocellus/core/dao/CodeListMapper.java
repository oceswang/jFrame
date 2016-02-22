package com.ocellus.core.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ocellus.core.model.CodeList;
@Repository
public interface CodeListMapper extends GenericMapper<CodeList, String>
{
	public List<CodeList> getByGroupName(String groupName);
}
