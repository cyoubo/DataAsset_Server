package com.geoq.dam.user;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.geoq.dam.user.bean.DepartmentBean;
import com.geoq.dam.user.bean.UserBean;
import com.geoq.dam.user.bean.UserFilterBean;
import com.geoq.framework.bean.BaseDataMessage;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/UserAdminController")
@Api(value = "基本功能测试接口", tags = "接口开放示例")
public class UserAdminController
{
	@Autowired
	private UserAdminService service;
	
	@RequestMapping(value = "/conncetTest.do", method = RequestMethod.POST)
	@ApiOperation(value = "网络通讯测试", notes = "网络通讯测试", httpMethod = "POST", response = BaseDataMessage.class)
	public ResponseEntity<BaseDataMessage> HelloWorld(HttpServletRequest request)
	{

		BaseDataMessage dataMessage = new BaseDataMessage();
		int status = service.conncetTest(request, dataMessage);
		return ResponseEntity.status(status).body(dataMessage);
	}
	
	
	@RequestMapping(value = "/create_updateDepartment.do", method = RequestMethod.POST)
	@ApiOperation(value = "网络通讯测试", notes = "网络通讯测试", httpMethod = "POST", response = BaseDataMessage.class)
	public ResponseEntity<BaseDataMessage> create_updateDepartment(HttpServletRequest request, @RequestBody DepartmentBean bean)
	{

		BaseDataMessage dataMessage = new BaseDataMessage();
		int status = service.create_updateDepartment(request, bean, dataMessage);
		return ResponseEntity.status(status).body(dataMessage);
	}
	
	@RequestMapping(value = "/travelDepartment.do", method = RequestMethod.POST)
	@ApiOperation(value = "网络通讯测试", notes = "网络通讯测试", httpMethod = "POST", response = BaseDataMessage.class)
	public ResponseEntity<BaseDataMessage> travelDepartment(HttpServletRequest request)
	{

		BaseDataMessage dataMessage = new BaseDataMessage();
		int status = service.travelDepartment(request, dataMessage);
		return ResponseEntity.status(status).body(dataMessage);
	}
	
	@RequestMapping(value = "/create_updateUserInfo.do", method = RequestMethod.POST)
	@ApiOperation(value = "网络通讯测试", notes = "网络通讯测试", httpMethod = "POST", response = BaseDataMessage.class)
	public ResponseEntity<BaseDataMessage> create_updateUserInfo(HttpServletRequest request, @RequestBody UserBean bean)
	{

		BaseDataMessage dataMessage = new BaseDataMessage();
		int status = service.create_updateUserInfo(request, bean ,dataMessage);
		return ResponseEntity.status(status).body(dataMessage);
	}
	
	
	@RequestMapping(value = "/queryUserInfo.do", method = RequestMethod.POST)
	@ApiOperation(value = "网络通讯测试", notes = "网络通讯测试", httpMethod = "POST", response = BaseDataMessage.class)
	public ResponseEntity<BaseDataMessage> queryUserInfo(HttpServletRequest request, @RequestBody UserFilterBean bean)
	{

		BaseDataMessage dataMessage = new BaseDataMessage();
		int status = service.queryUserInfo(request, bean ,dataMessage);
		return ResponseEntity.status(status).body(dataMessage);
	}
	
	
}
