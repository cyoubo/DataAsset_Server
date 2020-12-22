package com.geoq.dam.user.bean;

public class UserBean
{
	private String uuid;
	
	private String name;
	
	private String password;
	
	private String departmentuuid;
	
	private String roleid;

	public String getUuid()
	{
		return uuid;
	}

	public void setUuid(String uuid)
	{
		this.uuid = uuid;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getDepartmentuuid()
	{
		return departmentuuid;
	}

	public void setDepartmentuuid(String departmentuuid)
	{
		this.departmentuuid = departmentuuid;
	}

	public String getRoleid()
	{
		return roleid;
	}

	public void setRoleid(String roleid)
	{
		this.roleid = roleid;
	}
	
}
