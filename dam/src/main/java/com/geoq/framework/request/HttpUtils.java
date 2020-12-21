package com.geoq.framework.request;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicNameValuePair;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.geoq.framework.log.LoggerContext;

public class HttpUtils
{
	public static String ProcessOriginalURL(HttpServletRequest request)
	{
		String localAddr = request.getLocalAddr();
		String localPort = "" + request.getLocalPort();
		String contextPath = request.getContextPath();
		return String.format("http://%s:%s%s", localAddr, localPort, contextPath);
	}
	
	public static UrlEncodedFormEntity Convert2EncodedFormEntity(Map<String, String> param)
	{
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		for (Iterator<String> iter = param.keySet().iterator(); iter.hasNext();) {
			String key = (String) iter.next();
			String value = String.valueOf(param.get(key));
			nvps.add(new BasicNameValuePair(key, value));
		}
		return new UrlEncodedFormEntity(nvps, StandardCharsets.UTF_8);
	}
	
	public static UrlEncodedFormEntity Convert2EncodedFormEntity_Trim(Map<String, String> param)
	{
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		for (Iterator<String> iter = param.keySet().iterator(); iter.hasNext();) {
			String key = (String) iter.next();
			String value = String.valueOf(param.get(key));
			//如果value没有值，就不添加到entity中
			if(value!=null && value.trim()!="")
				nvps.add(new BasicNameValuePair(key, value));
		}
		return new UrlEncodedFormEntity(nvps, StandardCharsets.UTF_8);
	}
	
	public static StringEntity Convert2JsonStringEntity(Map<String, Object> param)
	{
		Map<String,Object> map = new HashMap<String,Object>();
	    ObjectMapper objectMapper = new ObjectMapper();
	    String jsonStr = "";
		try
		{
			jsonStr = objectMapper.writeValueAsString(map);
			
		} 
		catch (JsonProcessingException e)
		{
			LoggerContext.instance().error(e);
		}
		return new StringEntity(jsonStr, StandardCharsets.UTF_8);
	}
}
