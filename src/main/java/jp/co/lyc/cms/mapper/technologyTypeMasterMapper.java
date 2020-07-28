package jp.co.lyc.cms.mapper;

import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface technologyTypeMasterMapper {
	
	public String getTechnologyCodeSaiban();
	public String checkHave(String technologytypeName);
	public void insertTechnologyTypeMaster(HashMap<String, String> sendMap);
}
