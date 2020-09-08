package jp.co.lyc.cms.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.lyc.cms.mapper.SiteSearchMapper;
import jp.co.lyc.cms.model.SiteSearchModel;

@Component
public class SiteSearchService {

	@Autowired
	SiteSearchMapper SiteSearchMapper;

	public List<SiteSearchModel> getSiteInfo(Map<String, Object> sendMap) {

		List<SiteSearchModel> siteList = SiteSearchMapper.getSiteInfo(sendMap);
		return siteList;
	}

}
