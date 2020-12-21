package com.geoq.dam.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geoq.dam.user.bean.DepartmentBean;
import com.geoq.dam.user.component.ConvertFactory;
import com.geoq.dam.user.entry.DamDepartment;
import com.geoq.dam.user.entry.DamDepartmentMapper;
import com.geoq.framework.bean.BaseDataMessage;
import com.geoq.framework.log.LoggerContext;

@Service
public class UserAdminServiceImp implements UserAdminService
{
	private DamDepartmentMapper DepartmentMapper;
	
	@Override
	public int conncetTest(HttpServletRequest request, BaseDataMessage dataMessage)
	{
		int code = HttpServletResponse.SC_OK;
		try
		{
			dataMessage.ok("connect susccess。。。。");
		} catch (Exception e)
		{
			code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
			e.printStackTrace();
		}
		return code;
	}

	@Override
	public int create_updateDepartment(HttpServletRequest request, DepartmentBean bean, BaseDataMessage dataMessage)
	{
		int code = HttpServletResponse.SC_OK;
		try
		{
			int affectRows = DepartmentMapper.insert(ConvertFactory.toDamDepartment(bean));
			if(affectRows > 0)
				dataMessage.ok("sussess");
		} 
		catch (Exception e)
		{
			code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
			LoggerContext.instance().error(e);
			dataMessage.error(e.getMessage());
		}
		return code;
	}

}
