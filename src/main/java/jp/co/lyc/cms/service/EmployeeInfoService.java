package jp.co.lyc.cms.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import jp.co.lyc.cms.mapper.EmployeeInfoMapper;
import jp.co.lyc.cms.model.EmployeeModel;

@Component
public class EmployeeInfoService {

	@Autowired
	EmployeeInfoMapper employeeInfoMapper;

	/**
	 * 社員情報を取得
	 * 
	 * @param sendMap
	 * @return List
	 */
	public List<EmployeeModel> getEmployeeInfo(Map<String, String> sendMap) {
		List<EmployeeModel> employeeList = employeeInfoMapper.getEmployeeInfo(sendMap);
		return employeeList;
	}

	/**
	 * 社員情報を追加
	 * 
	 * @param sendMap
	 * @return boolean
	 */
	@Transactional(rollbackFor = Exception.class)
	public boolean insertEmployee(Map<String, String> sendMap) {
		boolean result = true;
		try {
			employeeInfoMapper.insertEmployeeInfo(sendMap);
			employeeInfoMapper.insertEmployeeInfoDetail(sendMap);
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
			return result = false;
		}
		return result;
	}

	/**
	 * 社員情報を削除
	 * 
	 * @param emp
	 * @return boolean
	 */
	@Transactional(rollbackFor = Exception.class)
	public boolean deleteEmployeeInfo(Map<String, String> sendMap) {
		boolean result = true;
		try {
			employeeInfoMapper.deleteEmployeeInfo(sendMap);
			employeeInfoMapper.deleteEmployeeInfoDetail(sendMap);
			employeeInfoMapper.deleteEmployeeSiteInfo(sendMap);
			employeeInfoMapper.deleteAddressInfo(sendMap);
			employeeInfoMapper.deleteExpensesInfo(sendMap);
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
			return result = false;
		}
		return result;
	}

	/**
	 * EmployeeNoによると、社員情報を取得
	 * 
	 * @param emp
	 * @return EmployeeModel
	 */
	public EmployeeModel getEmployeeByEmployeeNo(Map<String, String> sendMap) {
		EmployeeModel model = employeeInfoMapper.getEmployeeByEmployeeNo(sendMap);
		return model;
	}

	/**
	 * 社員情報を修正
	 * 
	 * @param emp
	 * @return boolean
	 */
	@Transactional(rollbackFor = Exception.class)

	public boolean updateEmployee(Map<String, String> sendMap) {
		boolean result = true;
		try {
			employeeInfoMapper.updateEmployeeInfo(sendMap);
			employeeInfoMapper.updateEmployeeInfoDetail(sendMap);
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
			return result = false;
		}
		return result;
	}

}
