package com.ocellus.core.plugins.typehandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;
@MappedTypes(Map.class)
public class MapTypeHandler implements TypeHandler<Map>
{

	@Override
	public void setParameter(PreparedStatement ps, int i, Map parameter, JdbcType jdbcType) throws SQLException
	{
		ps.setObject(i, parameter);
	}

	@Override
	public Map getResult(ResultSet rs, String columnName) throws SQLException
	{
		Map map = null;
		try
		{
			InputStream is = rs.getBinaryStream(columnName);
			if (is != null)
			{
				ObjectInputStream in = new ObjectInputStream(is);
				map = (Map) in.readObject();
				in.close();
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return map;
	}

	@Override
	public Map getResult(ResultSet rs, int columnIndex) throws SQLException
	{
		Map map = null;
		try
		{
			InputStream is = rs.getBinaryStream(columnIndex);
			if (is != null)
			{
				ObjectInputStream in = new ObjectInputStream(is);
				map = (Map) in.readObject();
				in.close();
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return map;
	}

	@Override
	public Map getResult(CallableStatement cs, int columnIndex) throws SQLException
	{
		Object obj = cs.getObject(columnIndex);
		if(obj != null)
		{
			return (Map)obj;
		}
		return null;
	}

}
