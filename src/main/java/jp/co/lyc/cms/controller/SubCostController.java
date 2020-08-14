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
	@RequestMapping(value = "/onload", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> onload(@RequestBody CostModel costModel, Model model) {
		logger.info("LoginController.login:" + "查询开始");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		ArrayList<CostModel> subCostList = selectData(costModel.getEmployeeNo(),null);
		if(subCostList.size() > 1) {//テーブルの年月
			for(int i = 0 ; i < subCostList.size() ; i++) {
				String yearAndMonthFirst = subCostList.get(i).getReflectYearAndMonth();
				yearAndMonthFirst = yearAndMonthFirst.substring(0,4) + "." + yearAndMonthFirst.substring(4);
				String yearAndMonthSecond = "";
				if(i != subCostList.size() - 1) {
					yearAndMonthSecond = subCostList.get(i+1).getReflectYearAndMonth();
					int year = Integer.parseInt(yearAndMonthSecond.substring(0,4));
					int month = Integer.parseInt(yearAndMonthSecond.substring(4));
					if(month == 1) {
						year -= 1;
						month = 12;
					}else {
						month -= 1;
					}
					yearAndMonthSecond = Integer.toString(year) + "." + Integer.toString(month);
				}
				subCostList.get(i).setDatePeriod(yearAndMonthSecond + "-" + yearAndMonthSecond);
			}
		}
		resultMap.put("subCostList" , subCostList);
		resultMap.put("checkKadoMap", GCS.checkKado(costModel.employeeNo));
		return resultMap;
	}

	// 更新方法
	public boolean updata(CostModel COmodel, Model model) {
		logger.info("GetEmployeeInfoController.getEmployeeInfo:" + "アープデート開始");
		Map<String, Object> sendMap = new HashMap<String, Object>();
		ArrayList<CostModel> checkList = new ArrayList<>();
		checkList = selectData(COmodel.employeeNo ,COmodel.getReflectYearAndMonth());
		sendMap.put("SocialInsuranceFlag", Integer.toString(COmodel.SocialInsuranceFlag));
		sendMap.put("bonusFlag", Integer.toString(COmodel.bonusFlag));
		if (!COmodel.salary.equals(checkList.get(0).salary)) {
			sendMap.put("salary", COmodel.salary);
		}
		if (!COmodel.waitingCost.equals(checkList.get(0).waitingCost)) {
			sendMap.put("waitingCost", COmodel.waitingCost);
		}
		if (!COmodel.welfarePensionAmount.equals(checkList.get(0).welfarePensionAmount)) {
			sendMap.put("welfarePensionAmount", COmodel.welfarePensionAmount);
		}
		if (!COmodel.healthInsuranceAmount.equals(checkList.get(0).healthInsuranceAmount)) {
			sendMap.put("healthInsuranceAmount", COmodel.healthInsuranceAmount);
		}
		if (!COmodel.insuranceFeeAmount.equals(checkList.get(0).insuranceFeeAmount)) {
			sendMap.put("insuranceFeeAmount", COmodel.insuranceFeeAmount);
		}
		if (!COmodel.lastTimeBonusAmount.equals(checkList.get(0).lastTimeBonusAmount)) {
			sendMap.put("lastTimeBonusAmount", COmodel.lastTimeBonusAmount);
		}
		if (!COmodel.scheduleOfBonusAmount.equals(checkList.get(0).scheduleOfBonusAmount)) {
			sendMap.put("scheduleOfBonusAmount", COmodel.scheduleOfBonusAmount);
		}
		if (!COmodel.transportationExpenses.equals(checkList.get(0).transportationExpenses)) {
			sendMap.put("transportationExpenses", COmodel.transportationExpenses);
		}
		if (!COmodel.nextBonusMonth.equals(checkList.get(0).nextBonusMonth)) {
			sendMap.put("nextBonusMonth", COmodel.nextBonusMonth);
		}
//		if (!COmodel.monthOfCompanyPay.equals(checkList.get(0).monthOfCompanyPay)) {
//			sendMap.put("monthOfCompanyPay", COmodel.monthOfCompanyPay);
//		}
		if (!COmodel.nextRaiseMonth.equals(checkList.get(0).nextRaiseMonth)) {
			sendMap.put("nextRaiseMonth", COmodel.nextRaiseMonth);
		}
		if (!COmodel.otherAllowance.equals(checkList.get(0).otherAllowance)) {
			sendMap.put("otherAllowance", COmodel.otherAllowance);
		}
		if (!COmodel.otherAllowanceAmount.equals(checkList.get(0).otherAllowanceAmount)) {
			sendMap.put("otherAllowanceAmount", COmodel.otherAllowanceAmount);
		}
		if (!COmodel.leaderAllowanceAmount.equals(checkList.get(0).leaderAllowanceAmount)) {
			sendMap.put("leaderAllowanceAmount", COmodel.leaderAllowanceAmount);
		}
		if (!COmodel.totalAmount.equals(checkList.get(0).totalAmount)) {
			sendMap.put("totalAmount", COmodel.totalAmount);
		}
		if (!COmodel.remark.equals(checkList.get(0).remark)) {
			sendMap.put("remark", COmodel.remark);
		}
		if (!COmodel.employeeFormCode.equals(checkList.get(0).employeeFormCode)) {
			sendMap.put("employeeFormCode", COmodel.employeeFormCode);
		}
		if (!COmodel.housingAllowance.equals(checkList.get(0).housingAllowance)) {
			sendMap.put("housingAllowance", COmodel.housingAllowance);
		}
		if (!COmodel.housingStatus.equals(checkList.get(0).housingStatus)) {
			sendMap.put("housingStatus", COmodel.housingStatus);
		}
		sendMap.put("employeeNo", COmodel.employeeNo);
		sendMap.put("reflectYearAndMonth", COmodel.reflectYearAndMonth);
		sendMap.put("updateUser", COmodel.updateUser);
		boolean result = GCS.update(sendMap);
		return result;
	}

	// 插入方法
	public boolean insert(CostModel COmodel, Model model) {
		logger.info("GetEmployeeInfoController.getEmployeeInfo:" + "インサート開始");
		Map<String, Object> sendMap = new HashMap<String, Object>();
		sendMap.put("employeeNo", COmodel.employeeNo);
		sendMap.put("reflectYearAndMonth", COmodel.reflectYearAndMonth);
		sendMap.put("salary", COmodel.salary);
		sendMap.put("waitingCost", COmodel.waitingCost);
		sendMap.put("welfarePensionAmount", COmodel.welfarePensionAmount);
		sendMap.put("healthInsuranceAmount", COmodel.healthInsuranceAmount);
		sendMap.put("insuranceFeeAmount", COmodel.insuranceFeeAmount);
		sendMap.put("lastTimeBonusAmount", COmodel.lastTimeBonusAmount);
		sendMap.put("scheduleOfBonusAmount", COmodel.scheduleOfBonusAmount);
		sendMap.put("transportationExpenses", COmodel.transportationExpenses);
		sendMap.put("nextBonusMonth", COmodel.nextBonusMonth);
//		sendMap.put("monthOfCompanyPay", COmodel.monthOfCompanyPay);
		sendMap.put("nextRaiseMonth", COmodel.nextRaiseMonth);
		sendMap.put("otherAllowance", COmodel.otherAllowance);
		sendMap.put("otherAllowanceAmount", COmodel.otherAllowanceAmount);
		sendMap.put("leaderAllowanceAmount", COmodel.leaderAllowanceAmount);
		sendMap.put("totalAmount", COmodel.totalAmount);
		sendMap.put("remark", COmodel.remark);
		sendMap.put("employeeFormCode", COmodel.employeeFormCode);
		sendMap.put("housingStatus", COmodel.housingStatus);
		sendMap.put("housingAllowance", COmodel.housingAllowance);
		sendMap.put("updateUser", COmodel.updateUser);
		boolean result = GCS.insert(sendMap);
		return result;
	}

	// 查询方法
	public ArrayList<CostModel> selectData(String employeeNo , String reflectYearAndMonth) {
		Map<String, String> sendMap = new HashMap<String, String>();
		sendMap.put("employeeNo", employeeNo);
		if(!isNullOrEmpty(reflectYearAndMonth)) {
			sendMap.put("employeeNo", reflectYearAndMonth);
		}
		return GCS.getEmployeeInfo(sendMap);

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
