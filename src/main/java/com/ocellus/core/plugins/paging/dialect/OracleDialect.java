package com.ocellus.core.plugins.paging.dialect;

public class OracleDialect extends Dialect
{

	@Override
	public String buildLimitString(String sql, int offset, int limit)
	{
		final StringBuilder pagingSelect = new StringBuilder( sql.length()+100 );
		pagingSelect.append( "select * from ( select * from ( select rownum rownum_, row_.* from ( " );
		pagingSelect.append( sql );
		pagingSelect.append( " ) row_ ) where rownum_ <= "+(offset+limit)+") where rownum_ > "+offset );
		return pagingSelect.toString();
	}

	@Override
	public String buildLimitString(String sql, int offset, int limit, String sidx, String sord)
	{
		if(sql.indexOf("order by")>0)
		{
			sql = sql.replaceAll("order by", " order by "+ sidx+" "+sord+", ");
		}
		else
		{
			sql += " order by "+ sidx+" "+sord;
		}
		return this.buildLimitString(sql, offset, limit);
	}

}
