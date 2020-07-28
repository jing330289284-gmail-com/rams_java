package jp.co.lyc.cms.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.CrossOrigin;

import jp.co.lyc.cms.common.BaseController;
import jp.co.lyc.cms.model.CostModel;
import jp.co.lyc.cms.service.GetCostService;

@Controller
@CrossOrigin(origins = "http://127.0.0.1:3000")
@RequestMapping(value = "/subCost")
public class SubCostController extends BaseController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	GetCostService GCS;

	// 画面の初期化の場合、データの取得
	@RequestMapping(value = "/loadCost", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> selectCost(@RequestBody CostModel costModel, Model model) {
		logger.info("LoginController.login:" + "查询开始");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if (costModel.shoriKbn.equals("tsuika")) {

		} else if (costModel.shoriKbn.equals("shusei")||costModel.shoriKbn.equals("shosai")) {
			resultMap.put("dataList" , selectData(costModel.employeeNo));
		} 
		resultMap.put("checkKadoMap", GCS.checkKado(costModel.employeeNo));
		return resultMap;
	}

//	
	@RequestMapping(value = "/toroku", method = RequestMethod.POST)
	@ResponseBody
	public boolean toroku(@RequestBody CostModel costModel, Model model) {
		boolean result = true;
		Map<String, ArrayList<CostModel>> checkMap = new HashMap<String, ArrayList<CostModel>>();
		checkMap = selectData(costModel.employeeNo);
		ArrayList<CostModel> checkList = checkMap.get("dataList");
		if (checkList.get(0) == null && costModel.shoriKbn.equals("tsuika")) {
			result = insertData(costModel, model);
		} else if (checkList.get(0) != null && costModel.shoriKbn.equals("shusei")) {
			result = updataData(costModel, model);
		}
		return result;
	}

	// 更新方法
	public boolean updataData(CostModel COmodel, Model model) {
		logger.info("GetEmployeeInfoController.getEmployeeInfo:" + "アープデート開始");
		Map<String, Object> sendMap = new HashMap<String, Object>();
		Map<String, ArrayList<CostModel>> checkMap = new HashMap<String, ArrayList<CostModel>>();
		checkMap = selectData(COmodel.employeeNo);
		ArrayList<CostModel> checkList = checkMap.get("dataList");
		if (!isNullOrEmpty(COmodel.salary) && !COmodel.salary.equals(checkList.get(0).salary)) {
			sendMap.put("salary", COmodel.salary);
		}
		sendMap.put("SocialInsuranceFlag", Integer.toString(COmodel.SocialInsuranceFlag));
		if (!COmodel.welfarePensionAmount.equals(checkList.get(0).welfarePensionAmount)) {
			sendMap.put("welfarePensionAmount", COmodel.welfarePensionAmount);
		}
		if (!COmodel.healthInsuranceAmount.equals(checkList.get(0).healthInsuranceAmount)) {
			sendMap.put("healthInsuranceAmount", COmodel.healthInsuranceAmount);
		}
		if (!COmodel.InsuranceFeeAmount.equals(checkList.get(0).InsuranceFeeAmount)) {
			sendMap.put("InsuranceFeeAmount", COmodel.InsuranceFeeAmount);
		}
		if (!COmodel.lastTimeBonusAmount.equals(checkList.get(0).lastTimeBonusAmount)) {
			sendMap.put("lastTimeBonusAmount", COmodel.lastTimeBonusAmount);
		}
		if (!COmodel.scheduleOfBonusAmount.equals(checkList.get(0).scheduleOfBonusAmount)) {
			sendMap.put("scheduleOfBonusAmount", COmodel.scheduleOfBonusAmount);
		}
		if (!COmodel.leaderAllowanceAmount.equals(checkList.get(0).leaderAllowanceAmount)) {
			sendMap.put("leaderAllowanceAmount", COmodel.leaderAllowanceAmount);
		}
		if (!COmodel.totalAmount.equals(checkList.get(0).totalAmount)) {
			sendMap.put("totalAmount", COmodel.totalAmount);
		}
		if (!COmodel.WaitingCost.equals(checkList.get(0).WaitingCost)) {
			sendMap.put("WaitingCost", COmodel.WaitingCost);
		}
		sendMap.put("BonusFlag", Integer.toString(COmodel.BonusFlag));
		if (!COmodel.TransportationExpenses.equals(checkList.get(0).TransportationExpenses)) {
			sendMap.put("TransportationExpenses", COmodel.TransportationExpenses);
		}
		if (!COmodel.NextBonusMonth.equals(checkList.get(0).NextBonusMonth)) {
			sendMap.put("NextBonusMonth", COmodel.NextBonusMonth);
		}
		if (!COmodel.NextRaiseMonth.equals(checkList.get(0).NextRaiseMonth)) {
			sendMap.put("NextRaiseMonth", COmodel.NextRaiseMonth);
		}
		if (!COmodel.otherAllowance.equals(checkList.get(0).otherAllowance)) {
			sendMap.put("otherAllowance", COmodel.otherAllowance);
		}
		if (!COmodel.otherAllowanceAmount.equals(checkList.get(0).otherAllowanceAmount)) {
			sendMap.put("otherAllowanceAmount", COmodel.otherAllowanceAmount);
		}
		if (!COmodel.remark.equals(checkList.get(0).remark)) {
			sendMap.put("remark", COmodel.remark);
		}
		sendMap.put("employeeNo", COmodel.employeeNo);
		sendMap.put("updateUser", COmodel.updateUser);
		boolean result = GCS.update(sendMap);
		return result;
	}

	// 插入方法
	public boolean insertData(CostModel COmodel, Model model) {
		logger.info("GetEmployeeInfoController.getEmployeeInfo:" + "インサート開始");
		Map<String, Object> sendMap = new HashMap<String, Object>();
		sendMap.put("employeeNo", COmodel.employeeNo);
		if (!isNullOrEmpty(COmodel.salary)) {
			sendMap.put("salary", COmodel.salary);
		}
		sendMap.put("SocialInsuranceFlag", Integer.toString(COmodel.SocialInsuranceFlag));
		sendMap.put("welfarePensionAmount", COmodel.welfarePensionAmount);
		sendMap.put("healthInsuranceAmount", COmodel.healthInsuranceAmount);
		sendMap.put("InsuranceFeeAmount", COmodel.InsuranceFeeAmount);
		sendMap.put("lastTimeBonusAmount", COmodel.lastTimeBonusAmount);
		sendMap.put("scheduleOfBonusAmount", COmodel.scheduleOfBonusAmount);
		sendMap.put("leaderAllowanceAmount", COmodel.leaderAllowanceAmount);
		sendMap.put("totalAmount", COmodel.totalAmount);
		sendMap.put("WaitingCost", COmodel.WaitingCost);
		sendMap.put("BonusFlag", Integer.toString(COmodel.BonusFlag));
		sendMap.put("scheduleOfBonusAmount", COmodel.scheduleOfBonusAmount);
		sendMap.put("TransportationExpenses", COmodel.TransportationExpenses);
		sendMap.put("NextBonusMonth", COmodel.NextBonusMonth);
		sendMap.put("NextRaiseMonth", COmodel.NextRaiseMonth);
		sendMap.put("otherAllowance", COmodel.otherAllowance);
		sendMap.put("otherAllowanceAmount", COmodel.otherAllowanceAmount);
		sendMap.put("remark", COmodel.remark);
		sendMap.put("updateUser", COmodel.updateUser);
		boolean result = GCS.insert(sendMap);
		return result;
	}

	// 查询方法
	public Map<String, ArrayList<CostModel>> selectData(String employeeNo) {
		Map<String, String> sendMap = new HashMap<String, String>();
		ArrayList<CostModel> dataList = new ArrayList<CostModel>();
		Map<String, ArrayList<CostModel>> resultMap = new HashMap<String, ArrayList<CostModel>>();
		sendMap.put("employeeNo", employeeNo);
		dataList.add(GCS.getEmployeeInfo(sendMap));
		resultMap.put("dataList", dataList);
		return resultMap;

	}

	// 判断字符串是否为null或空
	public boolean isNullOrEmpty(String aString) {
		boolean result = true;
		if (aString == null || aString.isEmpty()) {
			return result;
		} else {
			return result = false;
		}
	}

}
