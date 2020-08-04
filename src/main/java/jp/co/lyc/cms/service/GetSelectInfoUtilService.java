package jp.co.lyc.cms.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.lyc.cms.mapper.GetSelectInfoUtilMapper;
import jp.co.lyc.cms.model.ModelClass;

@Component
public class GetSelectInfoUtilService {
	@Autowired
	GetSelectInfoUtilMapper getSelectInfoUtilMapper;

	/**
	 * 国籍を取得
	 * 
	 * @return
	 */
	public List<ModelClass> getNationalitys() {
		List<ModelClass> list = getSelectInfoUtilMapper.getNationalitys();
		return list;
	}

	/**
	 * 社員形式を取得
	 * 
	 * @return
	 */
	public List<ModelClass> getStaffForms() {
		List<ModelClass> list = getSelectInfoUtilMapper.getStaffForms();
		return list;
	}

	/**
	 * 在留資格を取得
	 * 
	 * @return
	 */
	public List<ModelClass> getVisa() {
		List<ModelClass> list = getSelectInfoUtilMapper.getVisa();
		return list;
	}

	/**
	 * 開発言語を取得
	 * 
	 * @param sendMap
	 * 
	 * @return
	 */
	public List<ModelClass> getTechnologyType(Map<String, String> sendMap) {
		List<ModelClass> list = getSelectInfoUtilMapper.getTechnologyType(sendMap);
		return list;
	}

	/**
	 * 日本語レベルを取得
	 * 
	 * @return
	 */
	public List<ModelClass> getJapaneseLevel() {
		List<ModelClass> list = getSelectInfoUtilMapper.getJapaneseLevel();
		return list;
	}

	/**
	 * 入社区分を取得
	 * 
	 * @return
	 */
	public List<ModelClass> getIntoCompany() {
		List<ModelClass> list = getSelectInfoUtilMapper.getIntoCompany();
		return list;
	}

	/**
	 * 役割 を取得
	 * 
	 * @return
	 */
	public List<ModelClass> getSiteMaster() {
		List<ModelClass> list = getSelectInfoUtilMapper.getSiteMaster();
		return list;
	}

	/**
	 * 職種を取得
	 * 
	 * @return
	 */
	public List<ModelClass> getOccupation() {
		List<ModelClass> list = getSelectInfoUtilMapper.getOccupation();
		return list;
	}

	/**
	 * 部署を取得
	 * 
	 * @return
	 */
	public List<ModelClass> getDepartment() {
		List<ModelClass> list = getSelectInfoUtilMapper.getDepartment();
		return list;
	}

	/**
	 * 権限を取得
	 * 
	 * @return
	 */
	public List<ModelClass> getAuthority() {
		List<ModelClass> list = getSelectInfoUtilMapper.getAuthority();
		return list;
	}

	/**
	 * 英語を取得
	 * 
	 * @return
	 */
	public List<ModelClass> getEnglishLevel() {
		List<ModelClass> list = getSelectInfoUtilMapper.getEnglishLevel();
		return list;
	}

	/**
	 * 資格を取得
	 * 
	 * @return
	 */
	public List<ModelClass> getQualification() {
		List<ModelClass> list = getSelectInfoUtilMapper.getQualification();
		return list;
	}

	/**
	 * 採番
	 * @param sendMap 
	 * 
	 * @return
	 */
	public String getNO(Map<String, String> sendMap) {
		String no = getSelectInfoUtilMapper.getNO(sendMap);
		return no;
	}
	
	/**
	 * お客様ランキング
	 * @return
	 */
	public List<ModelClass> selectCustomerRanking() {
		return getSelectInfoUtilMapper.selectCustomerRanking();
	}
	
	/**
	 * お客様性質
	 * @return
	 */
	public List<ModelClass> selectCompanyNature() {
		return getSelectInfoUtilMapper.selectCompanyNature();
	}
	
	/**
	 * 職位
	 * @return
	 */
	public List<ModelClass> selectPosition() {
		return getSelectInfoUtilMapper.selectPosition();
	}
	
	/**
	 * 上位お客様連想
	 * @return
	 */
	public List<ModelClass> selectTopCustomer(String name) {
		return getSelectInfoUtilMapper.selectTopCustomer(name);
	}
	
	/**
	 * 部門名前連想
	 * @return
	 */
	public List<ModelClass> selectDepartmentMaster(String name) {
		return getSelectInfoUtilMapper.selectDepartmentMaster(name);
	}
	
	/**
	 * 銀行名検索
	 * @return
	 */
	public List<ModelClass> selectBankInfo() {
		return getSelectInfoUtilMapper.selectBankInfo();
	}
	
	/**
	 * 支店情報検索
	 * @return
	 */
	public List<ModelClass> getBankBranchInfo(HashMap<String, String> sendMap) {
		return getSelectInfoUtilMapper.getBankBranchInfo(sendMap);
	}
}
