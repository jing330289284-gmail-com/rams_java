package jp.co.lyc.cms.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import jp.co.lyc.cms.model.ModelClass;

@Mapper
public interface GetSelectInfoUtilMapper {
	/**
	 * 国籍を取得
	 * 
	 * 
	 */
	public List<ModelClass> getNationalitys();

	/**
	 * 社員形式を取得
	 * 
	 * 
	 */
	public List<ModelClass> getStaffForms();

	/**
	 * 在留資格を取得
	 * 
	 * 
	 */
	public List<ModelClass> getVisa();


	/**
	 * 技術種別を取得
	 * 
	 * @param sendMap
	 * 
	 * 
	 */
	public List<ModelClass> getTechnologyType(Map<String, String> sendMap);

	/**
	 * 日本語レベルを取得
	 * 
	 * 
	 */
	public List<ModelClass> getJapaneseLevel();

	/**
	 * 入社区分を取得
	 * 
	 * 
	 */
	public List<ModelClass> getIntoCompany();
	
	/**
	 * 役割 を取得
	 * 
	 * 
	 */
	public List<ModelClass> getSiteMaster();
}
