package jp.co.lyc.cms.controller;

import java.security.GeneralSecurityException;
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
import jp.co.lyc.cms.model.AllEmployName;
import jp.co.lyc.cms.model.EmailModel;
import jp.co.lyc.cms.model.EmployeeModel;
import jp.co.lyc.cms.model.SalesSituationModel;
import jp.co.lyc.cms.model.SendLettersConfirmModel;
import jp.co.lyc.cms.service.SendLettersConfirmService;
import jp.co.lyc.cms.util.UtilsCheckMethod;
import jp.co.lyc.cms.util.UtilsController;

@Controller
@RequestMapping(value = "/sendLettersConfirm")
public class SendLettersConfirm extends BaseController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	SendLettersConfirmService sendLettersConfirmService;

	@Autowired
	UtilsController utils;

	/**
	 * データを取得
	 * 
	 * @param model
	 * @return List
	 */

	@RequestMapping(value = "/getSalesEmps", method = RequestMethod.POST)
	@ResponseBody
	public List<SendLettersConfirmModel> getSalesEmps(@RequestBody SendLettersConfirmModel model) {

		logger.info("getSalesEmps:" + "検索開始");
		List<SendLettersConfirmModel> sendLettersConfirmModelList = new ArrayList<SendLettersConfirmModel>();
		try {
			sendLettersConfirmModelList = sendLettersConfirmService.getSalesEmps(model.getEmployeeNos());
			int i = 1;
			for (SendLettersConfirmModel sendletter : sendLettersConfirmModelList) {
				sendletter.setIndex(i++);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (int j = 0; j < sendLettersConfirmModelList.size(); j++) {
			ArrayList<String> resumeInfoTemp = new ArrayList<String>();
			/*
			 * if (!(sendLettersConfirmModelList.get(j).getResumeInfo1() == null ||
			 * sendLettersConfirmModelList.get(j).getResumeInfo1().equals(""))) {
			 * resumeInfoTemp.add(sendLettersConfirmModelList.get(j).getResumeInfo1()
			 * .split("/")[sendLettersConfirmModelList.get(j).getResumeInfo1().split("/").
			 * length - 1]); } if (!(sendLettersConfirmModelList.get(j).getResumeInfo2() ==
			 * null || sendLettersConfirmModelList.get(j).getResumeInfo2().equals(""))) {
			 * resumeInfoTemp.add(sendLettersConfirmModelList.get(j).getResumeInfo2()
			 * .split("/")[sendLettersConfirmModelList.get(j).getResumeInfo2().split("/").
			 * length - 1]); }
			 */
			if (!(sendLettersConfirmModelList.get(j).getResumeName1() == null
					|| sendLettersConfirmModelList.get(j).getResumeName1().equals(""))) {
				resumeInfoTemp.add(sendLettersConfirmModelList.get(j).getResumeName1());
			}
			if (!(sendLettersConfirmModelList.get(j).getResumeName2() == null
					|| sendLettersConfirmModelList.get(j).getResumeName2().equals(""))) {
				resumeInfoTemp.add(sendLettersConfirmModelList.get(j).getResumeName2());
			}
			sendLettersConfirmModelList.get(j).setResumeInfoList(resumeInfoTemp);
			if (resumeInfoTemp.size() > 0) {
				sendLettersConfirmModelList.get(j).setResumeInfoName(resumeInfoTemp.get(0));
			} else {
				sendLettersConfirmModelList.get(j).setResumeInfoName("");
			}
		}
		logger.info("getSalesEmps" + "検索結束");
		return sendLettersConfirmModelList;
	}

	@RequestMapping(value = "/updateSalesSentence", method = RequestMethod.POST)
	@ResponseBody
	public void updateSalesSentence(@RequestBody SendLettersConfirmModel model) {

		logger.info("updateSalesSentence:" + "更新開始");
		model.setUpdateUser(getSession().getAttribute("employeeName").toString());
		sendLettersConfirmService.updateSalesSentence(model);
		logger.info("updateSalesSentence" + "更新結束");
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
		if (sendLettersConfirmModelList.get(0).getPhoneNo() != null
				&& !sendLettersConfirmModelList.get(0).getPhoneNo().equals("")) {
			sendLettersConfirmModelList.get(0)
					.setPhoneNo(sendLettersConfirmModelList.get(0).getPhoneNo().substring(0, 3) + "-"
							+ sendLettersConfirmModelList.get(0).getPhoneNo().substring(3, 7) + "-"
							+ sendLettersConfirmModelList.get(0).getPhoneNo().substring(7, 11));
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

		// EmailModel emailModel = new EmailModel();
		// String mail = es.getEmployeeCompanyMail(loginModel.getEmployeeNo());
		// 受信人のメール
		emailModel.setUserName(getSession().getAttribute("employeeName").toString());
		emailModel.setPassword("Lyc2020-0908-");
		// emailModel.setFromAddress(model.getMailFrom());
		emailModel.setContextType("text/html;charset=utf-8");
		if (emailModel.getResumePath() == null || emailModel.getResumePath().equals(""))
			utils.EmailSend(emailModel);
		else
			utils.sendMailWithFile(emailModel);
		logger.info("sendMailWithFile" + "送信結束");
	}

	// 要員追加機能の新規 20201216 張棟 START
	/**
	 * 名前と所属を取る<br/>
	 * <br/>
	 * 要員送信確認画面初期化する時、全て社内要員とBP社員の名前と所属を取る<br/>
	 */
	@RequestMapping(value = "/getAllEmployInfoName", method = RequestMethod.POST)
	@ResponseBody
	public List<AllEmployName> getAllEmployInfoName() {
		List<AllEmployName> EmployInfo = new ArrayList<AllEmployName>();
		EmployInfo = sendLettersConfirmService.getAllEmployInfoName();
		return EmployInfo;
	}
	// 要員追加機能の新規 20201216 張棟 END

}
