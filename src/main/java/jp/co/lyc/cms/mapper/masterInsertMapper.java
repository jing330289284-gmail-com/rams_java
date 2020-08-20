package jp.co.lyc.cms.mapper;

import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

import jp.co.lyc.cms.model.masterModel;

@Mapper
public interface masterInsertMapper {

	/**
	 * 有無判断
	 * @param data
	 * @return
	 */
	public String checkHave(masterModel masterModel);
	/**
	 * インサート
	 * @param sendMap
	 */
	public void insertMaster(HashMap<String, String> sendMap);
}
