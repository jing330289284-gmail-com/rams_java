package jp.co.lyc.cms.controller;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import jp.co.lyc.cms.model.DutyManagementModel;
import jp.co.lyc.cms.service.DutyManagementService;

@Controller
@CrossOrigin(origins = "http://127.0.0.1:3000")
@RequestMapping(value = "/dutyManagement")
public class DutyManagementController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	DutyManagementService dutyManagementService;
	
	/**
	 * 登録ボタン
	 * @param topCustomerMod
	 * @return
	 */
	@RequestMapping(value = "/selectDutyManagement", method = RequestMethod.POST)
	@ResponseBody
	public boolean breakTimeInsert(@RequestBody HashMap<String, String> dutyManagementModel) {
		logger.info("DutyManagementController.selectDutyManagement:" + "検索開始");
		boolean result = false;
		logger.info(dutyManagementModel.toString());
		List<DutyManagementModel> checkMod = dutyManagementService.selectDutyManagement(dutyManagementModel);
		logger.info("DutyManagementController.selectDutyManagement:" + "検索終了");
		return result;
	}

	/**
	 * アップデート
	 * @param topCustomerMod
	 * @return
	 */
	@RequestMapping(value = "/updateDutyManagement", method = RequestMethod.POST)
	@ResponseBody
	public boolean update(HashMap<String, String> dutyManagementModel) {
		logger.info("DutyManagementController.updateDutyManagement:" + "アップデート開始");
		boolean result = false;
		HashMap<String, String> sendMap = dutyManagementModel;
		result  = dutyManagementService.updateDutyManagement(sendMap);
		logger.info("DutyManagementController.updateDutyManagement:" + "アップデート終了");
		return result;	
	}
}
