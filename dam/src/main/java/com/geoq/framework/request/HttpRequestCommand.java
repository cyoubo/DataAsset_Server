package com.geoq.framework.request;

import java.util.Map;

import org.apache.http.HttpEntity;

/**
 * http��������ӿ�
 * @author cyoubo
 *
 */
public interface HttpRequestCommand
{
	/***
	 * �жϵ�ǰ����ӿ��Ƿ���Query����
	 * @return
	 */
	boolean hasQueryParams();
	/***
	 * 
	 * @return ��õ�ǰ����ӿڵ�Query�����ֵ�
	 */
	Map<String, Object> getQueryParams();
	/***
	 * �жϵ�ǰ����ӿ��Ƿ����Զ���ͷ
	 * @return
	 */
	boolean hasCustomHeaders();
	/***
	 * 
	 * @return ��õ�ǰ����ӿڵ��Զ���ͷ�����ֵ�
	 */
	Map<String, String> getHeaderMap();
	/***
	 * �жϵ�ǰ����ӿ��Ƿ���Body����
	 * @return
	 */
	boolean hasBodyEntry();
	/***
	 * 
	 * @return ��õ�ǰ����ӿڵ�Body�����ֵ�
	 */
	HttpEntity getBodyEntry();
	/***
	 * 
	 * @return ��õ�ǰ���������Ŀ��URL(����query��������)
	 */
	String generateTargetUrl();
}
