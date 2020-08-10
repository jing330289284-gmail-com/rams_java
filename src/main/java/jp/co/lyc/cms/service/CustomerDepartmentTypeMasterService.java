package jp.co.lyc.cms.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.lyc.cms.mapper.CustomerDepartmentTypeMasterMapper;
@Component
public class CustomerDepartmentTypeMasterService {

	@Autowired
	CustomerDepartmentTypeMasterMapper customerDepartmentTypeMasterMapper;
	
	/**
	 * 有無判断
	 * @param customerDepartmenttypeName
	 * @return
	 */
	
	public boolean checkHave(String customerDepartmenttypeName) {
		if(customerDepartmentTypeMasterMapper.checkHave(customerDepartmenttypeName) == null) {
			return true;
		}
		return false;
	}
	
	/**
	 * インサート
	 * @param sendMap
	 */
	
	public boolean insertcustomerDepartmentTypeMaster(HashMap<String, String> sendMap) {
		try {
			customerDepartmentTypeMasterMapper.insertCustomerDepartmentTypeMaster(sendMap);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
