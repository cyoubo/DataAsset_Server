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
	 * ���������װ��Ҫ�ƶ���Header����������ͨ��onAddHeader
	 * @param headerMap ���ڼ�¼Header����ֵ�
	 * @see onAddHeader
	 */
	public abstract void onGenerateHeaderMap(Map<String, String> headerMap);
	/***
	 * ���������װ��Ҫ�ƶ���Query����������ͨ��onAddQueryParam
	 * @param queryParams ���ڼ�¼queryParam����ֵ�
	 * @see onAddQueryParam
	 */
	public abstract void onGenerateQueryParams(Map<String, Object> queryParams);
	
	/***
	 * ����http�����body����<br>
	 * ������httpUtils��ط�����������
	 * @return body����
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
	 * �������������һ���Զ�������ͷ������
	 * @param key
	 * @param value
	 */
	protected void onAddHeader(String key,String value)
	{
		if(headerMap.containsKey(key) == false)
			headerMap.put(key, value);
	}
	
	/***
	 * �������������һ���Զ���Query������
	 * @param key
	 * @param value
	 */
	protected void onAddQueryParam(String key,String value)
	{
		if(queryParams.containsKey(key) == false)
			queryParams.put(key, value);
	}
	

}
