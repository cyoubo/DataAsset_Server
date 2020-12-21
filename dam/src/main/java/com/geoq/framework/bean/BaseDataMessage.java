package com.geoq.framework.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class BaseDataMessage extends BaseMessage
{
	@ApiModelProperty(value = "返回的数据实体")
	protected Object data;

	public void setData(Object data)
	{
		this.data = data;
	}

	public Object getData()
	{
		return data;
	}

	public void ok(String msg, Object data)
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
	
	public void error(String msg, Object data)
	{
		this.status = false;
		this.message = msg;
		this.data = data;
	}
}
