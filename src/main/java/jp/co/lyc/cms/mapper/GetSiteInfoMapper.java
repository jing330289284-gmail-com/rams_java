package jp.co.lyc.cms.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import jp.co.lyc.cms.model.SiteModel;

@Mapper

public interface GetSiteInfoMapper {
	/**
	 * 現場情報を登録
	 * 
	 */
	public void siteInsert(Map<String, Object> sendMap);
	/**
	 * 現場情報を修正
	 * 
	 */
	public void siteUpdate(Map<String, Object> sendMap);
	/**
	 * 現場情報を削除
	 * 
	 */
	public void deleteEmployeeSiteInfo(Map<String, Object> sendMap);

	/**
	 * 現場情報を取得
	 * 
	 */
	public List<SiteModel> getSiteInfo(@Param("employeeNo")String employeeNo);
	

}
