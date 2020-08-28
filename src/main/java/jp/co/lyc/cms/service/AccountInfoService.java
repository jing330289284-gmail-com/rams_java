package jp.co.lyc.cms.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import jp.co.lyc.cms.mapper.AccountInfoMapper;
import jp.co.lyc.cms.model.AccountInfoModel;

@Component
public class AccountInfoService {

	@Autowired
	AccountInfoMapper bankMapper;

	/**
	 * 口座情報の検索
	 * @param employeeOrCustomerNo
	 * @param accountBelongsStatus
	 * @return
	 */
	
	public AccountInfoModel selectAccountInfo(String employeeNo , String accountBelongsStatus) {
		AccountInfoModel resultMod = bankMapper.selectAccountInfo(employeeNo , accountBelongsStatus);
		return resultMod;
	}
	
	/**
	 * インサートとアップデートの値を設定
	 * @param bankCol
	 * @return
	 */
	public HashMap<String, String> setSendMap(AccountInfoModel bankCol) {
		HashMap<String, String> sendMap = new HashMap<>();
		sendMap.put("accountBelongsStatus", bankCol.getAccountBelongsStatus());
		sendMap.put("bankCode", bankCol.getBankCode());
		sendMap.put("accountName", bankCol.getAccountName());
		sendMap.put("accountNo", bankCol.getAccountNo());
		sendMap.put("bankBranchCode", bankCol.getBankBranchCode());
		sendMap.put("accountTypeStatus", bankCol.getAccountTypeStatus());
		sendMap.put("updateUser", bankCol.getUpdateUser());
		sendMap.put("employeeOrCustomerNo", bankCol.getEmployeeOrCustomerNo());	
		return sendMap;
	}
}
