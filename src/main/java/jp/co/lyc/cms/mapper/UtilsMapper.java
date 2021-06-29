package jp.co.lyc.cms.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import jp.co.lyc.cms.model.ModelClass;

@Mapper
public interface UtilsMapper {
	/**
	 * 国籍を取得
	 * 
	 * 
	 */
	public List<ModelClass> getNationalitys();

	/**
	 * 営業結果パターンを取得
	 * 
	 * 
	 */
	public List<ModelClass> getSalesPuttern();

	/**
	 * 特別ポイントを取得
	 * 
	 * 
	 */
	public List<ModelClass> getSpecialPoint();

	/**
	 * お客様を取得
	 * 
	 * 
	 */
	public List<ModelClass> getCustomer();

	/**
	 * トップお客様を取得
	 * 
	 * 
	 */
	public List<ModelClass> getTopCustomer();

	/**
	 * 開発言語を取得
	 * 
	 * 
	 */
	public List<ModelClass> getDevelopLanguage();

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
	 * 入社区分を取得
	 * 
	 * 
	 */
	public List<ModelClass> getJapaneaseConversationLevel();

	/**
	 * 入社区分を取得
	 * 
	 * 
	 */
	public List<ModelClass> getEnglishConversationLevel();

	/**
	 * 入社区分を取得
	 * 
	 * 
	 */
	public List<ModelClass> getProjectPhase();

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
	 * 社員氏名を取得する
	 * 
	 * 
	 */
	public List<ModelClass> getEmployeeName();

	/**
	 * 本社社員氏名を取得する
	 * 
	 * 
	 */
	public List<ModelClass> getEmployeeNameNoBP();

	/**
	 * お客様名
	 * 
	 * 
	 */
	public List<ModelClass> getCustomerName();

	/**
	 * 資格を取得
	 * 
	 * 
	 */
	public List<ModelClass> getQualification();

	/**
	 * 採番
	 * 
	 * @param sendMap
	 * 
	 * 
	 */
	public String getNO(Map<String, String> sendMap);

	/**
	 * 採番
	 * 
	 * @param sendMap
	 * 
	 * 
	 */
	public String getNoNew(Map<String, String> sendMap);

	/**
	 * 採番
	 * 
	 * @param sendMap
	 * 
	 * 
	 */
	public String getNoG(Map<String, String> sendMap);

	/**
	 * 採番
	 * 
	 * @param sendMap
	 * 
	 * 
	 */
	public String getNoSP(Map<String, String> sendMap);

	/**
	 * 採番
	 * 
	 * @param sendMap
	 * 
	 * 
	 */
	public String getNoBP(Map<String, String> sendMap);

	/**
	 * レベル
	 * 
	 * @return
	 */
	public List<ModelClass> getLevel();

	/**
	 * お客様性質
	 * 
	 * @return
	 */
	public List<ModelClass> getCompanyNature();

	/**
	 * 職位
	 * 
	 * @return
	 */
	public List<ModelClass> getPosition();

	/**
	 * 部門名前連想
	 * 
	 * @return
	 */
	public List<ModelClass> getDepartmentMasterDrop();

	/**
	 * 銀行名検索
	 * 
	 * @return
	 */
	public List<ModelClass> getBankInfo();

	/**
	 * 支店情報検索
	 * 
	 * @return
	 */
	public List<ModelClass> getBankBranchInfo(HashMap<String, String> sendMap);

	/**
	 * 支払サイト検索
	 * 
	 * @return
	 */
	public List<ModelClass> getPaymentSite();

	/**
	 * パスワード取得
	 * 
	 * @return
	 */
	public String getPassword(String employeeNo);

	/**
	 * パスワードリセット
	 * 
	 * @return
	 */
	public void resetPassword(HashMap<String, String> sendMap);

	/**
	 * 営業担当検索
	 * 
	 * @return
	 */
	public List<ModelClass> getSalesPerson();

	/**
	 * 場所
	 * 
	 * @return
	 */
	public List<ModelClass> getStation();

	/**
	 * 費用区分
	 * 
	 * @return
	 */
	public List<ModelClass> getCostClassification();

	/**
	 * 業種
	 * 
	 * @return
	 */
	public List<ModelClass> getTypeOfIndustry();

	/**
	 * 営業状況取得
	 * 
	 * @return
	 */
	public List<ModelClass> getSalesProgress();

	/**
	 * 交通手段を取得
	 * 
	 * @return
	 */
	public List<ModelClass> getTransportation();

	/**
	 * 状況変動を取得
	 * 
	 * @return
	 */
	public List<ModelClass> getSituationChange();

	/**
	 * 確率取得
	 * 
	 * @return
	 */
	public List<ModelClass> getSuccessRate();

	/**
	 * 年齢制限取得
	 * 
	 * @return
	 */
	public List<ModelClass> getAgeClassification();
	
	/**
	 * お客様略称取得
	 * 
	 * @return
	 */
	public List<ModelClass> getCustomerAbbreviation();

	/**
	 * 面談回数取得
	 * 
	 * @return
	 */
	public List<ModelClass> getNoOfInterview();

	/**
	 * 案件期限取得
	 * 
	 * @return
	 */
	public List<ModelClass> getProjectPeriod();

	/**
	 * 案件タイプ取得
	 * 
	 * @return
	 */
	public List<ModelClass> getProjectType();

	/**
	 * 案件番号取得
	 * 
	 * @return
	 */
	public List<ModelClass> getProjectNo();

	/**
	 * 送信日付設定取得
	 * 
	 * @return
	 */
	public List<ModelClass> getSendReportOfDateSeting();

	/**
	 * 入場期日取得
	 * 
	 * @return
	 */
	public List<ModelClass> getAdmissionMonth();

	/**
	 * 送信対象格納リスト 取得
	 * 
	 * @return
	 */
	public List<ModelClass> getStorageListName();

	/**
	 * お客様担当者取得 取得
	 * 
	 * @return
	 */
	public List<ModelClass> getPurchasingManagers();

	/**
	 * 契約形態 取得
	 * 
	 * @return
	 */
	public List<ModelClass> getTypteOfContract();

	/**
	 * 結婚区分 取得
	 * 
	 * @return
	 */
	public List<ModelClass> getMarriageClassification();

	/**
	 * 退職理由区分取得
	 * 
	 * @return
	 */
	public List<ModelClass> getRetirementResonClassification();

	/**
	 * 社員氏名(営業、管理者)を取得する
	 * 
	 * 
	 */
	public List<ModelClass> getEmployeeNameByOccupationName();

	/**
	 * 報告書送信対象格納リスト 取得
	 * 
	 * @return
	 */
	public List<ModelClass> getStorageListName0();

	/**
	 * 処理区分取得
	 * 
	 * @return
	 */
	public List<ModelClass> getDealDistinction();

	/**
	 * フレームワーク取得
	 * 
	 * @return
	 */
	public List<ModelClass> getFramework();
}
