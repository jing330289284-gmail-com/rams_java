package jp.co.lyc.cms.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import jp.co.lyc.cms.mapper.AccountInfoMapper;
import jp.co.lyc.cms.mapper.BpInfoMapper;
import jp.co.lyc.cms.mapper.EmployeeInfoMapper;
import jp.co.lyc.cms.mapper.SiteInfoMapper;
import jp.co.lyc.cms.model.AccountInfoModel;
import jp.co.lyc.cms.model.BpInfoModel;
import jp.co.lyc.cms.model.EmployeeModel;

@Component
public class EmployeeInfoService {

	@Autowired
	EmployeeInfoMapper employeeInfoMapper;

	@Autowired
	AccountInfoMapper accountInfoMapper;

	@Autowired
	BpInfoMapper bpInfoMapper;

	@Autowired
	SiteInfoMapper siteInfoMapper;

	/**
	 * ログイン
	 * 
	 * @param sendMap
	 * @return
	 */

	public EmployeeModel getEmployeeModel(Map<String, String> sendMap) {
		// TODO Auto-generated method stub
		EmployeeModel employeeModel = employeeInfoMapper.getEmployeeModel(sendMap);
		return employeeModel;
	}

	/**
	 * 社員情報を取得
	 * 
	 * @param sendMap
	 * @return List
	 */
	public List<EmployeeModel> getEmployeeInfo(Map<String, Object> sendMap) {
		List<EmployeeModel> employeeList = employeeInfoMapper.getEmployeeInfo2(sendMap);
		return employeeList;
	}

	/**
	 * 社員情報を確認
	 * 
	 * @param sendMap
	 * @return List
	 */
	public List<String> verificationEmployeeInfo() {
		List<String> employeeList = employeeInfoMapper.verificationEmployeeInfo();
		return employeeList;
	}

	/**
	 * 社員情報を追加
	 * 
	 * @param sendMap
	 * @return boolean
	 */
	@Transactional(rollbackFor = Exception.class)
	public boolean insertEmployee(HashMap<String, Object> sendMap) {
		boolean result = true;
		try {
			employeeInfoMapper.insertEmployeeInfo(sendMap);
			employeeInfoMapper.insertEmployeeInfoDetail(sendMap);
			employeeInfoMapper.insertAddressInfo(sendMap);
			if (sendMap.get("bpInfoModel") != null) {// BP情報
				bpInfoMapper.insertBp(getParamBpModel(sendMap));
			}
			if (sendMap.get("bankInfoModel") != null) {// 口座情報
				accountInfoMapper.insertAccount(getParamBankInfoModel(sendMap));
			}
			employeeInfoMapper.insertResumeManagement(sendMap);// 履歴書を追加
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
			return result = false;
		}
		return result;
	}

	/**
	 * 社員情報を削除
	 * 
	 * @param emp
	 * @return boolean
	 */
	@Transactional(rollbackFor = Exception.class)
	public boolean deleteEmployeeInfo(Map<String, Object> sendMap) {
		boolean result = true;
		try {
			employeeInfoMapper.deleteEmployeeInfo(sendMap);
			employeeInfoMapper.deleteEmployeeInfoDetail(sendMap);
			siteInfoMapper.deleteEmployeeSiteInfo(sendMap);
			employeeInfoMapper.deleteAddressInfo(sendMap);
			bpInfoMapper.deleteBpInfo(sendMap);
			employeeInfoMapper.deleteResumeManagement(sendMap);
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
			return result = false;
		}
		return result;
	}

	/**
	 * EmployeeNoによると、社員情報を取得
	 * 
	 * @param emp
	 * @return EmployeeModel
	 */
	public EmployeeModel getEmployeeByEmployeeNo(Map<String, Object> sendMap) {
		EmployeeModel model = employeeInfoMapper.getEmployeeByEmployeeNo(sendMap);
		return model;
	}

	/**
	 * 社員情報を修正
	 * 
	 * @param emp
	 * @return boolean
	 */
	@Transactional(rollbackFor = Exception.class)
	public boolean updateEmployee(Map<String, Object> sendMap) {
		boolean result = true;
		try {
			employeeInfoMapper.updateEmployeeInfo(sendMap);
			employeeInfoMapper.updateEmployeeInfoDetail(sendMap);
			employeeInfoMapper.updateAddressInfo(sendMap);
			if (sendMap.get("bankInfoModel") != null) {// 口座情報
				String employeeNo = (String) sendMap.get("employeeNo");
				String accountBelongsStatus = null;
				if (employeeNo.substring(0, 3).equals("LYC")) {
					accountBelongsStatus = "0";
				} else {
					accountBelongsStatus = "1";
				}
				accountInfoMapper.updateAccount(getParamBankInfoModel(sendMap));
			}
			if (sendMap.get("bpInfoModel") != null) {
				int row = bpInfoMapper.updateBp(getParamBpModel(sendMap));
				if (row == 0) {
					bpInfoMapper.insertBp(getParamBpModel(sendMap));
				}
			}
			employeeInfoMapper.updateResumeManagement(sendMap);// 履歴書を追加
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
			return result = false;
		}
		return result;
	}

	// 口座情報のパラメータをセットします。
	public HashMap<String, String> getParamBankInfoModel(Map<String, Object> sendMap) {
		HashMap<String, String> bankInfoModelSendMap = new HashMap<String, String>();
		AccountInfoModel accountInfoModel = (AccountInfoModel) sendMap.get("bankInfoModel");
		String employeeNo = (String) sendMap.get("employeeNo");
		if (employeeNo.substring(0, 3).equals("LYC")) {
			bankInfoModelSendMap.put("accountBelongsStatus", "0");
		} else {
			bankInfoModelSendMap.put("accountBelongsStatus", "1");
		}
		bankInfoModelSendMap.put("bankCode", accountInfoModel.getBankCode());
		bankInfoModelSendMap.put("accountName", accountInfoModel.getAccountName());
		bankInfoModelSendMap.put("accountNo", accountInfoModel.getAccountNo());
		bankInfoModelSendMap.put("bankBranchCode", accountInfoModel.getBankBranchCode());
		bankInfoModelSendMap.put("accountTypeStatus", accountInfoModel.getAccountTypeStatus());
		bankInfoModelSendMap.put("updateUser", sendMap.get("updateUser").toString());
		bankInfoModelSendMap.put("employeeOrCustomerNo", sendMap.get("employeeNo").toString());
		return bankInfoModelSendMap;
	}

	private Map<String, Object> getParamBpModel(Map<String, Object> sendMap) {
		Map<String, Object> pbModelSendMap = new HashMap<String, Object>();
		BpInfoModel pbModel = (BpInfoModel) sendMap.get("bpInfoModel");
		pbModelSendMap.put("bpEmployeeNo", pbModel.getBpEmployeeNo());
		pbModelSendMap.put("bpBelongCustomerCode", pbModel.getBpBelongCustomerCode());
		pbModelSendMap.put("bpUnitPrice", pbModel.getBpUnitPrice());
		pbModelSendMap.put("bpSalesProgressCode", pbModel.getBpSalesProgressCode());
		pbModelSendMap.put("bpRemark", pbModel.getBpRemark());
		pbModelSendMap.put("bpOtherCompanyAdmissionEndDate", pbModel.getBpOtherCompanyAdmissionEndDate());
		pbModelSendMap.put("updateUser", sendMap.get("updateUser").toString());
		return pbModelSendMap;
	}

	/**
	 * ログイン認証番号の電話番号存在チェック
	 * 
	 * @param employeeNo
	 * @return
	 */

	public String getEmployeePhoneNo(String employeeNo) {
		return employeeInfoMapper.getEmployeePhoneNo(employeeNo);
	}

}