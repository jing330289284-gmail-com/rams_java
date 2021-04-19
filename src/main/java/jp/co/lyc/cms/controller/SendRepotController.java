package jp.co.lyc.cms.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import jp.co.lyc.cms.common.BaseController;
import jp.co.lyc.cms.model.ModelClass;
import jp.co.lyc.cms.model.SalesSendLettersListName;
import jp.co.lyc.cms.model.SendRepotModel;
import jp.co.lyc.cms.model.SendRepotsListName;
import jp.co.lyc.cms.service.SendRepotService;

@Controller
@RequestMapping(value = "/sendRepot")
public class SendRepotController  extends BaseController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	SendRepotService sendRepotService;

	/**
	 * データを取得
	 * 
	 * @param なし
	 * @return List
	 */

	@RequestMapping(value = "/getCustomers", method = RequestMethod.POST)
	@ResponseBody
	public List<SendRepotModel> getSalesSituation() {
		logger.info("getCustomers:" + "検索開始");
		List<SendRepotModel> sendRepotList = new ArrayList<SendRepotModel>();
		try {
			sendRepotList = sendRepotService.getCustomers();
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("getCustomers" + "検索結束");
		return sendRepotList;
	}
	/**
	 * リストを取得
	 * 
	 * @param なし
	 * @return List
	 */
	@RequestMapping(value = "/getLists", method = RequestMethod.POST)
	@ResponseBody
	public List<SendRepotModel> getLists() {

		List<SendRepotModel> salesPersonsList = new ArrayList<SendRepotModel>();
		try {
			salesPersonsList = sendRepotService.getLists();
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("getLists" + "検索結束");
		return salesPersonsList;
	}
	
	@RequestMapping(value = "/getTargetEmployees", method = RequestMethod.POST)
	@ResponseBody
	public List<SendRepotModel> getTargetEmployees(@RequestBody SendRepotModel model) {

		logger.info("getTargetEmployees:" + "検索開始");
		List<SendRepotModel> salesPersonsList = new ArrayList<SendRepotModel>();
		try {
			salesPersonsList = sendRepotService.getTargetEmployees(model.getCustomerNo());
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (int i = 0; i < salesPersonsList.size(); i++) {
			salesPersonsList.get(i).setRowId(i + 1);
		}
		logger.info("getSalesPersons" + "検索結束");
		return salesPersonsList;
	}
	
	@RequestMapping(value = "/creatList", method = RequestMethod.POST)
	@ResponseBody
	public int creatList(@RequestBody SendRepotModel model) {
		model.setUpdateUser(getSession().getAttribute("employeeName").toString());
		logger.info("creatList:" + "検索開始");
		int index=0;
		try {
			index = sendRepotService.creatList(model);
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("creatList" + "検索結束");
		return index;
	}
	@RequestMapping(value = "/getListByName", method = RequestMethod.POST)
	@ResponseBody
	public SendRepotModel getListByName(@RequestBody SendRepotModel model) {
		SendRepotModel sendRepotModel = new SendRepotModel();
		model.setUpdateUser(getSession().getAttribute("employeeName").toString());
		logger.info("getListByName:" + "検索開始");
		try {
			sendRepotModel = sendRepotService.getListByName(model);
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("getListByName" + "検索結束");
		return sendRepotModel;
	}	
	
	@RequestMapping(value = "/listNameUpdate", method = RequestMethod.POST)
	@ResponseBody
	public void listNameUpdate(@RequestBody SendRepotsListName sendRepotsListNames) {
			if (!sendRepotsListNames.getStorageListName().equals(sendRepotsListNames.getOldStorageListName())) {
				updateName(sendRepotsListNames.getStorageListName(),sendRepotsListNames.getOldStorageListName());
			}
	}
	@RequestMapping(value = "/getCustomersByNos", method = RequestMethod.POST)
	@ResponseBody
	public List<SendRepotModel> getCustomersByNos(@RequestBody SendRepotModel model) {
		logger.info("getCustomersByNos:" + "検索開始");
		List<SendRepotModel> salesCustomersList = new ArrayList<SendRepotModel>();
		SendRepotModel sendRepot = new SendRepotModel();
		try {
			salesCustomersList = sendRepotService.getCustomersByNos(model.getCtmNos());
			sendRepot = sendRepotService.getMainChargeList(model.getStorageListName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("getCustomersByNos" + "検索結束");
		if (model.getStorageListName() != "" && model.getStorageListName() != null) {
			String[] selectedRowKeys = sendRepot.getMainChargeList().split(";");
			String[] selectedRowNames = sendRepot.getDepartmentCodeList().split(";");

			for (int i = 0; i < salesCustomersList.size(); i++) {
				salesCustomersList.get(i).setStorageListName(model.getStorageListName());
				for (int j = 0; j < selectedRowKeys.length; j++) {
					String[] customerRowKeys = selectedRowKeys[j].split(":");
					if (salesCustomersList.get(i).getCustomerNo().equals(customerRowKeys[0])) {
						salesCustomersList.get(i)
								.setMainChargeList(customerRowKeys.length > 1 ? customerRowKeys[1] : "");
					}
				}
				for (int j = 0; j < selectedRowNames.length; j++) {
					String[] aaacustomerRowNames = selectedRowNames[j].split(":");
					if (salesCustomersList.get(i).getCustomerNo().equals(aaacustomerRowNames[0])) {
						salesCustomersList.get(i)
								.setSalesPersonsAppend(aaacustomerRowNames.length > 1 ? aaacustomerRowNames[1] : "");
					}
				}
			}
		}

		return salesCustomersList;
	}
	@RequestMapping(value = "/getCustomerDepartmentCode", method = RequestMethod.POST)
	@ResponseBody
	public List<ModelClass>  getCustomerDepartmentCode(@RequestBody SendRepotModel model) {
			List<ModelClass> list = sendRepotService.getCustomerDepartmentCode(model);
			return list ;
		}	
	@RequestMapping(value = "/getPurchasingManagersCode", method = RequestMethod.POST)
	@ResponseBody
	public List<ModelClass>  getPurchasingManagersCode(@RequestBody SendRepotModel model2) {
			List<ModelClass> list = sendRepotService.getPurchasingManagersCode(model2);
			return list;
		}
	@RequestMapping(value = "/getSalesPersonsLists", method = RequestMethod.POST)
	@ResponseBody
	public List<ModelClass>  getSalesPersonsLists() {
			List<ModelClass> list = sendRepotService.getSalesPersonsLists();
			return list ;
		}	
	private void updateName(String storageListName,String oldStorageListName) {
		SendRepotsListName updateModel= new SendRepotsListName();
		updateModel.setUpdateUser(getSession().getAttribute("employeeName").toString());
		updateModel.setStorageListName(storageListName);
		updateModel.setOldStorageListName(oldStorageListName);
		try {
			sendRepotService.listNameUpdate(updateModel);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/salesPersonsListsUpdate", method = RequestMethod.POST)
	@ResponseBody
	private void salesPersonsListsUpdate(String salesPersons,String oldStorageListName) {
		SendRepotsListName updateModel= new SendRepotsListName();
		updateModel.setUpdateUser(getSession().getAttribute("employeeName").toString());
		updateModel.setOldStorageListName(oldStorageListName);
		updateModel.setSubChargeMailList(salesPersons);
		try {
			sendRepotService.salesPersonsListsUpdate(updateModel);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@RequestMapping(value = "/deleteList", method = RequestMethod.POST)
	@ResponseBody
	public int deleteList(@RequestBody String storageListName) {
		logger.info("deleteList:" + "検索開始");
		int index=0;
		try {
			index = sendRepotService.deleteList(storageListName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("deleteList" + "検索結束");
		return index;
	}
	@RequestMapping(value = "/openFolder", method = RequestMethod.POST)
	@ResponseBody
	private static void openFolder() throws IOException{
		Calendar now = Calendar.getInstance();
		String theMonth=Integer.toString(now.get(Calendar.MONTH) + 1);
		if((now.get(Calendar.MONTH) + 1)<10) {
			 theMonth="0"+theMonth;
		}
		String path="C:\\file\\作業報告書フォルダ\\"+now.get(Calendar.YEAR) + "\\"+ theMonth;
	    Runtime.getRuntime().exec("explorer.exe  /n,/select, "+path);
	    }
	
	
	
	//追加部分
	@RequestMapping(value = "/customerListUpdate", method = RequestMethod.POST)
	@ResponseBody
	public String customerListUpdate(@RequestBody SendRepotModel model) {
		try {
			sendRepotService.customerListUpdate(model.getStorageListName(), model.getCustomerList());
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("customerListUpdate" + "検索結束");
		return sendRepotService.getCustomerList(model.getStorageListName());
	}
	@RequestMapping(value = "/deleteCustomerList", method = RequestMethod.POST)
	@ResponseBody
	public String deleteCustomerList(@RequestBody SendRepotModel model) {
		try {
			sendRepotService.deleteCustomerList(model);
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("deleteCustomerList" + "検索結束");
		return "";
	}
	@RequestMapping(value = "/addNewList", method = RequestMethod.POST)
	@ResponseBody
	public String addNewList(@RequestBody SendRepotModel model) {
		model.setUpdateUser(getSession().getAttribute("employeeName").toString());
		logger.info("addNewList:" + "検索開始");
		String name = "";
		String mainChargeList = "";
		String departmentCodeList = "";
		String[] code = model.getCode().split(",");
		for (int i = 0; i < code.length; i++) {
			mainChargeList += code[i] + ":;";
			departmentCodeList += code[i] + ":;";
		}
		model.setMainChargeList(mainChargeList);
		model.setDepartmentCodeList(departmentCodeList);
		try {
			name = sendRepotService.getMaxStorageListName();
			model.setName(name);
			sendRepotService.creatList(model);
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("addNewList" + "検索結束");
		return name;
	}
	@RequestMapping(value = "/deleteCustomerListByNo", method = RequestMethod.POST)
	@ResponseBody
	public String deleteCustomerListByNo(@RequestBody SendRepotModel model) {

		logger.info("deleteCustomerListByNo:" + "検索開始");
		String newCtmNos = "";
		for (int i = 0; i < model.getOldCtmNos().length; i++) {
			boolean flag = false;
			for (int j = 0; j < model.getDeleteCtmNos().length; j++) {
				if (model.getOldCtmNos()[i].equals(model.getDeleteCtmNos()[j])) {
					flag = true;
					break;
				}
			}
			if (!flag) {
				newCtmNos += model.getOldCtmNos()[i] + ",";
			}
		}
		if (newCtmNos.length() > 0) {
			newCtmNos = newCtmNos.substring(0, newCtmNos.length() - 1);
		}
		model.setCtmNos(newCtmNos.split(","));
		model.setUpdateUser(getSession().getAttribute("employeeName").toString());
		model.setCustomerList(newCtmNos);

		SendRepotModel sendRepot = new SendRepotModel();
		try {
			sendRepot = sendRepotService.getMainChargeList(model.getStorageListName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		String[] selectedRowKeys = sendRepot.getMainChargeList().split(";");
		String[] selectedRowNames = sendRepot.getDepartmentCodeList().split(";");
		String mainChargeList = "";
		String departmentCodeList = "";

		for (int i = 0; i < selectedRowKeys.length; i++) {
			for (int j = 0; j < model.getCtmNos().length; j++) {
				String[] customerRowKeys = selectedRowKeys[i].split(":");
				if (model.getCtmNos()[j].equals(customerRowKeys[0])) {
					mainChargeList += model.getCtmNos()[j] + ":";
					if (customerRowKeys.length > 1)
						mainChargeList += customerRowKeys[1] + ";";
					else
						mainChargeList += ";";
				}
			}
		}
		
		for (int i = 0; i < selectedRowNames.length; i++) {
			for (int j = 0; j < model.getCtmNos().length; j++) {
				String[] customerRowNames = selectedRowNames[i].split(":");
				if (model.getCtmNos()[j].equals(customerRowNames[0])) {
					departmentCodeList += model.getCtmNos()[j] + ":";
					if (customerRowNames.length > 1)
						departmentCodeList += customerRowNames[1] + ";";
					else
						departmentCodeList += ";";
				}
			}
		}
		
		model.setMainChargeList(mainChargeList);
		model.setDepartmentCodeList(departmentCodeList);


		try {
			sendRepotService.deleteCustomerListByNo(model);
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("deleteCustomerListByNo" + "検索結束");
		return newCtmNos;
	}
	}
