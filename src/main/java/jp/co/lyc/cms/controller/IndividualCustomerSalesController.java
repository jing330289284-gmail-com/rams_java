package jp.co.lyc.cms.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

import jp.co.lyc.cms.model.IndividualCustomerSalesModel;
import jp.co.lyc.cms.service.IndividualCustomerSalesService;
import jp.co.lyc.cms.util.UtilsCheckMethod;

@Controller
@RequestMapping(value = "/customerSales")
public class IndividualCustomerSalesController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	String errorsMessage="";
	@Autowired
	IndividualCustomerSalesService individualCustomerSalesService;
	@RequestMapping(value = "/searchCustomerSales", method = RequestMethod.POST)
	
	@ResponseBody
	public Map<String, Object> searchCustomerSales(@RequestBody IndividualCustomerSalesModel customerSalesInfo) throws IOException {
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
		if (startYandM != "0" && startYandM != null && endYandM != "0" && endYandM != null
				&& fiscalYear == "") {
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
		CustomerSalesModelList =individualCustomerSalesService.searchCustomerSales(sendMap);
		logger.info("IndividualCustomerSalesController.searchCustomerSales:" + "検索結束");
		List<IndividualCustomerSalesModel> cusModelLi = new ArrayList<IndividualCustomerSalesModel>();
		for(int i =0;i<getYandM.size();i++) {
			IndividualCustomerSalesModel indiviCusModel =new IndividualCustomerSalesModel();
			
			int calUnitPrice=0;
			int count =0;
			int overTimeFee =0;
			for(int j=0;j<CustomerSalesModelList.size();j++) {
				if(getYandM.get(i).equals(CustomerSalesModelList.get(j).getYearAndMonth())){
					if(UtilsCheckMethod.isNullOrEmpty(CustomerSalesModelList.get(j).getDeductionsAndOvertimePay())) {
						CustomerSalesModelList.get(j).setDeductionsAndOvertimePay("0");
					}
					calUnitPrice =calUnitPrice+Integer.parseInt(CustomerSalesModelList.get(j).getUnitPrice());
					overTimeFee=overTimeFee+Integer.parseInt(CustomerSalesModelList.get(j).getDeductionsAndOvertimePay());
					count ++;				
				}				
			}
			int averUnitPrice =calUnitPrice/count;
			indiviCusModel.setYearAndMonth(getYandM.get(i));
			indiviCusModel.setAverUnitPrice(Integer.toString(averUnitPrice));
			indiviCusModel.setTotalUnitPrice(Integer.toString(calUnitPrice));
			indiviCusModel.setMaxUnitPrice(CustomerSalesModelList.get(0).getMaxUnitPrice());
			indiviCusModel.setMinUnitPrice(CustomerSalesModelList.get(0).getMinUnitPrice());
			indiviCusModel.setWorkPeoSum(Integer.toString(count));
			if(overTimeFee<0) {
				indiviCusModel.setExpectFee(Integer.toString(overTimeFee));
			}
			if(overTimeFee>0) {
				indiviCusModel.setOverTimeFee(Integer.toString(overTimeFee));
			}
			cusModelLi.add(indiviCusModel);
		}
		
		if(CustomerSalesModelList.size()==0) {
			Map<String, Object> resulterr = new HashMap<>();
			String noData = "";
			noData="条件に該当する結果が存在しない";
			resulterr.put("noData",noData);
			return resulterr;
		}else {
			Map<String, Object> resultdata = new HashMap<>();
			resultdata.put("data", cusModelLi);
			return resultdata;
			}
		
	}
	public Map<String, Object> getDetailParam(IndividualCustomerSalesModel customerSalesInfo) {
		Map<String, Object> sendMap = new HashMap<String, Object>();
		String customerName=customerSalesInfo.getCustomerName();
		String fiscalYear =customerSalesInfo.getFiscalYear();
		String startYear = customerSalesInfo.getStartYear();
		String endYear = customerSalesInfo.getEndYear();
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
		
		return sendMap;
	}
}
