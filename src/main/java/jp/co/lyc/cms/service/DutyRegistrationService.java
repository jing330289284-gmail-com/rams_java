package jp.co.lyc.cms.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import jp.co.lyc.cms.model.DutyRegistrationModel;
import jp.co.lyc.cms.mapper.DutyRegistrationMapper;

@Component
public class DutyRegistrationService {

	@Autowired
	DutyRegistrationMapper dutyRegistrationMapper;
		
	/**
	 * 画面情報検索
	 * @param TopCustomerNo
	 * @return
	 */
	
	public DutyRegistrationModel selectDutyRegistration(HashMap<String, String> sendMap) {
		DutyRegistrationModel resultMod = dutyRegistrationMapper.selectDutyRegistration(sendMap);
		return resultMod;
	}
	
	/**
	 * インサート
	 * @param sendMap
	 */
	public boolean insertDutyRegistration(HashMap<String, String> sendMap) {
		boolean result = true;
		try {
			dutyRegistrationMapper.insertDutyRegistration(sendMap);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return result = false;
		}
		
		return result;
	}
	
	/**
	 * アップデート
	 * @param sendMap
	 */
	
	public boolean updateDutyRegistration(HashMap<String, String> sendMap) {
		boolean result = true;
		try {
			dutyRegistrationMapper.updateDutyRegistration(sendMap);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return result = false;
		}
		return result;
	}
	
	
	/**
	 * お客様の部門削除
	 * @param customerNo
	 */
	
	public boolean deleteDutyRegistration(String dutyRegistrationNo) {
		boolean result = true;
		try {
			dutyRegistrationMapper.deleteDutyRegistration(dutyRegistrationNo);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return result = false;
		}
		return result;
	}
}
