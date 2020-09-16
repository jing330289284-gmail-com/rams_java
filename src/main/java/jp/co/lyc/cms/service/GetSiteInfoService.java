package jp.co.lyc.cms.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import jp.co.lyc.cms.mapper.GetSiteInfoMapper;
import jp.co.lyc.cms.model.SiteModel;

@Component
public class GetSiteInfoService {

	@Autowired
	GetSiteInfoMapper getSiteInfoMapper;

	public boolean insertSiteInfo(Map<String, Object> sendMap) {
		try {
			getSiteInfoMapper.siteInsert(sendMap);
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public List<SiteModel> getSiteInfo(String employeeNo) {

		List<SiteModel> siteList = getSiteInfoMapper.getSiteInfo(employeeNo);
		return siteList;
	}

}
