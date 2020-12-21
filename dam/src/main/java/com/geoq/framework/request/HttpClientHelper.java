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
 * http������������
 * @author cyoubo
 *
 */
public class HttpClientHelper
{	
	private RequestConfig config;
	/**
	 * ���췽��������http����������
	 */
	public HttpClientHelper()
	{
		this.config = RequestConfig.custom().setSocketTimeout(10000).setConnectTimeout(10000).build();
	}
	
	/**
	 * ����http�������
	 * @param config
	 */
	public void setConfig(RequestConfig config)
	{
		this.config = config;
	}
	
	/**
	 * �����������еĶ�Ӧ����ת��Ϊhttp������ͷ
	 * @param command ��������
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
	 * �����������еĶ�Ӧ����תΪΪhttp��Query����
	 * @param command ��������
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
	 * ����http����
	 * @param uriRequest ����ʵ����
	 * @return ������Ӧ��״̬code
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
			// �ر�������Դ����
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
	 * ����Get����
	 * @param <T>
	 * @param command ��������ӿ�
	 * @return ������Ӧ��״̬code
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
	 * ����Post����
	 * @param command ��������ӿ�
	 * @return ������Ӧ��״̬code
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
