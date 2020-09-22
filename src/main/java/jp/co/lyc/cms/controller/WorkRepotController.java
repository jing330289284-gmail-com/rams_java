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

import jp.co.lyc.cms.model.WorkRepotModel;
import jp.co.lyc.cms.service.WorkRepotService;

@Controller
@CrossOrigin(origins = "http://127.0.0.1:3000")
@RequestMapping(value = "/workRepot")
public class WorkRepotController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	WorkRepotService workRepotService;
	
	/**
	 * 登録ボタン
	 * @param topCustomerMod
	 * @return
	 */
	@RequestMapping(value = "/selectWorkRepot", method = RequestMethod.POST)
	@ResponseBody
	public List<WorkRepotModel> selectWorkRepot() {
		logger.info("WorkRepotController.selectWorkRepot:" + "検索開始");
		List<WorkRepotModel> checkMod = workRepotService.selectWorkRepot();
		logger.info("WorkRepotController.selectWorkRepot:" + "検索終了");
		return checkMod;
	}

	/**
	 * アップデート
	 * @param topCustomerMod
	 * @return
	 */
	@RequestMapping(value = "/updateworkRepot", method = RequestMethod.POST)
	@ResponseBody
	public boolean updateWorkRepotModel(@RequestBody HashMap<String, String> workRepotModel){
		logger.info("DutyManagementController.updateworkRepot:" + "アップデート開始");
		boolean result = false;
		HashMap<String, String> sendMap = workRepotModel;
		result  = workRepotService.updateDutyManagement(sendMap);
		logger.info("DutyManagementController.updateworkRepot:" + "アップデート終了");
		return result;	
	}
}
