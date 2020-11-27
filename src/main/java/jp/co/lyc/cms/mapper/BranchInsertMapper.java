package jp.co.lyc.cms.mapper;

import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

import jp.co.lyc.cms.model.BranchModel;

@Mapper
public interface BranchInsertMapper {

	/**
	 * 有無判断
	 * @param data
	 * @return
	 */
	public String checkHave(BranchModel branchModel);
	/**
	 * インサート
	 * @param sendMap
	 */
	public void insertMaster(HashMap<String, Object> sendMap);
}
