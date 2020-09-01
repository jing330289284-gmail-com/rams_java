package jp.co.lyc.cms.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.lyc.cms.mapper.MasterUpdateMapper;
import jp.co.lyc.cms.model.MasterModel;

@Component
public class MasterUpdateService {

	@Autowired
	MasterUpdateMapper masterUpdateMapper;

	/**
	 * 修正
	 * 
	 * @param sendMap
	 */

	public boolean updateMaster(HashMap<String, String> sendMap) {
		try {
			masterUpdateMapper.updateMaster(sendMap);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * 明細取得
	 * 
	 * @param sendMap
	 */

	public List<MasterModel> getMasterInfo(HashMap<String, String> sendMap) {
		List<MasterModel> masterList = masterUpdateMapper.getMasterInfo(sendMap);
		return masterList;
	}
	
	/**
	 * 有無判断
	 * 
	 * @param technologytypeName
	 * @return
	 */

	public boolean checkHave(MasterModel masterModel) {
		if (masterUpdateMapper.checkHave(masterModel) == null) {
			return true;
		}
		return false;
	}
}
