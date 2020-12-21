package com.geoq.framework.request;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;

import com.geoq.framework.log.LoggerContext;
import com.geoq.framework.utils.StringUtils;

/**
 * http请求发送主机类
 * @author cyoubo
 *
 */
public class HttpClientHelper
{	
	private RequestConfig config;
	/**
	 * 构造方法：创建http请求主机类
	 */
	public HttpClientHelper()
	{
		this.config = RequestConfig.custom().setSocketTimeout(10000).setConnectTimeout(10000).build();
	}
	
	/**
	 * 设置http请求参数
	 * @param config
	 */
	public void setConfig(RequestConfig config)
	{
		this.config = config;
	}
	
	/**
	 * 将请求命令中的对应参数转化为http的请求头
	 * @param command 请求命令
	 * @return
	 */
	protected Header[] onProcessCustomHeaders(HttpRequestCommand command)
	{
		Header[] header = new Header[command.getHeaderMap().keySet().size()];
		int index = 0;
		for (String key : command.getHeaderMap().keySet())
			header[index++] = new BasicHeader(key, command.getHeaderMap().get(key));
		return header;		
	}
	
	/**
	 * 将请求命令中的对应参数转为为http的Query参数
	 * @param command 请求命令
	 * @return
	 */
	protected String onProcessQueryParams(HttpRequestCommand command)
	{
		if(command.getQueryParams() !=null && command.getQueryParams().keySet().size() > 0)
		{
			StringBuilder builder = new StringBuilder();
			try
			{
				for (String key : command.getQueryParams().keySet())
				{
					builder.append(key);
					builder.append("=");
					builder.append(StringUtils.tryEncodeStr(command.getQueryParams().get(key).toString()));
					builder.append("&");
				}
			} catch (Exception e)
			{
				e.printStackTrace();
			}
			if (builder.toString().endsWith("&"))
				return builder.toString().substring(0, builder.length() - 1);
			return builder.toString();
		}
		else 
			return "";
	}
	
	/**
	 * 发送http请求
	 * @param uriRequest 请求实体类
	 * @return 请求响应的状态code
	 */
	protected <T> int sendRequest(HttpRequestBase uriRequest, HttpResponseParser<T> parser)
	{
		int ResponseTag = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
		CloseableHttpClient httpclient = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		try
		{	
			uriRequest.setConfig(config);
			response = httpclient.execute(uriRequest);
			ResponseTag = response.getStatusLine().getStatusCode();
			if (ResponseTag != HttpServletResponse.SC_OK)
			{
				uriRequest.abort();
				parser.parserUnOkResponse(response);
			}
			HttpEntity entity = response.getEntity();
			if (entity != null)
			{
				String temp = EntityUtils.toString(entity);
				parser.parseResponseString(temp,response);
				EntityUtils.consume(entity);
			}
		} catch (Exception e)
		{
			LoggerContext.instance().error(e);
		} 
		finally
		{
			// 关闭所有资源连接
			if (response != null)
			{
				try
				{
					response.close();
				} catch (IOException e)
				{
					LoggerContext.instance().error(e);
				}
			}
			if (httpclient != null)
			{
				try
				{
					httpclient.close();
				} catch (IOException e)
				{
					LoggerContext.instance().error(e);
				}
			}
		}
		return ResponseTag;
	}
	
	/**
	 * 发送Get请求
	 * @param <T>
	 * @param command 请求命令接口
	 * @return 请求响应的状态code
	 */
	public <T> int  sendGetRequest(HttpRequestCommand command,HttpResponseParser<T> parser)
	{
		String url = command.generateTargetUrl();
		if(command.hasQueryParams())
			url += ( "?" + onProcessQueryParams(command)); 
		
		HttpGet httpGet =  new HttpGet(url);
		if(command.hasCustomHeaders())
			httpGet.setHeaders(onProcessCustomHeaders(command));
		return sendRequest(httpGet,parser);
	}

	/**
	 * 发送Post请求
	 * @param command 请求命令接口
	 * @return 请求响应的状态code
	 */
	public <T> int sendPostRequest(HttpRequestCommand command, HttpResponseParser<T> parser)
	{
		String url = command.generateTargetUrl();
		if(command.hasQueryParams())
			url += ( "?" + onProcessQueryParams(command)); 
		
		HttpPost httpPost = new HttpPost(url);
		if(command.hasCustomHeaders())
			httpPost.setHeaders(onProcessCustomHeaders(command));
		if(command.hasBodyEntry())
			httpPost.setEntity(command.getBodyEntry());
		return sendRequest(httpPost, parser);
	}
	
}
