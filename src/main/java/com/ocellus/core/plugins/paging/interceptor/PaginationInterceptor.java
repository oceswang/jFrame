package com.ocellus.core.plugins.paging.interceptor;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import org.apache.ibatis.executor.ErrorContext;
import org.apache.ibatis.executor.ExecutorException;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.ParameterMode;
import org.apache.ibatis.mapping.ResultMap;
import org.apache.ibatis.mapping.ResultMapping;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.property.PropertyTokenizer;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.apache.ibatis.scripting.xmltags.ForEachSqlNode;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.type.TypeHandler;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.apache.log4j.Logger;

import com.ocellus.core.plugins.paging.dialect.Dialect;
import com.ocellus.core.plugins.paging.model.PageRequest;
import com.ocellus.core.plugins.paging.model.PageResponse;
import com.ocellus.core.util.StringUtil;

@Intercepts({ @Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class }) })
public class PaginationInterceptor implements Interceptor {
	private final static Logger logger = Logger.getLogger(PaginationInterceptor.class);
	private Dialect dialect;

	@Override
	public Object intercept(Invocation inv) throws Throwable {
		InvocationWrapper invocation = new InvocationWrapper(inv);
		//没有设置分页功能，拦截器不进行分页
		PageRequest req = PageRequest.get();
		if (!req.isPaging()) {
			return invocation.proceed();
		}
		//Fix issue: paging function do not need to involved in relationship query 
		req.setPaging(false);
		
		StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
		BoundSql boundSql = statementHandler.getBoundSql();
		if (boundSql == null || boundSql.getSql() == null || "".equals(boundSql.getSql()))
			return null;
		String orignalSting = boundSql.getSql();
		//如果当前是INSERT、UPDATE、INSERT操作，拦截器不进行分页
		if (!(orignalSting.toUpperCase().trim().startsWith("SELECT") || orignalSting.toUpperCase().trim().startsWith("WITH"))){
			return invocation.proceed();
		}
		MetaObject metaStatementHandler = MetaObject.forObject(statementHandler, new DefaultObjectFactory(), new DefaultObjectWrapperFactory());
		MappedStatement mappedStatement = (MappedStatement) metaStatementHandler.getValue("delegate.mappedStatement");
		Object parameterObject = boundSql.getParameterObject();
		List<ResultMap> maps = mappedStatement.getResultMaps();

		// get total record number
		String countSql = dialect.buildCountString(orignalSting);
		if (logger.isDebugEnabled()) {
			logger.debug("Count SQL: " + countSql);
		}
		int total = 0;
		Connection connection = null;
		PreparedStatement countStmt = null;
		ResultSet rs = null;
		try
		{
			connection = mappedStatement.getConfiguration().getEnvironment().getDataSource().getConnection();
			countStmt = connection.prepareStatement(countSql.toString());
			BoundSql countBS = mappedStatement.getBoundSql(parameterObject);
			setParameters(countStmt, mappedStatement, countBS, parameterObject);
			rs = countStmt.executeQuery();
			if (rs.next()) {
				total = rs.getInt(1);
			}
		} catch (Exception e)
		{
			logger.error(e.getMessage(), e);
		}
		finally
		{
			if(rs != null) rs.close();
			if(countStmt != null) countStmt.close();
			if(connection != null) connection.close();
		}

		// generate reponse
		PageResponse resp = PageResponse.get();
		resp.setRecords(total);
		resp.setPage(req.getPage());
		int numOfPages = 1;
		if (total > 0) {
			numOfPages = total % req.getRows() == 0 ? total / req.getRows() : total / req.getRows() + 1;
		}
		resp.setTotal(numOfPages);

		// get sord column
		String pageSql = null;
		String sidx = req.getSidx();
		String sidColumn = null;
		if (!StringUtil.isEmpty(sidx)) {
			for (ResultMap map : maps) {
				List<ResultMapping> propMaps = map.getPropertyResultMappings();
				for (ResultMapping propMap : propMaps) {
					String prop = propMap.getProperty();
					if (sidx.equalsIgnoreCase(prop)) {
						sidColumn = propMap.getColumn();
						break;
					}
				}
			}
		}
		if(StringUtil.isEmpty(sidColumn) && !StringUtil.isEmpty(sidx)){
			sidColumn = sidx;
		}
		if (!StringUtil.isEmpty(sidColumn)) {
			pageSql = dialect.buildLimitString(orignalSting, (resp.getPage() - 1) * req.getRows(), req.getRows(), sidColumn, req.getSord());
		} else {
			pageSql = dialect.buildLimitString(orignalSting, (resp.getPage() - 1) * req.getRows(), req.getRows());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("Paging SQL: " + pageSql);
		}
		metaStatementHandler.setValue("delegate.boundSql.sql", pageSql);
		metaStatementHandler.setValue("delegate.rowBounds.offset", RowBounds.NO_ROW_OFFSET);
		metaStatementHandler.setValue("delegate.rowBounds.limit", RowBounds.NO_ROW_LIMIT);
		return invocation.proceed();
	}

	@Override
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	@Override
	public void setProperties(Properties properties) {
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void setParameters(PreparedStatement ps, MappedStatement mappedStatement, BoundSql boundSql, Object parameterObject) throws SQLException {
		ErrorContext.instance().activity("setting parameters").object(mappedStatement.getParameterMap().getId());
		List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
		if (parameterMappings != null) {
			Configuration configuration = mappedStatement.getConfiguration();
			TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
			MetaObject metaObject = parameterObject == null ? null : configuration.newMetaObject(parameterObject);
			for (int i = 0; i < parameterMappings.size(); i++) {
				ParameterMapping parameterMapping = parameterMappings.get(i);
				if (parameterMapping.getMode() != ParameterMode.OUT) {
					Object value;
					String propertyName = parameterMapping.getProperty();
					PropertyTokenizer prop = new PropertyTokenizer(propertyName);
					if (parameterObject == null) {
						value = null;
					} else if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
						value = parameterObject;
					} else if (boundSql.hasAdditionalParameter(propertyName)) {
						value = boundSql.getAdditionalParameter(propertyName);
					} else if (propertyName.startsWith(ForEachSqlNode.ITEM_PREFIX) && boundSql.hasAdditionalParameter(prop.getName())) {
						value = boundSql.getAdditionalParameter(prop.getName());
						if (value != null) {
							value = configuration.newMetaObject(value).getValue(propertyName.substring(prop.getName().length()));
						}
					} else {
						value = metaObject == null ? null : metaObject.getValue(propertyName);
					}
					TypeHandler typeHandler = parameterMapping.getTypeHandler();
					if (typeHandler == null) {
						throw new ExecutorException("There was no TypeHandler found for parameter " + propertyName + " of statement " + mappedStatement.getId());
					}
					typeHandler.setParameter(ps, i + 1, value, parameterMapping.getJdbcType());
				}
			}
		}
	}

	public void setDialect(Dialect dialect) {
		this.dialect = dialect;
	}
	/**
	 * 统计每次执行SQL花费时间
	 */
	private class InvocationWrapper 
	{
		private Invocation invocation;
		public InvocationWrapper(Invocation invocation)
		{
			this.invocation = invocation;
		}
		
		public Object getTarget()
		{
			return invocation.getTarget();
		}
		public Object proceed() throws InvocationTargetException, IllegalAccessException
		{
			long start = System.currentTimeMillis();
			String statementId = null;
			String sql = null;
			if(logger.isDebugEnabled())
			{
				StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
				MetaObject metaStatementHandler = MetaObject.forObject(statementHandler, new DefaultObjectFactory(), new DefaultObjectWrapperFactory());
				MappedStatement mappedStatement = (MappedStatement) metaStatementHandler.getValue("delegate.mappedStatement");
				statementId = mappedStatement.getId();
				logger.debug("DAO - Start Execute method:"+statementId);
				
				BoundSql boundSql = statementHandler.getBoundSql();
				logger.debug("DAO - SQL:"+boundSql.getSql());
			}
			Object obj = invocation.proceed();
			long proced = System.currentTimeMillis()-start;
			if(logger.isDebugEnabled())
			{
				logger.debug("DAO - End Execute method:"+statementId +". Time:"+proced+"ms.");
			}
			return obj;
		}
		
		
	}
}
