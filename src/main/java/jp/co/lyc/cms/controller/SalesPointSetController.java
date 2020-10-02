package jp.co.lyc.cms.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import jp.co.lyc.cms.common.BaseController;
import jp.co.lyc.cms.model.SalesPointSetModel;
import jp.co.lyc.cms.model.SiteModel;
import jp.co.lyc.cms.service.SalesPointSetService;
import jp.co.lyc.cms.validation.SiteInfoValidation;

@Controller
@CrossOrigin(origins = "http://127.0.0.1:3000")
public class SalesPointSetController extends BaseController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	SalesPointSetService salesPointSetService;
	String errorsMessage = "";

	/*
	 * @RequestMapping(value = "/insertSiteInfo")
	 * 
	 * @ResponseBody public Map<String, Object> insertSiteInfo(@RequestBody
	 * SiteModel siteModel) { Map<String, Object> result = new HashMap<>();
	 * errorsMessage = ""; DataBinder binder = new DataBinder(siteModel);
	 * binder.setValidator(new SiteInfoValidation()); binder.validate();
	 * BindingResult results = binder.getBindingResult(); if (results.hasErrors()) {
	 * results.getAllErrors().forEach(o -> { FieldError error = (FieldError) o;
	 * errorsMessage += error.getDefaultMessage();// エラーメッセージ });
	 * result.put("errorsMessage", errorsMessage);// エラーメッセージ return result; } //
	 * 登陆处理 if (insert(putData(siteModel))) { result.put("result", true); } else {
	 * result.put("result", false); }
	 * logger.info("SiteInfoController.insertSiteInfo:" + "追加結束"); return result;
	 * 
	 * }
	 * 
	 * @RequestMapping(value = "/updateSiteInfo")
	 * 
	 * @ResponseBody public Map<String, Object> updateSiteInfo(@RequestBody
	 * SiteModel siteModel) { Map<String, Object> result = new HashMap<>();
	 * errorsMessage = ""; DataBinder binder = new DataBinder(siteModel);
	 * binder.setValidator(new SiteInfoValidation()); binder.validate();
	 * BindingResult results = binder.getBindingResult(); if (results.hasErrors()) {
	 * results.getAllErrors().forEach(o -> { FieldError error = (FieldError) o;
	 * errorsMessage += error.getDefaultMessage();// エラーメッセージ });
	 * result.put("errorsMessage", errorsMessage);// エラーメッセージ return result; } //
	 * 登陆处理 if (update(putData(siteModel))) { result.put("result", true); } else {
	 * result.put("result", false); }
	 * logger.info("SiteInfoController.updateSiteInfo:" + "追加結束"); return result;
	 * 
	 * }
	 * 
	 *//**
		 * insert
		 * 
		 * @return
		 */
	/*
	 * 
	 * public boolean insert(Map<String, Object> sendMap) { return
	 * salesPointSetService.insertSiteInfo(sendMap); }
	 * 
	 *//**
		 * update
		 * 
		 * @return
		 *//*
			 * 
			 * public boolean update(Map<String, Object> sendMap) { return
			 * salesPointSetService.updateSiteInfo(sendMap); }
			 */

	/**
	 * データ整理
	 * 
	 * @return
	 */
	public Map<String, Object> putData(SiteModel siteModel) {
		HttpSession loginSession = getSession();
		Map<String, Object> sendMap = new HashMap<String, Object>();

		sendMap.put("updateUser", loginSession.getAttribute("employeeName"));
		return sendMap;
	}

	@RequestMapping(value = "/getSalesPointInfo", method = RequestMethod.POST)
	@ResponseBody
	public List<SalesPointSetModel> getSalesPointInfo(@RequestBody SalesPointSetModel salesPointSetModel) {
		List<SalesPointSetModel> siteList = new ArrayList<SalesPointSetModel>();
		Map<String, Object> sendMap = new HashMap<String, Object>();
		String employee = salesPointSetModel.getEmployee();
		String newMember = salesPointSetModel.getNewMember();
		String customerContract = salesPointSetModel.getCustomerContract();
		if (employee != null && employee.length() != 0) {
			sendMap.put("employee", employee);
		}
		if (newMember != null && newMember.length() != 0) {
			sendMap.put("newMember", newMember);
		}
		if (customerContract != null && customerContract.length() != 0) {
			sendMap.put("customerContract", customerContract);
		}
		try {
			siteList = salesPointSetService.getSalesPointInfo(sendMap);

		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("GetEmployeeInfoController.getEmployeeInfo:" + "検索結束");
		return siteList;
	}
}
