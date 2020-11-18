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
import jp.co.lyc.cms.service.PersonalSalesSearchService;
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
	@Autowired
	PersonalSalesSearchService personalSalesSearchService;

	String errorsMessage = "";

	@RequestMapping(value = "/getSalesProfitInfo", method = RequestMethod.POST)
	@ResponseBody
	public List<SalesProfitModel> getSalesPointInfo(@RequestBody SalesProfitModel salesProfitModel) {
		// List<SalesPointSetModel> siteList = new ArrayList<SalesPointSetModel>();
		// Map<String, Object> sendMap = new HashMap<String, Object>();
		// String employee = salesPointSetModel.getEmployee();
		// String newMember = salesPointSetModel.getNewMember();
		// String customerContract = salesPointSetModel.getCustomerContract();
		// if (employee != null && employee.length() != 0) {
		// sendMap.put("employee", employee);
		// }
		// if (newMember != null && newMember.length() != 0) {
		// sendMap.put("newMember", newMember);
		// }
		// if (customerContract != null && customerContract.length() != 0) {
		// sendMap.put("customerContract", customerContract);
		// }
		// try {
		// siteList = salesPointSetService.getSalesPointInfo(sendMap);
		//
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		List<SalesProfitModel> siteList = salesProfitService.getSalesProfitInfo(salesProfitModel);

		// SalesProfitModel sm = new SalesProfitModel();
		// sm.setRowNo("1");
		// sm.setYearAndMonth("10月");
		// sm.setEmployeeName("テスト社員");
		// sm.setEmployeeStatus("在籍");
		// siteList.add(sm);
//		SalesProfitModel sm = new SalesProfitModel();
//		sm.setRowNo("1");
//		sm.setYearAndMonth(salesProfitModel.getStartDate());
//		sm.setEmployeeName(salesProfitModel.getEmployeeName());
//		sm.setEmployeeStatus(salesProfitModel.getEmployeeStatus());
//		siteList.add(sm);

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

		List<SalesInfoModel> siteList = salesProfitService.getSalesInfo(salesProfitModel);
		logger.info("SalesProfitController.getSalesPointInfo:" + "検索結束");

		int siteRoleNameAll = 0;
		int profitAll = 0;

		for (int i = 0; i < siteList.size(); i++) {
			String yearAndMonth = siteList.get(i).getAdmissionStartDate().substring(0, 6);
			if (i > 0) {
				if (yearAndMonth.equals(siteList.get(i - 1).getYearAndMonth())) {
					yearAndMonth = "";
				}
			}
			siteList.get(i).setYearAndMonth(yearAndMonth.substring(0, 4) + "/" + yearAndMonth.substring(4, 6));
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
			String admissionEndDate = siteList.get(i).getAdmissionEndDate().substring(0, 6);
			String workDateStart = admissionStartDate;
			String workDateEnd = admissionEndDate;
			if (Integer.parseInt(startTime) > Integer.parseInt(admissionStartDate)) {
				workDateStart = startTime;
			}

			siteList.get(i).setWorkDate(workDateStart.substring(0, 4) + "/" + workDateStart.substring(4, 6) + " ~ "
					+ workDateEnd.substring(0, 4) + "/" + workDateEnd.substring(4, 6));

			DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM");
			int months = Months
					.monthsBetween(
							formatter
									.parseDateTime(workDateStart.substring(0, 4) + "-" + workDateStart.substring(4, 6)),
							formatter.parseDateTime(workDateEnd.substring(0, 4) + "-" + workDateEnd.substring(4, 6)))
					.getMonths() + 1;
			siteList.get(i).setProfit(Integer.toString(Integer.parseInt(siteList.get(i).getUnitPrice()) * months));
			profitAll += Integer.parseInt(siteList.get(i).getUnitPrice()) * months;
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
			Map<String, Object> sendMap = new HashMap<String, Object>();
			sendMap.put("getYandM", getYandM);
			sendMap.put("employeeName", siteList.get(i).getEmployeeNo());
			List<PersonalSalesSearchModel> personModelList = new ArrayList<PersonalSalesSearchModel>();
			personModelList = personalSalesSearchService.searchEmpDetails(sendMap);
			int salary = 0;
			int bpSalary = 0;
			for (int j = 0; j < personModelList.size(); j++) {
				if (!(personModelList.get(j).getSalary() == null))
					salary += Integer.parseInt(personModelList.get(j).getSalary());
			}
			siteList.get(i).setSalary(Integer.toString(salary));
			siteList.get(i).setRowNo(Integer.toString(i + 1));
			siteList.get(i).setSiteRoleName(Integer.toString(Integer.parseInt(siteList.get(i).getProfit()) - salary));
			siteRoleNameAll += Integer.parseInt(siteList.get(i).getProfit()) - salary;
			String employeeStatus = "";
			if (!(siteList.get(i).getEmployeeStatus() == null)) {
				if (siteList.get(i).getEmployeeStatus().equals("0"))
					employeeStatus = "社員";
				else if (siteList.get(i).getEmployeeStatus().equals("1")) {
					employeeStatus = "協力";
					if (siteList.get(i).getBpUnitPrice() != null) {
						bpSalary = Integer.parseInt(siteList.get(i).getBpUnitPrice()) * months;
						siteList.get(i).setSalary(Integer.toString(bpSalary));
					}
				} else
					employeeStatus = "";
			}
			siteList.get(i).setEmployeeStatus(employeeStatus);
			if (!(siteList.get(i).getBpBelongCustomerCode() == null)) {
				if (!(salesProfitService.getCustomerName(siteList.get(i).getBpBelongCustomerCode()) == null)) {
					String employeeFrom = salesProfitService.getCustomerName(siteList.get(i).getBpBelongCustomerCode())
							.getCustomerName();
					siteList.get(i).setEmployeeFrom(employeeFrom);
				}
			} else
				siteList.get(i).setEmployeeFrom("");
			siteList.get(i).setSiteRoleName(formatString((float) Integer.parseInt(siteList.get(i).getSiteRoleName())));
			siteList.get(i).setUnitPrice(formatString((float) Integer.parseInt(siteList.get(i).getUnitPrice())));
			siteList.get(i).setProfit(formatString((float) Integer.parseInt(siteList.get(i).getProfit())));
			siteList.get(i).setSalary(formatString((float) Integer.parseInt(siteList.get(i).getSalary())));
		}
		siteList.get(0).setProfitAll(formatString((float) profitAll));
		siteList.get(0).setSiteRoleNameAll(formatString((float) siteRoleNameAll));
		return siteList;
	}

	private String formatString(Float data) {
		DecimalFormat df = new DecimalFormat("#,###");
		return df.format(data);
	}
}
