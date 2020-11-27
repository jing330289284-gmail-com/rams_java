package jp.co.lyc.cms.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import jp.co.lyc.cms.common.BaseController;
import jp.co.lyc.cms.model.EmailModel;
import jp.co.lyc.cms.model.EmployeeModel;
import jp.co.lyc.cms.model.SalesSituationModel;
import jp.co.lyc.cms.model.SendLettersConfirmModel;
import jp.co.lyc.cms.service.SendLettersConfirmService;
import jp.co.lyc.cms.util.UtilsCheckMethod;
import jp.co.lyc.cms.util.UtilsController;

@Controller
@RequestMapping(value = "/sendLettersConfirm")
public class SendLettersConfirm  extends BaseController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	SendLettersConfirmService sendLettersConfirmService;
	
	@Autowired
	UtilsController utils;

	/**
	 * データを取得
	 * 
	 * @param emp
	 * @return List
	 */

	@RequestMapping(value = "/getSalesEmps", method = RequestMethod.POST)
	@ResponseBody
	public List<SendLettersConfirmModel> getSalesEmps(@RequestBody SendLettersConfirmModel model) {

		logger.info("getSalesEmps:" + "検索開始");
		List<SendLettersConfirmModel> sendLettersConfirmModelList = new ArrayList<SendLettersConfirmModel>();
		try {
			sendLettersConfirmModelList = sendLettersConfirmService.getSalesEmps(model.getEmployeeNos());
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("getSalesEmps" + "検索結束");
		return sendLettersConfirmModelList;
	}
	
	@RequestMapping(value = "/getAllEmpsWithResume", method = RequestMethod.POST)
	@ResponseBody
	public List<SalesSituationModel> getAllEmpsWithResume() {

		logger.info("getSalesEmps:" + "検索開始");
		List<SalesSituationModel> sendLettersConfirmModelList = new ArrayList<SalesSituationModel>();
		try {
			sendLettersConfirmModelList = sendLettersConfirmService.getAllEmpsWithResume();
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("getSalesEmps" + "検索結束");
		return sendLettersConfirmModelList;
	}
	
	@RequestMapping(value = "/getLoginUserInfo", method = RequestMethod.POST)
	@ResponseBody
	public List<EmployeeModel> getLoginUserInfo() {

		logger.info("getSalesEmps:" + "検索開始");
		String lobinUserNo = getSession().getAttribute("employeeNo").toString();
		List<EmployeeModel> sendLettersConfirmModelList = new ArrayList<EmployeeModel>();
		try {
			sendLettersConfirmModelList = sendLettersConfirmService.getLoginUserInfo(lobinUserNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("getSalesEmps" + "検索結束");
		return sendLettersConfirmModelList;
	}	
	
	@RequestMapping(value = "/getMail", method = RequestMethod.POST)
	@ResponseBody
	public List<EmployeeModel> getMail() {

		logger.info("getSalesEmps:" + "検索開始");
		
		List<EmployeeModel> sendLettersConfirmModelList = new ArrayList<EmployeeModel>();
		try {
			sendLettersConfirmModelList = sendLettersConfirmService.getMail();
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("getSalesEmps" + "検索結束");
		return sendLettersConfirmModelList;
	}
	
	@RequestMapping(value = "/sendMailWithFile", method = RequestMethod.POST)
	@ResponseBody
	public void sendMailWithFile(@RequestBody EmailModel emailModel) {

		logger.info("sendMailWithFile:" + "送信開始");
		
		//EmailModel emailModel = new EmailModel();
		//　String mail = es.getEmployeeCompanyMail(loginModel.getEmployeeNo());
		//　受信人のメール
		emailModel.setUserName(getSession().getAttribute("employeeName").toString());
		emailModel.setPassword("Lyc2020-0908-");
		//emailModel.setFromAddress(model.getMailFrom());
		emailModel.setContextType("text/html;charset=utf-8");
		utils.sendMailWithFile(emailModel);
		logger.info("sendMailWithFile" + "送信結束");
	}
}
