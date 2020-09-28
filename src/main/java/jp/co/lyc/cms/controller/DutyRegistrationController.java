package jp.co.lyc.cms.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import jp.co.lyc.cms.common.BaseController;
import jp.co.lyc.cms.model.BreakTimeModel;
import jp.co.lyc.cms.model.DutyRegistrationModel;
import jp.co.lyc.cms.model.EmployeeWorkTimeModel;
import jp.co.lyc.cms.service.DutyRegistrationService;

@Controller
@RequestMapping(value = "/dutyRegistration")
public class DutyRegistrationController extends BaseController{

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	DutyRegistrationService dutyRegistrationService;
	
	/**
	 * 登録ボタン
	 * @param topCustomerMod
	 * @return
	 */
	@RequestMapping(value = "/breakTimeInsert", method = RequestMethod.POST)
	@ResponseBody
	public boolean breakTimeInsert(@RequestBody DutyRegistrationModel dutyRegistrationModel) {
		logger.info("DutyRegistrationController.breakTimeInsert:" + "登録開始");
		boolean result = false;
		logger.info(dutyRegistrationModel.toString());
//		DutyRegistrationModel checkMod = dutyRegistrationService.selectDutyRegistration(dutyRegistrationModel.toHashMap());
		DutyRegistrationModel checkMod = null;
		if (checkMod == null) {
			result = this.insert(dutyRegistrationModel);
		} else if (checkMod != null ) {
			result = this.update(dutyRegistrationModel);
		}
		logger.info("DutyRegistrationController.breakTimeInsert:" + "登録終了");
		return result;
	}
	/**
	 */
	@RequestMapping(value = "/dutyInsert", method = RequestMethod.POST)
	@ResponseBody
	public boolean dutyInsert(@RequestBody String requestJson) {
		logger.info("DutyRegistrationController.dutyInsert:" + "登録開始");
		boolean result = false;
		logger.info(requestJson);
		JSONObject jsonObject = JSON.parseObject(requestJson);
		JSONObject tempJsonObject = null;
		JSONArray jsonArray = jsonObject.getJSONArray("dateData");
		int dataSize = jsonArray.size();
		HttpSession loginSession = getSession();
		EmployeeWorkTimeModel[] arrEmployeeWorkTimeModel = new EmployeeWorkTimeModel[dataSize];
		for (int i = 0; i < dataSize; i++)	{
			tempJsonObject = jsonArray.getJSONObject(i);
			tempJsonObject.put("employeeNo", super.getSession().getAttribute("employeeNo"));
			tempJsonObject.put("yearAndMonth", jsonObject.getOrDefault("yearMonth", ""));
			tempJsonObject.put("morningTime", tempJsonObject.getOrDefault("startTime", ""));
			tempJsonObject.put("afternoonTime", tempJsonObject.getOrDefault("endTime", ""));
			tempJsonObject.put("holidayFlag", tempJsonObject.getOrDefault("isWork", ""));
			tempJsonObject.put("workTime", tempJsonObject.getOrDefault("workHour", ""));
			tempJsonObject.put("confirmFlag", "0");
			tempJsonObject.put("siteCustomer", jsonObject.getOrDefault("siteCustomer", ""));
			tempJsonObject.put("customer", jsonObject.getOrDefault("customer", ""));
			tempJsonObject.put("siteResponsiblePerson", jsonObject.getOrDefault("siteResponsiblePerson", ""));
			tempJsonObject.put("systemName", jsonObject.getOrDefault("systemName", ""));
			tempJsonObject.put("updateUser", super.getSession().getAttribute("employeeNo"));
			
			EmployeeWorkTimeModel employeeWorkTimeModel = EmployeeWorkTimeModel.fromHashMap(tempJsonObject.getInnerMap());
			EmployeeWorkTimeModel[] checkMod = dutyRegistrationService.selectDuty(employeeWorkTimeModel.toHashMap());
			if (checkMod.length > 0)	{
				result = this.update(employeeWorkTimeModel);				
			}
			else	{
				result = this.insert(employeeWorkTimeModel);
			}
		}
		logger.info("DutyRegistrationController.dutyInsert:" + "登録終了");
		return result;
	}
	/**
	 */
	@RequestMapping(value = "/getDutyInfo", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getDutyInfo(@RequestBody String requestJson) {
		logger.info("DutyRegistrationController.dutySelect:" + "検索開始");
		Map<String, Object> result = new HashMap<String, Object>();
		JSONObject jsonObject = JSON.parseObject(requestJson);
		jsonObject.put("employeeNo", super.getSession().getAttribute("employeeNo"));
		jsonObject.put("breakTimeYearMonth", jsonObject.getOrDefault("yearMonth", ""));

		BreakTimeModel breakTimeModel = dutyRegistrationService.selectDutyRegistration(jsonObject.getInnerMap());

		result.put("breakTime", breakTimeModel);
		result.put("employeeNo", super.getSession().getAttribute("employeeNo"));
		ArrayList<Map<String, Object>> dutyData = this.dutySelect(requestJson);
		result.put("dateData", dutyData);
		if (dutyData.size() > 0)	{
			result.put("siteCustomer", dutyData.get(0).get("siteCustomer"));
			result.put("customer", dutyData.get(0).get("customer"));
			result.put("siteResponsiblePerson", dutyData.get(0).get("siteResponsiblePerson"));
			result.put("systemName", dutyData.get(0).get("systemName"));
		}
		logger.info("DutyRegistrationController.dutySelect:" + "検索終了");	
		return result;
	}
	public ArrayList<Map<String, Object>> dutySelect(String requestJson) {
		logger.info("DutyRegistrationController.dutySelect:" + "検索開始");
		ArrayList<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		EmployeeWorkTimeModel[] arrEmployeeWorkTimeModel = null;
		JSONObject jsonObject = JSON.parseObject(requestJson);
		jsonObject.put("employeeNo", super.getSession().getAttribute("employeeNo"));
		jsonObject.put("yearAndMonth", jsonObject.getOrDefault("yearMonth", ""));
		arrEmployeeWorkTimeModel  = dutyRegistrationService.selectDuty(jsonObject.getInnerMap());
		Map<String, Object> tempMap = new HashMap<String, Object>();
		for (EmployeeWorkTimeModel employeeWorkTimeModel : arrEmployeeWorkTimeModel)	{
			tempMap = new HashMap<String, Object>();
			tempMap.put("day",			employeeWorkTimeModel.getDay());
			tempMap.put("yearMonth",	employeeWorkTimeModel.getYearAndMonth());
			tempMap.put("startTime",	employeeWorkTimeModel.getMorningTime());
			tempMap.put("endTime",		employeeWorkTimeModel.getAfternoonTime());
			tempMap.put("isWork",		employeeWorkTimeModel.getHolidayFlag());
			tempMap.put("workHour",		employeeWorkTimeModel.getWorkTime());
			result.add(tempMap);
		}
		if (result.size() > 0)	{
			result.get(0).put("siteCustomer", arrEmployeeWorkTimeModel[0].getSiteCustomer());
			result.get(0).put("customer", arrEmployeeWorkTimeModel[0].getCustomer());
			result.get(0).put("siteResponsiblePerson", arrEmployeeWorkTimeModel[0].getSiteResponsiblePerson());
			result.get(0).put("systemName", arrEmployeeWorkTimeModel[0].getSystemName());
		}
		logger.info("DutyRegistrationController.dutySelect:" + "検索終了");	
		return result;
	}
	/**
	 * インサート
	 * @param topCustomerMod
	 * @return
	 */
	public boolean insert(DutyRegistrationModel dutyRegistrationModel) {
		logger.info("DutyRegistrationController.insert::" + "インサート開始");
		boolean result = false;
		dutyRegistrationModel.setEmployeeNo((String)super.getSession().getAttribute("employeeNo"));
		HashMap<String, Object> sendMap = dutyRegistrationModel.toHashMap();
		result  = dutyRegistrationService.insertDutyRegistration(sendMap);
		logger.info("DutyRegistrationController.insert::" + "インサート終了");
		return result;	
	}
	/**
	 * インサート
	 * @param topCustomerMod
	 * @return
	 */
	public boolean insert(EmployeeWorkTimeModel employeeWorkTimeModel) {
		logger.info("DutyRegistrationController.insert::" + "インサート開始");
		boolean result = false;
		Map<String, Object> sendMap = employeeWorkTimeModel.toHashMap();
		result  = dutyRegistrationService.insertDuty(sendMap);
		logger.info("DutyRegistrationController.insert::" + "インサート終了");
		return result;
	}
	
	/**
	 * アップデート
	 * @param topCustomerMod
	 * @return
	 */
	public boolean update(DutyRegistrationModel dutyRegistrationModel) {
		logger.info("DutyRegistrationController.update:" + "アップデート開始");
		boolean result = false;
		HashMap<String, Object> sendMap = dutyRegistrationModel.toHashMap();
		result  = dutyRegistrationService.updateDutyRegistration(sendMap);
		logger.info("DutyRegistrationController.update:" + "アップデート終了");
		return result;	
	}
	/**
	 * アップデート
	 * @param topCustomerMod
	 * @return
	 */
	public boolean update(EmployeeWorkTimeModel employeeWorkTimeModel) {
		logger.info("DutyRegistrationController.update:" + "アップデート開始");
		boolean result = false;
		Map<String, Object> sendMap = employeeWorkTimeModel.toHashMap();
		result  = dutyRegistrationService.updateDuty(sendMap);
		logger.info("DutyRegistrationController.update:" + "アップデート終了");
		return result;	
	}
}
