package jp.co.lyc.cms.service;

import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import jp.co.lyc.cms.mapper.ExpensesInfoMapper;
import jp.co.lyc.cms.model.ExpensesInfoModel;
import jp.co.lyc.cms.model.WagesInfoModel;

@Component
public class ExpensesInfoService {

	@Autowired
	ExpensesInfoMapper expensesInfoMapper;

	/**
	 * インサート
	 * 
	 * @param expensesInfoModel
	 * @return
	 */
	@Transactional(rollbackFor = Exception.class)
	public boolean insert(ExpensesInfoModel expensesInfoModel) {
		try {
			// 插入
			HashMap<String, String> sendMapExpensesInfo = getSendMap(expensesInfoModel);
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
	 * 
	 * @param expensesInfoModel
	 * @return
	 */
	@Transactional(rollbackFor = Exception.class)
	public boolean update(ExpensesInfoModel expensesInfoModel) {
		try {
			// アップデート
			HashMap<String, String> sendMapExpensesInfo = getSendMap(expensesInfoModel);
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
	 * 諸費用情報削除
	 * 
	 * @param customerNo
	 */

	public boolean delete(ExpensesInfoModel expensesInfoModel) {
		boolean result = true;
		try {
			expensesInfoMapper.delete(getSendMap(expensesInfoModel));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return result = false;
		}
		return result;
	}

	/**
	 * 諸費用sendMapの作成
	 * 
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
		sendMap.put("specialAllowance", expensesInfoModel.getSpecialAllowance());
		sendMap.put("remark", expensesInfoModel.getRemark());
		sendMap.put("updateUser", expensesInfoModel.getUpdateUser());
		return sendMap;
	}
}
