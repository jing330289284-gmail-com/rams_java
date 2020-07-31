package jp.co.lyc.cms.mapper;

import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface technologyTypeMasterMapper {
	
	/**
	 * 採番
	 * @return
	 */
	public String getTechnologyCodeSaiban();
	/**
	 * 有無判断
	 * @param technologytypeName
	 * @return
	 */
	public String checkHave(String technologytypeName);
	/**
	 * インサート
	 * @param sendMap
	 */
	public void insertTechnologyTypeMaster(HashMap<String, String> sendMap);
}
