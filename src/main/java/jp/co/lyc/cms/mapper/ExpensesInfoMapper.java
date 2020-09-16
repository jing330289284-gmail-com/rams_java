package jp.co.lyc.cms.mapper;

import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ExpensesInfoMapper {

	/**
	 * インサート
	 * @param sendMap
	 */
	public void insert(HashMap<String, String> sendMap);
	
	/**
	 * アップデート
	 * @param sendMap
	 */
	public void update(HashMap<String, String> sendMap);
}
