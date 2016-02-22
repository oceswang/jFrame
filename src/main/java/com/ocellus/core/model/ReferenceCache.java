package com.ocellus.core.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.ocellus.core.exception.BusinessException;
import com.ocellus.core.service.CodeListService;
import com.ocellus.core.util.ApplicationContextHolder;

public class ReferenceCache
{
	private final static Logger logger = Logger.getLogger(ReferenceCache.class);
	private static Map<String, ReferenceCache> cacheMap = new HashMap<String, ReferenceCache>();
	private Map<String,Object> map = new HashMap<String,Object>();
	private String key;
	private String NO_ARGUMENT_KEY = "LMEP";
	static 
	{
		cacheMap = Collections.synchronizedMap(cacheMap);
	}
	private ReferenceCache(){}
	public static synchronized ReferenceCache getReferenceCache(String key) 
	{
		key = key.toLowerCase();
		final long start = System.currentTimeMillis();
		ReferenceCache cache =  cacheMap.get(key);
		final boolean containsCache = (cache != null);
		if (!containsCache) {
			cache = new ReferenceCache();
			cache.key = key;
			cacheMap.put(key, cache);
			cache.map = Collections.synchronizedMap(cache.map);
		}
		if (logger.isDebugEnabled()) {
			logger.debug("Time taken to execute ReferenceCache.getReferenceCache(String key):Key=" + key + ":containsCache:" + containsCache + ":"
					+ (System.currentTimeMillis() - start));
		}
		return cache;
	}
	public void syncReset()
	{
		map.clear();
	}
	
	public Collection add(Collection collection)
	{
		map.put(NO_ARGUMENT_KEY, collection);
		return collection;
	}

	public Collection add(String key, Collection collection)
	{
		map.put(key, collection);
		return collection;
	}
	public Map addMap(String key, Map obj)
	{
		map.put(key, obj);
		return obj;
	}

	public Collection add(Object[] arguments, Collection collection)
	{
		add(generateKey(arguments), collection);
		return collection;
	}
	public Collection get(){ return (Collection)map.get(NO_ARGUMENT_KEY); }
	public Map getMap(){ return (Map)map.get(NO_ARGUMENT_KEY); }
	public Collection get(String key){ return (Collection)map.get(key); }
	public Collection get(Object... arguments){ return get(generateKey(arguments)); }
	
	public Map getMap(String key){ return (Map)map.get(key); }
	private String generateKey(Object... arguments)
	{
		StringBuffer key = new StringBuffer();
		for(int i=0;i<arguments.length;i++)
			key.append(""+arguments[i]).append('|');

		return key.toString();
	}
	
	public static List<CodeList> getCodeList(String groupName) throws BusinessException
	{
		ReferenceCache cache = ReferenceCache.getReferenceCache("codeList");
		Object[] key = { groupName};
		Collection result = cache.get(key);
		if(result == null)
		{
			CodeListService service = ApplicationContextHolder.getBean(CodeListService.class);
			List<CodeList> list = service.getByGroupName(groupName);
			result = cache.add(key, list);
		}
		return (List<CodeList>)result;
	}

}
