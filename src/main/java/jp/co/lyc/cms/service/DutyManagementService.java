package jp.co.lyc.cms.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.lyc.cms.model.DutyManagementModel;
import jp.co.lyc.cms.mapper.DutyManagementMapper;

@Component
public class DutyManagementService {

	@Autowired
	DutyManagementMapper dutyManagementMapper;
		
	/**
	 * 画面情報検索
	 * @param TopCustomerNo
	 * @return
	 */
	
	public List<DutyManagementModel> selectDutyManagement(HashMap<String, String> sendMap) {
		List<DutyManagementModel> resultMod = dutyManagementMapper.selectDutyManagement(sendMap);
		return resultMod;
	}
	/**
	 * アップデート
	 * @param sendMap
	 */
	
	public boolean updateDutyManagement(HashMap<String, String> sendMap) {
		boolean result = true;
		try {
			dutyManagementMapper.updateDutyManagement(sendMap);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return result = false;
		}
		return result;
	}
}
