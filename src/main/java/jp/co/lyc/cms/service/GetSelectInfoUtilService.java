package jp.co.lyc.cms.service;

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

}
