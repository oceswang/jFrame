package com.ocellus.core.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ocellus.core.model.Resource;
@Repository
public interface ResourceMapper extends GenericMapper<Resource, String>
{
	public Resource getByCode(String code);
	public List<Resource> getByParentCode(String code);
}
