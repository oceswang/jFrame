package com.ocellus.core.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.ocellus.core.model.SuperModel;

public interface GenericMapper <T extends SuperModel, PK extends Serializable>
{
	public int insert(T model);
	
	public int delete(PK id);

	public int update(T model);
	
	public T getById(PK id);
	
	public List<T> search(Map map);

	public void insertBatch(List<T> list);
	
	public void insertBatch(Map params);

	public List<T> getPageList(Map params);

}
