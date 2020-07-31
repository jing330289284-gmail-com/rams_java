package jp.co.lyc.cms.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import jp.co.lyc.cms.model.EmployeeModel;
import jp.co.lyc.cms.service.GetEmployeeInfoService;

@Controller
@CrossOrigin(origins = "http://127.0.0.1:3000")
public class GetEmployeeInfoController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	GetEmployeeInfoService getEmployeeInfoService;

	@RequestMapping(value = "/getEmployeeInfo", method = RequestMethod.POST)
	@ResponseBody
	public List<EmployeeModel> getEmployeeInfo(@RequestBody EmployeeModel emp) {

		logger.info("GetEmployeeInfoController.getEmployeeInfo:" + "検索開始");
		List<EmployeeModel> employeeList = new ArrayList<EmployeeModel>();
		try {
			Map<String, String> sendMap = getParam(emp);
			employeeList = getEmployeeInfoService.getEmployeeInfo(sendMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("GetEmployeeInfoController.getEmployeeInfo:" + "検索結束");
		return employeeList;
	}

	/**
	 * 条件を取得
	 * 
	 * @param emp
	 * @return
	 */
	public Map<String, String> getParam(EmployeeModel emp) {
		Map<String, String> sendMap = new HashMap<String, String>();
		String employeeNo = emp.getEmployeeNo();// 社員番号
		String employeeFristName = emp.getEmployeeFristName();// 社員名
		String emploryeeForm = emp.getEmploryeeForm();// 社員形式
		String genderCode = emp.getGenderCode();// 性別区別
		String ageFrom = emp.getAgeFrom();// 開始年齢
		String ageTo = emp.getAgeTo();// 終了年齢
		String birthplaceOfcontroy = emp.getBirthplaceOfcontroy();// 国籍
		String customer = emp.getCustomer();// お客様先 
		String intoCompanyCode = emp.getIntoCompanyCode();// 新人区分
		String japanease = emp.getJapanease();// 日本語のレベル
		String unitPriceFrom = emp.getUnitPriceFrom();// 単価範囲from
		String unitPriceTo = emp.getUnitPriceTo();// 単価範囲to
		String statusOfResidence = emp.getStatusOfResidence();// 在留資格
		String developmentLanguageNo1 = emp.getDevelopmentLanguageNo1();// 開発言語1
		String developmentLanguageNo2 = emp.getDevelopmentLanguageNo2();// 開発言語2
		String developmentLanguageNo3 = emp.getDevelopmentLanguageNo3();// 開発言語3
		String kadou = emp.getKadou();// 稼働
		String joinCompanyOfYearFrom =emp.getJoinCompanyOfYearFrom();//入社年月元
		String joinCompanyOfYearTo = emp.getJoinCompanyOfYearTo();//入社年月先
		
		if (statusOfResidence != null && statusOfResidence.length() != 0) {
			sendMap.put("statusOfResidence", statusOfResidence);
		}
		if (employeeNo != null && employeeNo.length() != 0) {
			sendMap.put("employeeNo", employeeNo);
		}
		if (employeeFristName != null && employeeFristName.length() != 0) {
			sendMap.put("employeeFristName", employeeFristName);
		}
		if (emploryeeForm != null && emploryeeForm.length() != 0) {
			sendMap.put("emploryeeForm", emploryeeForm);
		}
		if (customer != null && customer.length() != 0) {
			sendMap.put("customer", customer);
		}
		if (intoCompanyCode != null && intoCompanyCode.length() != 0) {
			sendMap.put("intoCompanyCode", intoCompanyCode);
		}
		if (birthplaceOfcontroy != null && birthplaceOfcontroy.length() != 0) {
			sendMap.put("birthplaceOfcontroy", birthplaceOfcontroy);
		}

		if (genderCode != null && genderCode.length() != 0) {
			sendMap.put("genderCode", genderCode);
		}
		if (ageFrom != null && ageFrom.length() != 0) {
			sendMap.put("ageFrom", ageFrom);
		}
		if (ageTo != null && ageTo.length() != 0) {
			sendMap.put("ageTo", ageTo);
		}

		if (unitPriceFrom != null && unitPriceFrom.length() != 0) {
			sendMap.put("unitPriceFrom", unitPriceFrom);
		}
		if (unitPriceTo != null && unitPriceTo.length() != 0) {
			sendMap.put("unitPriceTo", unitPriceTo);
		}
		if (japanease != null && japanease.length() != 0) {
			sendMap.put("japanease", japanease);
		}
		if (kadou != null && kadou.length() != 0) {
			sendMap.put("kadou", kadou);
		}
	
		if (developmentLanguageNo1 != null && developmentLanguageNo1.length() != 0) {
			sendMap.put("developmentLanguageNo1", developmentLanguageNo1);
		}
		if (developmentLanguageNo2 != null && developmentLanguageNo2.length() != 0) {
			sendMap.put("developmentLanguageNo2", developmentLanguageNo2);
		}
		if (developmentLanguageNo3 != null && developmentLanguageNo3.length() != 0) {
			sendMap.put("developmentLanguageNo3", developmentLanguageNo3);
		}
		if (joinCompanyOfYearFrom != null && joinCompanyOfYearFrom.length() != 0) {
			sendMap.put("joinCompanyOfYearFrom", joinCompanyOfYearFrom);
		}
		if (joinCompanyOfYearTo != null && joinCompanyOfYearTo.length() != 0) {
			sendMap.put("joinCompanyOfYearTo", joinCompanyOfYearTo);
		}
		
		return sendMap;

	}

}
