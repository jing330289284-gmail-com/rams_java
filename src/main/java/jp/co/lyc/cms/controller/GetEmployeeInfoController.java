package jp.co.lyc.cms.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import jp.co.lyc.cms.model.CustomerModel;
import jp.co.lyc.cms.model.EmployeeModel;
import jp.co.lyc.cms.model.GenderModel;
import jp.co.lyc.cms.model.IntoCompanyModel;
import jp.co.lyc.cms.model.JapaneseLevelModel;
import jp.co.lyc.cms.model.NationalityModel;
import jp.co.lyc.cms.model.StaffModel;
import jp.co.lyc.cms.model.TechnologyTypeModel;
import jp.co.lyc.cms.model.VisaModel;
import jp.co.lyc.cms.service.GetEmployeeInfoService;
import jp.co.lyc.cms.util.GetProperties;
import net.sf.json.JSONObject;

@Controller
@CrossOrigin(origins = "http://127.0.0.1:3000")
public class GetEmployeeInfoController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	GetEmployeeInfoService getEmployeeInfoService;

	@Autowired
	GetProperties getProperties;

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
	 * 国籍を取得
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getNationalitys", method = RequestMethod.POST)
	@ResponseBody
	public List<NationalityModel> getNationalitys() {
		List<NationalityModel> nationalitysList = getEmployeeInfoService.getNationalitys();
		return nationalitysList;

	}

	/**
	 * 社員形式を取得
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getStaffForms", method = RequestMethod.POST)
	@ResponseBody
	public List<StaffModel> getStaffForms() {
		List<StaffModel> emploryeeFormsList = getEmployeeInfoService.getStaffForms();
		return emploryeeFormsList;
	}

	/**
	 * 在留資格を取得
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getVisa", method = RequestMethod.POST)
	@ResponseBody
	public List<VisaModel> getVisa() {
		List<VisaModel> visaList = getEmployeeInfoService.getVisa();
		return visaList;
	}

	/**
	 * お客様を取得
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getCustomer", method = RequestMethod.POST)
	@ResponseBody
	public List<CustomerModel> getCustomer() {
		List<CustomerModel> customerList = getEmployeeInfoService.getCustomer();
		return customerList;
	}

	/**
	 * 技術種別を取得
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getTechnologyType", method = RequestMethod.POST)
	@ResponseBody
	public List<TechnologyTypeModel> getTechnologyType(@RequestBody EmployeeModel emp) {
		Map<String, String> sendMap = getParam(emp);
		List<TechnologyTypeModel> technologyTypeList = getEmployeeInfoService.getTechnologyType(sendMap);
		return technologyTypeList;
	}

	/**
	 * 日本語レベルを取得
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getJapaneseLevel", method = RequestMethod.POST)
	@ResponseBody
	public List<JapaneseLevelModel> getJapaneseLevel() {
		List<JapaneseLevelModel> japaneseLevelList = getEmployeeInfoService.getJapaneseLevel();
		return japaneseLevelList;
	}

	/**
	 * 入社区分を取得
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getIntoCompany", method = RequestMethod.POST)
	@ResponseBody
	public List<IntoCompanyModel> getIntoCompany() {
		List<IntoCompanyModel> intoCompanyList = getEmployeeInfoService.getIntoCompany();
		return intoCompanyList;
	}

	/**
	 * 性別区別
	 * 
	 * @return
	 */

	@RequestMapping(value = "/getGender", method = RequestMethod.POST)
	@ResponseBody
	public List<GenderModel> getGender() {
		Properties properties = getProperties.getProperties();
		String gender = properties.getProperty("gender");
		JSONObject genderJson = JSONObject.fromObject(gender);
		@SuppressWarnings("unchecked")
		Map<String, String> map = (Map<String, String>) genderJson;
		List<GenderModel> list = new ArrayList<GenderModel>();
		for (Entry<String, String> entry : map.entrySet()) {
			GenderModel intoCompanyModel = new GenderModel();
			intoCompanyModel.setCode(entry.getKey());
			intoCompanyModel.setName(entry.getValue());
			list.add(intoCompanyModel);
		}
		return list;
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
		String employeeLastName = emp.getEmployeeLastName();
		String emploryeeForm = emp.getEmploryeeForm();// 社員形式
		String genderCode = emp.getGenderCode();// 性別区別
		String joinCompanyOfYear = emp.getJoinCompanyOfYear();// 入社年
		String joinCompanyOfMonth = emp.getJoinCompanyOfMonth();// 入社月
		String ageFrom = emp.getAgeFrom();// 開始年齢
		String ageTo = emp.getAgeTo();// 終了年齢
		String birthplaceOfcontroy = emp.getBirthplaceOfcontroy();// 国籍
		String customer = emp.getCustomer();// お客様先 TODO
		String intoCompanyCode = emp.getIntoCompanyCode();// 新人区分
		String japanease = emp.getJapanease();// 日本語のレベル
		String developmentLanguageNo = emp.getDevelopmentLanguageNo();// 技術種別
		String salaryFrom = emp.getSalaryFrom();// 給料範囲from
		String salaryTo = emp.getSalaryTo();// 給料範囲to
		String unitPriceFrom = emp.getUnitPriceFrom();// 単価範囲from
		String unitPriceTo = emp.getUnitPriceTo();// 単価範囲to
		String sortToggleSalary = emp.getSortToggleSalary();// 給料ソート
		String statusOfResidence = emp.getStatusOfResidence();// 在留資格

		if (statusOfResidence != null && statusOfResidence.length() != 0) {
			sendMap.put("statusOfResidence", statusOfResidence);
		}
		if (employeeNo != null && employeeNo.length() != 0) {
			sendMap.put("employeeNo", employeeNo);
		}
		if (employeeFristName != null && employeeFristName.length() != 0) {
			sendMap.put("employeeFristName", employeeFristName);
		}
		if (employeeLastName != null && employeeLastName.length() != 0) {
			sendMap.put("employeeLastName", employeeLastName);
		}
		if (emploryeeForm != null && emploryeeForm.length() != 0) {
			sendMap.put("emploryeeForm", emploryeeForm);
		}
		if (joinCompanyOfYear != null && joinCompanyOfYear.length() != 0) {
			sendMap.put("joinCompanyOfYear", joinCompanyOfYear);
		}
		if (joinCompanyOfMonth != null && joinCompanyOfMonth.length() != 0) {
			sendMap.put("joinCompanyOfMonth", joinCompanyOfMonth);
		}
		if (joinCompanyOfMonth != null && joinCompanyOfMonth.length() != 0) {
			sendMap.put("joinCompanyOfMonth", joinCompanyOfMonth);
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
		if (salaryFrom != null && salaryFrom.length() != 0) {
			sendMap.put("salaryFrom", salaryFrom);
		}
		if (salaryTo != null && salaryTo.length() != 0) {
			sendMap.put("salaryTo", salaryTo);
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
		if (developmentLanguageNo != null && developmentLanguageNo.length() != 0) {
			sendMap.put("developmentLanguageNo", developmentLanguageNo);
		}

		if (sortToggleSalary != null && sortToggleSalary.length() != 0) {
			sendMap.put("sortToggleSalary", sortToggleSalary);
		}
		return sendMap;

	}

}
