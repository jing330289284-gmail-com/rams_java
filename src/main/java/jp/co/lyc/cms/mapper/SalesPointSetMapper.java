package jp.co.lyc.cms.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import jp.co.lyc.cms.model.SalesPointSetModel;
import jp.co.lyc.cms.model.SiteModel;

@Mapper

public interface SalesPointSetMapper {
	/**
	 * 営業ポイント設定を登録
	 * 
	 */
	public void salesPointInsert(Map<String, Object> sendMap);
	/**
	 * 営業ポイント設定を修正
	 * 
	 */
	public void salesPointUpdate(Map<String, Object> sendMap);
	/**
	 * 営業ポイント設定を削除
	 * 
	 */
	public void salesPointDelete(Map<String, Object> sendMap);
	/**
	 * 営業ポイント設定を削除修正
	 * 
	 */
	public void salesPointUpdateAfterDelete(Map<String, Object> sendMap);

	/**
	 * 営業ポイント設定を取得
	 * 
	 */
	public List<SalesPointSetModel> getSalesPointInfo(Map<String, Object> sendMap);
	

}
