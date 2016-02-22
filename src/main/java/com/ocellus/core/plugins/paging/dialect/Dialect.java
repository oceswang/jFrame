package com.ocellus.core.plugins.paging.dialect;

public abstract class Dialect
{
	public abstract String buildLimitString(String sql, int offset, int limit);
	public abstract String buildLimitString(String sql, int offset, int limit, String sidx, String sord);
	public String buildCountString(String sql)
	{
		return "select count(*) from ("+sql+") temp";
	}
}
