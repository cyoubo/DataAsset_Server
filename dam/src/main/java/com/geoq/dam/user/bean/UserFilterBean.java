package com.geoq.dam.user.bean;

import java.util.List;

import com.geoq.framework.bean.PageFilter;

public class UserFilterBean extends PageFilter
{
	private List<String> departmentids;
	
	private List<String> roleids;


	public List<String> getDepartmentids()
	{
		return departmentids;
	}
	
	public void setDepartmentids(List<String> departmentids)
	{
		this.departmentids = departmentids;
	}

	public List<String> getRoleids()
	{
		return roleids;
	}

	public void setRoleids(List<String> roleids)
	{
		this.roleids = roleids;
	}
	
	
	
}
