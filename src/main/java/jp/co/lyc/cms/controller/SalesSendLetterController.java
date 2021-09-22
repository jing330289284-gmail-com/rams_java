package jp.co.lyc.cms.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
		List<String> customersList = new ArrayList<String>();

		try {
			Date date = new Date();// 此时date为当前的时间
			SimpleDateFormat dateFormat = new SimpleDateFormat("YYYYMM");// 设置当前时间的格式，为年-月-日
			String nowMonth = dateFormat.format(date);
			String sendLetterMonth = salesSendLetterService.getSendLetterMonth();

			if (!nowMonth.equals(sendLetterMonth)) {
				salesSendLetterService.updateCustomers(nowMonth);
			}

			salesCustomersList = salesSendLetterService.getSalesCustomers();
			customersList = salesSendLetterService.getBusinessCount();
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("getCustomers" + "検索結束");

		for (int i = 0; i < salesCustomersList.size(); i++) {
			int count = 0;
			for (int j = 0; j < customersList.size(); j++) {
				if (salesCustomersList.get(i).getCustomerNo().equals(customersList.get(j))) {
					count++;
				}
			}
			salesCustomersList.get(i).setBusinessCount(String.valueOf(count));

			if ((salesCustomersList.get(i).getCommonMail() != null
					&& !salesCustomersList.get(i).getCommonMail().equals(""))
					|| (salesCustomersList.get(i).getPurchasingManagersMail() != null
							&& !salesCustomersList.get(i).getPurchasingManagersMail().equals(""))) {
				ArrayList<String> mailList = new ArrayList<String>();
				if (salesCustomersList.get(i).getCommonMail() != null
						&& !salesCustomersList.get(i).getCommonMail().equals(""))
					mailList.add(salesCustomersList.get(i).getCommonMail());
				if (salesCustomersList.get(i).getPurchasingManagersMail() != null
						&& !salesCustomersList.get(i).getPurchasingManagersMail().equals(""))
					mailList.add(salesCustomersList.get(i).getPurchasingManagersMail());
				if (mailList.size() > 0)
					salesCustomersList.get(i).setMailList(mailList);
			}

			if (salesCustomersList.get(i).getCommonMail() != null
					&& !salesCustomersList.get(i).getCommonMail().equals("")) {
				salesCustomersList.get(i).setPurchasingManagersMail(salesCustomersList.get(i).getCommonMail());
			}

			if (salesCustomersList.get(i).getLevelCode() != null) {
				salesCustomersList.get(i).setCustomerName(salesCustomersList.get(i).getCustomerName() + " ("
						+ salesCustomersList.get(i).getLevelCode() + ")");
			}
		}

		for (int i = 0; i < salesCustomersList.size(); i++) {
			if (salesCustomersList.get(i).getMailList() == null) {
				salesCustomersList.remove(i);
				i--;
			}
		}

		for (int i = 0; i < salesCustomersList.size(); i++) {
			salesCustomersList.get(i).setRowId(i);
		}

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

	@RequestMapping(value = "/customerSendMailStorageListUpdate", method = RequestMethod.POST)
	@ResponseBody
	public void customerSendMailStorageListUpdate(@RequestBody SalesSendLetterModel model) {
		SalesSendLetterModel salesSendLetter = new SalesSendLetterModel();
		String mainChargeList = "";
		String departmentCodeList = "";
		try {
			salesSendLetter = salesSendLetterService.getMainChargeList(model.getStorageListName());
		} catch (Exception e) {
			e.printStackTrace();
		}

		String[] selectedRowKeys = salesSendLetter.getMainChargeList().split(";");
		String[] selectedRowNames = salesSendLetter.getDepartmentCodeList().split(";");

		for (int i = 0; i < selectedRowKeys.length; i++) {
			String[] customerRowKeys = selectedRowKeys[i].split(":");
			if (model.getCustomerNo().equals(customerRowKeys[0])) {
				mainChargeList += model.getCustomerNo() + ":" + model.getMainChargeList() + ";";

			} else {
				mainChargeList += selectedRowKeys[i] + ";";
			}
		}

		for (int i = 0; i < selectedRowNames.length; i++) {
			String[] customerRowNames = selectedRowNames[i].split(":");
			if (model.getCustomerNo().equals(customerRowNames[0])) {
				departmentCodeList += model.getCustomerNo() + ":" + model.getDepartmentCodeList() + ";";

			} else {
				departmentCodeList += selectedRowNames[i] + ";";
			}
		}

		try {
			salesSendLetterService.customerSendMailStorageListUpdate(model.getStorageListName(), mainChargeList,
					departmentCodeList);
		} catch (Exception e) {
			e.printStackTrace();
		}
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
		SalesSendLetterModel salesSendLetter = new SalesSendLetterModel();
		List<String> customersList = new ArrayList<String>();
		try {
			salesCustomersList = salesSendLetterService.getSalesCustomersByNos(model.getCtmNos());
			salesSendLetter = salesSendLetterService.getMainChargeList(model.getStorageListName());
			customersList = salesSendLetterService.getBusinessCount();
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("getCustomers" + "検索結束");
		if (model.getStorageListName() != "" && model.getStorageListName() != null && salesSendLetter != null) {
			String[] selectedRowKeys = salesSendLetter.getMainChargeList().split(";");
			String[] selectedRowNames = salesSendLetter.getDepartmentCodeList().split(";");

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

		for (int i = 0; i < salesCustomersList.size(); i++) {
			int count = 0;
			for (int j = 0; j < customersList.size(); j++) {
				if (salesCustomersList.get(i).getCustomerNo().equals(customersList.get(j))) {
					count++;
				}
			}
			salesCustomersList.get(i).setBusinessCount(String.valueOf(count));

			if ((salesCustomersList.get(i).getCommonMail() != null
					&& !salesCustomersList.get(i).getCommonMail().equals(""))
					|| (salesCustomersList.get(i).getPurchasingManagersMail() != null
							&& !salesCustomersList.get(i).getPurchasingManagersMail().equals(""))) {
				ArrayList<String> mailList = new ArrayList<String>();
				if (salesCustomersList.get(i).getCommonMail() != null
						&& !salesCustomersList.get(i).getCommonMail().equals(""))
					mailList.add(salesCustomersList.get(i).getCommonMail());
				if (salesCustomersList.get(i).getPurchasingManagersMail() != null
						&& !salesCustomersList.get(i).getPurchasingManagersMail().equals(""))
					mailList.add(salesCustomersList.get(i).getPurchasingManagersMail());
				if (mailList.size() > 0)
					salesCustomersList.get(i).setMailList(mailList);
			}

			if (salesCustomersList.get(i).getCommonMail() != null
					&& !salesCustomersList.get(i).getCommonMail().equals("")) {
				salesCustomersList.get(i).setPurchasingManagersMail(salesCustomersList.get(i).getCommonMail());
			}

			if (salesCustomersList.get(i).getLevelCode() != null) {
				salesCustomersList.get(i).setCustomerName(salesCustomersList.get(i).getCustomerName() + " ("
						+ salesCustomersList.get(i).getLevelCode() + ")");
			}
		}

		for (int i = 0; i < salesCustomersList.size(); i++) {
			if (salesCustomersList.get(i).getMailList() == null) {
				salesCustomersList.remove(i);
				i--;
			}
		}

		for (int i = 0; i < salesCustomersList.size(); i++) {
			salesCustomersList.get(i).setRowId(i);
		}

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

		SalesSendLetterModel salesSendLetter = new SalesSendLetterModel();
		try {
			salesSendLetter = salesSendLetterService.getMainChargeList(model.getStorageListName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		String[] selectedRowKeys = salesSendLetter.getMainChargeList().split(";");
		String[] selectedRowNames = salesSendLetter.getDepartmentCodeList().split(";");
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
