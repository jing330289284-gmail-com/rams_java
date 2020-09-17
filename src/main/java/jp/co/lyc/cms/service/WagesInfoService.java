package jp.co.lyc.cms.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import jp.co.lyc.cms.mapper.ExpensesInfoMapper;
import jp.co.lyc.cms.mapper.WagesInfoMapper;
import jp.co.lyc.cms.model.WagesInfoModel;

@Component
public class WagesInfoService {
	
	@Autowired
	WagesInfoMapper wagesInfoMapper;
	
	@Autowired
	ExpensesInfoMapper expensesInfoMapper;
	
	@Autowired
	ExpensesInfoService expensesInfoService;
	/**
	 * 追加の場合
	 * @param wagesInfoModel
	 * @return
	 */
	@Transactional(rollbackFor = Exception.class)
	public boolean insert(WagesInfoModel wagesInfoModel) {
		try {
			HashMap<String, String> sendMap = getSendMap(wagesInfoModel);
			wagesInfoMapper.insert(sendMap);
			if(wagesInfoModel.getExpensesInfoModel() != null) {
				wagesInfoModel.getExpensesInfoModel().setUpdateUser(wagesInfoModel.getUpdateUser());
				HashMap<String, String> sendMapExpensesInfo = 
						expensesInfoService.getSendMap(wagesInfoModel.getExpensesInfoModel());
				expensesInfoMapper.insert(sendMapExpensesInfo);
			}
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 更新の場合
	 * @param wagesInfoModel
	 * @return
	 */
	@Transactional(rollbackFor = Exception.class)
	public boolean update(WagesInfoModel wagesInfoModel) {
		try {
			HashMap<String, String> sendMap = getSendMap(wagesInfoModel);
			wagesInfoMapper.update(sendMap);
			if(wagesInfoModel.getExpensesInfoModel() != null) {
				wagesInfoModel.getExpensesInfoModel().setUpdateUser(wagesInfoModel.getUpdateUser());
				HashMap<String, String> sendMapExpensesInfo = 
						expensesInfoService.getSendMap(wagesInfoModel.getExpensesInfoModel());
				expensesInfoMapper.update(sendMapExpensesInfo);
			}
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
			return false;
		}
	}
	/**
	 *給料情報sendMapの作成
	 * @param wagesInfoModel
	 * @return
	 */
	public HashMap<String, String> getSendMap(WagesInfoModel wagesInfoModel) {
		HashMap<String, String> sendMap = new HashMap<String, String>();
		sendMap.put("employeeNo", wagesInfoModel.getEmployeeNo());
		sendMap.put("reflectYearAndMonth", wagesInfoModel.getReflectYearAndMonth());
		sendMap.put("socialInsuranceFlag", wagesInfoModel.getSocialInsuranceFlag());
		sendMap.put("salary", wagesInfoModel.getSalary());
		sendMap.put("waitingCost", wagesInfoModel.getWaitingCost());
		sendMap.put("welfarePensionAmount", wagesInfoModel.getWelfarePensionAmount());
		sendMap.put("healthInsuranceAmount", wagesInfoModel.getHealthInsuranceAmount());
		sendMap.put("insuranceFeeAmount", wagesInfoModel.getInsuranceFeeAmount());
		sendMap.put("lastTimeBonusAmount", wagesInfoModel.getLastTimeBonusAmount());
		sendMap.put("scheduleOfBonusAmount", wagesInfoModel.getScheduleOfBonusAmount());
		sendMap.put("bonusFlag", wagesInfoModel.getBonusFlag());
		sendMap.put("nextBonusMonth", wagesInfoModel.getNextBonusMonth());
		sendMap.put("monthOfCompanyPay", wagesInfoModel.getMonthOfCompanyPay());
		sendMap.put("nextRaiseMonth", wagesInfoModel.getNextRaiseMonth());
		sendMap.put("totalAmount", wagesInfoModel.getTotalAmount());
		sendMap.put("employeeFormCode", wagesInfoModel.getEmployeeFormCode());
		sendMap.put("remark", wagesInfoModel.getRemark());
		sendMap.put("updateUser", wagesInfoModel.getUpdateUser());
		return sendMap;
	}
}
