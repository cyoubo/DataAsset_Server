package com.geoq.framework.request;

import org.apache.http.client.methods.CloseableHttpResponse;

/***
 * 
 * @author Administrator
 *
 */
public interface HttpResponseParser<T>
{
	
	T getResult();

	/***
	 * 
	 * @param temp
	 * @param response
	 */
	void parseResponseString(String temp, CloseableHttpResponse response);

	/***
	 * 
	 * @param response
	 */
	void parserUnOkResponse(CloseableHttpResponse response);

}
