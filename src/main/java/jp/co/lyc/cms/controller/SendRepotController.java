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
		List<SendRepotModel> salesCustomersList = new ArrayList<SendRepotModel>();
		try {
			salesCustomersList = sendRepotService.getSalesCustomers();
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("getCustomers" + "検索結束");
		return salesCustomersList;
	}
	
	@RequestMapping(value = "/getSalesPersons", method = RequestMethod.POST)
	@ResponseBody
	public List<SendRepotModel> getSalesPersons(@RequestBody SendRepotModel model) {

		logger.info("getSalesPersons:" + "検索開始");
		List<SendRepotModel> salesPersonsList = new ArrayList<SendRepotModel>();
		try {
			salesPersonsList = sendRepotService.getSalesPersons(model.getCustomerNo());
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("getSalesPersons" + "検索結束");
		return salesPersonsList;
	}
	
	@RequestMapping(value = "/creatList", method = RequestMethod.POST)
	@ResponseBody
	public int creatList(@RequestBody SendRepotModel model) {

		model.setUpdateUser(getSession().getAttribute("employeeName").toString());
		logger.info("getSalesPersons:" + "検索開始");
		int index=0;
		try {
			index = sendRepotService.creatList(model);
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("getSalesPersons" + "検索結束");
		return index;
	}
	
	@RequestMapping(value = "/getLists", method = RequestMethod.POST)
	@ResponseBody
	public List<SendRepotModel> getLists() {

		List<SendRepotModel> salesPersonsList = new ArrayList<SendRepotModel>();
		try {
			salesPersonsList = sendRepotService.getLists();
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("getSalesPersons" + "検索結束");
		return salesPersonsList;
	}
	
	@RequestMapping(value = "/listNameUpdate", method = RequestMethod.POST)
	@ResponseBody
	public void listNameUpdate(@RequestBody SendRepotsListName sendRepotsListNames) {

		if(!sendRepotsListNames.getStorageListName2().equals(sendRepotsListNames.getOldStorageListName2())) {
			updateName(sendRepotsListNames.getStorageListName2(),sendRepotsListNames.getOldStorageListName2());
		}
		if(!sendRepotsListNames.getStorageListName3().equals(sendRepotsListNames.getOldStorageListName3())) {
			updateName(sendRepotsListNames.getStorageListName3(),sendRepotsListNames.getOldStorageListName3());
		}
		if(!sendRepotsListNames.getStorageListName1().equals(sendRepotsListNames.getOldStorageListName1())) {
			updateName(sendRepotsListNames.getStorageListName1(),sendRepotsListNames.getOldStorageListName1());
		}
	}
	
	@RequestMapping(value = "/getCustomersByNos", method = RequestMethod.POST)
	@ResponseBody
	public List<SendRepotModel> getCustomersByNos(@RequestBody SendRepotModel model) {

		logger.info("getCustomers:" + "検索開始");
		List<SendRepotModel> salesCustomersList = new ArrayList<SendRepotModel>();
		try {
			salesCustomersList = sendRepotService.getSalesCustomersByNos(model.getCtmNos());
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("getCustomers" + "検索結束");
		return salesCustomersList;
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
	
	@RequestMapping(value = "/deleteList", method = RequestMethod.POST)
	@ResponseBody
	public int deleteList(@RequestBody SendRepotsListName sendRepotsListNames) {
		logger.info("getSalesPersons:" + "検索開始");
		int index=0;
		try {
			index = sendRepotService.deleteList(sendRepotsListNames.getStorageListName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("getSalesPersons" + "検索結束");
		return index;
	}
	@RequestMapping(value = "/openFolder", method = RequestMethod.POST)
	@ResponseBody
	private static void openFolder() throws IOException{
		Calendar now = Calendar.getInstance();
		String path="C:\\file\\作業報告書フォルダ\\"+now.get(Calendar.YEAR) + "\\"+ (now.get(Calendar.MONTH) + 1)+ "\\";
	    Runtime.getRuntime().exec("explorer.exe /select, "+path);
	    }
	}
