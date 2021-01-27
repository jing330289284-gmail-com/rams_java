package jp.co.lyc.cms.mapper;

import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

import jp.co.lyc.cms.model.CustomerTopModel;

@Mapper
public interface CustomerTopInsertMapper {

	/**
	 * 有無判断
	 * @param data
	 * @return
	 */
	public String checkHave(CustomerTopModel customerTopModel);
	
	/**
	 * 最大番号取得
	 * @return 最大番号
	 */
	public String getMaxCustomerNo();
	/**
	 * インサート
	 * @param sendMap
	 */
	public void insertMaster(HashMap<String, Object> sendMap);
}
