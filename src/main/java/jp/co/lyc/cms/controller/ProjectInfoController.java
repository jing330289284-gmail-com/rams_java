package jp.co.lyc.cms.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import jp.co.lyc.cms.common.BaseController;
import jp.co.lyc.cms.mapper.ProjectInfoMapper;
import jp.co.lyc.cms.model.ProjectInfoModel;
import jp.co.lyc.cms.service.ProjectInfoService;
import jp.co.lyc.cms.validation.ProjectInfoValidation;
import jp.co.lyc.cms.validation.WagesInfoValidation;

@Controller
@RequestMapping(value = "/projectInfo")
public class ProjectInfoController extends BaseController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	ProjectInfoMapper projectInfoMapper;
	@Autowired
	ProjectInfoService projectInfoService;

	String errorsMessage = "";

	/**
	 * 画面初期化
	 * 
	 * @param projectInfoModel
	 * @return
	 */
	@RequestMapping(value = "/init", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> init(@RequestBody ProjectInfoModel projectInfoModel) {
		logger.info("ProjectInfoController.init:" + "初期化開始");
		Map<String, Object> result = new HashMap<String, Object>();
		if (projectInfoModel.getActionType().equals("insert")) {
			result.put("projectNo", saiban());
		} else {
			HashMap<String, String> sendMap = new HashMap<String, String>();
			sendMap.put("projectNo", projectInfoModel.getProjectNo());
			result.put("resultList", projectInfoMapper.getProjectInfo(sendMap));
		}
		return result;
	}

	/**
	 * 責任者の取得
	 * 
	 * @param customerNo
	 * @return
	 */
	@RequestMapping(value = "/getPersonInCharge", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getPersonInCharge(@RequestBody ProjectInfoModel projectInfoModel) {
		logger.info("ProjectInfoController.getPersonInCharge:" + "初期化開始");
		Map<String, Object> result = new HashMap<String, Object>();
//		errorsMessage = "";
//		DataBinder binder = new DataBinder(wagesInfoModel);
//		binder.setValidator(new WagesInfoValidation());
//		binder.validate();
//		BindingResult results = binder.getBindingResult();
//		if (results.hasErrors()) {
//			results.getAllErrors().forEach(o -> {
//				FieldError error = (FieldError) o;
//				errorsMessage += error.getDefaultMessage();// エラーメッセージ
//			});
//			result.put("errorsMessage", errorsMessage);// エラーメッセージ
//			logger.info("WagesInfoController.onloadPage:" + "登録終了");
//			return result;
//		}
		result.put("personInChargeDrop", projectInfoService.getPersonInCharge(projectInfoModel.getCustomerNo()));
		return result;
	}

	/**
	 * 登録ボタン
	 * 
	 * @param customerNo
	 * @return
	 */
	@RequestMapping(value = "/toroku", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> toroku(@RequestBody ProjectInfoModel projectInfoModel) {
		logger.info("ProjectInfoController.toroku:" + "初期化開始");
		Map<String, Object> result = new HashMap<String, Object>();
		errorsMessage = "";
		DataBinder binder = new DataBinder(projectInfoModel);
		binder.setValidator(new ProjectInfoValidation());
		binder.validate();
		BindingResult results = binder.getBindingResult();
		if (results.hasErrors()) {
			results.getAllErrors().forEach(o -> {
				FieldError error = (FieldError) o;
				errorsMessage += error.getDefaultMessage();// エラーメッセージ
			});
			result.put("errorsMessage", errorsMessage);// エラーメッセージ
			logger.info("projectInfoSearchController.onloadPage:" + "登録終了");
			return result;
		}
		projectInfoModel.setUpdateUser((String) getSession().getAttribute("employeeName"));
		HashMap<String, String> sendMap = projectInfoService.getSendMap(projectInfoModel);
		boolean resultBoolean = true;
		if (projectInfoModel.getActionType().equals("insert")) {
			resultBoolean = projectInfoService.insert(sendMap);
		} else {
			resultBoolean = projectInfoService.update(sendMap);
		}
		if (resultBoolean) {
			result.put("message", "处理成功");
			return result;
		} else {
			result.put("errorsMessage", "处理失敗");
			return result;
		}
	}

	/**
	 * 採番
	 * 
	 * @return
	 */
	public String saiban() {
		Calendar cal = Calendar.getInstance();
		String yearAndMonth = Integer.toString(cal.get(Calendar.YEAR));
		int month = cal.get(Calendar.MONTH) + 1;
		yearAndMonth += month > 10 ? month : "0" + month;
		ArrayList<String> saibanList = projectInfoMapper.saiban(yearAndMonth);
		String saiban = "";
		if (saibanList.size() == 0 || saibanList.get(0) == null) {
			saiban = yearAndMonth + "01";
		} else {
			saiban = Integer.toString(Integer.parseInt(saibanList.get(0)) + 1);
		}
		return saiban;
	}
}
