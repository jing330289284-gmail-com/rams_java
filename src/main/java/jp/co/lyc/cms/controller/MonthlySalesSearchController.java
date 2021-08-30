package jp.co.lyc.cms.controller;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
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

import jp.co.lyc.cms.model.MonthlySalesSearchModel;
import jp.co.lyc.cms.service.MonthlySalesSearchService;
import jp.co.lyc.cms.util.UtilsCheckMethod;
import jp.co.lyc.cms.util.UtilsController;
import jp.co.lyc.cms.validation.MonthlySalesSearchValidation;

@Controller
@RequestMapping(value = "/monthlySales")
public class MonthlySalesSearchController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	String errorsMessage = "";
	@Autowired
	MonthlySalesSearchService MonthlySalesSearchService;

	@RequestMapping(value = "/searchMonthlySales", method = RequestMethod.POST)

	@ResponseBody
	public Map<String, Object> searchMonthlySales(@RequestBody MonthlySalesSearchModel monthlyInfo)
			throws IOException, ParseException {
		List<MonthlySalesSearchModel> MonthlySalesModelList = new ArrayList<MonthlySalesSearchModel>();
		logger.info("MonthlySalesSearchController.searchMonthlySales:" + "検索開始");
		errorsMessage = "";
		Date day = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyyMM");
		String sysTime = df.format(day);
		DataBinder binder = new DataBinder(monthlyInfo);
		binder.setValidator(new MonthlySalesSearchValidation());
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
			List<String> getYandM = new ArrayList<String>();
			String startYandM = monthlyInfo.getStartYandM();
			String endYandM = monthlyInfo.getEndYandM();
			if (monthlyInfo.getFiscalYear() == null || monthlyInfo.getFiscalYear().equals("")) {
				startYandM = monthlyInfo.getStartYandM();
				endYandM = monthlyInfo.getEndYandM();
			} else {
				startYandM = monthlyInfo.getFiscalYear() + "01";
				endYandM = monthlyInfo.getFiscalYear() + "12";
			}

			if (startYandM != "" && endYandM == "") {
				endYandM = sysTime;
			}
			if (startYandM == "" && endYandM != "") {

				startYandM = "201901";
			}

			if (startYandM != "0" && startYandM != null && endYandM != "0" && endYandM != null) {
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
			}

			if (UtilsCheckMethod.isNullOrEmpty(monthlyInfo.getEmployeeClassification())) {
				monthlyInfo.setEmployeeClassification("");
			}

			Map<String, Object> sendMap = getDetailParam(monthlyInfo);
			sendMap.put("getYandM", getYandM);

			MonthlySalesModelList = MonthlySalesSearchService.searchMonthlySales(sendMap);
			logger.info("MonthlySalesSearchController.searchMonthlySales:" + "検索結束");

			// 日割り計算
			for (int i = 0; i < MonthlySalesModelList.size(); i++) {
				// 日割り判断
				if (MonthlySalesModelList.get(i).getDailyCalculationStatus() != null
						&& MonthlySalesModelList.get(i).getDailyCalculationStatus().equals("0")) {
					// 入場月判断
					if (MonthlySalesModelList.get(i).getAdmissionStartDate() != null
							&& MonthlySalesModelList.get(i).getAdmissionStartDate().substring(0, 6)
									.equals(MonthlySalesModelList.get(i).getYearAndMonth())) {
						// 日割り計算
						SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
						String workMonth = MonthlySalesModelList.get(i).getAdmissionStartDate().substring(0, 6);
						Date startDate = sdf.parse(workMonth + "00");
						Date endDate = sdf.parse(workMonth + "31");
						Calendar calendarStart = Calendar.getInstance();
						Calendar calendarEnd = Calendar.getInstance();
						calendarStart.setTime(startDate);
						calendarEnd.setTime(endDate);
						int monthAlldays = countDays(calendarStart, calendarEnd);

						startDate = sdf.parse(String
								.valueOf(Integer.parseInt(MonthlySalesModelList.get(i).getAdmissionStartDate()) - 1));
						endDate = sdf.parse(workMonth + "31");
						calendarStart = Calendar.getInstance();
						calendarEnd = Calendar.getInstance();
						calendarStart.setTime(startDate);
						calendarEnd.setTime(endDate);
						int workdays = countDays(calendarStart, calendarEnd);
						double percent = (double) workdays / (double) monthAlldays;
						int unitprice = (int) (Double.parseDouble(MonthlySalesModelList.get(i).getUnitPrice())
								* percent);
						int cost = MonthlySalesModelList.get(i).getWaitingCost() == null
								|| MonthlySalesModelList.get(i).getWaitingCost().equals("")
										? (int) (Double.parseDouble(MonthlySalesModelList.get(i).getSalary()) * percent)
										: (int) (Double.parseDouble(MonthlySalesModelList.get(i).getWaitingCost())
												* percent);
						MonthlySalesModelList.get(i).setUnitPrice(String.valueOf(unitprice));
						if (MonthlySalesModelList.get(i).getWaitingCost() == null
								|| MonthlySalesModelList.get(i).getWaitingCost().equals("")) {
							MonthlySalesModelList.get(i).setSalary(String.valueOf(cost));
						} else {
							MonthlySalesModelList.get(i).setWaitingCost(String.valueOf(cost));
						}
					} else {
						MonthlySalesModelList.get(i).setDailyCalculationStatus("1");
					}
				}
			}

			for (int i = 0; i < MonthlySalesModelList.size(); i++) {
				if (UtilsCheckMethod.isNullOrEmpty(MonthlySalesModelList.get(i).getEmployeeName())) {
					MonthlySalesModelList.get(i).setEmployeeName("");
				}

				if (!monthlyInfo.getEmployeeClassification().equals("1")) {
					if (!UtilsCheckMethod.isNullOrEmpty(MonthlySalesModelList.get(i).getEmployeeNo())) {
						if (MonthlySalesModelList.get(i).getEmployeeNo().substring(0, 2).equals("BP")) {
							MonthlySalesModelList.get(i)
									.setEmployeeName(MonthlySalesModelList.get(i).getEmployeeName() + "(BP)");
						}
					}
				}
			}

			for (int i = 0; i < MonthlySalesModelList.size(); i++) {
				if (MonthlySalesModelList.get(i).getEmployeeName() == null
						|| MonthlySalesModelList.get(i).getEmployeeName().equals("")) {
					MonthlySalesModelList.remove(i);
					i--;
				}
			}

			if (monthlyInfo.getEmployeeClassification().equals("")
					|| monthlyInfo.getEmployeeClassification().equals("1")) {
				List<MonthlySalesSearchModel> bpMonthlySalesModelList = new ArrayList<MonthlySalesSearchModel>();
				bpMonthlySalesModelList = MonthlySalesSearchService.searchBpMonthlySales(sendMap);
				for (int i = 0; i < bpMonthlySalesModelList.size(); i++) {
					MonthlySalesModelList.add(bpMonthlySalesModelList.get(i));
				}
			}

			for (int i = 0; i < MonthlySalesModelList.size(); i++) {
				MonthlySalesModelList.get(i).setRowNo(String.valueOf(i + 1));
				int monthlyGrosProfits = Integer.parseInt(isNull(MonthlySalesModelList.get(i).getUnitPrice()))
						- Integer.parseInt(isNull(MonthlySalesModelList.get(i).getSalary()))
						- Integer.parseInt(isNull(MonthlySalesModelList.get(i).getOtherFee()))
						- Integer.parseInt(isNull(MonthlySalesModelList.get(i).getWaitingCost()));
				MonthlySalesModelList.get(i).setMonthlyGrosProfits(String.valueOf(monthlyGrosProfits));
			}

			if (MonthlySalesModelList.size() == 0) {
				String noData = "";
				noData = "条件に該当する結果が存在しない";
				resulterr.put("noData", noData);
				return resulterr;
			} else {
				Map<String, Object> resultdata = new HashMap<>();
				resultdata.put("data", MonthlySalesModelList);
				return resultdata;
			}
		}
	}

	public Map<String, Object> getDetailParam(MonthlySalesSearchModel monthlyInfo) {
		Map<String, Object> sendMap = new HashMap<String, Object>();
		String employeeClassification = monthlyInfo.getEmployeeClassification();
		String employeeForms = monthlyInfo.getEmployeeForms();
		String employeeOccupation = monthlyInfo.getEmployeeOccupation();
		String kadou = monthlyInfo.getKadou();
		String utilPricefront = monthlyInfo.getUtilPricefront();
		String utilPriceback = monthlyInfo.getUtilPriceback();
		String salaryfront = monthlyInfo.getSalaryfront();
		String salaryback = monthlyInfo.getSalaryback();
		String grossProfitFront = monthlyInfo.getGrossProfitFront();
		String grossProfitBack = monthlyInfo.getGrossProfitBack();
		String startYandM = monthlyInfo.getStartYandM();
		String endYandM = monthlyInfo.getEndYandM();
		if (employeeClassification != null && employeeClassification.length() != 0) {
			sendMap.put("employeeClassification", employeeClassification);
		}
		if (employeeForms != null && employeeForms.length() != 0) {
			sendMap.put("employeeForms", employeeForms);
		}
		if (employeeOccupation != null && employeeOccupation.length() != 0) {
			sendMap.put("employeeOccupation", employeeOccupation);
		}
		if (kadou != null && kadou.length() != 0) {
			sendMap.put("kadou", kadou);
		}
		if (utilPricefront != null && utilPricefront.length() != 0) {
			sendMap.put("utilPricefront", utilPricefront);
		}
		if (utilPriceback != null && utilPriceback.length() != 0) {
			sendMap.put("utilPriceback", utilPriceback);
		}
		if (salaryfront != null && salaryfront.length() != 0) {
			sendMap.put("salaryFront", salaryfront);
		}
		if (salaryback != null && salaryback.length() != 0) {
			sendMap.put("salaryBack", salaryback);
		}
		if (grossProfitFront != null && grossProfitFront.length() != 0) {
			sendMap.put("grossProfitFront", grossProfitFront);
		}
		if (grossProfitBack != null && grossProfitBack.length() != 0) {
			sendMap.put("grossProfitBack", grossProfitBack);
		}
		if (startYandM != null && startYandM.length() != 0) {
			sendMap.put("startYandM", startYandM);
		}
		if (endYandM != null && endYandM.length() != 0) {
			sendMap.put("endYandM", endYandM);
		}
		return sendMap;
	}

	public String isNull(String num) {
		if (num == null || num.equals(""))
			return "0";
		else
			return num;
	}

	/**
	 * 指定した2つの日付の間の営業日数をカウントします。 カウントを開始する日付当日は含まれません。 開始日付より終了日付が過去の場合は負数を返します。
	 * 
	 * @param from 開始日付
	 * @param to   終了日付
	 * @return 営業日数
	 */
	public int countDays(Calendar from, Calendar to) {
		int count = 0;
		Calendar cal1 = (Calendar) from.clone();
		Calendar cal2 = (Calendar) to.clone();
		int step = from.compareTo(to) <= 0 ? 1 : -1;

		if (isSameDate(cal1, cal2))
			return 0;

		do {
			cal1.add(Calendar.DAY_OF_YEAR, step);
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			String cal = formatter.format(cal1.getTime());
			if (!UtilsController.isHoliday(cal)) {
				count++;
			}
		} while (!isSameDate(cal1, cal2));

		return count * step;
	}

	private static boolean isSameDate(Calendar cal1, Calendar cal2) {
		return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR)
				&& cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH)
				&& cal1.get(Calendar.DAY_OF_MONTH) == cal2.get(Calendar.DAY_OF_MONTH);
	}
}
