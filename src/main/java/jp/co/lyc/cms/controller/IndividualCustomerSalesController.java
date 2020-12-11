package jp.co.lyc.cms.controller;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import jp.co.lyc.cms.model.CustomerEmployeeDetail;
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
		if(CustomerSalesModelList.size()==0) {
			Map<String, Object> resulterr = new HashMap<>();
			String noData = "";
			noData="条件に該当する結果が存在しない";
			resulterr.put("noData",noData);
			return resulterr;
		}else {
			Map<String, Object> resultdata = new HashMap<>();			
		List<IndividualCustomerSalesModel> CustomerSalesModelListTwice = new ArrayList<IndividualCustomerSalesModel>();
		List<Map> tempList = new ArrayList<Map>();
		for(int k=0;k<CustomerSalesModelList.size();k++) {
			Map<String, String> tempMap = new HashMap<String, String>();
			tempMap.put("empNo", CustomerSalesModelList.get(k).getEmployeeNo());
			tempMap.put("yearAndMonth", CustomerSalesModelList.get(k).getYearAndMonth());
			tempList.add(tempMap);
		}
		sendMap.put("tempList", tempList);
		logger.info("IndividualCustomerSalesController.CustomerSalesModelListTwice:" + "二回目検索開始");
		CustomerSalesModelListTwice =individualCustomerSalesService.searchCustomerSalestwice(sendMap);	
		logger.info("IndividualCustomerSalesController.CustomerSalesModelListTwice:" + "二回目検索結束");
		List<IndividualCustomerSalesModel> CustomerSalesModelListThird = new ArrayList<IndividualCustomerSalesModel>();
		logger.info("IndividualCustomerSalesController.CustomerSalesModelListThird:" + "三回目検索開始");
		CustomerSalesModelListThird =individualCustomerSalesService.searchCustomerSalesthird(sendMap);	
		logger.info("IndividualCustomerSalesController.CustomerSalesModelListThird:" + "三回目検索結束");
		
		List<IndividualCustomerSalesModel> cusModelLi = new ArrayList<IndividualCustomerSalesModel>();
		for(int i =0;i<getYandM.size();i++) {
			IndividualCustomerSalesModel indiviCusModel =new IndividualCustomerSalesModel();		
			float calUnitPrice=0;
			int calculateUintP=0;
			float count =0;
			int countNum =0;
			int peoNum =0;
			int overTimeFee =0;
			
			for(int j=0;j<CustomerSalesModelList.size();j++) {
				if(getYandM.get(i).equals(CustomerSalesModelList.get(j).getYearAndMonth())){
					if(UtilsCheckMethod.isNullOrEmpty(CustomerSalesModelList.get(j).getDeductionsAndOvertimePay())) {
						CustomerSalesModelList.get(j).setDeductionsAndOvertimePay("0");
					}					
					calUnitPrice =calculateUintP+Integer.parseInt(CustomerSalesModelList.get(j).getUnitPrice());
					calculateUintP =calculateUintP+Integer.parseInt(CustomerSalesModelList.get(j).getUnitPrice());
					overTimeFee=overTimeFee+Integer.parseInt(CustomerSalesModelList.get(j).getDeductionsAndOvertimePay());
					count ++;
					countNum ++;
					peoNum ++;
					indiviCusModel.setMaxUnitPrice(CustomerSalesModelList.get(j).getMaxUnitPrice());
					indiviCusModel.setMinUnitPrice(CustomerSalesModelList.get(j).getMinUnitPrice());
				}				
			}
			float averUnitPrice;
			String averUnitPr;
			DecimalFormat decimalFormat=new DecimalFormat(".0");
			if(calculateUintP==0||countNum==0) {
				averUnitPr ="0";
			}else {
				averUnitPrice =calUnitPrice/count;	
				 averUnitPr =decimalFormat.format(averUnitPrice);
			}		
			indiviCusModel.setYearAndMonth(getYandM.get(i));
			indiviCusModel.setAverUnitPrice(averUnitPr);
			indiviCusModel.setTotalUnitPrice(String.valueOf(calculateUintP));
			indiviCusModel.setWorkPeoSum(String.valueOf(peoNum));

			if(overTimeFee<0) {
				indiviCusModel.setExpectFee(Integer.toString(overTimeFee));
			}
			if(overTimeFee>0) {
				indiviCusModel.setOverTimeFee(Integer.toString(overTimeFee));
			}
			cusModelLi.add(indiviCusModel);
		}
		if(CustomerSalesModelListThird.size()>0) {
			for(int n =0;n<CustomerSalesModelListThird.size();n++) {
			int totalAm=0;
			for(int m =0;m<CustomerSalesModelListTwice.size();m++) {
				if(CustomerSalesModelListThird.get(n).getYearAndMonth().equals(CustomerSalesModelListTwice.get(m).getYearAndMonth())) {
				 totalAm =Integer.parseInt(CustomerSalesModelListTwice.get(m).getTotalAmount())+ Integer.parseInt(CustomerSalesModelListThird.get(n).getTotalExpenses());					
				 CustomerSalesModelListTwice.get(m).setTotalAmount(String.valueOf(totalAm));
					}
				}				
			}
		}	
		for(int p =0;p<getYandM.size();p++) {
			int costTotal = 0;
		for(int m =0;m<CustomerSalesModelListTwice.size();m++) {
			if(UtilsCheckMethod.isNullOrEmpty(CustomerSalesModelListTwice.get(m).getTotalAmount())) {
				CustomerSalesModelList.get(m).setTotalAmount("0");
			}
			if(UtilsCheckMethod.isNullOrEmpty(CustomerSalesModelListTwice.get(m).getTotalExpenses())) {
				CustomerSalesModelList.get(m).setTotalExpenses("0");
			}
			if(CustomerSalesModelListTwice.get(m).getYearAndMonth().equals(getYandM.get(p))){
				costTotal =costTotal + Integer.parseInt(CustomerSalesModelListTwice.get(m).getTotalAmount());
				}			
			}
		for(int s=0;s<cusModelLi.size();s++) {
			if(cusModelLi.get(s).getYearAndMonth().equals(getYandM.get(p))) {
				cusModelLi.get(s).setTotalAmount(String.valueOf(costTotal));
			}
		}
		cusModelLi.get(p).setGrossProfit(String.valueOf(Integer.parseInt(cusModelLi.get(p).getTotalUnitPrice())*10000-Integer.parseInt(cusModelLi.get(p).getTotalAmount())));
		}
		for(int d=cusModelLi.size()-1;d>=0;d--) {
			if(cusModelLi.get(d).getTotalUnitPrice().equals("0")||cusModelLi.get(d).getTotalUnitPrice()==null) {
				cusModelLi.remove(d);
			}
		}
		
		
		for(int a=0; a<getYandM.size();a++) {		
			List<CustomerEmployeeDetail>customerEmpDetail =new ArrayList<CustomerEmployeeDetail>();
			for(int b =0; b<CustomerSalesModelList.size();b++) {
				CustomerEmployeeDetail customerEmpDe = new CustomerEmployeeDetail();	
				if(CustomerSalesModelList.get(b).getYearAndMonth().equals(getYandM.get(a))) {
					customerEmpDe.setEmployeeName(CustomerSalesModelList.get(b).getEmployeeName());
					customerEmpDe.setSiteRoleName(CustomerSalesModelList.get(b).getSiteRoleName());
					customerEmpDe.setStationName(CustomerSalesModelList.get(b).getStationName());
					customerEmpDe.setUnitPrice(CustomerSalesModelList.get(b).getUnitPrice());
					customerEmpDetail.add(customerEmpDe);
				}
			}		
			for(int c=0;c<cusModelLi.size();c++) {
				if(cusModelLi.get(c).getYearAndMonth().equals((getYandM.get(a)))){
					cusModelLi.get(c).setEmpDetail(customerEmpDetail);
				}
			}
			
		}
		
		int totalworkPeoSum =0;
		for(int c=0;c<cusModelLi.size();c++) {
			totalworkPeoSum=totalworkPeoSum+Integer.parseInt(cusModelLi.get(c).getWorkPeoSum());
		}
		cusModelLi.get(0).setTotalworkPeoSum(totalworkPeoSum);
	
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
