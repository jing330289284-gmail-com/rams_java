package jp.co.lyc.cms.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import jp.co.lyc.cms.model.CustomerDepartmentTypeMasterModel;
import jp.co.lyc.cms.service.CustomerDepartmentTypeMasterService;

@Controller
@CrossOrigin(origins = "http://127.0.0.1:3000")
@RequestMapping(value = "/customerDepartmentTypeMaster")
public class CustomerDepartmentTypeMasterController {

	@Autowired
	CustomerDepartmentTypeMasterService customerDepartmentTypeMasterService;
	
	/**
	 * 登録ボタン
	 * @return
	 */
	@RequestMapping(value = "/toroku", method = RequestMethod.POST)
	@ResponseBody
	public boolean toroku(@RequestBody CustomerDepartmentTypeMasterModel customerDepartmentTypeMod) {
		boolean result = checkHave(customerDepartmentTypeMod.getCustomerDepartmentName());
		if(result) {
			return insert(customerDepartmentTypeMod);
		}
		return false;
	}
	/**
	 * インサート
	 * @return
	 */
	private boolean insert(CustomerDepartmentTypeMasterModel customerDepartmentTypeMod) {
		HashMap<String, String> sendMap = new HashMap<>();
		sendMap.put("customerDepartmenttypeRemark", customerDepartmentTypeMod.getCustomerDepartmenttypeRemark());
		sendMap.put("customerDepartmenttypeName", customerDepartmentTypeMod.getCustomerDepartmentName());
		sendMap.put("updateUser", customerDepartmentTypeMod.getUpdateUser());
		return customerDepartmentTypeMasterService.insertcustomerDepartmentTypeMaster(sendMap);
		
	}
	/**
	 * あるかどうかのチェック
	 * @return
	 */
	private boolean checkHave(String customerDepartmenttypeName) {
		return customerDepartmentTypeMasterService.checkHave(customerDepartmenttypeName);
		
	}
}
