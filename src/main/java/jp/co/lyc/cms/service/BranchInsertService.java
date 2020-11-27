package jp.co.lyc.cms.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import jp.co.lyc.cms.mapper.BranchInsertMapper;
import jp.co.lyc.cms.model.BranchModel;
@Component
public class BranchInsertService {
	@Autowired
	BranchInsertMapper branchInsertMapper;

	/**
	 * 有無判断
	 * 
	 * @param technologytypeName
	 * @return
	 */

	public boolean checkHave(BranchModel branchModel) {
		if (branchInsertMapper.checkHave(branchModel) == null) {
			return true;
		}
		return false;
	}
	/**
	 * インサート
	 * 
	 * @param sendMap
	 */
	@Transactional(rollbackFor = Exception.class)
	public boolean insertMaster(HashMap<String, Object> sendMap) {
		try {
			branchInsertMapper.insertMaster(sendMap);
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
