package jp.co.lyc.cms.controller;

import java.util.ArrayList;
import java.util.HashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import jp.co.lyc.cms.model.CustomerInfoModel;
import jp.co.lyc.cms.service.CustomerInfoSearchService;
import jp.co.lyc.cms.service.TopCustomerInfoService;

@Controller
@CrossOrigin(origins = "http://127.0.0.1:3000")
@RequestMapping(value = "/customerInfoSearch")
public class CustomerInfoSearchController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	CustomerInfoSearchService customerInfoSearchService;
	@Autowired
	TopCustomerInfoService topCustomerInfoService;
	/**
	 * データの検索
	 * @param customerInfoMod
	 * @return
	 */
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	@ResponseBody
	public ArrayList<CustomerInfoModel> seach(@RequestBody CustomerInfoModel customerInfoMod) {
		logger.info("CustomerInfoController.onloadPage:" + "検索開始");
		ArrayList<CustomerInfoModel> resultList = new ArrayList<>();
		ArrayList<CustomerInfoModel> databeseList = new ArrayList<>();
		databeseList = SelectCustomerInfo(customerInfoMod);
		int rowNo = 1;
		for(int i = 0 ; i < databeseList.size() ; i++) {
			if(i != 0) {//第二からのデータ
				if(!databeseList.get(i).getCustomerNo().equals(databeseList.get(i-1).getCustomerNo())) {
					//前後お客様番号が違い場合、データをリストに入る
					databeseList.get(i).setRowNo(Integer.toString(rowNo));
					if(databeseList.get(i).getEmployeeName() != null) {
						ArrayList<String> employeeNameList = new ArrayList<>();
						employeeNameList.add(databeseList.get(i).getEmployeeName());
						databeseList.get(i).setEmployeeNameList(employeeNameList);
					}
					if(databeseList.get(i).getLocation() != null) {
						ArrayList<String> locationList = new ArrayList<>();
						locationList.add(databeseList.get(i).getLocation());
						databeseList.get(i).setLocationList(locationList);
					}
					if(databeseList.get(i).getSiteManager() != null) {
						ArrayList<String> siteManagerList = new ArrayList<>();
						siteManagerList.add(databeseList.get(i).getSiteManager());
						databeseList.get(i).setSiteManagerList(siteManagerList);
					}
					if(databeseList.get(i).getUnitPrice() != null) {
						ArrayList<String> unitPriceList = new ArrayList<>();
						unitPriceList.add(databeseList.get(i).getUnitPrice());
						databeseList.get(i).setUnitPriceList(unitPriceList);
					}
					resultList.add(databeseList.get(i));
					rowNo ++;
				}else if(databeseList.get(i).getCustomerNo().equals(databeseList.get(i-1).getCustomerNo()) && 
						databeseList.get(i).getCustomerName().equals(databeseList.get(i-1).getCustomerName())) {
					//前後のお客様番号が同じの場合、データを整備する
					CustomerInfoModel dataChange = resultList.get(rowNo-2);
					ArrayList<String> employeeNameList = (dataChange.getEmployeeNameList() == null ? new ArrayList<>() :
						dataChange.getEmployeeNameList());
					employeeNameList.add(databeseList.get(i).getEmployeeName());
					dataChange.setEmployeeNameList(employeeNameList);
					
					ArrayList<String> locationList = (dataChange.getLocationList() == null ? new ArrayList<>() :
						dataChange.getLocationList());
					locationList.add(databeseList.get(i).getLocation());
					dataChange.setLocationList(locationList);
					
					ArrayList<String> siteManagerList = (dataChange.getSiteManagerList() == null ? new ArrayList<>() :
						dataChange.getSiteManagerList());
					siteManagerList.add(databeseList.get(i).getSiteManager());
					dataChange.setSiteManagerList(siteManagerList);
					
					ArrayList<String> unitPriceList = (dataChange.getUnitPriceList() == null ? new ArrayList<>() :
						dataChange.getUnitPriceList());
					unitPriceList.add(databeseList.get(i).getUnitPrice());
					dataChange.setUnitPriceList(unitPriceList);
					
					resultList.set((rowNo - 2), dataChange);
				}
			}else {//第一のデータ
				databeseList.get(i).setRowNo(Integer.toString(rowNo));
				if(databeseList.get(i).getEmployeeName() != null) {
					ArrayList<String> employeeNameList = new ArrayList<>();
					employeeNameList.add(databeseList.get(i).getEmployeeName());
					databeseList.get(i).setEmployeeNameList(employeeNameList);
				}
				if(databeseList.get(i).getLocation() != null) {
					ArrayList<String> locationList = new ArrayList<>();
					locationList.add(databeseList.get(i).getLocation());
					databeseList.get(i).setLocationList(locationList);
				}
				if(databeseList.get(i).getSiteManager() != null) {
					ArrayList<String> siteManagerList = new ArrayList<>();
					siteManagerList.add(databeseList.get(i).getSiteManager());
					databeseList.get(i).setSiteManagerList(siteManagerList);
				}
				if(databeseList.get(i).getUnitPrice() != null) {
					ArrayList<String> unitPriceList = new ArrayList<>();
					unitPriceList.add(databeseList.get(i).getUnitPrice());
					databeseList.get(i).setUnitPriceList(unitPriceList);
				}
				resultList.add(databeseList.get(i));
				rowNo ++;
			}
		}
		logger.info("CustomerInfoController.onloadPage:" + "検索終了");
		return resultList;
	}
	/**
	 * 削除ボタン
	 * @param customerNo
	 * @return
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public String delect( @RequestBody CustomerInfoModel customerInfoMod) {	
		logger.info("CustomerInfoController.onloadPage:" + "削除ボタン");
		if(customerInfoSearchService.checkCustomerInSiteInfo(customerInfoMod.getCustomerNo()).size() > 0) {
			return "2";//2:お客様が現場に使っている
		}else {
			String topCustomerNo = customerInfoSearchService.getTopCustomerNoInCustomerInfo(customerInfoMod.getCustomerNo());
			if(customerInfoSearchService.getCustomerNoWithSameTop(topCustomerNo).size() > 0) {
				if(customerInfoSearchService.deleteCustomerInfo(customerInfoMod.getCustomerNo()) && 
						customerInfoSearchService.deleteCustomerDepartmentInfo(customerInfoMod.getCustomerNo())) {
					return "3";//3:上位お客様の下位お客様が複数あるので
				}else {
					return "4";//4:上位お客様の下位お客様が複数あるの場合でも、お客様の削除が失敗し
				}
			}else {
				//0:成功した、1:削除失敗した
				return ((customerInfoSearchService.deleteCustomerInfo(customerInfoMod.getCustomerNo()) && 
							customerInfoSearchService.deleteCustomerDepartmentInfo(customerInfoMod.getCustomerNo()) &&
								topCustomerInfoService.deleteTopCustomerInfo(topCustomerNo)) ? "0" : "1");
			}
		}
	}
	/**
	 * データの検索
	 * @param customerInfoMod
	 * @return
	 */
	public ArrayList<CustomerInfoModel> SelectCustomerInfo(CustomerInfoModel customerInfoMod) {
		logger.info("CustomerInfoController.onloadPage:" + "検索開始");
		HashMap<String, String> sendMap = new HashMap<>();
		if(!isNullOrEmpty(customerInfoMod.getCustomerNo())) {
			sendMap.put("customerNo", customerInfoMod.getCustomerNo());
		}
		if(!isNullOrEmpty(customerInfoMod.getCustomerName())) {
			sendMap.put("customerName", customerInfoMod.getCustomerName());
		}
		if(!isNullOrEmpty(customerInfoMod.getStationCode())) {
			sendMap.put("stationCode", customerInfoMod.getStationCode());
		}
		if(!isNullOrEmpty(customerInfoMod.getPaymentsiteCode())) {
			sendMap.put("paymentsiteCode", customerInfoMod.getPaymentsiteCode());
		}
		if(!isNullOrEmpty(customerInfoMod.getBusinessStartDate())) {
			sendMap.put("businessStartDate", customerInfoMod.getBusinessStartDate());
		}
		if(!isNullOrEmpty(customerInfoMod.getLevelCode())) {
			sendMap.put("levelCode", customerInfoMod.getLevelCode());
		}
		if(!isNullOrEmpty(customerInfoMod.getCompanyNatureCode())) {
			sendMap.put("companyNatureCode", customerInfoMod.getCompanyNatureCode());
		}
		if(!isNullOrEmpty(customerInfoMod.getTopCustomerName())) {
			sendMap.put("topCustomerName", customerInfoMod.getTopCustomerName());
		}
		logger.info("CustomerInfoController.onloadPage:" + "検索終了");
		return customerInfoSearchService.SelectCustomerInfo(sendMap);
	}
	/**
	 * nullと空の判断
	 * @param aString
	 * @return
	 */
	public boolean isNullOrEmpty(String aString) {
		boolean result = true;
		if (aString == null || aString.isEmpty()) {
			return result;
		} else {
			return result = false;
		}
	}
}
