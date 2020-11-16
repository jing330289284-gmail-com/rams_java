package jp.co.lyc.cms.mapper;

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
	public BpInfoModel getBpInfo(Map<String, Object> sendMap);
	/**
	 * インサート
	 * @param sendMap
	 */
	public void insertBp(Map<String, Object> sendMap);
	/**
	 * アップデート
	 * @param sendMap
	 * @return int
	 */
	public int updateBp(Map<String, Object> sendMap);
	
	/**
	 * Bpを削除
	 * 
	 */
	public void deleteBpInfo(Map<String, Object> sendMap);
}
