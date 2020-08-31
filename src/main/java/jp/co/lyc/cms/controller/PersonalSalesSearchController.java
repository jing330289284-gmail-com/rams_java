package jp.co.lyc.cms.controller;

import java.util.ArrayList;
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
		try {
			Map<String, Object> sendMap = getDetailParam(empInfo);
			personModelList = personalSalesSearchService.searchEmpDetails(sendMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("PersonalSalesSearchController.searchEmpDetails:" + "検索結束");
		return personModelList;
	}
	
	public Map<String, Object> getDetailParam(PersonalSalesSearchModel empInfo){
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
