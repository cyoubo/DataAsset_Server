package com.geoq.framework.request;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpEntity;

/***
 * 
 * @author Administrator
 *
 */
public abstract class HttpRequestBaseCommand implements HttpRequestCommand
{
	private Map<String, String> headerMap;
	
	private Map<String, Object> queryParams;
	
	private HttpEntity bodyEntry;
	
	/***
	 * 向参数中填装需要制定的Header参数，或者通过onAddHeader
	 * @param headerMap 用于记录Header项的字典
	 * @see onAddHeader
	 */
	public abstract void onGenerateHeaderMap(Map<String, String> headerMap);
	/***
	 * 向参数中填装需要制定的Query参数，或者通过onAddQueryParam
	 * @param queryParams 用于记录queryParam项的字典
	 * @see onAddQueryParam
	 */
	public abstract void onGenerateQueryParams(Map<String, Object> queryParams);
	
	/***
	 * 生成http请求的body参数<br>
	 * 可利用httpUtils相关方法辅助生成
	 * @return body参数
	 * @see com.geoq.framework.request.HttpUtils
	 */
	public abstract HttpEntity onGenerateBodyEntry();
	
	@Override
	public boolean hasQueryParams()
	{
		if(queryParams == null)
			queryParams = new HashMap<String, Object>();
		queryParams = new HashMap<String, Object>();
		onGenerateQueryParams(queryParams);
		return queryParams!=null && queryParams.keySet().size() > 0;
	}
	
	@Override
	public Map<String, Object> getQueryParams()
	{
		
		return queryParams;
	}

	@Override
	public boolean hasCustomHeaders()
	{
		if(headerMap != null)
			headerMap.clear();
		headerMap = new HashMap<String, String>();
		onGenerateHeaderMap(headerMap);
		return headerMap!= null && headerMap.keySet().size() > 0;
	}

	@Override
	public Map<String, String> getHeaderMap()
	{
		return headerMap;
	}

	@Override
	public boolean hasBodyEntry()
	{
		bodyEntry = onGenerateBodyEntry();
		return bodyEntry != null;
	}
	
	@Override
	public HttpEntity getBodyEntry()
	{
		if(bodyEntry == null)
			bodyEntry = onGenerateBodyEntry();
		return bodyEntry;
	}
	
	/***
	 * 辅助方法：添加一个自定义请求头参数项
	 * @param key
	 * @param value
	 */
	protected void onAddHeader(String key,String value)
	{
		if(headerMap.containsKey(key) == false)
			headerMap.put(key, value);
	}
	
	/***
	 * 辅助方法：添加一个自定义Query参数项
	 * @param key
	 * @param value
	 */
	protected void onAddQueryParam(String key,String value)
	{
		if(queryParams.containsKey(key) == false)
			queryParams.put(key, value);
	}
	

}
