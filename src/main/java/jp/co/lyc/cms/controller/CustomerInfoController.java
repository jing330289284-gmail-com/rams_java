package jp.co.lyc.cms.controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

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

import jp.co.lyc.cms.common.BaseController;
import jp.co.lyc.cms.model.CustomerDepartmentInfoModel;
import jp.co.lyc.cms.model.CustomerInfoModel;
import jp.co.lyc.cms.service.CustomerInfoService;
import jp.co.lyc.cms.util.UtilsCheckMethod;
import jp.co.lyc.cms.model.TopCustomerInfoModel;
@Controller
@CrossOrigin(origins = "http://127.0.0.1:3000")
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
	@Autowired
	UtilsCheckMethod utilsCheckMethod;
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
	 * 上位お客様連想
	 * @param customerInfoMod
	 * @return
	 */
	@RequestMapping(value = "/getTopCustomer", method = RequestMethod.POST)
	@ResponseBody
	public ArrayList<TopCustomerInfoModel> selectTopCustomer(@RequestBody CustomerInfoModel customerInfoMod) {
		logger.info("CustomerInfoController.onloadPage:" + "上位お客様連想");
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
		logger.info("CustomerInfoController.onloadPage:" + "部門連想");
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
		HttpSession session = getSession();
		customerInfoMod.setUpdateUser((String)session.getAttribute("employeeNo"));
		return customerInfoSer.CustomerInfoToDB(customerInfoMod);
	}

	/**
	 * 明細更新ボタン
	 * @param customerDepartmentInfoModel
	 * @return 0成功，1失败，2部门在部门表中不存在
	 */
	@RequestMapping(value = "/meisaiUpdate", method = RequestMethod.POST)
	@ResponseBody
	@Transactional(rollbackFor = Exception.class)
	public String meisaiUpdate(@RequestBody CustomerDepartmentInfoModel customerDepartmentInfoModel) {
		logger.info("CustomerInfoController.onloadPage:" + "明細更新開始");
		try {
			logger.info("CustomerInfoController.onloadPage:" + "明細更新終了");
			return customerInfoSer.meisaiToroku(customerDepartmentInfoModel);
		} catch (Exception e) {
			// TODO: handle exception
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
			logger.info("CustomerInfoController.onloadPage:" + "明細更新終了");
			return "1";
		}
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
