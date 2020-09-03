package jp.co.lyc.cms.mapper;

import java.util.ArrayList;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import jp.co.lyc.cms.model.BpInfoModel;

@Mapper
public interface BpInfoMapper {

	/**
	 * 画面データの検索
	 * @param sendMap
	 * @return
	 */
	public ArrayList<BpInfoModel> selectBp(Map<String, String> sendMap);
	/**
	 * インサート
	 * @param sendMap
	 */
	public void insertBp(Map<String, Object> sendMap);
	/**
	 * アップデート
	 * @param sendMap
	 */
	public void updateBp(Map<String, Object> sendMap);
	
	/**
	 * Bpを削除
	 * 
	 */
	public void deleteBpInfo(Map<String, Object> sendMap);
}
