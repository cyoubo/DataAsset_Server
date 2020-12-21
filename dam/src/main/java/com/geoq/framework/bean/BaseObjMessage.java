package com.geoq.framework.bean;

public class BaseObjMessage<T> extends BaseMessage
{
	private T data;
	
	public void setData(T data)
	{
		this.data = data;
	}
	
	public T getData()
	{
		return data;
	}
	
	public void ok(String msg, T data)
	{
		this.data = data;
		this.status = true;
		this.message = msg;
	}
	
	public void error(String msg)
	{
		this.status = false;
		this.message = msg;
	}
	
	public void error(String msg, T data)
	{
		this.status = false;
		this.message = msg;
		this.data = data;
	}
	
}
