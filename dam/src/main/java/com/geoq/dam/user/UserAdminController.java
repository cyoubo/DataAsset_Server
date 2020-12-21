package com.geoq.dam.user;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.geoq.dam.user.bean.DepartmentBean;
import com.geoq.framework.bean.BaseDataMessage;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/UserAdminController")
@Api(value = "�������ܲ��Խӿ�", tags = "�ӿڿ���ʾ��")
public class UserAdminController
{
	@Autowired
	private UserAdminService service;
	
	@RequestMapping(value = "/conncetTest.do", method = RequestMethod.POST)
	@ApiOperation(value = "����ͨѶ����", notes = "����ͨѶ����", httpMethod = "POST", response = BaseDataMessage.class)
	public ResponseEntity<BaseDataMessage> HelloWorld(HttpServletRequest request)
	{

		BaseDataMessage dataMessage = new BaseDataMessage();
		int status = service.conncetTest(request, dataMessage);
		return ResponseEntity.status(status).body(dataMessage);
	}
	
	
	@RequestMapping(value = "/create_updateDepartment.do", method = RequestMethod.POST)
	@ApiOperation(value = "����ͨѶ����", notes = "����ͨѶ����", httpMethod = "POST", response = BaseDataMessage.class)
	public ResponseEntity<BaseDataMessage> create_updateDepartment(HttpServletRequest request, @RequestBody DepartmentBean bean)
	{

		BaseDataMessage dataMessage = new BaseDataMessage();
		int status = service.create_updateDepartment(request, bean, dataMessage);
		return ResponseEntity.status(status).body(dataMessage);
	}
}
