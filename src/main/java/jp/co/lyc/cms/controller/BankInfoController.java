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

import jp.co.lyc.cms.model.BankInfoModel;
import jp.co.lyc.cms.service.GetBankInfoService;

@Controller
@CrossOrigin(origins = "http://127.0.0.1:3000")
@RequestMapping(value = "/bankInfo")
public class BankInfoController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	GetBankInfoService bankInfoSer;
	
	/**
	 * 画面の初期化
	 * @param onloadMol
	 * @return
	 */
	@RequestMapping(value = "/getBankInfo", method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String, Object> getBankInfo(@RequestBody BankInfoModel onloadMol) {
		logger.info("BankInfoController.getBankInfo:" + "検索開始");
		HashMap<String, Object> resultMap = new HashMap<>();
		BankInfoModel accountInfoMod = new BankInfoModel();
		if (onloadMol.getShoriKbn().equals("shusei") || onloadMol.getShoriKbn().equals("shosai")) {
			accountInfoMod = selectAccountInfo(onloadMol.getEmployeeOrCustomerNo() , onloadMol.getAccountBelongsStatus());
		}
		accountInfoMod.setBankName(bankInfoSer.selectBankInfo());
		resultMap.put("accountInfoMod", accountInfoMod);
		return resultMap;	
	}
	
	/**
	 * 支店の情報の取得
	 * @param sendMap
	 * @return
	 */
	@RequestMapping(value = "/getBankBranchInfo", method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String, String> getBankBranchInfo(@RequestBody HashMap<String, String> sendMap) {
		logger.info("BankInfoController.getBankBranchInfo:" + "検索開始");
		ArrayList<HashMap<String, String>> resultList = bankInfoSer.getBankBranchInfo(sendMap);
		HashMap<String, String> resultMap = null;
		if(!resultList.isEmpty()) {
			resultMap = resultList.get(0);
		}
		return resultMap;	
	}
	
	/**
	 * 登録ボタン
	 * @param bankCol
	 * @return
	 */
	@RequestMapping(value = "/toroku", method = RequestMethod.POST)
	@ResponseBody
	public boolean toroku(@RequestBody BankInfoModel bankCol) {
		logger.info("BankInfoController.toroku:" + "登录開始");
		BankInfoModel checkMod = new BankInfoModel();
		checkMod = selectAccountInfo(bankCol.getEmployeeOrCustomerNo() , bankCol.getAccountBelongsStatus());
		boolean result = true;
		if(checkMod == null && bankCol.getShoriKbn().equals("tsuika")) {
			result = insert(bankCol);
		}else if(bankCol.getShoriKbn().equals("shusei")) {
			result = update(bankCol);
		}
		return result;	
	}
	
	/**
	 * インサート
	 * @param bankCol
	 * @return
	 */
	public boolean insert(BankInfoModel bankCol) {
		logger.info("BankInfoController.toroku:" + "登录開始");
		boolean result = true;
		HashMap<String, String> sendMap = new HashMap<>();
		sendMap.put("accountBelongsStatus", bankCol.getAccountBelongsStatus());
		sendMap.put("bankCode", bankCol.getBankCode());
		sendMap.put("accountName", bankCol.getAccountName());
		sendMap.put("accountNo", bankCol.getAccountNo());
		sendMap.put("bankBranchCode", bankCol.getBankBranchCode());
		sendMap.put("accountTypeStatus", bankCol.getAccountTypeStatus());
		sendMap.put("updateUser", bankCol.getUpdateUser());
		sendMap.put("employeeOrCustomerNo", bankCol.getEmployeeOrCustomerNo());	
		result  = bankInfoSer.insertAccount(sendMap);
		return result;	
	}
	
	/**
	 * アップデート
	 * @param bankCol
	 * @return
	 */
	public boolean update(BankInfoModel bankCol) {
		logger.info("BankInfoController.toroku:" + "登录開始");
		boolean result = true;
		BankInfoModel checkMod = new BankInfoModel();
		checkMod = selectAccountInfo(bankCol.getEmployeeOrCustomerNo() , bankCol.getAccountBelongsStatus());
		HashMap<String, String> sendMap = new HashMap<>();
		sendMap.put("accountBelongsStatus", bankCol.getAccountBelongsStatus());
		if(!checkMod.getBankCode().equals(bankCol.getBankCode())) {
			sendMap.put("bankCode", bankCol.getBankCode());
		}
		if(!checkMod.getAccountName().equals(bankCol.getAccountName())) {
			sendMap.put("accountName", bankCol.getAccountName());
		}
		if(!checkMod.getAccountNo().equals(bankCol.getAccountNo())) {
			sendMap.put("accountNo", bankCol.getAccountNo());
		}
		if(!checkMod.getBankBranchCode().equals(bankCol.getBankBranchCode())) {
			sendMap.put("bankBranchCode", bankCol.getBankBranchCode());
		}
		sendMap.put("accountTypeStatus", bankCol.getAccountTypeStatus());
		sendMap.put("updateUser", bankCol.getUpdateUser());
		sendMap.put("employeeOrCustomerNo", bankCol.getEmployeeOrCustomerNo());	
		result  = bankInfoSer.updateAccount(sendMap);
		return result;	
	}
	
	/**
	 * データを取得
	 * @param employeeOrCustomerNo
	 * * @param accountBelongsStatus
	 * @return
	 */
	public BankInfoModel selectAccountInfo(String employeeOrCustomerNo , String accountBelongsStatus) {
		logger.info("BankInfoController.selectBankInfo:" + "查询開始");
		BankInfoModel resultMod = bankInfoSer.selectAccountInfo(employeeOrCustomerNo , accountBelongsStatus);
		return resultMod;	
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
