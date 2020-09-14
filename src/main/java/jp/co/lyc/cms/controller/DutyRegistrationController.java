package jp.co.lyc.cms.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import jp.co.lyc.cms.common.BaseController;
import jp.co.lyc.cms.model.DutyManagementModel;
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
		DutyRegistrationModel checkMod = dutyRegistrationService.selectDutyRegistration(dutyRegistrationModel.toHashMap());
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
			result = this.insert(employeeWorkTimeModel);
		}
		
//		DutyRegistrationModel checkMod = dutyRegistrationService.selectDutyRegistration(dutyRegistrationModel.toHashMap());
//		if (checkMod == null) {
//			result = insert(dutyRegistrationModel);
//		} else if (checkMod != null ) {
//			result = update(dutyRegistrationModel);
//		}
		logger.info("DutyRegistrationController.dutyInsert:" + "登録終了");
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
		HashMap<String, String> sendMap = dutyRegistrationModel.toHashMap();
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
		HashMap<String, String> sendMap = dutyRegistrationModel.toHashMap();
		result  = dutyRegistrationService.updateDutyRegistration(sendMap);
		logger.info("DutyRegistrationController.update:" + "アップデート終了");
		return result;	
	}
}
