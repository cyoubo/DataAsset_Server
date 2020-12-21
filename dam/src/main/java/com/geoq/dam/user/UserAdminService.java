package com.geoq.dam.user;

import javax.servlet.http.HttpServletRequest;

import com.geoq.dam.user.bean.DepartmentBean;
import com.geoq.framework.bean.BaseDataMessage;

public interface UserAdminService
{

	int conncetTest(HttpServletRequest request, BaseDataMessage dataMessage);

	int create_updateDepartment(HttpServletRequest request, DepartmentBean bean, BaseDataMessage dataMessage);

}
