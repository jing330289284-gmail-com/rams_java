package jp.co.lyc.cms.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import jp.co.lyc.cms.mapper.SiteInfoMapper;
import jp.co.lyc.cms.model.SiteModel;

@Component
public class SiteInfoService {

	@Autowired
	SiteInfoMapper siteInfoMapper;

	public boolean insertSiteInfo(Map<String, Object> sendMap) {
		try {
			siteInfoMapper.siteInsert(sendMap);
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean updateSiteInfo(Map<String, Object> sendMap) {
		try {
			siteInfoMapper.siteUpdate(sendMap);
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public List<SiteModel> getSiteInfo(String employeeNo) {

		List<SiteModel> siteList = siteInfoMapper.getSiteInfo(employeeNo);
		return siteList;
	}

}
