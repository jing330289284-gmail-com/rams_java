package jp.co.lyc.cms.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import jp.co.lyc.cms.model.SiteSearchModel;

@Mapper

public interface SiteSearchMapper {

	public List<SiteSearchModel> getSiteInfo(Map<String, Object> sendMap);
	
}
