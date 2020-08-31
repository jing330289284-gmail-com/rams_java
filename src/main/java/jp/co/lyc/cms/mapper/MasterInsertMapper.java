package jp.co.lyc.cms.mapper;

import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

import jp.co.lyc.cms.model.MasterModel;

@Mapper
public interface MasterInsertMapper {

	/**
	 * 有無判断
	 * @param data
	 * @return
	 */
	public String checkHave(MasterModel masterModel);
	/**
	 * インサート
	 * @param sendMap
	 */
	public void insertMaster(HashMap<String, String> sendMap);
}
