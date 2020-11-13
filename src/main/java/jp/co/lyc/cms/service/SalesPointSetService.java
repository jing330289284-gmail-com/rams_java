package jp.co.lyc.cms.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import jp.co.lyc.cms.mapper.SalesPointSetMapper;
import jp.co.lyc.cms.model.SalesPointSetModel;

@Component
public class SalesPointSetService {

	@Autowired
	SalesPointSetMapper salesPointSetMapper;

	public boolean salesPointInsert(Map<String, Object> sendMap) {
		try {
			salesPointSetMapper.salesPointInsert(sendMap);
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

	public boolean salesPointDelete(HashMap<String, Object> sendMap) {
		try {
			salesPointSetMapper.salesPointDelete(sendMap);
			salesPointSetMapper.salesPointUpdateAfterDelete(sendMap);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Transactional(rollbackFor = Exception.class)
	public boolean salesPointUpdate(Map<String, Object> sendMap) {
		try {
			salesPointSetMapper.salesPointUpdate(sendMap);
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public List<SalesPointSetModel> getSalesPointInfo(Map<String, Object> sendMap) {

		List<SalesPointSetModel> siteList = salesPointSetMapper.getSalesPointInfo(sendMap);
		return siteList;
	}

}
