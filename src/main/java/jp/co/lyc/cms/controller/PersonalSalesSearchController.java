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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import jp.co.lyc.cms.model.PersonalSalesSearchModel;
import jp.co.lyc.cms.service.PersonalSalesSearchService;

@Controller
@CrossOrigin(origins = "http://127.0.0.1:3000")
@RequestMapping(value = "/personalSales")
public class PersonalSalesSearchController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	PersonalSalesSearchService personalSalesSearchService;

	@RequestMapping(value = "/searchEmpDetails", method = RequestMethod.POST)
	@ResponseBody
	public List<PersonalSalesSearchModel> searchEmpDetails(@RequestBody PersonalSalesSearchModel empInfo) {

		logger.info("PersonalSalesSearchController.searchEmpDetails:" + "検索開始");
		List<PersonalSalesSearchModel> personModelList = new ArrayList<PersonalSalesSearchModel>();
		Date day=new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyyMM");
		String sysTime = df.format(day);
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
			for(int i=0;i<getYandM.size();i++) {
				sendMap.put("getYandM", getYandM.get(i));
				List<PersonalSalesSearchModel>result =new ArrayList<PersonalSalesSearchModel>();
				result = personalSalesSearchService.searchEmpDetails(sendMap);
				if(result.size()==1&&null==result.get(0)||result.size()==0) {
					PersonalSalesSearchModel onlyYandM =new PersonalSalesSearchModel();
					onlyYandM.setOnlyYandM(getYandM.get(i));
					personModelList.add(onlyYandM);
				}else {
				workCount++;
				personModelList.add(result.get(0));
				personModelList.get(i).setOnlyYandM(getYandM.get(i));
				}
				personModelList.get(0).setWorkMonthCount(workCount);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("PersonalSalesSearchController.searchEmpDetails:" + "検索結束");
		return personModelList;
	}

	public Map<String, Object> getDetailParam(PersonalSalesSearchModel empInfo) {
		Map<String, Object> sendMap = new HashMap<String, Object>();
		String employeeFirstName = empInfo.getEmployeeFirstName();
		String employeeLastName = empInfo.getEmployeeLastName();
		String employeeNo = empInfo.getEmployeeNo();
		String fiscalYear = empInfo.getFiscalYear();
		String startYearAndMonth = empInfo.getStartYearAndMonth();
		String endYearAndMonth = empInfo.getEndYearAndMonth();
		if (employeeNo != null && employeeNo.length() != 0) {
			sendMap.put("employeeNo", employeeNo);
		}
		if (employeeFirstName != null && employeeFirstName.length() != 0) {
			sendMap.put("employeeFirstName", employeeFirstName);
		}
		if (employeeLastName != null && employeeLastName.length() != 0) {
			sendMap.put("employeeLastName", employeeLastName);
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
