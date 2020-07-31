package jp.co.lyc.cms.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.lyc.cms.mapper.technologyTypeMasterMapper;
@Component
public class technologyTypeMasterService {

	@Autowired
	technologyTypeMasterMapper technologyTypeMasterMapper;
	
	/**
	 * 採番
	 * @return
	 */
	
	public String getTechnologyCodeSaiban() {
		return technologyTypeMasterMapper.getTechnologyCodeSaiban();
	}
	
	/**
	 * 有無判断
	 * @param technologytypeName
	 * @return
	 */
	
	public boolean checkHave(String technologytypeName) {
		if(technologyTypeMasterMapper.checkHave(technologytypeName) == null) {
			return true;
		}
		return false;
	}
	
	/**
	 * インサート
	 * @param sendMap
	 */
	
	public boolean insertTechnologyTypeMaster(HashMap<String, String> sendMap) {
		try {
			technologyTypeMasterMapper.insertTechnologyTypeMaster(sendMap);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
