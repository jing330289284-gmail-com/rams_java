package jp.co.lyc.cms.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import jp.co.lyc.cms.mapper.SalesPointSetMapper;
import jp.co.lyc.cms.model.SalesPointSetModel;

@Component
public class SalesPointSetService {

	@Autowired
	SalesPointSetMapper salesPointSetMapper;

	public boolean insertSiteInfo(Map<String, Object> sendMap) {
		try {
			salesPointSetMapper.salesPointInsert(sendMap);
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean updateSiteInfo(Map<String, Object> sendMap) {
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
