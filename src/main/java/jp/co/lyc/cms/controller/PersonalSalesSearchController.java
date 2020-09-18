package jp.co.lyc.cms.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import jp.co.lyc.cms.model.PersonalSalesSearchModel;
import jp.co.lyc.cms.service.PersonalSalesSearchService;
import jp.co.lyc.cms.validation.PersonalSalesValidation;


@Controller
@CrossOrigin(origins = "http://127.0.0.1:3000")
@RequestMapping(value = "/personalSales")
public class PersonalSalesSearchController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	PersonalSalesSearchService personalSalesSearchService;

	String errorsMessage = "";
	@RequestMapping(value = "/searchEmpDetails", method = RequestMethod.POST)
	@ResponseBody
	public  Map<String, Object>searchEmpDetails(@RequestBody PersonalSalesSearchModel empInfo) {
		List<PersonalSalesSearchModel> personModelList = new ArrayList<PersonalSalesSearchModel>();
		logger.info("PersonalSalesSearchController.searchEmpDetails:" + "検索開始");	
		Date day=new Date();
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
		}
		else {
		

		try {
			String startYandM = empInfo.getStartYearAndMonth();
			String endYandM = empInfo.getEndYearAndMonth();
			String fiscalYear = empInfo.getFiscalYear();
			List<String> getYandM = new ArrayList<String>();
			if(startYandM!=""&&endYandM=="") {
			    empInfo.setEndYearAndMonth(sysTime);
			    endYandM =empInfo.getEndYearAndMonth();
			}
			if(startYandM==""&&endYandM!="") {
				empInfo.setStartYearAndMonth("201901");
				startYandM =empInfo.getStartYearAndMonth();
			}
			if(startYandM!="0"&&startYandM!=null&&endYandM!="0"&&endYandM!=null&&fiscalYear=="") {
			int startY = Integer.parseInt(startYandM.substring(0, 4));
			int startM = Integer.parseInt(startYandM.substring(4, 6));
			int endY = Integer.parseInt(endYandM.substring(0, 4));
			int endM = Integer.parseInt(endYandM.substring(4, 6));
			int count = 0;		
			for (int y = startY; y <= endY; y++) {
				if(y == startY && y == endY) {
					for(int m=startM;m <=endM;m++) {
						String monthStr = Integer.toString(m);
						if (m < 10) {
							monthStr = "0" + Integer.toString(m);
						}
						getYandM.add(count, Integer.toString(startY) + monthStr);
						count++;
					}
				}
				else if (y == startY) {
					for (int m = startM; m <= 12; m++) {
						
						String monthStr = Integer.toString(m);
						if (m < 10) {
							monthStr = "0" + Integer.toString(m);
						}
						getYandM.add(count, Integer.toString(startY) + monthStr);
						count++;
					}
				}
				else if (y != startY && y != endY) {
						for (int m = 1; m <= 12; m++) {
							
							String monthStr = Integer.toString(m);
							if (m < 10) {
								monthStr = "0" + Integer.toString(m);
							}
							getYandM.add(count, Integer.toString(y) + monthStr);
							count++;
						}
					}
				else if (y == endY) {
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
			}else {	
				int count = 0;
				for(int m= 1;m<=12;m++) {
					String monthStr = Integer.toString(m);
					if (m < 10) {
						monthStr = "0" + Integer.toString(m);
					}
					getYandM.add(count,fiscalYear+monthStr);
					count++;
				}
			}

			Map<String, Object> sendMap = getDetailParam(empInfo);
			int workCount =0;		
				sendMap.put("getYandM", getYandM);
				personModelList = personalSalesSearchService.searchEmpDetails(sendMap);
				for(int i=0;i<personModelList.size();i++) {
					if(personModelList.get(i).getUnitPrice()!=null) {
						workCount++;
						personModelList.get(0).setWorkMonthCount(workCount);
					}
				}
		}
			catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("PersonalSalesSearchController.searchEmpDetails:" + "検索結束");
		Map<String, Object>resultdata = new HashMap<>();
		resultdata.put("data",personModelList);
		return resultdata;
	}
	}

	public Map<String, Object> getDetailParam(PersonalSalesSearchModel empInfo) {
		Map<String, Object> sendMap = new HashMap<String, Object>();
		String employeeName = empInfo.getEmployeeName();
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
