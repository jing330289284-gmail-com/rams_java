package jp.co.lyc.cms.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import jp.co.lyc.cms.mapper.GetBankInfoMapper;
import jp.co.lyc.cms.model.BankInfoModel;

@Component
public class GetBankInfoService {

	@Autowired
	GetBankInfoMapper bankMapper;

	/**
	 * 口座情報の検索
	 * @param employeeOrCustomerNo
	 * @param accountBelongsStatus
	 * @return
	 */
	
	public BankInfoModel selectAccountInfo(String employeeNo , String accountBelongsStatus) {
		BankInfoModel resultMod = bankMapper.selectAccountInfo(employeeNo , accountBelongsStatus);
		return resultMod;
	}
	
	/**
	 * インサート
	 * @param sendMap
	 */
	
	public boolean insertAccount(HashMap<String, String> sendMap) {
		boolean result = true;
		try {
			bankMapper.insertAccount(sendMap);
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
	
	public boolean updateAccount(HashMap<String, String> sendMap) {
		boolean result = true;
		try {
			bankMapper.updateAccount(sendMap);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return result = false;
		}
		return result;
	}
}
