package com.geoq.dam.user;

import javax.servlet.http.HttpServletRequest;

import com.geoq.dam.user.bean.DepartmentBean;
import com.geoq.dam.user.bean.UserBean;
import com.geoq.dam.user.bean.UserFilterBean;
import com.geoq.framework.bean.BaseDataMessage;

public interface UserAdminService
{

	int conncetTest(HttpServletRequest request, BaseDataMessage dataMessage);

	int create_updateDepartment(HttpServletRequest request, DepartmentBean bean, BaseDataMessage dataMessage);

	int travelDepartment(HttpServletRequest request, BaseDataMessage dataMessage);

	int create_updateUserInfo(HttpServletRequest request, UserBean bean, BaseDataMessage dataMessage);

	int queryUserInfo(HttpServletRequest request, UserFilterBean bean, BaseDataMessage dataMessage);

}
