package jp.co.lyc.cms.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import jp.co.lyc.cms.mapper.EmployeeInfoMapper;
import jp.co.lyc.cms.mapper.GetBankInfoMapper;
import jp.co.lyc.cms.mapper.GetCostMapper;
import jp.co.lyc.cms.mapper.GetSiteInfoMapper;
import jp.co.lyc.cms.model.BankInfoModel;
import jp.co.lyc.cms.model.CostModel;
import jp.co.lyc.cms.model.EmployeeModel;
import jp.co.lyc.cms.model.SiteModel;

@Component
public class EmployeeInfoService {

	@Autowired
	EmployeeInfoMapper employeeInfoMapper;

	@Autowired
	GetBankInfoMapper bankMapper;

	@Autowired
	GetCostMapper costMapper;

	@Autowired
	GetSiteInfoMapper siteInfoMapper;

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
			if (sendMap.get("bankInfoModel") != null) {// 口座情報
				bankMapper.insertAccount(getParamBankInfoModel(sendMap));
			}
			if (sendMap.get("costModel") != null) {// 諸費用
				costMapper.insertCost(getParamCostModel(sendMap));
			}
			if (sendMap.get("siteModel") != null) {// 現場情報
				siteInfoMapper.siteInsert(getParamSiteModel(sendMap));
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
			employeeInfoMapper.deleteEmployeeSiteInfo(sendMap);
			employeeInfoMapper.deleteAddressInfo(sendMap);
			employeeInfoMapper.deleteExpensesInfo(sendMap);
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
		BankInfoModel bankInfoModel = (BankInfoModel) sendMap.get("bankInfoModel");
		bankInfoModelSendMap.put("accountBelongsStatus", bankInfoModel.getAccountBelongsStatus());
		bankInfoModelSendMap.put("bankCode", bankInfoModel.getBankCode());
		bankInfoModelSendMap.put("accountName", bankInfoModel.getAccountName());
		bankInfoModelSendMap.put("accountNo", bankInfoModel.getAccountNo());
		bankInfoModelSendMap.put("bankBranchCode", bankInfoModel.getBankBranchCode());
		bankInfoModelSendMap.put("accountTypeStatus", bankInfoModel.getAccountTypeStatus());
		bankInfoModelSendMap.put("updateUser", sendMap.get("updateUser").toString());
		bankInfoModelSendMap.put("employeeOrCustomerNo", sendMap.get("employeeNo").toString());
		return bankInfoModelSendMap;
	}

	// 諸費用のパラメータをセットします。
	public Map<String, Object> getParamCostModel(Map<String, Object> sendMap) {
		Map<String, Object> costModelSendMap = new HashMap<String, Object>();
		CostModel costModel = (CostModel) sendMap.get("costModel");
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

}
