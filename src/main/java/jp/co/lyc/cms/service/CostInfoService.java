package jp.co.lyc.cms.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import jp.co.lyc.cms.mapper.CostInfoMapper;
import jp.co.lyc.cms.model.CostInfoModel;

@Component
public class CostInfoService {

	@Autowired
	CostInfoMapper getCostMapper;

	/**
	 * 画面データの検索
	 * @param sendMap
	 * @return
	 */
	
	public ArrayList<CostInfoModel> getEmployeeInfo(Map<String, String> sendMap) {
		return getCostMapper.selectCost(sendMap);
	}
	
	/**
	 * 稼働の判断
	 * @param employeeNo
	 * @return
	 */
	
	public HashMap<String,String> checkKado(String employeeNo) {
		return getCostMapper.checkKado(employeeNo);
	}
	
	//  インサートとアップデートの値を設定
	public HashMap<String, Object> setSendMap(CostInfoModel COmodel) {
		HashMap<String, Object> sendMap = new HashMap<String, Object>();
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
		return sendMap;
	}
	
	/**
	 * インサート
	 * @param sendMap
	 */
	
	public boolean insert(Map<String, Object> sendMap) {
		try {
			getCostMapper.insertCost(sendMap);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * アップデート
	 * @param sendMap
	 */
	
	public boolean update(Map<String, Object> sendMap) {
		try {
			getCostMapper.updateCost(sendMap);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
