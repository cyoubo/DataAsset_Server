package com.geoq.dam.user;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geoq.dam.user.bean.DepartmentBean;
import com.geoq.dam.user.bean.UserBean;
import com.geoq.dam.user.bean.UserFilterBean;
import com.geoq.dam.user.component.ConvertFactory;
import com.geoq.dam.user.entry.DamDepartment;
import com.geoq.dam.user.entry.DamDepartmentMapper;
import com.geoq.dam.user.entry.DamUserinfo;
import com.geoq.dam.user.entry.DamUserinfoMapper;
import com.geoq.framework.bean.BaseDataMessage;
import com.geoq.framework.log.LoggerContext;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class UserAdminServiceImp implements UserAdminService
{
	@Autowired
	private DamDepartmentMapper DepartmentMapper;
	
	@Autowired
	private DamUserinfoMapper UserinfoMapper;
	
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
			int affectRows = 0; 
			if(bean.getUuid()==null || bean.getUuid().contentEquals(""))
				affectRows = DepartmentMapper.insert(ConvertFactory.toDamDepartment(bean));
			else
				affectRows = DepartmentMapper.updateByPrimaryKey(ConvertFactory.toDamDepartment(bean));
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

	@Override
	public int travelDepartment(HttpServletRequest request, BaseDataMessage dataMessage)
	{
		int code = HttpServletResponse.SC_OK;
		List<DamDepartment> resultsDamDepartments = new ArrayList<DamDepartment>();
		try
		{
			resultsDamDepartments = DepartmentMapper.travelAll();
			dataMessage.ok("", resultsDamDepartments);
		} 
		catch (Exception e)
		{
			code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
			LoggerContext.instance().error(e);
			dataMessage.error("", e.getMessage());
		}
		return code;
	}

	@Override
	public int create_updateUserInfo(HttpServletRequest request, UserBean bean, BaseDataMessage dataMessage)
	{
		int code = HttpServletResponse.SC_OK;
		try
		{
			int affectRows = 0; 
			if(bean.getUuid()==null||bean.getUuid().contentEquals(""))
				affectRows = UserinfoMapper.insert(ConvertFactory.toDamUserInfo(bean));
			else
				affectRows = UserinfoMapper.updateByPrimaryKey(ConvertFactory.toDamUserInfo(bean));
			if(affectRows > 0)
				dataMessage.ok("sussess");
		} 
		catch (Exception e)
		{
			code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
			LoggerContext.instance().error(e);
			dataMessage.error("", e.getMessage());
		}
		return code;
	}

	@Override
	public int queryUserInfo(HttpServletRequest request, UserFilterBean bean, BaseDataMessage dataMessage)
	{
		int code = HttpServletResponse.SC_OK;
		try
		{
			PageHelper.startPage(bean.getPageNum(), bean.getPageSize());
			List<UserBean> result =  new ArrayList<UserBean>();
			for (DamUserinfo userInfo : UserinfoMapper.selectByFilter(bean))
				result.add(ConvertFactory.toUserBean(userInfo, false));
			PageInfo<UserBean> pageInfo = new PageInfo<>(result);
			dataMessage.ok("", pageInfo);
//			dataMessage.ok("", UserinfoMapper.travelAll());
		} 
		catch (Exception e)
		{
			code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
			LoggerContext.instance().error(e);
			dataMessage.error("", e.getMessage());
		}
		return code;
	}
	
	

}
