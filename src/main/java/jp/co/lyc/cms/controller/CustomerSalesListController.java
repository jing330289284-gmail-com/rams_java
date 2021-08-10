package jp.co.lyc.cms.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import jp.co.lyc.cms.model.CustomerEmployeeDetail;
import jp.co.lyc.cms.model.CustomerSalesListModel;
import jp.co.lyc.cms.service.CustomerSalesListService;
import jp.co.lyc.cms.util.UtilsCheckMethod;

@Controller
@RequestMapping(value = "/customerSalesList")
public class CustomerSalesListController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	String errorsMessage = "";
	@Autowired
	CustomerSalesListService CustomerSalesListService;

	@RequestMapping(value = "/searchCustomerSalesList", method = RequestMethod.POST)

	@ResponseBody
	public Map<String, Object> searchCustomerSalesList(@RequestBody CustomerSalesListModel customerSalesListInfo)
			throws IOException, java.text.ParseException {
		List<CustomerSalesListModel> CustomerSalesListModel = new ArrayList<CustomerSalesListModel>();
		Map<String, Object> sendMap = getDetailParam(customerSalesListInfo);

		Calendar calendar = Calendar.getInstance();
		String date = customerSalesListInfo.getYearAndMonth();
		DateFormat format = new SimpleDateFormat("yyyyMM");
		Date lastMonth = format.parse(date);
		calendar.setTime(lastMonth);
		calendar.add(Calendar.MONTH, -1);
		Date lastYearMonth = calendar.getTime();
		String lastYearmonth = format.format(lastYearMonth);
		List<String> getYandM = new ArrayList<String>();
		getYandM.add(lastYearmonth);
		getYandM.add(customerSalesListInfo.getYearAndMonth());
		sendMap.put("getYandM", getYandM);
		logger.info("IndividualCustomerSalesController.searchCustomerSales:" + "検索開始");
		CustomerSalesListModel = CustomerSalesListService.searchCustomerSalesList(sendMap);
		logger.info("IndividualCustomerSalesController.searchCustomerSales:" + "検索結束");
		List<CustomerSalesListModel> lastMonthData = new ArrayList<CustomerSalesListModel>();
		List<CustomerSalesListModel> MonthData = new ArrayList<CustomerSalesListModel>();

		// 検索結果を整理する（検索月と検索月先月データ）
		for (int i = 0; i < CustomerSalesListModel.size(); i++) {
			if (CustomerSalesListModel.get(i).getYearAndMonth().equals(getYandM.get(0))) {
				lastMonthData.add(CustomerSalesListModel.get(i));
			}
			if (CustomerSalesListModel.get(i).getYearAndMonth().equals(getYandM.get(1))) {
				MonthData.add(CustomerSalesListModel.get(i));
			}
		}

		List<String> getCompanyNo = new ArrayList<String>();
		for (int j = 0; j < MonthData.size(); j++) {
			getCompanyNo.add(MonthData.get(j).getCustomerNo());
		}
		// 重複テータ削除する
		List uniqueGetCompanyNo = getCompanyNo.stream().distinct().collect(Collectors.toList());

		String averUnitPr;
		DecimalFormat decimalFormat = new DecimalFormat(".0");
		List<CustomerSalesListModel> resultData = new ArrayList<CustomerSalesListModel>();
		List<Map> tempList = new ArrayList<Map>();
		for (int k = 0; k < MonthData.size(); k++) {
			Map<String, Object> tempMap = new HashMap<String, Object>();
			tempMap.put("empNo", MonthData.get(k).getEmployeeNo());
			tempMap.put("yearAndMonth", MonthData.get(k).getYearAndMonth());
			tempMap.put("customerNo", MonthData.get(k).getCustomerNo());
			tempList.add(tempMap);
		}
		sendMap.put("tempList", tempList);
		List<CustomerSalesListModel> CustomerSalesListModelTwice = new ArrayList<CustomerSalesListModel>();
		logger.info("IndividualCustomerSalesController.CustomerSalesListModelTwice:" + "二回目検索開始");
		CustomerSalesListModelTwice = CustomerSalesListService.searchCustomerSalesListTwice(sendMap);
		logger.info("IndividualCustomerSalesController.CustomerSalesListModelTwice:" + "二回目検索結束");
		for (int k = 0; k < uniqueGetCompanyNo.size(); k++) {
			int totalUnitPrice = 0;
			float totalUnitPriceCal = 0;
			int countpeo = 0;
			float countpeoCal = 0;
			float averageUnitPrice;
			int DeductionsAndOvertimePay = 0;
			CustomerSalesListModel cusSalesListM = new CustomerSalesListModel();
			List<CustomerEmployeeDetail> customerEmpDetail = new ArrayList<CustomerEmployeeDetail>();
			for (int n = 0; n < MonthData.size(); n++) {
				CustomerEmployeeDetail customerEmpDe = new CustomerEmployeeDetail();
				if (uniqueGetCompanyNo.get(k).equals(MonthData.get(n).getCustomerNo())) {
					if (UtilsCheckMethod.isNullOrEmpty(MonthData.get(n).getDeductionsAndOvertimePay())) {
						MonthData.get(n).setDeductionsAndOvertimePay("0");
					}
					totalUnitPrice = totalUnitPrice + Integer.parseInt(MonthData.get(n).getUnitPrice());
					totalUnitPriceCal = totalUnitPriceCal + Integer.parseInt(MonthData.get(n).getUnitPrice());
					DeductionsAndOvertimePay = DeductionsAndOvertimePay
							+ Integer.parseInt(MonthData.get(n).getDeductionsAndOvertimePay());
					countpeo++;
					countpeoCal++;
					cusSalesListM.setEmployeeNo(MonthData.get(n).getEmployeeNo());
					String employeeName = MonthData.get(n).getEmployeeName();
					if (MonthData.get(n).getEmployeeNo().substring(0, 3).equals("BPR")) {
						employeeName = employeeName + "(" + MonthData.get(n).getEmployeeNo().substring(0, 3) + ")";
					} else if (MonthData.get(n).getEmployeeNo().substring(0, 2).equals("BP")
							|| MonthData.get(n).getEmployeeNo().substring(0, 2).equals("SC")
							|| MonthData.get(n).getEmployeeNo().substring(0, 2).equals("SP")) {
						employeeName = employeeName + "(" + MonthData.get(n).getEmployeeNo().substring(0, 2) + ")";
					}
					cusSalesListM.setCustomerName(MonthData.get(n).getCustomerName());
					cusSalesListM.setCustomerNo(MonthData.get(n).getCustomerNo());
					customerEmpDe.setEmployeeNo(MonthData.get(n).getEmployeeNo());
					customerEmpDe.setEmployeeName(employeeName);
					customerEmpDe.setSiteRoleName(MonthData.get(n).getSiteRoleName());
					customerEmpDe.setStationName(MonthData.get(n).getStationName());
					double unitPrice = Double.parseDouble(MonthData.get(n).getUnitPrice()) / 10000.0;
					DecimalFormat up = new DecimalFormat("#.#");
					customerEmpDe.setUnitPrice(up.format(unitPrice));
					if (MonthData.get(n).getEmployeeNo().substring(0, 2).equals("BP")) {
						for (int i = 0; i < CustomerSalesListModel.size(); i++) {
							if (CustomerSalesListModel.get(i).getEmployeeNo()
									.equals(MonthData.get(n).getEmployeeNo())) {
								customerEmpDe.setCost(CustomerSalesListModel.get(i).getBpUnitPrice());
							}
						}
					} else {
						for (int i = 0; i < CustomerSalesListModel.size(); i++) {
							if (CustomerSalesListModel.get(i).getEmployeeNo()
									.equals(MonthData.get(n).getEmployeeNo())) {
								double totalCost = 0;
								for (int j = 0; j < CustomerSalesListModelTwice.size(); j++) {
									if (CustomerSalesListModel.get(i).getCustomerNo()
											.equals(CustomerSalesListModelTwice.get(j).getCustomerNo())) {
										if (CustomerSalesListModel.get(i).getEmployeeNo()
												.equals(CustomerSalesListModelTwice.get(j).getEmployeeNo())) {
											totalCost += Double
													.parseDouble(CustomerSalesListModelTwice.get(j).getTotalAmount());
										}
									}
								}
								totalCost = totalCost / 10000.0;
								DecimalFormat cost = new DecimalFormat("#.#");
								customerEmpDe.setCost(cost.format(totalCost));
							}
						}
					}
					customerEmpDetail.add(customerEmpDe);
				}
			}
			if (DeductionsAndOvertimePay < 0) {
				cusSalesListM.setExpectFee(String.valueOf(DeductionsAndOvertimePay));
			}
			if (DeductionsAndOvertimePay > 0) {
				cusSalesListM.setOverTimeFee(String.valueOf(DeductionsAndOvertimePay));
			}
			List<CustomerEmployeeDetail> customerEmpDetailTemp = new ArrayList<CustomerEmployeeDetail>();
			for (int i = 0; i < customerEmpDetail.size(); i++) {
				if (!customerEmpDetail.get(i).getEmployeeNo().substring(0, 2).equals("BP")) {
					customerEmpDetailTemp.add(customerEmpDetail.get(i));
				}
			}
			for (int i = 0; i < customerEmpDetail.size(); i++) {
				if (customerEmpDetail.get(i).getEmployeeNo().substring(0, 2).equals("BP")) {
					customerEmpDetailTemp.add(customerEmpDetail.get(i));
				}
			}
			cusSalesListM.setEmpDetail(customerEmpDetailTemp);
			cusSalesListM.setTotalUnitPrice(String.valueOf(totalUnitPrice));
			cusSalesListM.setCountPeo(countpeo);
			averageUnitPrice = totalUnitPriceCal / countpeoCal;
			averUnitPr = decimalFormat.format(averageUnitPrice);
			cusSalesListM.setAverUnitPrice(averUnitPr);
			resultData.add(cusSalesListM);
		}
		int rowNo = 0;
		DecimalFormat df = new DecimalFormat("000");
		for (int j = 0; j < resultData.size(); j++) {
			rowNo++;
			resultData.get(j).setRowNo(df.format(rowNo));
			int lastmonthPeoCal = 0;
			for (int k = 0; k < lastMonthData.size(); k++) {
				if (resultData.get(j).getCustomerNo().equals(lastMonthData.get(k).getCustomerNo())) {
					lastmonthPeoCal++;
				}
			}
			resultData.get(j).setLastMonthCountPeo(lastmonthPeoCal);
		}

		for (int k = 0; k < uniqueGetCompanyNo.size(); k++) {
			int totalAmount = 0;
			for (int s = 0; s < CustomerSalesListModelTwice.size(); s++) {
				if (UtilsCheckMethod.isNullOrEmpty(CustomerSalesListModelTwice.get(s).getTotalAmount())) {
					CustomerSalesListModelTwice.get(s).setTotalAmount("0");
				}
				if (uniqueGetCompanyNo.get(k).equals(CustomerSalesListModelTwice.get(s).getCustomerNo())) {
					totalAmount += Integer.parseInt(CustomerSalesListModelTwice.get(s).getTotalAmount());
				}
				/*
				 * for (int i = 0; i < CustomerSalesListModel.size(); i++) { if
				 * (uniqueGetCompanyNo.get(k).equals(CustomerSalesListModel.get(i).getCustomerNo
				 * ())) { if
				 * (uniqueGetCompanyNo.get(k).equals(CustomerSalesListModelTwice.get(s).
				 * getCustomerNo())) { totalAmount +=
				 * (CustomerSalesListModel.get(i).getBpUnitPrice() == null ||
				 * CustomerSalesListModel.get(i).getBpUnitPrice().equals("") ? 0 :
				 * Integer.parseInt(CustomerSalesListModel.get(i).getBpUnitPrice()) * 10000); }
				 * } }
				 */
				for (int x = 0; x < resultData.size(); x++) {
					if (resultData.get(x).getCustomerNo().equals(uniqueGetCompanyNo.get(k))) {
						resultData.get(x).setTotalAmount(String.valueOf(totalAmount));
					}
				}
			}
		}
		List<CustomerSalesListModel> CustomerSalesListModelThird = new ArrayList<CustomerSalesListModel>();
		logger.info("IndividualCustomerSalesController.CustomerSalesListModelThird:" + "三回目検索開始");
		CustomerSalesListModelThird = CustomerSalesListService.searchCustomerSalesListThird(sendMap);
		logger.info("IndividualCustomerSalesController.CustomerSalesListModelThird:" + "三回目検索結束");

		for (int k = 0; k < uniqueGetCompanyNo.size(); k++) {
			int totalAmount = 0;
			for (int s = 0; s < CustomerSalesListModelThird.size(); s++) {
				if (UtilsCheckMethod.isNullOrEmpty(CustomerSalesListModelThird.get(s).getTotalAmount())) {
					CustomerSalesListModelThird.get(s).setTotalAmount("0");
				}
				if (uniqueGetCompanyNo.get(k).equals(CustomerSalesListModelThird.get(s).getCustomerNo())) {
					totalAmount = totalAmount + Integer.parseInt(CustomerSalesListModelThird.get(s).getTotalAmount());
				}
				for (int x = 0; x < resultData.size(); x++) {
					if (resultData.get(x).getCustomerNo().equals(uniqueGetCompanyNo.get(k))) {
						resultData.get(x).setTotalAmount(
								String.valueOf(Integer.parseInt(resultData.get(x).getTotalAmount()) + totalAmount));
					}
					String grossProfit = String.valueOf(Integer.parseInt(resultData.get(x).getTotalUnitPrice())
							- Integer.parseInt(resultData.get(x).getTotalAmount()));
					resultData.get(x).setGrossProfit(grossProfit);
				}
			}
		}
		int calPeoCount = 0;
		int unitPTotal = 0;
		int totalOverTimeFee = 0;
		int totalExpectFee = 0;
		int totalSales = 0;
		int totalgrossProfit = 0;
		for (int a = 0; a < resultData.size(); a++) {
			if (UtilsCheckMethod.isNullOrEmpty(resultData.get(a).getOverTimeFee())) {
				resultData.get(a).setOverTimeFee("0");
			}
			if (UtilsCheckMethod.isNullOrEmpty(resultData.get(a).getExpectFee())) {
				resultData.get(a).setExpectFee("0");
			}
			if (UtilsCheckMethod.isNullOrEmpty(resultData.get(a).getGrossProfit())) {
				resultData.get(a).setGrossProfit("0");
			}
			calPeoCount = calPeoCount + resultData.get(a).getCountPeo();
			unitPTotal = unitPTotal + Integer.parseInt(resultData.get(a).getTotalUnitPrice());
			totalOverTimeFee = totalOverTimeFee + Integer.parseInt(resultData.get(a).getOverTimeFee());
			totalExpectFee = totalExpectFee + Integer.parseInt(resultData.get(a).getExpectFee());
			totalgrossProfit = totalgrossProfit + Integer.parseInt(resultData.get(a).getGrossProfit());

		}
		totalSales = unitPTotal + (totalOverTimeFee + totalExpectFee);
		resultData.get(0).setCalPeoCount(calPeoCount);
		resultData.get(0).setUnitPTotal(unitPTotal);
		resultData.get(0).setTotalgrossProfit(totalgrossProfit);
		resultData.get(0).setTotalSales(totalSales);
		for (int b = 0; b < resultData.size(); b++) {
			resultData.get(b).setTotalSales(totalSales);
		}

		Map<String, Object> resultdata = new HashMap<>();
		resultdata.put("data", resultData);
		return resultdata;
	}

	public Map<String, Object> getDetailParam(CustomerSalesListModel customerSalesListInfo) {
		Map<String, Object> sendMap = new HashMap<String, Object>();
		String yearAndMonth = customerSalesListInfo.getYearAndMonth();

		if (yearAndMonth != null && yearAndMonth.length() != 0) {
			sendMap.put("yearAndMonth", yearAndMonth);
		}
		return sendMap;
	}

}
