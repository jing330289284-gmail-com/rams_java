package jp.co.lyc.cms.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import jp.co.lyc.cms.mapper.CustomerTopInsertMapper;
import jp.co.lyc.cms.model.CustomerTopModel;

@Component
public class CustomerTopInsertService {
	@Autowired
	CustomerTopInsertMapper customerTopInsertMapper;

	/**
	 * 有無判断
	 * 
	 * @param technologytypeName
	 * @return
	 */

	public boolean checkHave(CustomerTopModel customerTopModel) {
		if (customerTopInsertMapper.checkHave(customerTopModel) == null) {
			return true;
		}
		return false;
	}

	/**
	 * 最大番号取得
	 * 
	 * @return 最大番号
	 */
	public String getMaxCustomerNo() {
		String customerNo = customerTopInsertMapper.getMaxCustomerNo();
		if (customerNo != null) {
			customerNo = customerNo.substring(1);
			customerNo = "T" + String.format("%0" + 3 + "d", Integer.parseInt(customerNo) + 1);
		} else {
			customerNo = "T001";
		}
		return customerNo;
	}

	/**
	 * インサート
	 * 
	 * @param sendMap
	 */
	@Transactional(rollbackFor = Exception.class)
	public boolean insertMaster(HashMap<String, Object> sendMap) {
		try {
			customerTopInsertMapper.insertMaster(sendMap);
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
