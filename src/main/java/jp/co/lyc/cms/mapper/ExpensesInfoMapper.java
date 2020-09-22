package jp.co.lyc.cms.mapper;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

import jp.co.lyc.cms.model.ExpensesInfoModel;

@Mapper
public interface ExpensesInfoMapper {

	/**
	 * 諸費用データ取得
	 * @param sendMap
	 * @return
	 */
	public ArrayList<ExpensesInfoModel> getExpensesInfo(HashMap<String, String> sendMap);
	
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
