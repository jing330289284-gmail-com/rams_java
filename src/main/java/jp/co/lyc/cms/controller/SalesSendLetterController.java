package jp.co.lyc.cms.controller;

import java.util.ArrayList;
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
import jp.co.lyc.cms.model.SalesSendLetterModel;
import jp.co.lyc.cms.model.SalesSendLettersListName;
import jp.co.lyc.cms.model.SendLettersConfirmModel;
import jp.co.lyc.cms.model.ModelClass;
import jp.co.lyc.cms.service.SalesSendLetterService;

@Controller
@RequestMapping(value = "/salesSendLetters")
public class SalesSendLetterController extends BaseController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	SalesSendLetterService salesSendLetterService;

	/**
	 * データを取得
	 * 
	 * @param なし
	 * @return List
	 */

	@RequestMapping(value = "/getCustomers", method = RequestMethod.POST)
	@ResponseBody
	public List<SalesSendLetterModel> getSalesSituation() {

		logger.info("getCustomers:" + "検索開始");
		List<SalesSendLetterModel> salesCustomersList = new ArrayList<SalesSendLetterModel>();
		try {
			salesCustomersList = salesSendLetterService.getSalesCustomers();
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("getCustomers" + "検索結束");
		return salesCustomersList;
	}

	/**
	 * データを取得
	 * 
	 * @param なし
	 * @return List
	 */

	@RequestMapping(value = "/getSalesCustomerByNo", method = RequestMethod.POST)
	@ResponseBody
	public List<SalesSendLetterModel> getSalesCustomerByNo(@RequestBody SalesSendLetterModel model) {

		logger.info("getCustomers:" + "検索開始");
		List<SalesSendLetterModel> salesCustomersList = new ArrayList<SalesSendLetterModel>();
		try {
			salesCustomersList = salesSendLetterService.getSalesCustomerByNo(model.getCustomerNo());
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("getCustomers" + "検索結束");
		return salesCustomersList;
	}

	@RequestMapping(value = "/getSalesPersons", method = RequestMethod.POST)
	@ResponseBody
	public List<SalesSendLetterModel> getSalesPersons(@RequestBody SalesSendLetterModel model) {

		logger.info("getSalesPersons:" + "検索開始");
		List<SalesSendLetterModel> salesPersonsList = new ArrayList<SalesSendLetterModel>();
		try {
			salesPersonsList = salesSendLetterService.getSalesPersons(model.getCustomerNo());
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
	public int creatList(@RequestBody SalesSendLetterModel model) {

		model.setUpdateUser(getSession().getAttribute("employeeName").toString());
		logger.info("getSalesPersons:" + "検索開始");
		int index = 0;
		try {
			index = salesSendLetterService.creatList(model);
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("getSalesPersons" + "検索結束");
		return index;
	}

	@RequestMapping(value = "/addNewList", method = RequestMethod.POST)
	@ResponseBody
	public String addNewList(@RequestBody SalesSendLetterModel model) {

		model.setUpdateUser(getSession().getAttribute("employeeName").toString());
		logger.info("getSalesPersons:" + "検索開始");
		String name = "";
		try {
			name = salesSendLetterService.getMaxStorageListName();
			model.setName(name);
			salesSendLetterService.creatList(model);
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("getSalesPersons" + "検索結束");
		return name;
	}

	@RequestMapping(value = "/getLists", method = RequestMethod.POST)
	@ResponseBody
	public List<SalesSendLetterModel> getLists() {

		List<SalesSendLetterModel> salesPersonsList = new ArrayList<SalesSendLetterModel>();
		try {
			salesPersonsList = salesSendLetterService.getLists();
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("getSalesPersons" + "検索結束");
		return salesPersonsList;
	}

	@RequestMapping(value = "/listNameUpdate", method = RequestMethod.POST)
	@ResponseBody
	public void listNameUpdate(@RequestBody SalesSendLettersListName salesSendLettersListNames) {

		if (!salesSendLettersListNames.getStorageListName().equals(salesSendLettersListNames.getOldStorageListName())) {
			updateName(salesSendLettersListNames.getStorageListName(),
					salesSendLettersListNames.getOldStorageListName());
		}
	}

	@RequestMapping(value = "/customerListUpdate", method = RequestMethod.POST)
	@ResponseBody
	public String customerListUpdate(@RequestBody SalesSendLetterModel model) {
		try {
			salesSendLetterService.customerListUpdate(model.getStorageListName(), model.getCustomerList());
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("getSalesPersons" + "検索結束");
		return salesSendLetterService.getCustomerList(model.getStorageListName());
	}

	@RequestMapping(value = "/deleteCustomerList", method = RequestMethod.POST)
	@ResponseBody
	public String deleteCustomerList(@RequestBody SalesSendLetterModel model) {
		try {
			salesSendLetterService.deleteCustomerList(model);
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("getSalesPersons" + "検索結束");
		return "";
	}

	@RequestMapping(value = "/getCustomersByNos", method = RequestMethod.POST)
	@ResponseBody
	public List<SalesSendLetterModel> getCustomersByNos(@RequestBody SalesSendLetterModel model) {

		logger.info("getCustomers:" + "検索開始");
		List<SalesSendLetterModel> salesCustomersList = new ArrayList<SalesSendLetterModel>();
		try {
			salesCustomersList = salesSendLetterService.getSalesCustomersByNos(model.getCtmNos());
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("getCustomers" + "検索結束");
		return salesCustomersList;
	}

	@RequestMapping(value = "/deleteCustomerListByNo", method = RequestMethod.POST)
	@ResponseBody
	public String deleteCustomerListByNo(@RequestBody SalesSendLetterModel model) {

		logger.info("getCustomers:" + "検索開始");
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
		try {
			salesSendLetterService.deleteCustomerListByNo(model);
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("getCustomers" + "検索結束");
		return newCtmNos;
	}

	private void updateName(String storageListName, String oldStorageListName) {
		SalesSendLettersListName updateModel = new SalesSendLettersListName();
		updateModel.setUpdateUser(getSession().getAttribute("employeeName").toString());
		updateModel.setStorageListName(storageListName);
		updateModel.setOldStorageListName(oldStorageListName);
		try {
			salesSendLetterService.listNameUpdate(updateModel);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/deleteList", method = RequestMethod.POST)
	@ResponseBody
	public int deleteList(@RequestBody SalesSendLettersListName salesSendLettersListNames) {
		logger.info("getSalesPersons:" + "検索開始");
		int index = 0;
		try {
			index = salesSendLetterService.deleteList(salesSendLettersListNames.getStorageListName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("getSalesPersons" + "検索結束");
		return index;
	}
}
