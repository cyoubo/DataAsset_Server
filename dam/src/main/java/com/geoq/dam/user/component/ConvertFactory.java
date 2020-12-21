package com.geoq.dam.user.component;

import com.geoq.dam.user.bean.DepartmentBean;
import com.geoq.dam.user.entry.DamDepartment;

public class ConvertFactory
{
	public static DamDepartment toDamDepartment(DepartmentBean bean)
	{
		return new DamDepartment(bean.getUuid(),bean.getDepartmentname());
 	}
}
