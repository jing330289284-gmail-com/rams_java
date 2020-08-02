package jp.co.lyc.cms.mapper;

import java.util.HashMap;
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

	/**
	 * 職種を取得
	 * 
	 * 
	 */

	public List<ModelClass> getOccupation();

	/**
	 * 部署を取得
	 * 
	 * 
	 */
	public List<ModelClass> getDepartment();

	/**
	 * 権限を取得
	 * 
	 * 
	 */

	public List<ModelClass> getAuthority();

	/**
	 * 英語を取得
	 * 
	 * 
	 */
	public List<ModelClass> getEnglishLevel();

	/**
	 * 資格を取得
	 * 
	 * 
	 */
	public List<ModelClass> getQualification();

	/**
	 * 採番
	 * 
	 * 
	 */
	public String getNO();
	
	/**
	 * お客様ランキング
	 * @return
	 */
	public List<ModelClass> selectCustomerRanking();
	
	/**
	 * お客様性質
	 * @return
	 */
	public List<ModelClass> selectCompanyNature();
	
	/**
	 * 職位
	 * @return
	 */
	public List<ModelClass> selectPosition();
	
	/**
	 * 上位お客様連想
	 * @return
	 */
	public List<ModelClass> selectTopCustomer(String name);
	
	/**
	 * 部門名前連想
	 * @return
	 */
	public List<ModelClass> selectDepartmentMaster(String name);
	
	/**
	 * 銀行名検索
	 * @return
	 */
	public List<ModelClass> selectBankInfo();
	
	/**
	 * 支店情報検索
	 * @return
	 */
	public List<ModelClass> getBankBranchInfo(HashMap<String, String> sendMap);
}
