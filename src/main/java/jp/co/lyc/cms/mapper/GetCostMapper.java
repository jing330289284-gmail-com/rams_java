package jp.co.lyc.cms.mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import jp.co.lyc.cms.model.CostModel;

@Mapper
public interface GetCostMapper {

	/**
	 * 画面データの検索
	 * @param sendMap
	 * @return
	 */
	public ArrayList<CostModel> selectCost(Map<String, String> sendMap);
	/**
	 * インサート
	 * @param sendMap
	 */
	public void insertCost(Map<String, Object> sendMap);
	/**
	 * アップデート
	 * @param sendMap
	 */
	public void updateCost(Map<String, Object> sendMap);
	/**
	 * 稼働の判断
	 * @param employeeNo
	 * @return
	 */
	public HashMap<String, String> checkKado(String employeeNo);
}
