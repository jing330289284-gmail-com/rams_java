package jp.co.lyc.cms.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import jp.co.lyc.cms.mapper.LoginEmployeeMapper;
import jp.co.lyc.cms.model.EmployeeModel;

@Component
public class LoginEmployeeService {
	
	@Autowired
	LoginEmployeeMapper login2Mapper;
	
	
	/**
	 * ログイン
	 * 
	 * @param sendMap
	 * @return
	 */

	public EmployeeModel getEmployeeModel(Map<String, String> sendMap) {
		// TODO Auto-generated method stub
		EmployeeModel employeeModel = login2Mapper.getEmployeeModel(sendMap);
		return employeeModel;
	}
	
	/**
	 * ログイン社員番号のメールを取得
	 * @param employeeNo
	 * @return
	 */
	
	public String getEmployeeCompanyMail(String employeeNo) {
		return login2Mapper.getEmployeeCompanyMail(employeeNo);
	}
	
	/**
	 * パスワードリセットURLをインサート
	 * @param employeeNo
	 * @return
	 */
	@Transactional(rollbackFor = Exception.class)
	public boolean insert(HashMap<String, String> sendMap) {
		try {
			login2Mapper.insert(sendMap);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
			return false;
		}
		
	}
}
