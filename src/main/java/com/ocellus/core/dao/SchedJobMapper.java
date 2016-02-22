package com.ocellus.core.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ocellus.core.model.SchedJob;
import com.ocellus.core.model.SchedJobHistory;

@Repository
public interface SchedJobMapper extends GenericMapper<SchedJob,String>
{
	public void insertHistory(SchedJobHistory history);
	public List<SchedJobHistory> searchHistory(Map params);
	public SchedJobHistory getHistoryById(String historyId);
}
