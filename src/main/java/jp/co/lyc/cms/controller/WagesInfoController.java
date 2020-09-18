package jp.co.lyc.cms.controller;

import java.util.ArrayList;
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
import jp.co.lyc.cms.mapper.WagesInfoMapper;
import jp.co.lyc.cms.model.WagesInfoModel;
import jp.co.lyc.cms.service.WagesInfoService;
import jp.co.lyc.cms.validation.WagesInfoValidation;

@Controller
@RequestMapping(value = "/wagesInfo")
public class WagesInfoController extends BaseController{
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	String errorsMessage = "";
	
	@Autowired
	WagesInfoService wagesInfoService;
	
	@Autowired
	WagesInfoMapper wagesInfoMapper;
	
	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getWagesInfo", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getWagesInfo(@RequestBody WagesInfoModel wagesInfoMod) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		HashMap<String, String> sendMap = new HashMap<String, String>();
		sendMap.put("employeeNo", wagesInfoMod.getEmployeeNo());
		ArrayList<WagesInfoModel> wagesInfoList = wagesInfoMapper.getWagesInfo(sendMap);
		if(wagesInfoList.size() == 0) {
			result.put("errorsMessage", "該当社員の給料データがない");
			return result;
		}
		result.put("wagesInfoList", wagesInfoList);
		return result;
	}
	
	/**
	 * 登録ボタン
	 * @param wagesInfoModel
	 * @return
	 */
	@RequestMapping(value = "/toroku", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> toroku(@RequestBody WagesInfoModel wagesInfoModel) {
		logger.info("WagesInfoController.onloadPage:" + "登録開始");
		Map<String, Object> result = new HashMap<String, Object>();
		errorsMessage = "";
		DataBinder binder = new DataBinder(wagesInfoModel);
		binder.setValidator(new WagesInfoValidation());
		binder.validate();
		BindingResult results = binder.getBindingResult();
		if (results.hasErrors()) {
			results.getAllErrors().forEach(o -> {
				FieldError error = (FieldError) o;
				errorsMessage += error.getDefaultMessage();// エラーメッセージ
			});
			result.put("errorsMessage", errorsMessage);// エラーメッセージ
			logger.info("WagesInfoController.onloadPage:" + "登録終了");
			return result;
		}
		wagesInfoModel.setUpdateUser((String)getSession().getAttribute("employeeName"));
		if(wagesInfoModel.getActionType().equals("insert")) {//插入场合
			HashMap<String, String> sendMap = new HashMap<String, String>();
			sendMap.put("employeeNo", wagesInfoModel.getEmployeeNo());
			sendMap.put("reflectYearAndMonth", wagesInfoModel.getReflectYearAndMonth());
			ArrayList<WagesInfoModel> checkMod = wagesInfoMapper.getWagesInfo(sendMap);
			if(checkMod.size() > 0) {
				result.put("errorsMessage","データが存在しているため、追加はできません！");
				return result;
			}
			boolean resultBool = wagesInfoService.insert(wagesInfoModel);
			if(resultBool) {
				result.put("message","登録成功");
			}else {
				result.put("errorsMessage","登録失败");
			}
		}else if(wagesInfoModel.getActionType().equals("update")) {//更新场合
			HashMap<String, String> sendMap = new HashMap<String, String>();
			sendMap.put("employeeNo", wagesInfoModel.getEmployeeNo());
			//更新しておく反映年月の取得
			ArrayList<WagesInfoModel> lastList = wagesInfoMapper.getWagesInfo(sendMap);
			if(!lastList.get(lastList.size() - 1).getReflectYearAndMonth().equals(
					wagesInfoModel.getReflectYearAndMonth())) {
				sendMap.put("reflectYearAndMonth", wagesInfoModel.getReflectYearAndMonth());
				ArrayList<WagesInfoModel> checkMod = wagesInfoMapper.getWagesInfo(sendMap);
				if(checkMod.size() > 0) {
					result.put("errorsMessage","更新した反映年月はデータベースにあるため、反映年月を確認してください！");
					return result;
				}
			}
			wagesInfoModel.setUpdatedReflectYearAndMonth(
					lastList.get(lastList.size()-1).getReflectYearAndMonth());
			boolean resultBool = wagesInfoService.update(wagesInfoModel);
			if(resultBool) {
				result.put("message","更新成功");
			}else {
				result.put("errorsMessage","更新失败");
			}
		}
		logger.info("WagesInfoController.onloadPage:" + "登録終了");
		return result;
	}
}
