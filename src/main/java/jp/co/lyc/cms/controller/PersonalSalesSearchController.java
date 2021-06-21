package jp.co.lyc.cms.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
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

import jp.co.lyc.cms.model.PersonalSalesSearchModel;
import jp.co.lyc.cms.service.PersonalSalesSearchService;
import jp.co.lyc.cms.validation.PersonalSalesValidation;
import jp.co.lyc.cms.util.UtilsCheckMethod;

@Controller
@RequestMapping(value = "/personalSales")
public class PersonalSalesSearchController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	PersonalSalesSearchService personalSalesSearchService;
	
	String errorsMessage = "";

	@RequestMapping(value = "/searchEmpDetails", method = RequestMethod.POST)

	@ResponseBody
	public Map<String, Object> searchEmpDetails(@RequestBody PersonalSalesSearchModel empInfo) {

		Date day = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyyMM");
		String sysTime = df.format(day);
		errorsMessage = "";
		DataBinder binder = new DataBinder(empInfo);
		binder.setValidator(new PersonalSalesValidation());
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
			List<PersonalSalesSearchModel> personModelList = new ArrayList<PersonalSalesSearchModel>();
			Map<String, Object> sendMap = getDetailParam(empInfo);	
				String startYandM = empInfo.getStartYearAndMonth();
				String endYandM = empInfo.getEndYearAndMonth();
				String fiscalYear = empInfo.getFiscalYear();
				List<String> getYandM = new ArrayList<String>();
				if (startYandM != "" && endYandM == "") {
					empInfo.setEndYearAndMonth(sysTime);
					endYandM = empInfo.getEndYearAndMonth();
				}
				if (startYandM == "" && endYandM != "") {
					empInfo.setStartYearAndMonth("201901");
					startYandM = empInfo.getStartYearAndMonth();
				}
				if (startYandM != "0" && startYandM != null && endYandM != "0" && endYandM != null
						&&fiscalYear==""||fiscalYear==null) {
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
				int workCount = 0;				
				sendMap.put("getYandM", getYandM);
				String grosProfits ="";
				logger.info("PersonalSalesSearchController.searchEmpDetails:" + "検索開始");
				personModelList = personalSalesSearchService.searchEmpDetails(sendMap);
				logger.info("PersonalSalesSearchController.searchEmpDetails:" + "検索結束");
				if(personModelList.size()==0) {
					String noData = "";
					noData="条件に該当する結果が存在しない";
					resulterr.put("noData",noData);
					return resulterr;
				}
				else{
					List<PersonalSalesSearchModel> personModelListTwice = new ArrayList<PersonalSalesSearchModel>();
					logger.info("PersonalSalesSearchController.searchEmpAllowance:" + "二回目検索開始");
					personModelListTwice = personalSalesSearchService.searchEmpAllowance(sendMap);
					logger.info("PersonalSalesSearchController.searchEmpAllowance:" + "二回目検索結束");
					
					for (int i = 0; i < personModelList.size(); i++) {
						for(int m = 0; m < personModelListTwice.size(); m++) {
							if(personModelList.get(i).getOnlyYandM().equals(personModelListTwice.get(m).getNextBonusMonth())) {
								personModelList.get(i).setBonusFee(personModelListTwice.get(m).getScheduleOfBonusAmount());
							}
						}
					}
				for (int i = 0; i < personModelList.size(); i++) {

					if(UtilsCheckMethod.isNullOrEmpty(personModelList.get(i).getUnitPrice())) {
						personModelList.get(i).setUnitPrice("0");
					}
					if( UtilsCheckMethod.isNullOrEmpty(personModelList.get(i).getSalary())) {
						personModelList.get(i).setSalary("0");				
					}
					if( personModelList.get(i).getSalary().equals("0")) {
						personModelList.get(i).setSalary(personModelList.get(i).getWaitingCost());				
					}
					if(UtilsCheckMethod.isNullOrEmpty(personModelList.get(i).getTransportationExpenses())) {
						personModelList.get(i).setTransportationExpenses("0");
					}
					if(UtilsCheckMethod.isNullOrEmpty(personModelList.get(i).getWaitingCost())) {
						personModelList.get(i).setWaitingCost("0");
					}
					if(UtilsCheckMethod.isNullOrEmpty(personModelList.get(i).getInsuranceFeeAmount())) {
						personModelList.get(i).setInsuranceFeeAmount("0");
					}
					if(UtilsCheckMethod.isNullOrEmpty(personModelList.get(i).getBonusFee())) {
						personModelList.get(i).setBonusFee("0");;
					}
					if(UtilsCheckMethod.isNullOrEmpty(personModelList.get(i).getLeaderAllowanceAmount())) {
						personModelList.get(i).setLeaderAllowanceAmount("0");
					}
					if(UtilsCheckMethod.isNullOrEmpty(personModelList.get(i).getOtherAllowanceAmount())) {
						personModelList.get(i).setOtherAllowanceAmount("0");
					}
					if(UtilsCheckMethod.isNullOrEmpty(personModelList.get(i).getIntroductionAllowance())) {
						personModelList.get(i).setIntroductionAllowance("0");
					}
					if(UtilsCheckMethod.isNullOrEmpty(personModelList.get(i).getDeductionsAndOvertimePay())) {
						personModelList.get(i).setDeductionsAndOvertimePay("0");
					}
					if(UtilsCheckMethod.isNullOrEmpty(personModelList.get(i).getDeductionsAndOvertimePayOfUnitPrice())) {
						personModelList.get(i).setDeductionsAndOvertimePayOfUnitPrice("0");;
					}
					
					
					List<String> empNameList =new ArrayList<String>();
					if(personModelList.get(i).getRelatedEmployees()!=null) {
						
						String[] empName =personModelList.get(i).getRelatedEmployees().split(",");
						 empNameList = Arrays.asList(empName);
					}
					personModelList.get(i).setEmpNameList(empNameList);
					if (personModelList.get(i).getCustomerName() != null) {
						workCount++;
						personModelList.get(0).setWorkMonthCount(workCount);
					}
					grosProfits = String.valueOf(Integer.parseInt(personModelList.get(i).getUnitPrice())+
						Integer.parseInt(personModelList.get(i).getDeductionsAndOvertimePayOfUnitPrice())-
								(Integer.parseInt(personModelList.get(i).getSalary())+
								Integer.parseInt(personModelList.get(i).getTransportationExpenses())+
								Integer.parseInt(personModelList.get(i).getInsuranceFeeAmount())+
								Integer.parseInt(personModelList.get(i).getBonusFee())+
								Integer.parseInt(personModelList.get(i).getLeaderAllowanceAmount())+
								Integer.parseInt(personModelList.get(i).getOtherAllowanceAmount())+
								Integer.parseInt(personModelList.get(i).getIntroductionAllowance())));
					personModelList.get(i).setGrosProfits(grosProfits);
				}
				
				Map<String, Object> resultdata = new HashMap<>();
				resultdata.put("data", personModelList);
				return resultdata;
				}		
		}
	}

	public Map<String, Object> getDetailParam(PersonalSalesSearchModel empInfo) {
		Map<String, Object> sendMap = new HashMap<String, Object>();
		String employeeName = empInfo.getEmployeeName().substring(empInfo.getEmployeeName().indexOf("(")+1,empInfo.getEmployeeName().indexOf(")"));
		String employeeNo = empInfo.getEmployeeNo();
		String fiscalYear = empInfo.getFiscalYear();
		String startYearAndMonth = empInfo.getStartYearAndMonth();
		String endYearAndMonth = empInfo.getEndYearAndMonth();
		if (employeeNo != null && employeeNo.length() != 0) {
			sendMap.put("employeeNo", employeeNo);
		}
		if (employeeName != null && employeeName.length() != 0) {
			sendMap.put("employeeName", employeeName);
		}
		if (fiscalYear != null && fiscalYear.length() != 0) {
			sendMap.put("fiscalYear", fiscalYear);
		}
		if (startYearAndMonth != null && startYearAndMonth.length() != 0) {
			sendMap.put("startYearAndMonth", startYearAndMonth);
		}
		if (endYearAndMonth != null && endYearAndMonth.length() != 0) {
			sendMap.put("endYearAndMonth", endYearAndMonth);
		}
		return sendMap;
	}
	
}
