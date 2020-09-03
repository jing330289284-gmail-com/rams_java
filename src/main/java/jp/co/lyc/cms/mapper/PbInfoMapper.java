package jp.co.lyc.cms.mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import jp.co.lyc.cms.model.PbInfoModel;

@Mapper
public interface PbInfoMapper {

	/**
	 * 画面データの検索
	 * @param sendMap
	 * @return
	 */
	public ArrayList<PbInfoModel> selectPb(Map<String, String> sendMap);
	/**
	 * インサート
	 * @param sendMap
	 */
	public void insertPb(Map<String, Object> sendMap);
	/**
	 * アップデート
	 * @param sendMap
	 */
	public void updatePb(Map<String, Object> sendMap);
	
	/**
	 * Pbを削除
	 * 
	 */
	public void deletePbInfo(Map<String, Object> sendMap);
}
