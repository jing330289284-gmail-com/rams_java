package jp.co.lyc.cms.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.lyc.cms.mapper.EmployeeMapper;
import jp.co.lyc.cms.model.EmployeeModel;

@Component
public class LoginService{

	@Autowired
	EmployeeMapper employeeMapper;
	public EmployeeModel getEmployeeModel(Map<String, String> sendMap) {
		// TODO Auto-generated method stub
		EmployeeModel employeeModel = employeeMapper.getEmployeeModel(sendMap);
		return employeeModel;
	}
}
