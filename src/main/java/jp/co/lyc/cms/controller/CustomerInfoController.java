package jp.co.lyc.cms.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import jp.co.lyc.cms.common.BaseController;
import jp.co.lyc.cms.model.CustomerDepartmentInfoModel;
import jp.co.lyc.cms.model.CustomerInfoModel;
import jp.co.lyc.cms.service.CustomerInfoService;
import jp.co.lyc.cms.validation.CustomerInfoValidation;
@Controller
@RequestMapping(value = "/customerInfo")
public class CustomerInfoController extends BaseController{
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	//お客様情報service
	@Autowired
	CustomerInfoService customerInfoSer;
	//口座情報service
	@Autowired
	AccountInfoController accountInfoController;
	//上位お客様情報service
	@Autowired
	TopCustomerInfoController topCustomerInfoController;
	
	String errorsMessage = "";
	/**
	 * 页面加载
	 * @param customerInfoMod
	 * @return
	 */
	@RequestMapping(value = "/onloadPage", method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String,Object> onloadPage(@RequestBody CustomerInfoModel customerInfoMod ) {
		logger.info("CustomerInfoController.onloadPage:" + "初期化開始");
		HashMap<String,Object> resultMap = new HashMap<>();
		HashMap<String, String> sendMap = new HashMap<>();
		if(customerInfoMod.getActionType() != null) {
			//修正と詳細の場合
			if (customerInfoMod.getActionType().equals("update") || customerInfoMod.getActionType().equals("detail")) {
				customerInfoMod = customerInfoSer.selectCustomerInfo(customerInfoMod.getCustomerNo());	
				sendMap.put("customerNo", customerInfoMod.getCustomerNo());
				ArrayList<CustomerDepartmentInfoModel> customerDepartmentInfoList = 
						customerInfoSer.selectCustomerDepartmentInfo(sendMap);
				resultMap.put("customerInfoMod", customerInfoMod);
				resultMap.put("customerDepartmentInfoList", customerDepartmentInfoList);
			}else if(customerInfoMod.getActionType().equals("insert")){
				//追加の場合
				String saiban = customerInfoSer.customerNoSaiBan();
				int num = Integer.parseInt(saiban.substring(1),10);
				num += 1;
				if(num < 10) {
					saiban = "C00" + Integer.toString(num);
				}else if(num >=10 && num < 100) {
					saiban = "C0" + Integer.toString(num);
				}else if(num >= 100) {
					saiban = "C" + Integer.toString(num);
				}
				sendMap.put("customerNo", saiban);
				//部門のデータ
				ArrayList<CustomerDepartmentInfoModel> customerDepartmentInfoList = 
						customerInfoSer.selectCustomerDepartmentInfo(sendMap);
				resultMap.put("customerNoSaiBan",saiban);
				resultMap.put("customerDepartmentInfoList", customerDepartmentInfoList);
			}
		}
		logger.info("CustomerInfoController.onloadPage:" + "初期化終了");
		return resultMap;	
	}
	/**
	 * 登录按钮
	 * @param customerInfoMod
	 * @return 0成功，1失败，2上位客户不存在，3明细登录失败，4部门在部门表中不存在
	 */
	@RequestMapping(value = "/toroku", method = RequestMethod.POST)
	@ResponseBody
	@Transactional(rollbackFor = Exception.class)
	public Map<String, Object> toroku(@RequestBody CustomerInfoModel customerInfoMod) {
		errorsMessage = "";
		DataBinder binder = new DataBinder(customerInfoMod);
		binder.setValidator(new CustomerInfoValidation());
		binder.validate();
		BindingResult results = binder.getBindingResult();
		Map<String, Object> result = new HashMap<>();
		if (results.hasErrors()) {
			results.getAllErrors().forEach(o -> {
				FieldError error = (FieldError) o;
				errorsMessage += error.getDefaultMessage();// エラーメッセージ
			});
			result.put("errorsMessage", errorsMessage);// エラーメッセージ
			return result;
		}
		HttpSession session = getSession();
		customerInfoMod.setUpdateUser((String)session.getAttribute("employeeNo"));
		result.put("result", customerInfoSer.CustomerInfoToDB(customerInfoMod));
		return result;
	}

	/**
	 * 部門情報検索
	 * @param customerInfoModel
	 * @return
	 */
	public ArrayList<CustomerDepartmentInfoModel> getCustomerDepartmentInfo( String customerNo) {
		logger.info("BankInfoController.toroku:" + "部門情報検索開始");
		HashMap<String, String> sendMap = new HashMap<>();
		sendMap.put("customerNo", customerNo);
		ArrayList<CustomerDepartmentInfoModel> customerDepartmentInfoList = 
				customerInfoSer.selectCustomerDepartmentInfo(sendMap);
		logger.info("BankInfoController.toroku:" + "部門情報検索終了");
		return customerDepartmentInfoList;
	}
	
	/**
	 * 部門削除
	 * @return
	 */
	@RequestMapping(value = "/customerDepartmentdelete", method = RequestMethod.POST)
	@ResponseBody
	public boolean customerDepartmentdelete(@RequestBody CustomerDepartmentInfoModel customerDepartmentInfoModel) {
		logger.info("BankInfoController.toroku:" + "部門削除開始");
		HashMap<String, String> sendMap = new HashMap<>();
		sendMap.put("customerNo", customerDepartmentInfoModel.getCustomerNo());
		sendMap.put("customerDepartmentCode", customerDepartmentInfoModel.getCustomerDepartmentCode());
		logger.info("BankInfoController.toroku:" + "部門削除終了");
		return customerInfoSer.customerDepartmentdelete(sendMap);
	}
}
