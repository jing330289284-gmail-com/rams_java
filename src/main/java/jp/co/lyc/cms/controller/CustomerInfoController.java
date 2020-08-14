package jp.co.lyc.cms.controller;

import java.util.ArrayList;
import java.util.HashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import jp.co.lyc.cms.model.CustomerDepartmentInfoModel;
import jp.co.lyc.cms.model.CustomerInfoModel;
import jp.co.lyc.cms.service.CustomerInfoService;
import jp.co.lyc.cms.model.TopCustomerInfoModel;
@Controller
@CrossOrigin(origins = "http://127.0.0.1:3000")
@RequestMapping(value = "/customerInfo")
public class CustomerInfoController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	//お客様情報service
	@Autowired
	CustomerInfoService customerInfoSer;
	//口座情報service
	@Autowired
	BankInfoController bankInfoController;
	//上位お客様情報service
	@Autowired
	TopCustomerInfoController topCustomerInfoController;
	/**
	 * 页面加载
	 * @param customerInfoMod
	 * @return
	 */
	@RequestMapping(value = "/onloadPage", method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String,Object> onloadPage(@RequestBody CustomerInfoModel customerInfoMod ) {
		logger.info("CustomerInfoController.onloadPage:" + "页面加载開始");
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
		return resultMap;	
	}
	/**
	 * 上位客户联想查询
	 * @param customerInfoMod
	 * @return
	 */
	@RequestMapping(value = "/getTopCustomer", method = RequestMethod.POST)
	@ResponseBody
	public ArrayList<TopCustomerInfoModel> selectTopCustomer(@RequestBody CustomerInfoModel customerInfoMod) {
		//上位客户信息
		return customerInfoSer.selectTopCustomer(customerInfoMod.getTopCustomerName());
	}
	/**
	 * 部門連想
	 * @param customerDepartmentInfoModel
	 * @return
	 */
	@RequestMapping(value = "/selectDepartmentMaster", method = RequestMethod.POST)
	@ResponseBody
	public ArrayList<CustomerDepartmentInfoModel> selectDepartmentMaster(@RequestBody 
			CustomerDepartmentInfoModel customerDepartmentInfoModel){
		return customerInfoSer.selectDepartmentMaster(customerDepartmentInfoModel.getCustomerDepartmentName());
	}
	/**
	 * 登录按钮
	 * @param customerInfoMod
	 * @return 0成功，1失败，2上位客户不存在，3明细登录失败，4部门在部门表中不存在
	 */
	@RequestMapping(value = "/toroku", method = RequestMethod.POST)
	@ResponseBody
	@Transactional(rollbackFor = Exception.class)
	public String toroku(@RequestBody CustomerInfoModel customerInfoMod) {
		boolean result = true;
		String topCustomerNo = "";
		CustomerInfoModel checkMod = customerInfoSer.selectCustomerInfo(customerInfoMod.getCustomerNo());
		if(!customerInfoMod.getTopCustomerName().isEmpty()) {
			topCustomerNo = customerInfoSer.checkTopCustomer(customerInfoMod.getTopCustomerName());
			if(isNullOrEmpty(topCustomerNo)) {
				return "2";//result（2）上位お客様名前がお客様情報テーブルに存じません
			}else {
				customerInfoMod.setTopCustomerNo(topCustomerNo);
			}
			customerInfoMod.setTopCustomerNo(topCustomerNo);
		}else {
			customerInfoMod.setTopCustomerNo("");
		}
		for(CustomerDepartmentInfoModel customerDepartmentInfoModel:
			customerInfoMod.getCustomerDepartmentList()) {
			String customerDepartmentCode = 
					customerInfoSer.selectDepartmentCode(customerDepartmentInfoModel.getCustomerDepartmentName());
			if(isNullOrEmpty(customerDepartmentCode)) {
				return "4";
			}
		}
		if (checkMod == null && customerInfoMod.getActionType().equals("insert")) {//追加の場合
			try {
				result = insert(customerInfoMod);
				if(result == false) {
					return "1";
				}
				customerInfoMod.getAccountInfo().setUpdateUser(customerInfoMod.getUpdateUser());
				customerInfoMod.getTopCustomerInfo().setUpdateUser(customerInfoMod.getUpdateUser());
				bankInfoController.insert(customerInfoMod.getAccountInfo());
				topCustomerInfoController.insert(customerInfoMod.getTopCustomerInfo());
				for(CustomerDepartmentInfoModel customerDepartmentInfoModel:
					customerInfoMod.getCustomerDepartmentList()) {
					customerDepartmentInfoModel.setShoriKbn(customerInfoMod.getActionType());
					customerDepartmentInfoModel.setCustomerNo(customerInfoMod.getCustomerNo());
					String meisaiResult = 
							meisaiToroku(customerDepartmentInfoModel);
					if(meisaiResult.equals("1")) {
						return "3";
					}else if(meisaiResult.equals("2")) {
						return "4";
					}
				}
				return "0";//result（0）成功（1）失敗
			} catch (Exception e) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				e.printStackTrace();
				return "1";
				// TODO: handle exception
			}
		} else if (checkMod != null && (customerInfoMod.getActionType().equals("update"))) {//修正の場合
			try {
				result = update(customerInfoMod);
				if(result == false) {
					return "1";
				}
				customerInfoMod.getAccountInfo().setUpdateUser(customerInfoMod.getUpdateUser());
				customerInfoMod.getTopCustomerInfo().setUpdateUser(customerInfoMod.getUpdateUser());
				bankInfoController.update(customerInfoMod.getAccountInfo());
				topCustomerInfoController.update(customerInfoMod.getTopCustomerInfo());
				for(CustomerDepartmentInfoModel customerDepartmentInfoModel:
					customerInfoMod.getCustomerDepartmentList()) {
					customerDepartmentInfoModel.setShoriKbn(customerInfoMod.getActionType());
					customerDepartmentInfoModel.setCustomerNo(customerInfoMod.getCustomerNo());
					String meisaiResult = 
							meisaiToroku(customerDepartmentInfoModel);
					if(meisaiResult.equals("1")) {
						return "3";
					}else if(meisaiResult.equals("2")) {
						return "4";
					}
				}
				return "0";//result（0）成功（1）失敗
			} catch (Exception e) {
				// TODO: handle exception
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				e.printStackTrace();
				return "1";
			}
			
		}
		return "0";
	}

	/**
	 * 明細更新按钮
	 * @param customerDepartmentInfoModel
	 * @return 0成功，1失败，2部门在部门表中不存在
	 */
	@RequestMapping(value = "/meisaiUpdate", method = RequestMethod.POST)
	@ResponseBody
	@Transactional(rollbackFor = Exception.class)
	public String meisaiUpdate(@RequestBody CustomerDepartmentInfoModel customerDepartmentInfoModel) {
		try {
			return meisaiToroku(customerDepartmentInfoModel);
		} catch (Exception e) {
			// TODO: handle exception
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
			return "1";
		}
	}
	/**
	 * 插入数据
	 * @param customerInfoMod
	 * @return
	 */
	public boolean insert(CustomerInfoModel customerInfoMod) {
		logger.info("BankInfoController.toroku:" + "登录開始");
		boolean result = true;
		HashMap<String, String> sendMap = new HashMap<>();
		sendMap.put("customerName", customerInfoMod.getCustomerName());
		sendMap.put("headOffice", customerInfoMod.getHeadOffice());
		sendMap.put("representative", customerInfoMod.getRepresentative());
		sendMap.put("customerAbbreviation", customerInfoMod.getCustomerAbbreviation());
		sendMap.put("topCustomerNo", customerInfoMod.getTopCustomerNo());
		sendMap.put("establishmentDate", customerInfoMod.getEstablishmentDate());
		sendMap.put("businessStartDate", customerInfoMod.getBusinessStartDate());
		sendMap.put("levelCode", customerInfoMod.getLevelCode());
		sendMap.put("listedCompanyFlag", customerInfoMod.getListedCompanyFlag());
		sendMap.put("companyNatureCode", customerInfoMod.getCompanyNatureCode());
		sendMap.put("url", customerInfoMod.getUrl());
		sendMap.put("purchasingManagers", customerInfoMod.getPurchasingManagers());
		sendMap.put("remark", customerInfoMod.getRemark());
		sendMap.put("purchasingManagersMail", customerInfoMod.getPurchasingManagersMail());
		sendMap.put("paymentsiteCode", customerInfoMod.getPaymentsiteCode());
		sendMap.put("updateUser", customerInfoMod.getUpdateUser());
		sendMap.put("customerNo", customerInfoMod.getCustomerNo());	
		result  = customerInfoSer.insertCustomerInfo(sendMap);
		return result;	
	}
	
	/**
	 * 更新数据
	 * @param customerInfoMod
	 * @return
	 */
	public boolean update(CustomerInfoModel customerInfoMod) {
		logger.info("BankInfoController.toroku:" + "登录開始");
		boolean result = true;
		CustomerInfoModel checkMod = new CustomerInfoModel();
		checkMod = customerInfoSer.selectCustomerInfo(customerInfoMod.getCustomerNo());
		HashMap<String, String> sendMap = new HashMap<>();
		if(!checkMod.getCustomerName().equals(customerInfoMod.getCustomerName())) {
			sendMap.put("customerName", customerInfoMod.getCustomerName());
		}
		if(!checkMod.getHeadOffice().equals(customerInfoMod.getHeadOffice())) {
			sendMap.put("headOffice", customerInfoMod.getHeadOffice());
		}
		if(!checkMod.getRepresentative().equals(customerInfoMod.getRepresentative())) {
			sendMap.put("representative", customerInfoMod.getRepresentative());
		}
		if(!checkMod.getCustomerAbbreviation().equals(customerInfoMod.getCustomerAbbreviation())) {
			sendMap.put("customerAbbreviation", customerInfoMod.getCustomerAbbreviation());
		}
		if(!checkMod.getTopCustomerNo().equals(customerInfoMod.getTopCustomerNo())) {
			sendMap.put("topCustomerNo", customerInfoMod.getTopCustomerNo());
		}
		if(!checkMod.getEstablishmentDate().equals(customerInfoMod.getEstablishmentDate())) {
			sendMap.put("establishmentDate", customerInfoMod.getEstablishmentDate());
		}
		if(!checkMod.getBusinessStartDate().equals(customerInfoMod.getBusinessStartDate())) {
			sendMap.put("businessStartDate", customerInfoMod.getBusinessStartDate());
		}
		if(!checkMod.getLevelCode().equals(customerInfoMod.getLevelCode())) {
			sendMap.put("levelCode", customerInfoMod.getLevelCode());
		}
		if(!checkMod.getListedCompanyFlag().equals(customerInfoMod.getListedCompanyFlag())) {
			sendMap.put("listedCompanyFlag", customerInfoMod.getListedCompanyFlag());
		}
		if(!checkMod.getCompanyNatureCode().equals(customerInfoMod.getCompanyNatureCode())) {
			sendMap.put("companyNatureCode", customerInfoMod.getCompanyNatureCode());
		}
		if(!checkMod.getUrl().equals(customerInfoMod.getUrl())) {
			sendMap.put("url", customerInfoMod.getUrl());
		}
		if(!checkMod.getRemark().equals(customerInfoMod.getRemark())) {
			sendMap.put("remark", customerInfoMod.getRemark());
		}
		if(!checkMod.getPurchasingManagers().equals(customerInfoMod.getPurchasingManagers())) {
			sendMap.put("purchasingManagers", customerInfoMod.getPurchasingManagers());
		}
		if(!checkMod.getPurchasingManagersMail().equals(customerInfoMod.getPurchasingManagersMail())) {
			sendMap.put("purchasingManagersMail", customerInfoMod.getPurchasingManagersMail());
		}
		if(!checkMod.getPaymentsiteCode().equals(customerInfoMod.getPaymentsiteCode())) {
			sendMap.put("paymentsiteCode", customerInfoMod.getPaymentsiteCode());
		}
		sendMap.put("updateUser", customerInfoMod.getUpdateUser());
		sendMap.put("customerNo", customerInfoMod.getCustomerNo());	
		result  = customerInfoSer.updateCustomerInfo(sendMap);
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
		return customerDepartmentInfoList;
	}
	/**
	 * 部門登録
	 * @param customerDepartmentInfoModel
	 * @return
	 */
	@Transactional(rollbackFor = Exception.class)
	public String meisaiToroku(CustomerDepartmentInfoModel customerDepartmentInfoModel) {
		logger.info("BankInfoController.toroku:" + "明細登録開始");
		HashMap<String, String> sendMap = new HashMap<>();
		sendMap.put("customerNo", customerDepartmentInfoModel.getCustomerNo());	
		String resultCode = "0";//処理結果
		String customerDepartmentCode = 
				customerInfoSer.selectDepartmentCode(customerDepartmentInfoModel.getCustomerDepartmentName());
		//resultCode : 2(部門が部門マスタに存在しない)
		if(isNullOrEmpty(customerDepartmentCode)) {
			return resultCode = "2";
		}
		sendMap.put("customerDepartmentCode", customerDepartmentCode);
		sendMap.put("customerNo", customerDepartmentInfoModel.getCustomerNo());
		sendMap.put("positionCode", customerDepartmentInfoModel.getPositionCode());
		sendMap.put("responsiblePerson", customerDepartmentInfoModel.getResponsiblePerson());
		sendMap.put("customerDepartmentMail", customerDepartmentInfoModel.getCustomerDepartmentMail());
		sendMap.put("updateUser", customerDepartmentInfoModel.getUpdateUser());
		//resultCode : 0(処理成功)1（処理失敗）
		if(customerDepartmentInfoModel.getShoriKbn().equals("update")) {
			if(customerInfoSer.selectCustomerDepartmentInfo(sendMap).size() != 0 ) {
				try {
					resultCode = (customerInfoSer.updateCustomerDepartment(sendMap) ? "0" : "1");	
				} catch (Exception e) {
					// TODO: handle exception
					TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
					e.printStackTrace();
					resultCode = "1";
				}
			}else {
				try {
					resultCode = (customerInfoSer.insertCustomerDepartment(sendMap) ? "0" : "1");
				} catch (Exception e) {
					// TODO: handle exception
					TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
					e.printStackTrace();
					resultCode = "1";
				}
			}
		}else if(customerDepartmentInfoModel.getShoriKbn().equals("insert")) {
			try {
				resultCode = (customerInfoSer.insertCustomerDepartment(sendMap) ? "0" : "1");
			} catch (Exception e) {
				// TODO: handle exception
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				e.printStackTrace();
				resultCode = "1";
			}
		}
		return resultCode;
	}
	
	/**
	 * 部門削除
	 * @return
	 */
	@RequestMapping(value = "/customerDepartmentdelete", method = RequestMethod.POST)
	@ResponseBody
	public boolean customerDepartmentdelete(@RequestBody CustomerDepartmentInfoModel customerDepartmentInfoModel) {
		HashMap<String, String> sendMap = new HashMap<>();
		sendMap.put("customerNo", customerDepartmentInfoModel.getCustomerNo());
		sendMap.put("customerDepartmentCode", customerDepartmentInfoModel.getCustomerDepartmentCode());
		return customerInfoSer.customerDepartmentdelete(sendMap);
	}
	
	/**
	 * 判断字符串是否为null或空
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
