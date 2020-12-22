package com.geoq.dam.user.component;

import java.util.UUID;

import com.geoq.dam.user.bean.DepartmentBean;
import com.geoq.dam.user.bean.UserBean;
import com.geoq.dam.user.entry.DamDepartment;
import com.geoq.dam.user.entry.DamUserinfo;

public class ConvertFactory
{
	public static DamDepartment toDamDepartment(DepartmentBean bean)
	{
		if(bean.getUuid()==null ||bean.getUuid().trim().contentEquals(""))
			bean.setUuid(UUID.randomUUID().toString());
		return new DamDepartment(bean.getUuid(),bean.getDepartmentname());
 	}

	public static DamUserinfo toDamUserInfo(UserBean bean)
	{
		if(bean.getUuid()==null ||bean.getUuid().trim().contentEquals(""))
			bean.setUuid(UUID.randomUUID().toString());
		return new DamUserinfo(bean.getUuid(), bean.getName(), bean.getPassword(), bean.getRoleid(), bean.getDepartmentuuid());
	}

	public static UserBean toUserBean(DamUserinfo userInfo, boolean maintainPassword)
	{
		UserBean resultBean = new UserBean();
		resultBean.setDepartmentuuid(userInfo.getDepartmentuuid());
		resultBean.setName(userInfo.getName());
		resultBean.setRoleid(userInfo.getRoleid());
		resultBean.setUuid(userInfo.getUuid());
		if(maintainPassword)
			resultBean.setPassword(userInfo.getPassword());
		return resultBean;
	}
}
