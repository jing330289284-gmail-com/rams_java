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
import jp.co.lyc.cms.mapper.CostInfoMapper;
import jp.co.lyc.cms.mapper.EmployeeInfoMapper;
import jp.co.lyc.cms.mapper.GetSiteInfoMapper;
import jp.co.lyc.cms.model.AccountInfoModel;
import jp.co.lyc.cms.model.BpInfoModel;
import jp.co.lyc.cms.model.CostInfoModel;
import jp.co.lyc.cms.model.EmployeeModel;
import jp.co.lyc.cms.model.SiteModel;

@Component
public class EmployeeInfoService {

	@Autowired
	EmployeeInfoMapper employeeInfoMapper;

	@Autowired
	AccountInfoMapper accountInfoMapper;

	@Autowired
	CostInfoMapper costInfoMapper;

	@Autowired
	BpInfoMapper bpInfoMapper;
	
	@Autowired
	GetSiteInfoMapper siteInfoMapper;

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
		List<EmployeeModel> employeeList = employeeInfoMapper.getEmployeeInfo(sendMap);
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
		
			if(sendMap.get("bpInfoModel") != null) {// BP情報
				bpInfoMapper.insertBp(getParamBpModel(sendMap));
			}
			if (sendMap.get("bankInfoModel") != null) {// 口座情報
				accountInfoMapper.insertAccount(getParamBankInfoModel(sendMap));
			}
			if (sendMap.get("costModel") != null) {// 諸費用
				costInfoMapper.insertCost(getParamCostModel(sendMap));
			}
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
			costInfoMapper.deleteCostInfo(sendMap);
			bpInfoMapper.deleteBpInfo(sendMap);
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
				accountInfoMapper.updateAccount(getParamBankInfoModel(sendMap));
			}
			if (sendMap.get("costModel") != null) {// 諸費用
				costInfoMapper.updateCost(getParamCostModel(sendMap));
			}
			if(sendMap.get("bpInfoModel") != null) {
				bpInfoMapper.updateBp(getParamBpModel(sendMap));
			}
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
		bankInfoModelSendMap.put("accountBelongsStatus", accountInfoModel.getAccountBelongsStatus());
		bankInfoModelSendMap.put("bankCode", accountInfoModel.getBankCode());
		bankInfoModelSendMap.put("accountName", accountInfoModel.getAccountName());
		bankInfoModelSendMap.put("accountNo", accountInfoModel.getAccountNo());
		bankInfoModelSendMap.put("bankBranchCode", accountInfoModel.getBankBranchCode());
		bankInfoModelSendMap.put("accountTypeStatus", accountInfoModel.getAccountTypeStatus());
		bankInfoModelSendMap.put("updateUser", sendMap.get("updateUser").toString());
		bankInfoModelSendMap.put("employeeOrCustomerNo", sendMap.get("employeeNo").toString());
		return bankInfoModelSendMap;
	}

	// 諸費用のパラメータをセットします。
	public Map<String, Object> getParamCostModel(Map<String, Object> sendMap) {
		Map<String, Object> costModelSendMap = new HashMap<String, Object>();
		CostInfoModel costModel = (CostInfoModel) sendMap.get("costModel");
		sendMap.put("employeeNo", costModel.getEmployeeNo());
		sendMap.put("reflectYearAndMonth", costModel.getReflectYearAndMonth());
		sendMap.put("salary", costModel.getSalary());
		sendMap.put("waitingCost", costModel.getWaitingCost());
		sendMap.put("welfarePensionAmount", costModel.getWelfarePensionAmount());
		sendMap.put("healthInsuranceAmount", costModel.getHealthInsuranceAmount());
		sendMap.put("insuranceFeeAmount", costModel.getInsuranceFeeAmount());
		sendMap.put("lastTimeBonusAmount", costModel.getLastTimeBonusAmount());
		sendMap.put("scheduleOfBonusAmount", costModel.getScheduleOfBonusAmount());
		sendMap.put("transportationExpenses", costModel.getTransportationExpenses());
		sendMap.put("nextBonusMonth", costModel.getNextBonusMonth());
		sendMap.put("nextRaiseMonth", costModel.getNextRaiseMonth());
		sendMap.put("otherAllowance", costModel.getOtherAllowance());
		sendMap.put("otherAllowanceAmount", costModel.getOtherAllowanceAmount());
		sendMap.put("leaderAllowanceAmount", costModel.getLeaderAllowanceAmount());
		sendMap.put("totalAmount", costModel.getTotalAmount());
		sendMap.put("remark", costModel.getRemark());
		sendMap.put("employeeFormCode", costModel.getEmployeeFormCode());
		sendMap.put("housingStatus", costModel.getHousingStatus());
		sendMap.put("housingAllowance", costModel.getHousingAllowance());
		sendMap.put("updateUser", costModel.getUpdateUser());
		return costModelSendMap;
	}
	
	private Map<String, Object> getParamBpModel(Map<String, Object> sendMap) {
		Map<String, Object> pbModelSendMap = new HashMap<String, Object>();
		BpInfoModel pbModel = (BpInfoModel) sendMap.get("bpInfoModel");
		pbModelSendMap.put("bpEmployeeNo", pbModel.getBpEmployeeNo());
		pbModelSendMap.put("actionType", pbModel.getActionType());
		pbModelSendMap.put("bpBelongCustomerCode", pbModel.getBpBelongCustomerCode());
		pbModelSendMap.put("bpUnitPrice", pbModel.getBpUnitPrice());
		pbModelSendMap.put("bpSalesProgressCode", pbModel.getBpSalesProgressCode());
		pbModelSendMap.put("bpRemark", pbModel.getBpRemark());
		pbModelSendMap.put("bpOtherCompanyAdmissionEndDate", pbModel.getBpOtherCompanyAdmissionEndDate());
		pbModelSendMap.put("updateUser", pbModel.getUpdateUser());
		return pbModelSendMap;
	}

	// 現場情報のパラメータをセットします。
	public Map<String, String> getParamSiteModel(Map<String, Object> sendMap) {
		Map<String, String> costModelSendMap = new HashMap<String, String>();
		SiteModel siteModel = (SiteModel) sendMap.get("siteModel");
		sendMap.put("employeeNo", siteModel.getEmployeeNo());
		sendMap.put("customerNo", siteModel.getCustomerNo());
		sendMap.put("topCustomerNo", siteModel.getTopCustomerNo());
		sendMap.put("admissionStartDate", siteModel.getAdmissionStartDate());
		sendMap.put("location", siteModel.getLocation());
		sendMap.put("siteManager", siteModel.getSiteManager());
		sendMap.put("admissionEndDate", siteModel.getAdmissionEndDate());
		sendMap.put("unitPrice", siteModel.getUnitPrice());
		sendMap.put("siteRoleCode", siteModel.getSiteRoleCode());
		sendMap.put("payOffRange1", siteModel.getPayOffRange1());
		sendMap.put("payOffRange2", siteModel.getPayOffRange2());
		sendMap.put("systemName", siteModel.getSystemName());
		sendMap.put("developLanguageCode", siteModel.getDevelopLanguageCode());
		sendMap.put("related1Employees", siteModel.getRelated1Employees());
		sendMap.put("levelCode", siteModel.getLevelCode());
		sendMap.put("remark", siteModel.getRemark());
		sendMap.put("updateUser", siteModel.getUpdateUser());
		return costModelSendMap;
	}
	
	/**
	 * ログイン認証番号の電話番号存在チェック
	 * @param employeeNo
	 * @return
	 */
	
	public String getEmployeePhoneNo(String employeeNo) {
		return employeeInfoMapper.getEmployeePhoneNo(employeeNo);
	}
	
}