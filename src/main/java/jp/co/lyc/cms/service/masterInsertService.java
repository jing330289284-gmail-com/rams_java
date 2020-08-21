package jp.co.lyc.cms.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.lyc.cms.mapper.masterInsertMapper;
import jp.co.lyc.cms.model.masterModel;

@Component
public class masterInsertService {

	@Autowired
	masterInsertMapper masterInsertMapper;

	/**
	 * 有無判断
	 * 
	 * @param technologytypeName
	 * @return
	 */

	public boolean checkHave(masterModel masterModel) {
		if (masterInsertMapper.checkHave(masterModel) == null) {
			return true;
		}
		return false;
	}

	/**
	 * インサート
	 * 
	 * @param sendMap
	 */

	public boolean insertMaster(HashMap<String, String> sendMap) {
		try {
			masterInsertMapper.insertMaster(sendMap);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
