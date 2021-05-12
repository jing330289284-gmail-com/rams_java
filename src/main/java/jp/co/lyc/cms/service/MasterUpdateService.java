package jp.co.lyc.cms.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import jp.co.lyc.cms.mapper.MasterInsertMapper;
import jp.co.lyc.cms.mapper.MasterUpdateMapper;
import jp.co.lyc.cms.model.CompanySystemSetModel;
import jp.co.lyc.cms.model.MasterModel;

@Component
public class MasterUpdateService {

	@Autowired
	MasterUpdateMapper masterUpdateMapper;
	
	/*
	 * @Autowired MasterInsertMapper masterInsertMapper;
	 */

	/**
	 * 修正
	 * 
	 * @param sendMap
	 */
	@Transactional(rollbackFor = Exception.class)
	public boolean updateMaster(HashMap<String, Object> sendMap) {
		try {
			masterUpdateMapper.updateMaster(sendMap);
			/*
			 * List<MasterModel> tempList = masterInsertMapper.getMaster(sendMap); for (int
			 * i = 0; i < tempList.size(); i++) { tempList.get(i).setRow(i); }
			 * masterInsertMapper.orderMaster(tempList, sendMap);
			 */
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * 修正
	 * 
	 * @param sendMap
	 */
	@Transactional(rollbackFor = Exception.class)
	public boolean updateBankMaster(HashMap<String, Object> sendMap) {
		try {
			masterUpdateMapper.updateBankMaster(sendMap);
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * 修正
	 * 
	 * @param sendMap
	 */
	@Transactional(rollbackFor = Exception.class)
	public boolean updateCustomerMaster(HashMap<String, Object> sendMap) {
		try {
			masterUpdateMapper.updateCustomerMaster(sendMap);
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * 削除
	 * 
	 * @param sendMap
	 */
	public boolean deleteMaster(HashMap<String, Object> sendMap) {
		try {
			masterUpdateMapper.deleteMaster(sendMap);
			masterUpdateMapper.masterUpdateAfterDelete(sendMap);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * 削除
	 * 
	 * @param sendMap
	 */
	public boolean deleteBankMaster(HashMap<String, Object> sendMap) {
		try {
			masterUpdateMapper.deleteBankMaster(sendMap);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * 削除
	 * 
	 * @param sendMap
	 */
	public boolean deleteCustomerMaster(HashMap<String, Object> sendMap) {
		try {
			masterUpdateMapper.deleteCustomerMaster(sendMap);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * 明細取得
	 * 
	 * @param sendMap
	 */

	public List<MasterModel> getMasterInfo(HashMap<String, String> sendMap) {
		List<MasterModel> masterList = masterUpdateMapper.getMasterInfo(sendMap);
		return masterList;
	}

	public List<MasterModel> getBankMasterInfo(HashMap<String, String> sendMap) {
		List<MasterModel> masterList = masterUpdateMapper.getBankMasterInfo(sendMap);
		return masterList;
	}

	public List<MasterModel> getCustomerMasterInfo(HashMap<String, String> sendMap) {
		List<MasterModel> masterList = masterUpdateMapper.getCustomerMasterInfo(sendMap);
		return masterList;
	}

	/**
	 * 有無判断
	 * 
	 * @param technologytypeName
	 * @return
	 */

	public boolean checkHave(MasterModel masterModel) {
		if (masterUpdateMapper.checkHave(masterModel) == null) {
			return true;
		}
		return false;
	}

	/**
	 * システム更新
	 * 
	 * @return
	 */
	public void updateSystem(HashMap<String, Object> sendMap) {
		masterUpdateMapper.updateSystem(sendMap);
		masterUpdateMapper.updateEmployeeNoT001(sendMap);
		masterUpdateMapper.updateEmployeeNoT002(sendMap);
}
}
