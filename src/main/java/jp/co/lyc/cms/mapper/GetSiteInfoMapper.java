package jp.co.lyc.cms.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import jp.co.lyc.cms.model.SiteModel;

@Mapper

public interface GetSiteInfoMapper {

	public void siteInsert(Map<String, String> sendMap);

	public List<SiteModel> getSiteInfo(@Param("employeeNo")String employeeNo);
}
