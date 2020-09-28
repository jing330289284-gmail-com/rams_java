package jp.co.lyc.cms.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import jp.co.lyc.cms.common.BaseController;
import jp.co.lyc.cms.model.WorkRepotModel;
import jp.co.lyc.cms.service.WorkRepotService;


@Controller
@RequestMapping(value = "/workRepot")
public class WorkRepotController extends BaseController { 

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
	public List<WorkRepotModel> selectWorkRepot(WorkRepotModel workRepotModel) {
		logger.info("WorkRepotController.selectWorkRepot:" + "検索開始");
		workRepotModel.setEmployeeNo(getSession().getAttribute("employeeNo").toString());
		List<WorkRepotModel> checkMod = workRepotService.selectWorkRepot(workRepotModel);
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
	public boolean updateWorkRepotModel(@RequestBody WorkRepotModel emp){
		emp.setEmployeeNo(getSession().getAttribute("employeeNo").toString());
		logger.info("DutyManagementController.updateworkRepot:" + "アップデート開始");
		boolean result = false;	
		result  = workRepotService.updateDutyManagement(emp);
		logger.info("DutyManagementController.updateworkRepot:" + "アップデート終了");
		return result;	
	}
}
