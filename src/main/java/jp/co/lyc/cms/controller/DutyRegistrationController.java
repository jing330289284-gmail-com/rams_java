package jp.co.lyc.cms.controller;

import java.util.HashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import jp.co.lyc.cms.model.DutyRegistrationModel;
import jp.co.lyc.cms.service.DutyRegistrationService;

@Controller
@CrossOrigin(origins = "http://127.0.0.1:3000")
@RequestMapping(value = "/dutyRegistration")
public class DutyRegistrationController {

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
			result = insert(dutyRegistrationModel);
		} else if (checkMod != null ) {
			result = update(dutyRegistrationModel);
		}
		logger.info("DutyRegistrationController.breakTimeInsert:" + "登録終了");
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
