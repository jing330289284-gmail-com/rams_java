package jp.co.lyc.cms.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.aspectj.internal.lang.annotation.ajcDeclareAnnotation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.amazonaws.services.s3.model.GetS3AccountOwnerRequest;

import jp.co.lyc.cms.common.BaseController;
import jp.co.lyc.cms.model.CompanySystemSetModel;
import jp.co.lyc.cms.model.EmployeeModel;
import jp.co.lyc.cms.model.MasterModel;
import jp.co.lyc.cms.service.MasterUpdateService;
import jp.co.lyc.cms.util.StatusCodeToMsgMap;

@Controller
@RequestMapping(value = "/masterUpdate")
public class MasterUpdateController extends BaseController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	MasterUpdateService masterUpdateService;

	@RequestMapping(value = "/getMasterInfo", method = RequestMethod.POST)
	@ResponseBody
	// master信息查询
	public List<MasterModel> getMasterInfo(@RequestBody Map master) {
		List<MasterModel> masterList = new ArrayList<MasterModel>();

		try {
			HashMap<String, String> sendMap = new HashMap<>();
			if (!master.get("master").toString().equals("M002BankBranch")
					&& !master.get("master").toString().equals("T008TopCustomerInfo")) {
				sendMap.put("master", master.get("master").toString());
				sendMap.put("columnName", master.get("master").toString().substring(4) + "name");
				sendMap.put("columnCode", master.get("master").toString().substring(4) + "code");
				masterList = masterUpdateService.getMasterInfo(sendMap);
			} else if (master.get("master").toString().equals("M002BankBranch") && master.get("bankCode") != null) {
				sendMap.put("bankCode", master.get("bankCode").toString());
				masterList = masterUpdateService.getBankMasterInfo(sendMap);
			} else if (master.get("master").toString().equals("T008TopCustomerInfo")) {
				masterList = masterUpdateService.getCustomerMasterInfo(sendMap);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		for (int i = 0; i < masterList.size(); i++) {
			masterList.get(i).setRow(i + 1);
		}
		return masterList;
	}

	/**
	 * 修正ボタン
	 * 
	 * @return
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> toroku(@RequestBody MasterModel masterModel) {
		logger.info("MasterUpdateController.toroku:" + "追加開始");
		// チェック
		Map<String, Object> result = new HashMap<>();
		if (!masterModel.getMaster().toString().equals("T008TopCustomerInfo")) {
			if (checkHave(masterModel) == false) {
				result.put("errorsMessage", StatusCodeToMsgMap.getErrMsgbyCode("MSG008"));// エラーメッセージ
				return result;
			}
		}

		// 修正处理
		if (update(masterModel)) {
			result.put("result", true);
		} else {
			result.put("result", false);
		}
		logger.info("MasterUpdateController.toroku:" + "追加結束");
		return result;
	}

	/**
	 * システム更新
	 * 
	 * @return
	 */
	@RequestMapping(value = "/updateSystem", method = RequestMethod.POST)
	@ResponseBody
	public void updateSystem(@RequestBody CompanySystemSetModel companySystemSetModel) {
		HashMap<String, Object> sendMap = new HashMap<>();
		sendMap.put("companyName", companySystemSetModel.getCompanyName().toString());
		sendMap.put("companyLogo", companySystemSetModel.getCompanyLogo().toString());
		sendMap.put("backgroundColor", companySystemSetModel.getBackgroundColor().toString());
		sendMap.put("empNoHead", companySystemSetModel.getEmpNoHead().toString());
		sendMap.put("taxRate", companySystemSetModel.getTaxRate().toString());
		String employeeNo = (String) getSession().getAttribute("employeeNo");
		String newEmployeeNo = companySystemSetModel.getEmpNoHead().toString()
				+ employeeNo.substring(employeeNo.length() - 3, employeeNo.length());
		sendMap.put("employeeNo", employeeNo);
		sendMap.put("newEmployeeNo", newEmployeeNo);
		masterUpdateService.updateSystem(sendMap);
		getSession().setAttribute("employeeNo", newEmployeeNo);
	}

	/**
	 * 削除ボタン
	 * 
	 * @return
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public boolean delete(@RequestBody MasterModel masterModel) {
		HashMap<String, Object> sendMap = new HashMap<>();

		if (masterModel.getMaster().toString().equals("T008TopCustomerInfo")) {
			sendMap.put("master", masterModel.getMaster());
			sendMap.put("topCustomerNo", masterModel.getTopCustomerNo());
			return masterUpdateService.deleteCustomerMaster(sendMap);
		} else if (masterModel.getMaster().toString().equals("M002BankBranch")) {
			sendMap.put("master", masterModel.getMaster());
			sendMap.put("bankCode", masterModel.getBankCode());
			sendMap.put("bankBranchName", masterModel.getBankBranchName());
			sendMap.put("bankBranchCode", masterModel.getBankBranchCode());
			return masterUpdateService.deleteBankMaster(sendMap);
		} else {
			sendMap.put("master", masterModel.getMaster());
			sendMap.put("columnCode", masterModel.getMaster().toString().substring(4) + "code");
			sendMap.put("code", masterModel.getCodeNo());
			return masterUpdateService.deleteMaster(sendMap);
		}
	}

	/**
	 * アップデート
	 * 
	 * @return
	 */
	public boolean update(MasterModel masterModel) {
		HttpSession loginSession = getSession();
		HashMap<String, Object> sendMap = new HashMap<>();
		if (masterModel.getMaster().toString().equals("T008TopCustomerInfo")) {
			sendMap.put("master", masterModel.getMaster());
			sendMap.put("url", masterModel.getUrl());
			sendMap.put("topCustomerNo", masterModel.getTopCustomerNo());
			sendMap.put("topCustomerName", masterModel.getTopCustomerName());
			sendMap.put("topCustomerAbbreviation", masterModel.getTopCustomerAbbreviation());
			sendMap.put("updateUser", loginSession.getAttribute("employeeName"));
			return masterUpdateService.updateCustomerMaster(sendMap);
		} else if (masterModel.getMaster().toString().equals("M002BankBranch")) {
			sendMap.put("master", masterModel.getMaster());
			sendMap.put("bankCode", masterModel.getBankCode());
			sendMap.put("bankBranchName", masterModel.getBankBranchName());
			sendMap.put("bankBranchCode", masterModel.getBankBranchCode());
			sendMap.put("newBankBranchName", masterModel.getNewBankBranchName());
			sendMap.put("newBankBranchCode", masterModel.getNewBankBranchCode());
			sendMap.put("updateUser", loginSession.getAttribute("employeeName"));
			return masterUpdateService.updateBankMaster(sendMap);
		} else {
			sendMap.put("master", masterModel.getMaster());
			sendMap.put("code", masterModel.getCodeNo());
			sendMap.put("data", masterModel.getData());
			sendMap.put("columnName", masterModel.getMaster().substring(4) + "name");
			sendMap.put("columnCode", masterModel.getMaster().toString().substring(4) + "code");
			sendMap.put("updateUser", loginSession.getAttribute("employeeName"));
			return masterUpdateService.updateMaster(sendMap);
		}
	}

	/**
	 * あるかどうかのチェック
	 * 
	 * @return
	 */
	public boolean checkHave(MasterModel masterModel) {
		masterModel.setColumnName(masterModel.getMaster().substring(4) + "name");
		return masterUpdateService.checkHave(masterModel);

	}

	/**
	 * nullと空の判断
	 * 
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
