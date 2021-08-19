package jp.co.lyc.cms.controller;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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

import jp.co.lyc.cms.model.CustomerEmployeeDetail;
import jp.co.lyc.cms.model.IndividualCustomerSalesModel;
import jp.co.lyc.cms.service.IndividualCustomerSalesService;
import jp.co.lyc.cms.util.UtilsCheckMethod;
import jp.co.lyc.cms.validation.IndividualCustomerSalesValidation;

@Controller
@RequestMapping(value = "/customerSales")
public class IndividualCustomerSalesController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	String errorsMessage = "";
	@Autowired
	IndividualCustomerSalesService individualCustomerSalesService;

	@RequestMapping(value = "/searchCustomerSales", method = RequestMethod.POST)

	@ResponseBody
	public Map<String, Object> searchCustomerSales(@RequestBody IndividualCustomerSalesModel customerSalesInfo)
			throws IOException {
		errorsMessage = "";
		DataBinder binder = new DataBinder(customerSalesInfo);
		binder.setValidator(new IndividualCustomerSalesValidation());
		binder.validate();
		BindingResult results = binder.getBindingResult();
		Map<String, Object> resulterr = new HashMap<>();
		if (results.hasErrors()) {
			results.getAllErrors().forEach(o -> {
				FieldError error = (FieldError) o;
				errorsMessage += error.getDefaultMessage();// エラーメッセージ
			});

			resulterr.put("errorsMessage", errorsMessage);// エラーメッセージ
			return resulterr;
		} else {
			List<IndividualCustomerSalesModel> CustomerSalesModelList = new ArrayList<IndividualCustomerSalesModel>();
			Date day = new Date();
			SimpleDateFormat df = new SimpleDateFormat("yyyyMM");
			String sysTime = df.format(day);
			Map<String, Object> sendMap = getDetailParam(customerSalesInfo);
			String startYandM = customerSalesInfo.getStartYear();
			String endYandM = customerSalesInfo.getEndYear();
			String fiscalYear = customerSalesInfo.getFiscalYear();
			List<String> getYandM = new ArrayList<String>();
			if (startYandM != "" && endYandM == "") {
				customerSalesInfo.setEndYear(sysTime);
				endYandM = customerSalesInfo.getEndYear();
			}
			if (startYandM == "" && endYandM != "") {
				customerSalesInfo.setStartYear("201901");
				startYandM = customerSalesInfo.getStartYear();
			}
			if (startYandM != "0" && startYandM != null && endYandM != "0" && endYandM != null && fiscalYear == "") {
				int startY = Integer.parseInt(startYandM.substring(0, 4));
				int startM = Integer.parseInt(startYandM.substring(4, 6));
				int endY = Integer.parseInt(endYandM.substring(0, 4));
				int endM = Integer.parseInt(endYandM.substring(4, 6));
				int count = 0;
				for (int y = startY; y <= endY; y++) {
					if (y == startY && y == endY) {
						for (int m = startM; m <= endM; m++) {
							String monthStr = Integer.toString(m);
							if (m < 10) {
								monthStr = "0" + Integer.toString(m);
							}
							getYandM.add(count, Integer.toString(startY) + monthStr);
							count++;
						}
					} else if (y == startY) {
						for (int m = startM; m <= 12; m++) {

							String monthStr = Integer.toString(m);
							if (m < 10) {
								monthStr = "0" + Integer.toString(m);
							}
							getYandM.add(count, Integer.toString(startY) + monthStr);
							count++;
						}
					} else if (y != startY && y != endY) {
						for (int m = 1; m <= 12; m++) {

							String monthStr = Integer.toString(m);
							if (m < 10) {
								monthStr = "0" + Integer.toString(m);
							}
							getYandM.add(count, Integer.toString(y) + monthStr);
							count++;
						}
					} else if (y == endY) {
						for (int m = 1; m <= endM; m++) {

							String monthStr = Integer.toString(m);
							if (m < 10) {
								monthStr = "0" + Integer.toString(m);
							}
							getYandM.add(count, Integer.toString(endY) + monthStr);
							count++;
						}
					}

				}
			} else {
				int count = 0;
				for (int m = 1; m <= 12; m++) {
					String monthStr = Integer.toString(m);
					if (m < 10) {
						monthStr = "0" + Integer.toString(m);
					}
					getYandM.add(count, fiscalYear + monthStr);
					count++;
				}
			}
			sendMap.put("getYandM", getYandM);
			logger.info("IndividualCustomerSalesController.searchCustomerSales:" + "検索開始");
			errorsMessage = "";
			CustomerSalesModelList = individualCustomerSalesService.searchCustomerSales(sendMap);
			logger.info("IndividualCustomerSalesController.searchCustomerSales:" + "検索結束");
			if (CustomerSalesModelList.size() == 0) {
				String noData = "";
				noData = "条件に該当する結果が存在しない";
				resulterr.put("noData", noData);
				return resulterr;
			} else {
				Map<String, Object> resultdata = new HashMap<>();
				List<IndividualCustomerSalesModel> CustomerSalesModelListTwice = new ArrayList<IndividualCustomerSalesModel>();
				List<Map> tempList = new ArrayList<Map>();
				for (int k = 0; k < CustomerSalesModelList.size(); k++) {
					Map<String, String> tempMap = new HashMap<String, String>();
					tempMap.put("empNo", CustomerSalesModelList.get(k).getEmployeeNo());
					tempMap.put("yearAndMonth", CustomerSalesModelList.get(k).getYearAndMonth());
					tempList.add(tempMap);
				}
				sendMap.put("tempList", tempList);
				logger.info("IndividualCustomerSalesController.CustomerSalesModelListTwice:" + "二回目検索開始");
				CustomerSalesModelListTwice = individualCustomerSalesService.searchCustomerSalestwice(sendMap);
				logger.info("IndividualCustomerSalesController.CustomerSalesModelListTwice:" + "二回目検索結束");
				List<IndividualCustomerSalesModel> CustomerSalesModelListThird = new ArrayList<IndividualCustomerSalesModel>();
				logger.info("IndividualCustomerSalesController.CustomerSalesModelListThird:" + "三回目検索開始");
				CustomerSalesModelListThird = individualCustomerSalesService.searchCustomerSalesthird(sendMap);
				logger.info("IndividualCustomerSalesController.CustomerSalesModelListThird:" + "三回目検索結束");

				List<IndividualCustomerSalesModel> cusModelLi = new ArrayList<IndividualCustomerSalesModel>();
				for (int i = 0; i < getYandM.size(); i++) {
					IndividualCustomerSalesModel indiviCusModel = new IndividualCustomerSalesModel();
					float calUnitPrice = 0;
					int calculateUintP = 0;
					float count = 0;
					int countNum = 0;
					int peoNum = 0;
					int overTimeFee = 0;

					for (int j = 0; j < CustomerSalesModelList.size(); j++) {
						if (getYandM.get(i).equals(CustomerSalesModelList.get(j).getYearAndMonth())) {
							if (UtilsCheckMethod
									.isNullOrEmpty(CustomerSalesModelList.get(j).getDeductionsAndOvertimePay())) {
								CustomerSalesModelList.get(j).setDeductionsAndOvertimePay("0");
							}
							calUnitPrice = calculateUintP
									+ Integer.parseInt(CustomerSalesModelList.get(j).getUnitPrice());
							calculateUintP = calculateUintP
									+ Integer.parseInt(CustomerSalesModelList.get(j).getUnitPrice());
							overTimeFee = overTimeFee
									+ Integer.parseInt(CustomerSalesModelList.get(j).getDeductionsAndOvertimePay());
							count++;
							countNum++;
							peoNum++;
							indiviCusModel.setMaxUnitPrice(CustomerSalesModelList.get(j).getMaxUnitPrice());
							indiviCusModel.setMinUnitPrice(CustomerSalesModelList.get(j).getMinUnitPrice());
						}
					}
					float averUnitPrice;
					String averUnitPr;
					DecimalFormat decimalFormat = new DecimalFormat(".0");
					if (calculateUintP == 0 || countNum == 0) {
						averUnitPr = "0";
					} else {
						averUnitPrice = calUnitPrice / count;
						averUnitPr = decimalFormat.format(averUnitPrice);
					}
					indiviCusModel.setYearAndMonth(getYandM.get(i));
					indiviCusModel.setAverUnitPrice(averUnitPr);
					indiviCusModel.setTotalUnitPrice(String.valueOf(calculateUintP));
					indiviCusModel.setWorkPeoSum(String.valueOf(peoNum));

					if (overTimeFee < 0) {
						indiviCusModel.setExpectFee(Integer.toString(overTimeFee));
					}
					if (overTimeFee > 0) {
						indiviCusModel.setOverTimeFee(Integer.toString(overTimeFee));
					}
					cusModelLi.add(indiviCusModel);
				}
				if (CustomerSalesModelListThird.size() > 0) {
					for (int n = 0; n < CustomerSalesModelListThird.size(); n++) {
						int totalAm = 0;
						for (int m = 0; m < CustomerSalesModelListTwice.size(); m++) {
							if (CustomerSalesModelListThird.get(n).getEmployeeNo()
									.equals(CustomerSalesModelListTwice.get(m).getEmployeeNo())) {
								if (CustomerSalesModelListThird.get(n).getYearAndMonth()
										.equals(CustomerSalesModelListTwice.get(m).getYearAndMonth())) {
									if (CustomerSalesModelListThird.get(n).getTotalExpenses() == null
											|| CustomerSalesModelListThird.get(n).getTotalExpenses().equals("")) {
										CustomerSalesModelListThird.get(n).setTotalExpenses("0");
									}
									totalAm = Integer.parseInt(CustomerSalesModelListTwice.get(m).getTotalAmount())
											+ Integer.parseInt(CustomerSalesModelListThird.get(n).getTotalExpenses());
									CustomerSalesModelListTwice.get(m).setTotalAmount(String.valueOf(totalAm));
								}
							}
						}
					}
				}
				for (int p = 0; p < getYandM.size(); p++) {
					int costTotal = 0;
					for (int m = 0; m < CustomerSalesModelListTwice.size(); m++) {
						if (UtilsCheckMethod.isNullOrEmpty(CustomerSalesModelListTwice.get(m).getTotalAmount())) {
							CustomerSalesModelList.get(m).setTotalAmount("0");
						}
						if (UtilsCheckMethod.isNullOrEmpty(CustomerSalesModelListTwice.get(m).getTotalExpenses())) {
							CustomerSalesModelList.get(m).setTotalExpenses("0");
						}
						if (CustomerSalesModelListTwice.get(m).getYearAndMonth().equals(getYandM.get(p))) {
							costTotal = costTotal
									+ Integer.parseInt(CustomerSalesModelListTwice.get(m).getTotalAmount());
						}
					}
					for (int m = 0; m < CustomerSalesModelList.size(); m++) {
						if (CustomerSalesModelList.get(m).getBpUnitPrice() != null
								&& !CustomerSalesModelList.get(m).getBpUnitPrice().equals("")) {
							if (CustomerSalesModelList.get(m).getYearAndMonth().equals(getYandM.get(p))) {
								costTotal = costTotal
										+ Integer.parseInt(CustomerSalesModelList.get(m).getBpUnitPrice()) * 10000;
							}
						}
					}
					for (int s = 0; s < cusModelLi.size(); s++) {
						if (cusModelLi.get(s).getYearAndMonth().equals(getYandM.get(p))) {
							cusModelLi.get(s).setTotalAmount(String.valueOf(costTotal));
						}
					}
					cusModelLi.get(p)
							.setGrossProfit(String.valueOf(Integer.parseInt(cusModelLi.get(p).getTotalUnitPrice())
									- Integer.parseInt(cusModelLi.get(p).getTotalAmount())));
				}
				for (int d = cusModelLi.size() - 1; d >= 0; d--) {
					if (cusModelLi.get(d).getTotalUnitPrice().equals("0")
							|| cusModelLi.get(d).getTotalUnitPrice() == null) {
						cusModelLi.remove(d);
					}
				}

				for (int a = 0; a < getYandM.size(); a++) {
					List<CustomerEmployeeDetail> customerEmpDetail = new ArrayList<CustomerEmployeeDetail>();
					for (int b = 0; b < CustomerSalesModelList.size(); b++) {
						CustomerEmployeeDetail customerEmpDe = new CustomerEmployeeDetail();
						if (CustomerSalesModelList.get(b).getYearAndMonth().equals(getYandM.get(a))) {
							customerEmpDe.setEmployeeNo(CustomerSalesModelList.get(b).getEmployeeNo());
							String employeeName = CustomerSalesModelList.get(b).getEmployeeName();
							if (CustomerSalesModelList.get(b).getEmployeeNo().substring(0, 3).equals("BPR")) {
								employeeName = employeeName + "("
										+ CustomerSalesModelList.get(b).getEmployeeNo().substring(0, 3) + ")";
							} else if (CustomerSalesModelList.get(b).getEmployeeNo().substring(0, 2).equals("BP")
									|| CustomerSalesModelList.get(b).getEmployeeNo().substring(0, 2).equals("SC")
									|| CustomerSalesModelList.get(b).getEmployeeNo().substring(0, 2).equals("SP")) {
								employeeName = employeeName + "("
										+ CustomerSalesModelList.get(b).getEmployeeNo().substring(0, 2) + ")";
							}
							customerEmpDe.setEmployeeName(employeeName);
							customerEmpDe.setSiteRoleName(CustomerSalesModelList.get(b).getSiteRoleName());
							customerEmpDe.setStationName(CustomerSalesModelList.get(b).getStationName());
							double unitPrice = Double.parseDouble(CustomerSalesModelList.get(b).getUnitPrice())
									/ 10000.0;
							DecimalFormat up = new DecimalFormat("#.#");
							customerEmpDe.setUnitPrice(up.format(unitPrice));
							if (CustomerSalesModelList.get(b).getBpUnitPrice() != null
									&& !CustomerSalesModelList.get(b).getBpUnitPrice().equals("")) {
								customerEmpDe.setCost(String
										.valueOf(Integer.parseInt(CustomerSalesModelList.get(b).getBpUnitPrice())));
							}
							for (int i = 0; i < CustomerSalesModelListTwice.size(); i++) {
								if (CustomerSalesModelListTwice.get(i).getEmployeeNo()
										.equals(CustomerSalesModelList.get(b).getEmployeeNo())) {
									if (CustomerSalesModelListTwice.get(i).getYearAndMonth().equals(getYandM.get(a))) {
										if (CustomerSalesModelListTwice.get(i).getTotalAmount() != null
												|| !CustomerSalesModelListTwice.get(i).getTotalAmount().equals(""))
											if (CustomerSalesModelListTwice.get(i).getTotalAmount().length() >= 2) {
												double cost = Double.parseDouble(
														CustomerSalesModelListTwice.get(i).getTotalAmount()) / 10000.0;
												DecimalFormat costFormat = new DecimalFormat("#.#");
												customerEmpDe.setCost(costFormat.format(cost));
											} else {
												customerEmpDe
														.setCost(CustomerSalesModelListTwice.get(i).getTotalAmount());
											}
									}
								}
							}
							customerEmpDetail.add(customerEmpDe);
						}
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
					for (int c = 0; c < cusModelLi.size(); c++) {
						if (cusModelLi.get(c).getYearAndMonth().equals((getYandM.get(a)))) {
							cusModelLi.get(c).setEmpDetail(customerEmpDetailTemp);
						}
					}

				}

				int totalworkPeoSum = 0;
				int totaluPrice = 0;
				int overTimeOrExpectFee = 0;
				int totalgrossProfit = 0;
				for (int c = 0; c < cusModelLi.size(); c++) {
					if (cusModelLi.get(c).getOverTimeFee() == null || cusModelLi.get(c).getOverTimeFee().equals("")) {
						cusModelLi.get(c).setOverTimeFee("0");
					}
					if (cusModelLi.get(c).getExpectFee() == null || cusModelLi.get(c).getExpectFee().equals("")) {
						cusModelLi.get(c).setExpectFee("0");
					}
					totalworkPeoSum = totalworkPeoSum + Integer.parseInt(cusModelLi.get(c).getWorkPeoSum());
					totaluPrice = totaluPrice + Integer.parseInt(cusModelLi.get(c).getTotalUnitPrice());
					overTimeOrExpectFee = overTimeOrExpectFee + Integer.parseInt(cusModelLi.get(c).getTotalUnitPrice())
							+ Integer.parseInt(cusModelLi.get(c).getOverTimeFee())
							- Integer.parseInt(cusModelLi.get(c).getExpectFee());
					totalgrossProfit = totalgrossProfit + Integer.parseInt(cusModelLi.get(c).getGrossProfit());
				}
				if (cusModelLi.size() > 0) {
					cusModelLi.get(0).setTotalworkPeoSum(totalworkPeoSum);
					cusModelLi.get(0).setTotaluPrice(totaluPrice);
					cusModelLi.get(0).setOverTimeOrExpectFee(overTimeOrExpectFee);
					cusModelLi.get(0).setTotalgrossProfit(totalgrossProfit);
				}

				List<String> bpNoList = new ArrayList<String>();
				for (int i = 0; i < cusModelLi.size(); i++) {
					bpNoList.add(cusModelLi.get(i).getEmployeeNo());
				}

				resultdata.put("data", cusModelLi);
				return resultdata;
			}
		}
	}

	public Map<String, Object> getDetailParam(IndividualCustomerSalesModel customerSalesInfo) {
		Map<String, Object> sendMap = new HashMap<String, Object>();
		String customerName = customerSalesInfo.getCustomerName();
		String fiscalYear = customerSalesInfo.getFiscalYear();
		String startYear = customerSalesInfo.getStartYear();
		String endYear = customerSalesInfo.getEndYear();
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		String yAndM = year + "" + month;
		if (customerName != null && customerName.length() != 0) {
			sendMap.put("customerName", customerName);
		}
		if (fiscalYear != null && fiscalYear.length() != 0) {
			sendMap.put("fiscalYear", fiscalYear);
		}
		if (startYear != null && startYear.length() != 0) {
			sendMap.put("startYear", startYear);
		}
		if (endYear != null && endYear.length() != 0) {
			sendMap.put("endYear", endYear);
		}
		if (startYear == null || startYear == "") {
			sendMap.put("startYear", "201901");
		}

		if (endYear == null || endYear == "") {
			sendMap.put("endYear", yAndM);
		}

		return sendMap;
	}
}
