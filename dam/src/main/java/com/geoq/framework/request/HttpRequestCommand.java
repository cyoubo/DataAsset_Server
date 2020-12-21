package com.geoq.framework.request;

import java.util.Map;

import org.apache.http.HttpEntity;

/**
 * http请求命令接口
 * @author cyoubo
 *
 */
public interface HttpRequestCommand
{
	/***
	 * 判断当前命令接口是否有Query参数
	 * @return
	 */
	boolean hasQueryParams();
	/***
	 * 
	 * @return 获得当前请求接口的Query参数字典
	 */
	Map<String, Object> getQueryParams();
	/***
	 * 判断当前命令接口是否有自定义头
	 * @return
	 */
	boolean hasCustomHeaders();
	/***
	 * 
	 * @return 获得当前请求接口的自定义头参数字典
	 */
	Map<String, String> getHeaderMap();
	/***
	 * 判断当前命令接口是否有Body参数
	 * @return
	 */
	boolean hasBodyEntry();
	/***
	 * 
	 * @return 获得当前请求接口的Body参数字典
	 */
	HttpEntity getBodyEntry();
	/***
	 * 
	 * @return 获得当前请求命令的目的URL(不含query参数部分)
	 */
	String generateTargetUrl();
}
