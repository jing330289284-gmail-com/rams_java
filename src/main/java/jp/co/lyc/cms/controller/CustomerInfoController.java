package jp.co.lyc.cms.controller;

import java.util.ArrayList;
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

import jp.co.lyc.cms.model.CustomerDepartmentInfoModel;
import jp.co.lyc.cms.model.CustomerInfoModel;
import jp.co.lyc.cms.model.CustomerInfoSelectModel;
import jp.co.lyc.cms.service.CustomerInfoService;
import jp.co.lyc.cms.model.TopCustomerInfoModel;
@Controller
@CrossOrigin(origins = "http://127.0.0.1:3000")
@RequestMapping(value = "/customerInfo")
public class CustomerInfoController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	CustomerInfoService customerInfoSer;
	
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
		CustomerInfoSelectModel selectModel = new CustomerInfoSelectModel();
		//お客様ランキング内容
		selectModel.setCustomerRanking(customerInfoSer.selectCustomerRanking());
		//会社性質内容
		selectModel.setCompanyNature(customerInfoSer.selectCompanyNature());
		//職位
		selectModel.setPosition(customerInfoSer.selectPosition());
		//选择框内容
		resultMap.put("selectModel", selectModel);
		HashMap<String, String> sendMap = new HashMap<>();
		if(customerInfoMod.getShoriKbn() != null) {
			if (customerInfoMod.getShoriKbn().equals("shusei") || customerInfoMod.getShoriKbn().equals("sansho")) {
				customerInfoMod = customerInfoSer.selectCustomerInfo(customerInfoMod.getCustomerNo());	
				sendMap.put("customerNo", customerInfoMod.getCustomerNo());
				ArrayList<CustomerDepartmentInfoModel> customerDepartmentInfoList = customerInfoSer.selectCustomerDepartmentInfo(sendMap);
				resultMap.put("customerInfoMod", customerInfoMod);
				resultMap.put("customerDepartmentInfoList", customerDepartmentInfoList);
			}else if(customerInfoMod.getShoriKbn().equals("tsuika")){
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
				ArrayList<CustomerDepartmentInfoModel> customerDepartmentInfoList = customerInfoSer.selectCustomerDepartmentInfo(sendMap);
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
	public ArrayList<CustomerDepartmentInfoModel> selectDepartmentMaster(@RequestBody CustomerDepartmentInfoModel customerDepartmentInfoModel){
		return customerInfoSer.selectDepartmentMaster(customerDepartmentInfoModel.getCustomerDepartmentName());
	}
	/**
	 * 登录按钮
	 * @param customerInfoMod
	 * @return
	 */
	@RequestMapping(value = "/toroku", method = RequestMethod.POST)
	@ResponseBody
	public int toroku(@RequestBody CustomerInfoModel customerInfoMod) {
		boolean result = true;
		String topCustomerNo = "";
		CustomerInfoModel checkMod = customerInfoSer.selectCustomerInfo(customerInfoMod.getCustomerNo());
		topCustomerNo = customerInfoSer.checkTopCustomer(customerInfoMod.getTopCustomerName());
		if(isNullOrEmpty(topCustomerNo)) {
			return 2;
		}else {
			customerInfoMod.setTopCustomerNo(topCustomerNo);
		}
		customerInfoMod.setTopCustomerNo(topCustomerNo);
		if (checkMod == null && customerInfoMod.getShoriKbn().equals("tsuika")) {
			result = insert(customerInfoMod);
		} else if (checkMod != null && (customerInfoMod.getShoriKbn().equals("shusei"))||
				customerInfoMod.getShoriKbn().equals("sansho")) {
			result = update(customerInfoMod);
		}
		return (result == true ? 0 : 1);
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
		sendMap.put("customerAbbreviation", customerInfoMod.getCustomerAbbreviation());
		sendMap.put("topCustomerNo", customerInfoMod.getTopCustomerNo());
		sendMap.put("establishmentDate", customerInfoMod.getEstablishmentDate());
		sendMap.put("businessStartDate", customerInfoMod.getBusinessStartDate());
		sendMap.put("customerRankingCode", customerInfoMod.getCustomerRankingCode());
		sendMap.put("listedCompany", customerInfoMod.getListedCompany());
		sendMap.put("companyNatureCode", customerInfoMod.getCompanyNatureCode());
		sendMap.put("url", customerInfoMod.getUrl());
		sendMap.put("PurchasingManagers", customerInfoMod.getPurchasingManagers());
		sendMap.put("remark", customerInfoMod.getRemark());
		sendMap.put("PurchasingManagersOfmail", customerInfoMod.getPurchasingManagersOfmail());
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
		if(!checkMod.getCustomerRankingCode().equals(customerInfoMod.getCustomerRankingCode())) {
			sendMap.put("customerRankingCode", customerInfoMod.getCustomerRankingCode());
		}
		if(!checkMod.getCustomerRankingCode().equals(customerInfoMod.getListedCompany())) {
			sendMap.put("listedCompany", customerInfoMod.getListedCompany());
		}
		if(!checkMod.getCompanyNatureCode().equals(customerInfoMod.getCompanyNatureCode())) {
			sendMap.put("companyNatureCode", customerInfoMod.getCustomerRankingCode());
		}
		if(!checkMod.getUrl().equals(customerInfoMod.getUrl())) {
			sendMap.put("url", customerInfoMod.getUrl());
		}
		if(!checkMod.getRemark().equals(customerInfoMod.getRemark())) {
			sendMap.put("remark", customerInfoMod.getRemark());
		}
		if(!checkMod.getPurchasingManagers().equals(customerInfoMod.getPurchasingManagers())) {
			sendMap.put("PurchasingManagers", customerInfoMod.getPurchasingManagers());
		}
		if(!checkMod.getPurchasingManagersOfmail().equals(customerInfoMod.getPurchasingManagersOfmail())) {
			sendMap.put("PurchasingManagersOfmail", customerInfoMod.getPurchasingManagersOfmail());
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
		ArrayList<CustomerDepartmentInfoModel> customerDepartmentInfoList = customerInfoSer.selectCustomerDepartmentInfo(sendMap);
		return customerDepartmentInfoList;
	}
	/**
	 * 部門登録
	 * @param customerDepartmentInfoModel
	 * @return
	 */
	@RequestMapping(value = "/meisaiToroku", method = RequestMethod.POST)
	@ResponseBody
	public ArrayList<CustomerDepartmentInfoModel> meisaiToroku(@RequestBody CustomerDepartmentInfoModel customerDepartmentInfoModel) {
		logger.info("BankInfoController.toroku:" + "明細登録開始");
		HashMap<String, String> sendMap = new HashMap<>();
		ArrayList<CustomerDepartmentInfoModel> resultList = new ArrayList<>();
		sendMap.put("customerNo", customerDepartmentInfoModel.getCustomerNo());	
		String resultCode = "0";//処理結果
		String customerDepartmentCode = 
				customerInfoSer.selectDepartmentCode(customerDepartmentInfoModel.getCustomerDepartmentName());
		//resultCode : 2(部門が部門マスタに存在しない)
		if(isNullOrEmpty(customerDepartmentCode)) {
			resultList = 
					getCustomerDepartmentInfo(customerDepartmentInfoModel.getCustomerNo());
			if(resultList.get(0) == null) {
				resultList.add(new CustomerDepartmentInfoModel());
			}
			resultList.get(0).setResultCode("2");
			return resultList;
		}
		sendMap.put("customerDepartmentCode", customerDepartmentCode);
		sendMap.put("customerNo", customerDepartmentInfoModel.getCustomerNo());
		sendMap.put("customerDepartmentName", customerDepartmentInfoModel.getCustomerDepartmentName());
		sendMap.put("position", customerDepartmentInfoModel.getPosition());
		sendMap.put("responsiblePerson", customerDepartmentInfoModel.getResponsiblePerson());
		sendMap.put("mail", customerDepartmentInfoModel.getMail());
		sendMap.put("updateuser", customerDepartmentInfoModel.getUpdateuser());
		//resultCode : 0(処理成功)1（処理失敗）
		if(customerInfoSer.selectCustomerDepartmentInfo(sendMap).size() != 0 ) {
			resultCode = (customerInfoSer.updateCustomerDepartment(sendMap) ? "0" : "1");	
		}else {
			resultCode = (customerInfoSer.insertCustomerDepartment(sendMap) ? "0" : "1");
		}
		resultList = getCustomerDepartmentInfo(customerDepartmentInfoModel.getCustomerNo());
		if(resultList.get(0) == null) {
			resultList.add(new CustomerDepartmentInfoModel());
		}
		resultList.get(0).setResultCode(resultCode);
		return resultList;
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
