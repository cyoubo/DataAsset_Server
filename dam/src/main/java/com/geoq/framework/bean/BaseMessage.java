package com.geoq.framework.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class BaseMessage
{
	@ApiModelProperty(value = "操作本身是否成功" , example = "True")
	protected boolean status;
	
	@ApiModelProperty(value = "操作本身的返回信息" , example = "success")
	protected String message;
	
	public String getMessage()
	{
		return message;
	}
	
	public boolean isStatus()
	{
		return status;
	}
	
	public void setMessage(String message)
	{
		this.message = message;
	}
	
	public void setStatus(boolean status)
	{
		this.status = status;
	}
	
	public void ok()
	{
		this.status = true;
		this.message = "success";
	}
	
	public void ok(String message)
	{
		this.status = true;
		this.message = message;
	}

	public void error(String errorMsg)
	{
		this.status = false;
		this.message = errorMsg;
	}
}
