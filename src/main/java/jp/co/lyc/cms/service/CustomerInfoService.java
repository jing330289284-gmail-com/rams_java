package jp.co.lyc.cms.service;

import java.util.ArrayList;
import java.util.HashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import jp.co.lyc.cms.model.TopCustomerInfoModel;
import jp.co.lyc.cms.util.UtilsCheckMethod;
import jp.co.lyc.cms.mapper.AccountInfoMapper;
import jp.co.lyc.cms.mapper.CustomerInfoMapper;
import jp.co.lyc.cms.mapper.TopCustomerInfoMapper;
import jp.co.lyc.cms.model.AccountInfoModel;
import jp.co.lyc.cms.model.CustomerDepartmentInfoModel;
import jp.co.lyc.cms.model.CustomerInfoModel;

@Component
public class CustomerInfoService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	// 客户信息检索画面service
	@Autowired
	CustomerInfoMapper customerInfoMapper;
	@Autowired
	AccountInfoService accountInfoService;
	@Autowired
	AccountInfoMapper accountInfoMapper;
	@Autowired
	TopCustomerInfoService topCustomerInfoService;
	@Autowired
	TopCustomerInfoMapper topCustomerInfoMapper;

	/**
	 * 上位お客様連想
	 * 
	 * @param topCustpmerName
	 * @return
	 */
	public ArrayList<TopCustomerInfoModel> selectTopCustomer(String topCustpmerName) {
		ArrayList<TopCustomerInfoModel> resultList = customerInfoMapper.selectTopCustomer(topCustpmerName);
		return resultList;
	}

	/**
	 * 部門連想
	 * 
	 * @param customerDepartmentName
	 * @return
	 */
	public ArrayList<CustomerDepartmentInfoModel> selectDepartmentMaster(String customerDepartmentName) {
		ArrayList<CustomerDepartmentInfoModel> resultList = customerInfoMapper
				.selectDepartmentMaster(customerDepartmentName);
		return resultList;
	}

	/**
	 * 部門番号検索
	 * 
	 * @param customerDepartmentName
	 * @return
	 */
	public String selectDepartmentCode(String customerDepartmentName) {
		return customerInfoMapper.selectDepartmentCode(customerDepartmentName);
	}

	/**
	 * 上位客户是否存在
	 * 
	 * @param topCustpmerName
	 * @return
	 */
	public String checkTopCustomer(String topCustpmerName) {
		String result = customerInfoMapper.checkTopCustomer(topCustpmerName);
		return result;
	}

	/**
	 * 部門情報検索
	 * 
	 * @param customerNo
	 * @return
	 */
	public ArrayList<CustomerDepartmentInfoModel> selectCustomerDepartmentInfo(HashMap<String, String> sendMapd) {
		ArrayList<CustomerDepartmentInfoModel> resultList = customerInfoMapper.selectCustomerDepartmentInfo(sendMapd);
		return resultList;
	}

	/**
	 * お客様情報検索
	 * 
	 * @param customerNo
	 * @return
	 */
	public CustomerInfoModel selectCustomerInfo(String customerNo) {
		CustomerInfoModel resultMod = customerInfoMapper.selectCustomerInfo(customerNo);
		return resultMod;
	}

	/**
	 * 客户番号采番
	 * 
	 * @return
	 */
	public String customerNoSaiBan() {
		String result = customerInfoMapper.customerNoSaiBan();
		return result;
	}

	/**
	 * お客様情報処理
	 * 
	 * @param customerInfoMod
	 * @return
	 */
	@Transactional(rollbackFor = Exception.class)
	public String CustomerInfoToDB(CustomerInfoModel customerInfoMod) {
		logger.info("CustomerInfoController.onloadPage:" + "登録開始");
		CustomerInfoModel checkMod = selectCustomerInfo(customerInfoMod.getCustomerNo());
		if (checkMod == null && customerInfoMod.getActionType().equals("insert")) {// 追加の場合
			try {
				customerInfoMapper.insertCustomerInfo(setSendMap(customerInfoMod));
				if (customerInfoMod.getAccountInfo() != null) {
					AccountInfoModel accountInfoModel = customerInfoMod.getAccountInfo();
					accountInfoModel.setUpdateUser(customerInfoMod.getUpdateUser());
					accountInfoMapper.insertAccount(accountInfoService.setSendMap(accountInfoModel));
				}
				if (customerInfoMod.getTopCustomerInfo() != null) {
					TopCustomerInfoModel topCustomerInfoModel = customerInfoMod.getTopCustomerInfo();
					topCustomerInfoModel.setUpdateUser(customerInfoMod.getUpdateUser());
					topCustomerInfoMapper
							.insertTopCustomerInfo(topCustomerInfoService.setSendMap(topCustomerInfoModel));
				}
				if (customerInfoMod.getCustomerDepartmentList().size() > 0) {
					if (!checkCustomerDepartment(customerInfoMod.getCustomerDepartmentList())) {
						return "5";
					}
				}
				for (CustomerDepartmentInfoModel customerDepartmentInfoModel : customerInfoMod
						.getCustomerDepartmentList()) {
					customerDepartmentInfoModel.setActionType(customerInfoMod.getActionType());
					customerDepartmentInfoModel.setCustomerNo(customerInfoMod.getCustomerNo());
					customerDepartmentInfoModel.setUpdateUser(customerInfoMod.getUpdateUser());
					if (!UtilsCheckMethod.isNullOrEmpty(customerDepartmentInfoModel.getCustomerDepartmentCode())) {
						String meisaiResult = meisaiToroku(customerDepartmentInfoModel);
						if (meisaiResult.equals("1")) {
							logger.info("CustomerInfoController.onloadPage:" + "登録終了");
							return "3";
						}
					}
				}
				return "0";// result（0）成功（1）失敗
			} catch (Exception e) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				e.printStackTrace();
				logger.info("CustomerInfoController.onloadPage:" + "登録終了");
				return "1";
				// TODO: handle exception
			}
		} else if (checkMod != null && (customerInfoMod.getActionType().equals("update"))) {// 修正の場合
			try {
				customerInfoMapper.updateCustomerInfo(setSendMap(customerInfoMod));
				if (customerInfoMod.getAccountInfo() != null) {
					AccountInfoModel accountInfoModel = customerInfoMod.getAccountInfo();
					accountInfoModel.setUpdateUser(customerInfoMod.getUpdateUser());
					accountInfoMapper.updateAccount(accountInfoService.setSendMap(accountInfoModel));
				}
				if (customerInfoMod.getCustomerDepartmentList().size() > 0) {
					if (!checkCustomerDepartment(customerInfoMod.getCustomerDepartmentList())) {
						return "5";
					}
				}
				for (CustomerDepartmentInfoModel customerDepartmentInfoModel : customerInfoMod
						.getCustomerDepartmentList()) {
					// 存在チェック
					HashMap<String, String> sendMap = new HashMap<String, String>();
					sendMap.put("customerNo", customerInfoMod.getCustomerNo());
					sendMap.put("customerDepartmentCode", customerDepartmentInfoModel.getCustomerDepartmentCode());
					sendMap.put("positionCode", customerDepartmentInfoModel.getPositionCode());
					ArrayList<CustomerDepartmentInfoModel> checkList = customerInfoMapper
							.selectCustomerDepartmentInfo(sendMap);
					if (checkList.size() > 0) {
						return "4";
					}
					if (!UtilsCheckMethod.isNullOrEmpty(customerDepartmentInfoModel.getBeforeCDCode())
							&& !UtilsCheckMethod.isNullOrEmpty(customerDepartmentInfoModel.getBeforePCode())) {
						customerDepartmentInfoModel.setActionType(customerInfoMod.getActionType());
					} else {
						customerDepartmentInfoModel.setActionType("insert");
					}
					customerDepartmentInfoModel.setCustomerNo(customerInfoMod.getCustomerNo());
					customerDepartmentInfoModel.setUpdateUser(customerInfoMod.getUpdateUser());
					if (!UtilsCheckMethod.isNullOrEmpty(customerDepartmentInfoModel.getCustomerDepartmentCode())) {
						String meisaiResult = meisaiToroku(customerDepartmentInfoModel);
						if (meisaiResult.equals("1")) {
							logger.info("CustomerInfoController.onloadPage:" + "登録終了");
							return "3";
						}
					}
				}
				return "0";// result（0）成功（1）失敗
			} catch (Exception e) {
				// TODO: handle exception
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				e.printStackTrace();
				logger.info("CustomerInfoController.onloadPage:" + "登録終了");
				return "1";
			}

		}
		logger.info("CustomerInfoController.onloadPage:" + "登録終了");
		return "0";
	}

	/**
	 * お客様部門と職位が二重チェック
	 * 
	 * @param initList
	 * @return
	 */
	public boolean checkCustomerDepartment(ArrayList<CustomerDepartmentInfoModel> initList) {
		for (CustomerDepartmentInfoModel c : initList) {
			int count = 0;
			if(!UtilsCheckMethod.isNullOrEmpty(c.getPositionCode()) 
					&& !UtilsCheckMethod.isNullOrEmpty(c.getCustomerDepartmentCode())) {
				String position = c.getPositionCode();
				String customerDepartmentCode = c.getCustomerDepartmentCode();
				for (CustomerDepartmentInfoModel d : initList) {
					if (position.equals(d.getPositionCode())
							&& customerDepartmentCode.equals(d.getCustomerDepartmentCode())) {
						count++;
					}
				}
				if (count > 1) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * 部門登録
	 * 
	 * @param customerDepartmentInfoModel
	 * @return
	 */
	@Transactional(rollbackFor = Exception.class)
	public String meisaiToroku(CustomerDepartmentInfoModel customerDepartmentInfoModel) {
		logger.info("BankInfoController.toroku:" + "明細登録開始");
		HashMap<String, String> sendMap = new HashMap<>();
		sendMap.put("customerNo", customerDepartmentInfoModel.getCustomerNo());
		String resultCode = "0";// 処理結果
		if (customerDepartmentInfoModel.getActionType().equals("update")) {
			sendMap.put("beforeCDCode", customerDepartmentInfoModel.getBeforeCDCode());
			sendMap.put("beforePCode", customerDepartmentInfoModel.getBeforePCode());
		} else {
			sendMap.put("positionCode", customerDepartmentInfoModel.getPositionCode());
			sendMap.put("customerDepartmentCode", customerDepartmentInfoModel.getCustomerDepartmentCode());
		}
		sendMap.put("customerNo", customerDepartmentInfoModel.getCustomerNo());
		sendMap.put("responsiblePerson", customerDepartmentInfoModel.getResponsiblePerson());
		sendMap.put("typeOfIndustryCode", customerDepartmentInfoModel.getTypeOfIndustryCode());
		sendMap.put("developLanguageCode1", customerDepartmentInfoModel.getDevelopLanguageCode1());
		sendMap.put("developLanguageCode2", customerDepartmentInfoModel.getDevelopLanguageCode2());
		sendMap.put("stationCode", customerDepartmentInfoModel.getStationCode());
		sendMap.put("customerDepartmentMail", customerDepartmentInfoModel.getCustomerDepartmentMail());
		sendMap.put("updateUser", customerDepartmentInfoModel.getUpdateUser());
		// resultCode : 0(処理成功)1（処理失敗）
		if (customerDepartmentInfoModel.getActionType().equals("update")) {
			if (selectCustomerDepartmentInfo(sendMap).size() != 0) {
				try {
					resultCode = (updateCustomerDepartment(sendMap) ? "0" : "1");
				} catch (Exception e) {
					// TODO: handle exception
					TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
					e.printStackTrace();
					resultCode = "1";
				}
			} else {
				try {
					resultCode = (insertCustomerDepartment(sendMap) ? "0" : "1");
				} catch (Exception e) {
					// TODO: handle exception
					TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
					e.printStackTrace();
					resultCode = "1";
				}
			}
		} else if (customerDepartmentInfoModel.getActionType().equals("insert")) {
			try {
				resultCode = (insertCustomerDepartment(sendMap) ? "0" : "1");
			} catch (Exception e) {
				// TODO: handle exception
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				e.printStackTrace();
				resultCode = "1";
			}
		}
		logger.info("BankInfoController.toroku:" + "明細登録終了");
		return resultCode;
	}

	/**
	 * 插入部门信息
	 * 
	 * @param sendMap
	 * @return
	 */
	@Transactional(rollbackFor = Exception.class)
	public boolean insertCustomerDepartment(HashMap<String, String> sendMap) {
		boolean result = true;
		try {
			customerInfoMapper.insertCustomerDepartment(sendMap);
		} catch (Exception e) {
			// TODO: handle exception
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
			return result = false;
		}
		return result;
	}

	/**
	 * 更新部门信息
	 * 
	 * @param sendMap
	 * @return
	 */
	@Transactional(rollbackFor = Exception.class)
	public boolean updateCustomerDepartment(HashMap<String, String> sendMap) {
		boolean result = true;
		try {
			customerInfoMapper.updateCustomerDepartment(sendMap);
		} catch (Exception e) {
			// TODO: handle exception
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
			return result = false;
		}
		return result;
	}

	/**
	 * 部門削除
	 * 
	 * @param customerNo
	 */
	@Transactional(rollbackFor = Exception.class)
	public boolean customerDepartmentdelete(HashMap<String, String> sendMap) {
		boolean result = true;
		try {
			customerInfoMapper.customerDepartmentdelete(sendMap);
		} catch (Exception e) {
			// TODO: handle exception
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
			return result = false;
		}
		return result;
	}

	/**
	 * 插入数据
	 * 
	 * @param customerInfoMod
	 * @return
	 */
	public HashMap<String, String> setSendMap(CustomerInfoModel customerInfoMod) {
		HashMap<String, String> sendMap = new HashMap<>();
		sendMap.put("customerName", customerInfoMod.getCustomerName());
		sendMap.put("stationCode", customerInfoMod.getStationCode());
		sendMap.put("representative", customerInfoMod.getRepresentative());
		sendMap.put("customerAbbreviation", customerInfoMod.getCustomerAbbreviation());
		sendMap.put("topCustomerNo", customerInfoMod.getTopCustomerNo());
		sendMap.put("establishmentDate", customerInfoMod.getEstablishmentDate());
		sendMap.put("businessStartDate", customerInfoMod.getBusinessStartDate());
		sendMap.put("levelCode", customerInfoMod.getLevelCode());
		sendMap.put("listedCompanyFlag", customerInfoMod.getListedCompanyFlag());
		sendMap.put("companyNatureCode", customerInfoMod.getCompanyNatureCode());
		sendMap.put("url", customerInfoMod.getUrl());
		sendMap.put("purchasingManagers", customerInfoMod.getPurchasingManagers());
		sendMap.put("remark", customerInfoMod.getRemark());
		sendMap.put("purchasingManagersMail", customerInfoMod.getPurchasingManagersMail());
		sendMap.put("paymentsiteCode", customerInfoMod.getPaymentsiteCode());
		sendMap.put("capitalStock", customerInfoMod.getCapitalStock());
		sendMap.put("updateUser", customerInfoMod.getUpdateUser());
		sendMap.put("customerNo", customerInfoMod.getCustomerNo());
		return sendMap;
	}
}
