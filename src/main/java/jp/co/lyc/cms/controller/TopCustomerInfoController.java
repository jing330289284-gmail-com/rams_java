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

import jp.co.lyc.cms.model.CustomerInfoModel;
import jp.co.lyc.cms.model.TopCustomerInfoModel;
import jp.co.lyc.cms.service.TopCustomerInfoService;

@Controller
@CrossOrigin(origins = "http://127.0.0.1:3000")
@RequestMapping(value = "/topCustomerInfo")
public class TopCustomerInfoController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	TopCustomerInfoService topCustomerInfoService;
	
	/**
	 * 画面の初期化
	 * @param topCustomerMod
	 * @return
	 */
	@RequestMapping(value = "/onloadPage", method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String,Object> onloadPage(@RequestBody TopCustomerInfoModel topCustomerMod) {
		logger.info("TopCustomerInfoController.onloadPage:" + "初期化開始");
		HashMap<String,Object> resultMap = new HashMap<>();
		topCustomerMod = topCustomerInfoService.selectTopCustomerInfo(topCustomerMod.getTopCustomerNo());	
		resultMap.put("topCustomerMod", topCustomerMod);
		logger.info("TopCustomerInfoController.onloadPage:" + "初期化終了");
		return resultMap;
	}
	/**
	 * 登録ボタン
	 * @param topCustomerMod
	 * @return
	 */
	@RequestMapping(value = "/toroku", method = RequestMethod.POST)
	@ResponseBody
	public boolean toroku(@RequestBody TopCustomerInfoModel topCustomerMod) {
		logger.info("TopCustomerInfoController.onloadPage:" + "登録開始");
		boolean result = true;
		TopCustomerInfoModel checkMod = topCustomerInfoService.selectTopCustomerInfo(topCustomerMod.getTopCustomerNo());
		if (checkMod == null && topCustomerMod.getActionType().equals("insert")) {
			result = insert(topCustomerMod);
		} else if (checkMod != null && (topCustomerMod.getActionType().equals("update"))) {
			result = update(topCustomerMod);
		}
		logger.info("TopCustomerInfoController.onloadPage:" + "登録終了");
		return result;
	}
	/**
	 * インサート
	 * @param topCustomerMod
	 * @return
	 */
	public boolean insert(TopCustomerInfoModel topCustomerMod) {
		logger.info("BankInfoController.toroku:" + "インサート開始");
		boolean result = true;
		HashMap<String, String> sendMap = new HashMap<>();
		sendMap.put("topCustomerName", topCustomerMod.getTopCustomerName());
		sendMap.put("url", topCustomerMod.getUrl());
		sendMap.put("topCustomerAbbreviation", topCustomerMod.getTopCustomerAbbreviation());
		sendMap.put("remark", topCustomerMod.getRemark());
		sendMap.put("updateUser", topCustomerMod.getUpdateUser());
		sendMap.put("topCustomerNo", topCustomerMod.getTopCustomerNo());	
		result  = topCustomerInfoService.insertTopCustomerInfo(sendMap);
		logger.info("BankInfoController.toroku:" + "インサート終了");
		return result;	
	}
	
	/**
	 * アップデート
	 * @param topCustomerMod
	 * @return
	 */
	public boolean update(TopCustomerInfoModel topCustomerMod) {
		logger.info("BankInfoController.toroku:" + "アップデート開始");
		boolean result = true;
		TopCustomerInfoModel checkMod = new TopCustomerInfoModel();
		checkMod = topCustomerInfoService.selectTopCustomerInfo(topCustomerMod.getTopCustomerNo());
		HashMap<String, String> sendMap = new HashMap<>();
		if(!checkMod.getTopCustomerName().equals(topCustomerMod.getTopCustomerName())) {
			sendMap.put("topCustomerName", topCustomerMod.getTopCustomerName());
		}
		if(!checkMod.getUrl().equals(topCustomerMod.getUrl())) {
			sendMap.put("url", topCustomerMod.getUrl());
		}
		if(!checkMod.getRemark().equals(topCustomerMod.getRemark())) {
			sendMap.put("remark", topCustomerMod.getRemark());
		}
		if(!checkMod.getTopCustomerAbbreviation().equals(topCustomerMod.getTopCustomerAbbreviation())) {
			sendMap.put("topCustomerAbbreviation", topCustomerMod.getTopCustomerAbbreviation());
		}
		sendMap.put("updateUser", topCustomerMod.getUpdateUser());
		sendMap.put("topCustomerNo", topCustomerMod.getTopCustomerNo());	
		result  = topCustomerInfoService.updateTopCustomerInfo(sendMap);
		logger.info("BankInfoController.toroku:" + "アップデート終了");
		return result;	
	}
	/**
	 * nullと空の判断
	 * @param aString
	 * @return
	 */
	public boolean isNullOrEmpty(String aString) {
		boolean result = true;
		if (aString == null || aString.isEmpty()) {
			return result;
		} else {
			return result = false;
		}
	}
}
