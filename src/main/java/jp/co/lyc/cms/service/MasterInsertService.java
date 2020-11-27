package jp.co.lyc.cms.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import jp.co.lyc.cms.mapper.MasterInsertMapper;
import jp.co.lyc.cms.model.MasterModel;

@Component
public class MasterInsertService {

	@Autowired
	MasterInsertMapper masterInsertMapper;

	/**
	 * 有無判断
	 * 
	 * @param technologytypeName
	 * @return
	 */

	public boolean checkHave(MasterModel masterModel) {
		if (masterInsertMapper.checkHave(masterModel) == null) {
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
			masterInsertMapper.insertMaster(sendMap);
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
