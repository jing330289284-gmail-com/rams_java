package jp.co.lyc.cms.mapper;

import java.util.HashMap;
import java.util.List;

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
	public void insertMaster(HashMap<String, Object> sendMap);
	
	/**
	 * マスター取得
	 * @param sendMap
	 */
	public List<MasterModel> getMaster(HashMap<String, Object> sendMap);
	
	/**
	 * マスター取得
	 * @param sendMap 
	 * @param sendMap
	 */
	public void orderMaster(List<MasterModel> tempList, HashMap<String, Object> sendMap);
}
