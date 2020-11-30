package jp.co.lyc.cms.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import jp.co.lyc.cms.mapper.SiteInfoMapper;
import jp.co.lyc.cms.model.SiteModel;

@Component
public class SiteInfoService {

	@Autowired
	SiteInfoMapper siteInfoMapper;

	@Transactional(rollbackFor = Exception.class)
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

	@Transactional(rollbackFor = Exception.class)
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


	
	/**
	 * 現場情報を削除
	 * 
	 * @param emp
	 * @return boolean
	 */
	@Transactional(rollbackFor = Exception.class)
	public boolean deleteSiteInfo(Map<String, Object> sendMap) {
		boolean result = true;
		try {
			siteInfoMapper.deleteSiteInfo(sendMap);
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
			return result = false;
		}
		return result;
	}

}
