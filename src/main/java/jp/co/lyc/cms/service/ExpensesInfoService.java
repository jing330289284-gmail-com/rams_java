package jp.co.lyc.cms.service;

import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import jp.co.lyc.cms.mapper.ExpensesInfoMapper;
import jp.co.lyc.cms.model.ExpensesInfoModel;

@Component
public class ExpensesInfoService {
	
	@Autowired
	ExpensesInfoMapper expensesInfoMapper;
	
	/**
	 * インサート
	 * @param expensesInfoModel
	 * @return
	 */
	@Transactional(rollbackFor = Exception.class)
	public boolean insert(ExpensesInfoModel expensesInfoModel) {
		try {
				//插入
				HashMap<String, String> sendMapExpensesInfo = 
						getSendMap(expensesInfoModel);
				expensesInfoMapper.insert(sendMapExpensesInfo);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * アップデート
	 * @param expensesInfoModel
	 * @return
	 */
	@Transactional(rollbackFor = Exception.class)
	public boolean update(ExpensesInfoModel expensesInfoModel) {
		try {
				//アップデート
				HashMap<String, String> sendMapExpensesInfo = 
						getSendMap(expensesInfoModel);
				expensesInfoMapper.update(sendMapExpensesInfo);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
			return false;
		}
	}
	/**
	 *諸費用sendMapの作成
	 * @param wagesInfoModel
	 * @return
	 */
	public HashMap<String, String> getSendMap(ExpensesInfoModel expensesInfoModel) {
		HashMap<String, String> sendMap = new HashMap<String, String>();
		
		sendMap.put("employeeNo", expensesInfoModel.getEmployeeNo());
		sendMap.put("expensesReflectYearAndMonth", expensesInfoModel.getExpensesReflectYearAndMonth());
		sendMap.put("updateExpensesReflectYearAndMonth", expensesInfoModel.getUpdateExpensesReflectYearAndMonth());
		sendMap.put("transportationExpenses", expensesInfoModel.getTransportationExpenses());
		sendMap.put("otherAllowanceName", expensesInfoModel.getOtherAllowanceName());
		sendMap.put("otherAllowanceAmount", expensesInfoModel.getOtherAllowanceAmount());
		sendMap.put("leaderAllowanceAmount", expensesInfoModel.getLeaderAllowanceAmount());
		sendMap.put("totalExpenses", expensesInfoModel.getTotalExpenses());
		sendMap.put("housingAllowance", expensesInfoModel.getHousingAllowance());
		sendMap.put("updateUser", expensesInfoModel.getUpdateUser());
		return sendMap;
	}
}
