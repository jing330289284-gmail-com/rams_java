package jp.co.lyc.cms.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.lyc.cms.mapper.GetSiteInfoMapper;
import jp.co.lyc.cms.model.SiteModel;

@Component
public class GetSiteInfoService {

	@Autowired
	GetSiteInfoMapper getSiteInfoMapper;

	public void insertSiteInfo(Map<String, String> sendMap) {

		getSiteInfoMapper.siteInsert(sendMap);
	}

	public List<SiteModel> getSiteInfo(String employeeNo) {

		List<SiteModel> siteList = getSiteInfoMapper.getSiteInfo(employeeNo);
		return siteList;
	}

}
