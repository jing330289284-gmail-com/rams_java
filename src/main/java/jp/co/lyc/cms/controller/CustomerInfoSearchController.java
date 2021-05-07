package jp.co.lyc.cms.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import jp.co.lyc.cms.model.CustomerInfoModel;
import jp.co.lyc.cms.service.CustomerInfoSearchService;
import jp.co.lyc.cms.service.TopCustomerInfoService;
import jp.co.lyc.cms.util.UtilsCheckMethod;
import jp.co.lyc.cms.validation.CustomerInfoSearchValidation;
import jp.co.lyc.cms.validation.CustomerInfoValidation;

@Controller
@RequestMapping(value = "/customerInfoSearch")
public class CustomerInfoSearchController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	CustomerInfoSearchService customerInfoSearchService;
	@Autowired
	TopCustomerInfoService topCustomerInfoService;

	String errorsMessage = "";

	/**
	 * データの検索
	 * 
	 * @param customerInfoMod
	 * @return
	 */
	@RequestMapping(value = "/customerSearch", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> customerSearch(@RequestBody CustomerInfoModel customerInfoMod) {
		errorsMessage = "";
		DataBinder binder = new DataBinder(customerInfoMod);
		binder.setValidator(new CustomerInfoSearchValidation());
		binder.validate();
		BindingResult results = binder.getBindingResult();
		Map<String, Object> result = new HashMap<>();
		if (results.hasErrors()) {
			results.getAllErrors().forEach(o -> {
				FieldError error = (FieldError) o;
				errorsMessage += error.getDefaultMessage();// エラーメッセージ
			});
			result.put("errorsMessage", errorsMessage);// エラーメッセージ
			return result;
		}
		ArrayList<CustomerInfoModel> resultList = seach(customerInfoMod);
		ArrayList<CustomerInfoModel> resiltArrayList = new ArrayList<CustomerInfoModel>();
		if (resultList.size() > 0) {
			resultList.forEach((customerMod) -> {
				// 取引人月が前後ある
				int rowNo = 1;
				if (!UtilsCheckMethod.isNullOrEmpty(customerInfoMod.getTraderPersonFront())
						&& !UtilsCheckMethod.isNullOrEmpty(customerInfoMod.getTraderPersonBack())) {
					if ((Integer.parseInt(customerMod.getTraderPerson()) > Integer
							.parseInt(customerInfoMod.getTraderPersonFront())
							|| Integer.parseInt(customerMod.getTraderPerson()) == Integer
									.parseInt(customerInfoMod.getTraderPersonFront()))
							&& (Integer.parseInt(customerMod.getTraderPerson()) < Integer
									.parseInt(customerInfoMod.getTraderPersonBack())
									|| Integer.parseInt(customerMod.getTraderPerson()) == Integer
											.parseInt(customerInfoMod.getTraderPersonBack()))) {
						// 取引区分は取引中
						if (!customerInfoMod.getTransactionStatus().equals("0")) {
							if (customerMod.getTransactionStatus().equals(customerInfoMod.getTransactionStatus())) {
								resiltArrayList.add(customerMod);
							}
							// 取引区分は取引中
						} else {
							resiltArrayList.add(customerMod);
						}
					}
					// 取引人月は前ある後ない
				} else if (!UtilsCheckMethod.isNullOrEmpty(customerInfoMod.getTraderPersonFront())
						&& UtilsCheckMethod.isNullOrEmpty(customerInfoMod.getTraderPersonBack())) {
					if (Integer.parseInt(customerMod.getTraderPerson()) > Integer
							.parseInt(customerInfoMod.getTraderPersonFront())
							|| Integer.parseInt(customerMod.getTraderPerson()) == Integer
									.parseInt(customerInfoMod.getTraderPersonFront())) {
						// 取引区分は取引中
						if (!customerInfoMod.getTransactionStatus().equals("0")) {
							if (customerMod.getTransactionStatus().equals(customerInfoMod.getTransactionStatus())) {
								resiltArrayList.add(customerMod);
							}
							// 取引区分は取引中
						} else {
							resiltArrayList.add(customerMod);
						}
					}
					// 取引人月は前ない後ある
				} else if (UtilsCheckMethod.isNullOrEmpty(customerInfoMod.getTraderPersonFront())
						&& !UtilsCheckMethod.isNullOrEmpty(customerInfoMod.getTraderPersonBack())) {
					if (Integer.parseInt(customerMod.getTraderPerson()) < Integer
							.parseInt(customerInfoMod.getTraderPersonBack())
							|| Integer.parseInt(customerMod.getTraderPerson()) == Integer
									.parseInt(customerInfoMod.getTraderPersonBack())) {
						// 取引区分は取引中
						if (!customerInfoMod.getTransactionStatus().equals("0")) {
							if (customerMod.getTransactionStatus().equals(customerInfoMod.getTransactionStatus())) {
								resiltArrayList.add(customerMod);
							}
							// 取引区分は取引中
						} else {
							resiltArrayList.add(customerMod);
						}
					}
					// 取引人月は入っていない
				} else if (UtilsCheckMethod.isNullOrEmpty(customerInfoMod.getTraderPersonFront())
						&& UtilsCheckMethod.isNullOrEmpty(customerInfoMod.getTraderPersonBack())) {
					// 取引区分は取引中
					if (!customerInfoMod.getTransactionStatus().equals("0")) {
						if (customerMod.getTransactionStatus().equals(customerInfoMod.getTransactionStatus())) {
							resiltArrayList.add(customerMod);
						}
						// 取引区分は取引中
					} else {
						resiltArrayList.add(customerMod);
					}
				}
			});
		}
		if (resiltArrayList.size() != 0) {
			int rowNo = 1;
			for (CustomerInfoModel a : resiltArrayList) {
				a.setRowNo(Integer.toString(rowNo));
				if (!UtilsCheckMethod.isNullOrEmpty(a.getEstablishmentDate())) {
					a.setEstablishmentDate(
							a.getEstablishmentDate().substring(0, 4) + "/" + a.getEstablishmentDate().substring(4, 6));
				}
				if (!UtilsCheckMethod.isNullOrEmpty(a.getBusinessStartDate())) {
					a.setBusinessStartDate(
							a.getBusinessStartDate().substring(0, 4) + "/" + a.getBusinessStartDate().substring(4, 6));
				}
				rowNo++;
			}
			result.put("resultList", resiltArrayList);
		} else {
			result.put("errorsMessage", "該当データない");
		}
		return result;
	}

	/**
	 * データの検索
	 * 
	 * @param customerInfoMod
	 * @return
	 */
	public ArrayList<CustomerInfoModel> seach(CustomerInfoModel customerInfoMod) {
		logger.info("CustomerInfoController.onloadPage:" + "検索開始");
		ArrayList<CustomerInfoModel> resultList = new ArrayList<>();
		ArrayList<CustomerInfoModel> databeseList = new ArrayList<>();
		databeseList = SelectCustomerInfo(customerInfoMod);
		int rowNo = 1;
		int traderPerson = 0;
		String transactionStatus = "1";// 1は取引中
		for (int i = 0; i < databeseList.size(); i++) {
			// 今のデータ
			CustomerInfoModel firstModel = databeseList.get(i);
			// 1index前のデータ
			CustomerInfoModel lastModel = new CustomerInfoModel();
			// 1index後のデータ
			CustomerInfoModel lastestModel = new CustomerInfoModel();
			if (i != 0) {
				lastModel = databeseList.get(i - 1);
			}
			if (i != databeseList.size() - 1) {
				lastestModel = databeseList.get(i + 1);
			}
			if (i != 0) {// 第二からのデータ
				if (!firstModel.getCustomerNo().equals(lastModel.getCustomerNo())) {
					// 前後お客様番号が違い場合、データをリストに入る
					traderPerson = 0;
					transactionStatus = "2";// 2は休眠中、1は取引中
					// 現場開始期日分け
					if (!UtilsCheckMethod.isNullOrEmpty(firstModel.getAdmissionStartDate())) {
						int startYear = Integer.parseInt(firstModel.getAdmissionStartDate().substring(0, 4));
						int startMonth = Integer.parseInt(firstModel.getAdmissionStartDate().substring(4, 6));
						// 現場終了期日分け
						int endYear = 0;
						int endMonth = 0;
						if (!UtilsCheckMethod.isNullOrEmpty(firstModel.getAdmissionEndDate())) {
							endYear = Integer.parseInt(firstModel.getAdmissionEndDate().substring(0, 4));
							endMonth = Integer.parseInt(firstModel.getAdmissionEndDate().substring(4, 6));
						} else {
							transactionStatus = "1";
							endYear = new Date().getYear() + 1900;
							endMonth = new Date().getMonth() + 1;
						}
						int year = endYear - startYear;
						int month = endMonth - startMonth;
						traderPerson += (year * 12 + month);
					}
					firstModel.setRowNo(Integer.toString(rowNo));
					if (firstModel.getEmployeeName() != null) {
						ArrayList<String> employeeNameList = new ArrayList<>();
						employeeNameList.add(firstModel.getEmployeeName());
						firstModel.setEmployeeNameList(employeeNameList);
					}
					if (firstModel.getLocation() != null) {
						ArrayList<String> locationList = new ArrayList<>();
						locationList.add(firstModel.getLocation());
						firstModel.setLocationList(locationList);
					}
					if (firstModel.getSiteManager() != null) {
						ArrayList<String> siteManagerList = new ArrayList<>();
						siteManagerList.add(firstModel.getSiteManager());
						firstModel.setSiteManagerList(siteManagerList);
					}
					if (firstModel.getUnitPrice() != null) {
						ArrayList<String> unitPriceList = new ArrayList<>();
						unitPriceList.add(firstModel.getUnitPrice());
						firstModel.setUnitPriceList(unitPriceList);
					}
					if (!firstModel.getCustomerNo().equals(lastestModel.getCustomerNo())) {
						if (traderPerson < 0) {
							firstModel.setTraderPerson("0");
							firstModel.setTransactionStatus(transactionStatus);
						} else {
							firstModel.setTraderPerson(Integer.toString(traderPerson));
							firstModel.setTransactionStatus(transactionStatus);
						}
					}
					resultList.add(firstModel);
					rowNo++;
				} else if (firstModel.getCustomerNo().equals(lastModel.getCustomerNo())
						&& firstModel.getCustomerName().equals(lastModel.getCustomerName())) {
					// 前後のお客様番号が同じの場合、データを整備する
					// 現場開始期日分け
					if (!UtilsCheckMethod.isNullOrEmpty(firstModel.getAdmissionStartDate())) {
						int startYear = Integer.parseInt(firstModel.getAdmissionStartDate().substring(0, 4));
						int startMonth = Integer.parseInt(firstModel.getAdmissionStartDate().substring(4, 6));
						// 現場終了期日分け
						int endYear = 0;
						int endMonth = 0;
						if (!UtilsCheckMethod.isNullOrEmpty(firstModel.getAdmissionEndDate())) {
							endYear = Integer.parseInt(firstModel.getAdmissionEndDate().substring(0, 4));
							endMonth = Integer.parseInt(firstModel.getAdmissionEndDate().substring(4, 6));
						} else {
							transactionStatus = "1";// 1は取引中
							endYear = new Date().getYear() + 1900;
							endMonth = new Date().getMonth() + 1;
						}
						int year = endYear - startYear;
						int month = endMonth - startMonth;
						traderPerson += (year * 12 + month);
					}
					CustomerInfoModel dataChange = resultList.get(rowNo - 2);
					ArrayList<String> employeeNameList = (dataChange.getEmployeeNameList() == null ? new ArrayList<>()
							: dataChange.getEmployeeNameList());
					employeeNameList.add(firstModel.getEmployeeName());
					dataChange.setEmployeeNameList(employeeNameList);

					ArrayList<String> locationList = (dataChange.getLocationList() == null ? new ArrayList<>()
							: dataChange.getLocationList());
					locationList.add(firstModel.getLocation());
					dataChange.setLocationList(locationList);

					ArrayList<String> siteManagerList = (dataChange.getSiteManagerList() == null ? new ArrayList<>()
							: dataChange.getSiteManagerList());
					siteManagerList.add(firstModel.getSiteManager());
					dataChange.setSiteManagerList(siteManagerList);

					ArrayList<String> unitPriceList = (dataChange.getUnitPriceList() == null ? new ArrayList<>()
							: dataChange.getUnitPriceList());
					unitPriceList.add(firstModel.getUnitPrice());
					dataChange.setUnitPriceList(unitPriceList);
					if (!firstModel.getCustomerNo().equals(lastestModel.getCustomerNo())) {
						if (traderPerson < 0) {
							dataChange.setTraderPerson("0");
							dataChange.setTransactionStatus(transactionStatus);
						} else {
							dataChange.setTraderPerson(Integer.toString(traderPerson));
							dataChange.setTransactionStatus(transactionStatus);
						}
					}
					resultList.set((rowNo - 2), dataChange);
				}
			} else {// 第一のデータ
				transactionStatus = "2";
				if (!UtilsCheckMethod.isNullOrEmpty(firstModel.getAdmissionStartDate())) {
					// 現場開始期日分け
					int startYear = Integer.parseInt(firstModel.getAdmissionStartDate().substring(0, 4));
					int startMonth = Integer.parseInt(firstModel.getAdmissionStartDate().substring(4, 6));
					// 現場終了期日分け
					int endYear = 0;
					int endMonth = 0;
					if (!UtilsCheckMethod.isNullOrEmpty(firstModel.getAdmissionEndDate())) {
						endYear = Integer.parseInt(firstModel.getAdmissionEndDate().substring(0, 4));
						endMonth = Integer.parseInt(firstModel.getAdmissionEndDate().substring(4, 6));
					} else {
						transactionStatus = "1";
						endYear = new Date().getYear() + 1900;
						endMonth = new Date().getMonth() + 1;
					}
					int year = endYear - startYear;
					int month = endMonth - startMonth;
					traderPerson += (year * 12 + month);
				}
				firstModel.setRowNo(Integer.toString(rowNo));
				if (firstModel.getEmployeeName() != null) {
					ArrayList<String> employeeNameList = new ArrayList<>();
					employeeNameList.add(firstModel.getEmployeeName());
					firstModel.setEmployeeNameList(employeeNameList);
				}
				if (firstModel.getLocation() != null) {
					ArrayList<String> locationList = new ArrayList<>();
					locationList.add(firstModel.getLocation());
					firstModel.setLocationList(locationList);
				}
				if (firstModel.getSiteManager() != null) {
					ArrayList<String> siteManagerList = new ArrayList<>();
					siteManagerList.add(firstModel.getSiteManager());
					firstModel.setSiteManagerList(siteManagerList);
				}
				if (firstModel.getUnitPrice() != null) {
					ArrayList<String> unitPriceList = new ArrayList<>();
					unitPriceList.add(firstModel.getUnitPrice());
					firstModel.setUnitPriceList(unitPriceList);
				}
				rowNo++;
				if (!firstModel.getCustomerNo().equals(lastestModel.getCustomerNo())) {
					if (traderPerson < 0) {
						firstModel.setTraderPerson("0");
						firstModel.setTransactionStatus(transactionStatus);
					} else {
						firstModel.setTraderPerson(Integer.toString(traderPerson));
						firstModel.setTransactionStatus(transactionStatus);
					}
				}
				resultList.add(firstModel);
			}
		}
		logger.info("CustomerInfoController.onloadPage:" + "検索終了");
		return resultList;
	}

	/**
	 * 削除ボタン
	 * 
	 * @param customerNo
	 * @return
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public String delete(@RequestBody CustomerInfoModel customerInfoMod) {
		logger.info("CustomerInfoController.onloadPage:" + "削除ボタン");
		if (customerInfoSearchService.checkCustomerInSiteInfo(customerInfoMod.getCustomerNo()).size() > 0) {
			return "2";// 2:お客様が現場に使っている
		} else {
			String topCustomerNo = customerInfoSearchService
					.getTopCustomerNoInCustomerInfo(customerInfoMod.getCustomerNo());
			if (customerInfoSearchService.getCustomerNoWithSameTop(topCustomerNo).size() > 0) {
				if (customerInfoSearchService.deleteCustomerInfo(customerInfoMod.getCustomerNo())
						&& customerInfoSearchService.deleteCustomerDepartmentInfo(customerInfoMod.getCustomerNo())) {
					return "3";// 3:上位お客様の下位お客様が複数あるので
				} else {
					return "4";// 4:上位お客様の下位お客様が複数あるの場合でも、お客様の削除が失敗し
				}
			} else {
				// 0:成功した、1:削除失敗した
				return ((customerInfoSearchService.deleteCustomerInfo(customerInfoMod.getCustomerNo())
						&& customerInfoSearchService.deleteCustomerDepartmentInfo(customerInfoMod.getCustomerNo())
						&& topCustomerInfoService.deleteTopCustomerInfo(topCustomerNo)) ? "0" : "1");
			}
		}
	}

	/**
	 * データの検索
	 * 
	 * @param customerInfoMod
	 * @return
	 */
	public ArrayList<CustomerInfoModel> SelectCustomerInfo(CustomerInfoModel customerInfoMod) {
		logger.info("CustomerInfoController.onloadPage:" + "検索開始");
		HashMap<String, String> sendMap = new HashMap<>();
		if (!UtilsCheckMethod.isNullOrEmpty(customerInfoMod.getCustomerNo())) {
			sendMap.put("customerNo", customerInfoMod.getCustomerNo());
		}
		if (!UtilsCheckMethod.isNullOrEmpty(customerInfoMod.getCustomerName())) {
			sendMap.put("customerName", customerInfoMod.getCustomerName());
		}
		if (!UtilsCheckMethod.isNullOrEmpty(customerInfoMod.getStationCode())) {
			sendMap.put("stationCode", customerInfoMod.getStationCode());
		}
		if (!UtilsCheckMethod.isNullOrEmpty(customerInfoMod.getPaymentsiteCode())) {
			sendMap.put("paymentsiteCode", customerInfoMod.getPaymentsiteCode());
		}
		if (!UtilsCheckMethod.isNullOrEmpty(customerInfoMod.getBusinessStartDate())) {
			sendMap.put("businessStartDate", customerInfoMod.getBusinessStartDate());
		}
		if (!UtilsCheckMethod.isNullOrEmpty(customerInfoMod.getLevelCode())) {
			sendMap.put("levelCode", customerInfoMod.getLevelCode());
		}
		if (!UtilsCheckMethod.isNullOrEmpty(customerInfoMod.getCompanyNatureCode())) {
			sendMap.put("companyNatureCode", customerInfoMod.getCompanyNatureCode());
		}
		if (!UtilsCheckMethod.isNullOrEmpty(customerInfoMod.getTopCustomerNo())) {
			sendMap.put("topCustomerNo", customerInfoMod.getTopCustomerNo());
		}
		if (!UtilsCheckMethod.isNullOrEmpty(customerInfoMod.getCapitalStockFront())) {
			sendMap.put("capitalStockFront", customerInfoMod.getCapitalStockFront());
		}
		if (!UtilsCheckMethod.isNullOrEmpty(customerInfoMod.getCapitalStockBack())) {
			sendMap.put("capitalStockBack", customerInfoMod.getCapitalStockBack());
		}
		logger.info("CustomerInfoController.onloadPage:" + "検索終了");
		return customerInfoSearchService.SelectCustomerInfo(sendMap);
	}
}
