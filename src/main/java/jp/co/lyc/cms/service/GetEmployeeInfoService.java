package jp.co.lyc.cms.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.lyc.cms.mapper.GetEmployeeInfoMapper;
import jp.co.lyc.cms.model.EmployeeModel;
import jp.co.lyc.cms.model.ModelClass;

@Component
public class GetEmployeeInfoService {

	@Autowired
	GetEmployeeInfoMapper getEmployeeInfoMapper;

	/**
	 * 社員情報を取得
	 * 
	 * @param sendMap
	 * @return
	 */
	public List<EmployeeModel> getEmployeeInfo(Map<String, String> sendMap) {
		List<EmployeeModel> employeeList = getEmployeeInfoMapper.getEmployeeInfo(sendMap);
		return employeeList;
	}

	

}
