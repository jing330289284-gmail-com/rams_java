package jp.co.lyc.cms.controller;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import jp.co.lyc.cms.common.BaseController;
import jp.co.lyc.cms.model.PersonalSalesSearchModel;
import jp.co.lyc.cms.model.SalesInfoModel;
import jp.co.lyc.cms.model.SalesPointSetModel;
import jp.co.lyc.cms.model.SalesProfitModel;
import jp.co.lyc.cms.service.SalesProfitService;

import org.joda.time.DateTime;
import org.joda.time.Months;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

@Controller
@CrossOrigin(origins = "http://127.0.0.1:3000")
public class SalesProfitController extends BaseController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	SalesProfitService salesProfitService;

	String errorsMessage = "";

	@RequestMapping(value = "/getPointInfo", method = RequestMethod.POST)
	@ResponseBody
	public List<SalesProfitModel> getPointInfo(@RequestBody SalesProfitModel salesProfitModel) {

		List<SalesProfitModel> siteList = salesProfitService.getPointInfo(salesProfitModel);

		// 取值之后的操作

		logger.info("SalesProfitController.getSalesPointInfo:" + "検索結束");
		return siteList;
	}

	@RequestMapping(value = "/getSalesInfo", method = RequestMethod.POST)
	@ResponseBody
	public List<SalesInfoModel> getSalesInfo(@RequestBody SalesProfitModel salesProfitModel) {

		if (salesProfitModel.getEmployeeName() != null && salesProfitModel.getEmployeeName().equals(""))
			salesProfitModel.setEmployeeName(null);
		if (salesProfitModel.getEmployeeStatus() != null && salesProfitModel.getEmployeeStatus().equals(""))
			salesProfitModel.setEmployeeStatus(null);

		List<SalesInfoModel> employeeSales = salesProfitService.getEmployeeNoSalary();
		List<SalesInfoModel> customerName = salesProfitService.getCustomerName();
		List<SalesInfoModel> employeeNameToNo = salesProfitService.getEmployeeName();

		if (salesProfitModel.getEmployeeName() != null) {
			for (int i = 0; i < employeeNameToNo.size(); i++) {
				if (salesProfitModel.getEmployeeName().equals(employeeNameToNo.get(i).getEmployeeFristName()
						+ employeeNameToNo.get(i).getEmployeeLastName())) {
					salesProfitModel.setEmployeeName(employeeNameToNo.get(i).getEmployeeNo());
					break;
				}
			}
		}

		List<SalesInfoModel> siteList = salesProfitService.getSalesInfo(salesProfitModel);
		logger.info("SalesProfitController.getSalesPointInfo:" + "検索結束");
		if (siteList.size() > 0) {
			int siteRoleNameAll = 0;
			int profitAll = 0;
			for (int i = 0; i < siteList.size(); i++) {
				String yearAndMonth = siteList.get(i).getAdmissionStartDate().substring(0, 6);
				/*
				 * if (i > 0) { String yearAndMonthTemp = yearAndMonth.substring(0, 4) + "/" +
				 * yearAndMonth.substring(4, 6); if (yearAndMonthTemp.equals(siteList.get(i -
				 * 1).getYearAndMonth())) { yearAndMonth = ""; } else { yearAndMonth =
				 * yearAndMonthTemp; } } else { yearAndMonth = yearAndMonth.substring(0, 4) +
				 * "/" + yearAndMonth.substring(4, 6); }
				 */
				yearAndMonth = yearAndMonth.substring(0, 4) + "/" + yearAndMonth.substring(4, 6);
				siteList.get(i).setYearAndMonth(yearAndMonth);
				siteList.get(i).setCustomerName(
						siteList.get(i).getCustomerAbbreviation() == null ? siteList.get(i).getCustomerName()
								: siteList.get(i).getCustomerAbbreviation());
				String employeeName = (siteList.get(i).getEmployeeFristName() == null ? ""
						: siteList.get(i).getEmployeeFristName())
						+ (siteList.get(i).getEmployeeLastName() == null ? "" : siteList.get(i).getEmployeeLastName());
				siteList.get(i).setEmployeeName(employeeName);

				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMM");
				String startTime = dateFormat.format(salesProfitModel.getStartDate()).toString();
				String endTime = dateFormat.format(salesProfitModel.getEndDate()).toString();
				String admissionStartDate = siteList.get(i).getAdmissionStartDate().substring(0, 6);
				String admissionEndDate = "";
				if (siteList.get(i).getAdmissionEndDate() != null)
					admissionEndDate = siteList.get(i).getAdmissionEndDate().substring(0, 6);
				String workDateStart = admissionStartDate;
				String workDateEnd = admissionEndDate;
				if (Integer.parseInt(startTime) > Integer.parseInt(admissionStartDate)) {
					workDateStart = startTime;
				}

				if (workDateEnd.equals(""))
					siteList.get(i)
							.setWorkDate(workDateStart.substring(0, 4) + "/" + workDateStart.substring(4, 6) + " ~ ");
				else
					siteList.get(i).setWorkDate(workDateStart.substring(0, 4) + "/" + workDateStart.substring(4, 6)
							+ " ~ " + workDateEnd.substring(0, 4) + "/" + workDateEnd.substring(4, 6));

				if (workDateEnd.equals(""))
					workDateEnd = endTime;
				DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM");
				int months = Months.monthsBetween(
						formatter.parseDateTime(workDateStart.substring(0, 4) + "-" + workDateStart.substring(4, 6)),
						formatter.parseDateTime(workDateEnd.substring(0, 4) + "-" + workDateEnd.substring(4, 6)))
						.getMonths() + 1;
				siteList.get(i)
						.setProfit(Integer.toString(Integer.parseInt(siteList.get(i).getUnitPrice()) * 10000 * months));
				profitAll += Integer.parseInt(siteList.get(i).getUnitPrice()) * months * 10000;
				String startYandM = workDateStart;
				String endYandM = workDateEnd;
				List<String> getYandM = new ArrayList<String>();
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
				int salary = 0;
				for (int z = 0; z < getYandM.size(); z++) {
					String employeeNo = siteList.get(i).getEmployeeNo();
					String yearMonth = getYandM.get(z);
					int salaryTemp = 0;

					for (int x = 0; x < employeeSales.size(); x++) {
						if (employeeNo.equals(employeeSales.get(x).getEmployeeNo())) {
							if (Integer.parseInt(employeeSales.get(x).getReflectYearAndMonth()) <= Integer
									.parseInt(yearMonth)) {
								if (!employeeSales.get(x).getSalary().equals(""))
									salaryTemp = Integer.parseInt(employeeSales.get(x).getSalary());
							}
						}

					}
					salary += salaryTemp;
				}
				siteList.get(i).setSalary(Integer.toString(salary));
				siteList.get(i).setRowNo(Integer.toString(i + 1));
				String employeeStatus = "";
				int bpSalary = 0;
				if (!(siteList.get(i).getEmployeeStatus() == null)) {
					if (siteList.get(i).getEmployeeStatus().equals("0")) {
						employeeStatus = "社員";
						siteRoleNameAll += Integer.parseInt(siteList.get(i).getProfit()) - salary;
						siteList.get(i).setSiteRoleName(
								Integer.toString(Integer.parseInt(siteList.get(i).getProfit()) - salary));
					} else if (siteList.get(i).getEmployeeStatus().equals("1")) {
						employeeStatus = "協力";
						if (siteList.get(i).getBpUnitPrice() != null) {
							if (!siteList.get(i).getBpUnitPrice().equals(""))
								bpSalary = Integer.parseInt(siteList.get(i).getBpUnitPrice()) * months * 10000;
							siteList.get(i).setSalary(Integer.toString(bpSalary));
							siteRoleNameAll += Integer.parseInt(siteList.get(i).getProfit()) - bpSalary;
						}
						siteList.get(i).setSiteRoleName(
								Integer.toString(Integer.parseInt(siteList.get(i).getProfit()) - bpSalary));
					} else
						employeeStatus = "";
				}
				siteList.get(i).setEmployeeStatus(employeeStatus);
				if (!(siteList.get(i).getBpBelongCustomerCode() == null)) {
					for (int z = 0; z < customerName.size(); z++) {
						if (siteList.get(i).getBpBelongCustomerCode().equals(customerName.get(z).getCustomerNo())) {
							siteList.get(i).setEmployeeFrom(customerName.get(z).getCustomerName());
						}
					}

				} else
					siteList.get(i).setEmployeeFrom("");
				if (siteList.get(i).getSiteRoleName() != null)
					siteList.get(i)
							.setSiteRoleName(formatString((float) Integer.parseInt(siteList.get(i).getSiteRoleName())));
				siteList.get(i)
						.setUnitPrice(formatString((float) Integer.parseInt(siteList.get(i).getUnitPrice()) * 10000));
				siteList.get(i).setProfit(formatString((float) Integer.parseInt(siteList.get(i).getProfit())));
				siteList.get(i).setSalary(formatString((float) Integer.parseInt(siteList.get(i).getSalary())));

			}

			siteList.get(0).setProfitAll(formatString((float) profitAll));
			siteList.get(0).setSiteRoleNameAll(formatString((float) siteRoleNameAll));
		}
		return siteList;
	}

	private String formatString(Float data) {
		DecimalFormat df = new DecimalFormat("#,###");
		return df.format(data);
	}
}
