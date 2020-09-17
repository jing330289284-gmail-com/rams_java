package jp.co.lyc.cms.service;

import java.util.HashMap;

import org.springframework.stereotype.Component;

import jp.co.lyc.cms.model.ExpensesInfoModel;

@Component
public class ExpensesInfoService {
	
	/**
	 *諸費用sendMapの作成
	 * @param wagesInfoModel
	 * @return
	 */
	public HashMap<String, String> getSendMap(ExpensesInfoModel expensesInfoModel) {
		HashMap<String, String> sendMap = new HashMap<String, String>();
		
		sendMap.put("employeeNo", expensesInfoModel.getEmployeeNo());
		sendMap.put("expensesReflectYearAndMonth", expensesInfoModel.getExpensesReflectYearAndMonth());
		sendMap.put("transportationExpenses", expensesInfoModel.getTransportationExpenses());
		sendMap.put("otherAllowanceName", expensesInfoModel.getOtherAllowanceName());
		sendMap.put("otherAllowanceAmount", expensesInfoModel.getOtherAllowanceAmount());
		sendMap.put("leaderAllowanceAmount", expensesInfoModel.getLeaderAllowanceAmount());
		sendMap.put("housingStatus", expensesInfoModel.getHousingStatus());
		sendMap.put("housingAllowance", expensesInfoModel.getHousingAllowance());
		sendMap.put("updateUser", expensesInfoModel.getUpdateUser());
		return sendMap;
	}
}
