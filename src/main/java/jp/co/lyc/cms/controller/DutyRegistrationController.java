package jp.co.lyc.cms.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Comparator;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.thoughtworks.xstream.io.path.Path;

import edu.emory.mathcs.backport.java.util.Collections;
import jp.co.lyc.cms.common.BaseController;
import jp.co.lyc.cms.model.BreakTimeModel;
import jp.co.lyc.cms.model.DutyRegistrationModel;
import jp.co.lyc.cms.model.EmployeeWorkTimeModel;
import jp.co.lyc.cms.service.DutyRegistrationService;
import jp.co.lyc.cms.util.UtilsController;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.data.JRMapCollectionDataSource;
import net.sf.jasperreports.engine.design.JRDesignVariable;
import net.sf.jasperreports.engine.util.JRLoader;

@Controller
@RequestMapping(value = "/dutyRegistration")
public class DutyRegistrationController extends BaseController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	DutyRegistrationService dutyRegistrationService;

	/**
	 * 登録ボタン
	 * 
	 * @param topCustomerMod
	 * @return
	 */
	@RequestMapping(value = "/breakTimeInsert", method = RequestMethod.POST)
	@ResponseBody
	public boolean breakTimeInsert(@RequestBody DutyRegistrationModel dutyRegistrationModel) {
		logger.info("DutyRegistrationController.breakTimeInsert:" + "登録開始");
		boolean result = false;
		logger.info(dutyRegistrationModel.toString());
//		DutyRegistrationModel checkMod = dutyRegistrationService.selectDutyRegistration(dutyRegistrationModel.toHashMap());
		DutyRegistrationModel checkMod = null;
		if (checkMod != null) {
			result = this.insert(dutyRegistrationModel);
		} else {
			result = this.update(dutyRegistrationModel);
		}
		logger.info("DutyRegistrationController.breakTimeInsert:" + "登録終了");
		return result;
	}

	/**
	 */
	@RequestMapping(value = "/dutyInsert", method = RequestMethod.POST)
	@ResponseBody
	public boolean dutyInsert(@RequestBody String requestJson) {
		logger.info("DutyRegistrationController.dutyInsert:" + "登録開始");
		boolean result = false;
		logger.info(requestJson);
		JSONObject jsonObject = JSON.parseObject(requestJson);
		JSONObject tempJsonObject = null;
		JSONArray jsonArray = jsonObject.getJSONArray("dateData");
		int dataSize = jsonArray.size();
		HttpSession loginSession = getSession();
		EmployeeWorkTimeModel[] arrEmployeeWorkTimeModel = new EmployeeWorkTimeModel[dataSize];
		for (int i = 0; i < dataSize; i++) {
			tempJsonObject = jsonArray.getJSONObject(i);
			tempJsonObject.put("employeeNo", super.getSession().getAttribute("employeeNo"));
			tempJsonObject.put("yearAndMonth", jsonObject.getOrDefault("yearMonth", ""));
			tempJsonObject.put("morningTime", tempJsonObject.getOrDefault("startTime", ""));
			tempJsonObject.put("afternoonTime", tempJsonObject.getOrDefault("endTime", ""));
			tempJsonObject.put("holidayFlag", tempJsonObject.getOrDefault("isWork", ""));
			tempJsonObject.put("workTime", tempJsonObject.getOrDefault("workHour", ""));
			tempJsonObject.put("breakTime", tempJsonObject.getOrDefault("sleepHour", ""));
			tempJsonObject.put("confirmFlag", "0");
			tempJsonObject.put("siteCustomer", jsonObject.getOrDefault("siteCustomer", ""));
			tempJsonObject.put("customer", jsonObject.getOrDefault("customer", ""));
			tempJsonObject.put("siteResponsiblePerson", jsonObject.getOrDefault("siteResponsiblePerson", ""));
			tempJsonObject.put("systemName", jsonObject.getOrDefault("systemName", ""));
			tempJsonObject.put("updateUser", super.getSession().getAttribute("employeeNo"));

			EmployeeWorkTimeModel employeeWorkTimeModel = EmployeeWorkTimeModel
					.fromHashMap(tempJsonObject.getInnerMap());
			EmployeeWorkTimeModel[] checkMod = dutyRegistrationService.selectDuty(employeeWorkTimeModel.toHashMap());
			if (checkMod.length > 0) {
				result = this.update(employeeWorkTimeModel);
			} else {
				result = this.insert(employeeWorkTimeModel);
			}
		}
		logger.info("DutyRegistrationController.dutyInsert:" + "登録終了");
		return result;
	}

	/**
	 */
	@RequestMapping(value = "/downloadPDF", method = RequestMethod.POST)
	@ResponseBody
	public String downloadPDF(@RequestBody String requestJson) {
		logger.info("DutyRegistrationController.downloadPDF:" + "開始");
		JSONObject jsonObject = JSON.parseObject(requestJson);
		String yearMonth = (String) jsonObject.getOrDefault("yearMonth", "");
		Map<String, Object> dutyData = this.getDutyInfo(requestJson);
		ArrayList<Map<String, Object>> dutyDate = this.dutySelect(requestJson);
		String user = (String) dutyData.get("employeeName");
		File nowFile = new File(".").getAbsoluteFile();
		File inputFile = new File(nowFile.getParentFile(), "src/main/resources/PDFTemplate/dutyTemplate.jrxml");
		File outputFile = new File(UtilsController.DOWNLOAD_PATH_BASE + "dutyReport/", user + "_" + yearMonth + ".pdf");
		outputFile.getParentFile().mkdirs();
		try {
			Map<String, Object> parameters = new HashMap<String, Object>();
			Collection<Map<String, ?>> source = new ArrayList<>();
			parameters.put("Year", yearMonth.substring(0, 4));
			parameters.put("Month", yearMonth.substring(4, 6));
			parameters.put("siteCustomer", dutyData.get("siteCustomer"));
			parameters.put("customer", dutyData.get("customer"));
			parameters.put("siteResponsiblePerson", dutyData.get("siteResponsiblePerson"));
			parameters.put("systemName", dutyData.get("systemName"));
			parameters.put("user", user);

			ArrayList<Map<String, ?>> tableData = new ArrayList<>();
			Map<String, Object> rowData = new Hashtable<>();
			int totalWorkDays = 0;
			Double totalWorkTime = 0.0;
			for (int i = 0; i < dutyDate.size(); i++) {
				rowData = new Hashtable<>();
				rowData = dutyDate.get(i);
				rowData.put("morningTime", UtilsController.TimeInsertChar((String) dutyDate.get(i).get("startTime")));
				rowData.put("afternoonTime", UtilsController.TimeInsertChar((String) dutyDate.get(i).get("endTime")));
				rowData.put("workTime", dutyDate.get(i).get("workHour"));
				rowData.put("breakTime", StringUtils.defaultString((String) dutyDate.get(i).get("breakTime"), "0"));
				String nowDate = yearMonth.substring(0, 4) + "-" + yearMonth.substring(4, 6) + "-"
						+ StringUtils.leftPad((String) dutyDate.get(i).get("day"), 2, "0");
				rowData.put("isBreak", UtilsController.isHoliday(nowDate));
				tableData.add(rowData);
				totalWorkTime += Double.valueOf((String) dutyDate.get(i).get("workHour"));
				if (rowData.get("isWork").equals("1"))
					totalWorkDays++;
			}
			Collections.sort(tableData, new Comparator<Map<String, Object>>() {
				public int compare(Map<String, Object> first, Map<String, Object> second) {
					// TODO: Null checking, both for maps and values
					Integer firstValue = Integer.valueOf((String) first.get("day"));
					Integer secondValue = Integer.valueOf((String) second.get("day"));
					return firstValue.compareTo(secondValue);
				}
			});
			JRDataSource ds = new JRBeanCollectionDataSource(tableData);
			parameters.put("dataTableResource", ds);
			parameters.put("TotalWorkTime", totalWorkTime);
			parameters.put("TotalWorkDays", totalWorkDays);
			JasperReport report = JasperCompileManager.compileReport(inputFile.getAbsolutePath());
			JasperPrint print = JasperFillManager.fillReport(report, parameters, new JREmptyDataSource());
			JasperExportManager.exportReportToPdfFile(print, outputFile.getAbsolutePath());
		} catch (JRException e) {
			logger.error(e.getMessage());
		}
		logger.info("DutyRegistrationController.downloadPDF:" + "終了");
		return outputFile.getAbsolutePath();
	}

	/**
	 */
	@RequestMapping(value = "/getDutyInfo", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getDutyInfo(@RequestBody String requestJson) {
		logger.info("DutyRegistrationController.dutySelect:" + "検索開始");
		Map<String, Object> result = new HashMap<String, Object>();
		JSONObject jsonObject = JSON.parseObject(requestJson);
		jsonObject.put("employeeNo", super.getSession().getAttribute("employeeNo"));
		jsonObject.put("breakTimeYearMonth", jsonObject.getOrDefault("yearMonth", ""));

		BreakTimeModel breakTimeModel = dutyRegistrationService.selectDutyRegistration(jsonObject.getInnerMap());

		result.put("breakTime", breakTimeModel);
		result.put("employeeNo", super.getSession().getAttribute("employeeNo"));
		result.put("employeeName", super.getSession().getAttribute("employeeName"));
		ArrayList<Map<String, Object>> dutyData = this.dutySelect(requestJson);
		result.put("dateData", dutyData);
		if (dutyData != null && dutyData.size() > 0) {
			result.put("siteCustomer", dutyData.get(0).get("siteCustomer"));
			result.put("customer", dutyData.get(0).get("customer"));
			result.put("siteResponsiblePerson", dutyData.get(0).get("siteResponsiblePerson"));
			result.put("systemName", dutyData.get(0).get("systemName"));
		}
		logger.info("DutyRegistrationController.dutySelect:" + "検索終了");
		return result;
	}

	public ArrayList<Map<String, Object>> dutySelect(String requestJson) {
		logger.info("DutyRegistrationController.dutySelect:" + "検索開始");
		ArrayList<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		EmployeeWorkTimeModel[] arrEmployeeWorkTimeModel = null;
		JSONObject jsonObject = JSON.parseObject(requestJson);
		jsonObject.put("employeeNo", super.getSession().getAttribute("employeeNo"));
		jsonObject.put("yearAndMonth", jsonObject.getOrDefault("yearMonth", ""));
		arrEmployeeWorkTimeModel = dutyRegistrationService.selectDuty(jsonObject.getInnerMap());
		Map<String, Object> tempMap = new HashMap<String, Object>();
		for (EmployeeWorkTimeModel employeeWorkTimeModel : arrEmployeeWorkTimeModel) {
			tempMap = new HashMap<String, Object>();
			tempMap = employeeWorkTimeModel.toHashMap();
			tempMap.put("day", employeeWorkTimeModel.getDay());
			tempMap.put("yearMonth", employeeWorkTimeModel.getYearAndMonth());
			tempMap.put("startTime", employeeWorkTimeModel.getMorningTime());
			tempMap.put("endTime", employeeWorkTimeModel.getAfternoonTime());
			tempMap.put("isWork", Float.parseFloat(employeeWorkTimeModel.getWorkTime()) > 0.0 ? "1" : "0");
			tempMap.put("workHour", employeeWorkTimeModel.getWorkTime());
			tempMap.put("workContent", employeeWorkTimeModel.getWorkContent());
			tempMap.put("remark", employeeWorkTimeModel.getRemark());
			result.add(tempMap);
		}
		if (result.size() > 0) {
			result.get(0).put("siteCustomer", arrEmployeeWorkTimeModel[0].getSiteCustomer());
			result.get(0).put("customer", arrEmployeeWorkTimeModel[0].getCustomer());
			result.get(0).put("siteResponsiblePerson", arrEmployeeWorkTimeModel[0].getSiteResponsiblePerson());
			result.get(0).put("systemName", arrEmployeeWorkTimeModel[0].getSystemName());
		}
		logger.info("DutyRegistrationController.dutySelect:" + "検索終了");
		return result;
	}

	/**
	 * インサート
	 * 
	 * @param topCustomerMod
	 * @return
	 */
	public boolean insert(DutyRegistrationModel dutyRegistrationModel) {
		logger.info("DutyRegistrationController.insert::" + "インサート開始");
		boolean result = false;
		dutyRegistrationModel.setEmployeeNo((String) super.getSession().getAttribute("employeeNo"));
		HashMap<String, Object> sendMap = dutyRegistrationModel.toHashMap();
		result = dutyRegistrationService.insertDutyRegistration(sendMap);
		logger.info("DutyRegistrationController.insert::" + "インサート終了");
		return result;
	}

	/**
	 * インサート
	 * 
	 * @param topCustomerMod
	 * @return
	 */
	public boolean insert(EmployeeWorkTimeModel employeeWorkTimeModel) {
		logger.info("DutyRegistrationController.insert::" + "インサート開始");
		boolean result = false;
		Map<String, Object> sendMap = employeeWorkTimeModel.toHashMap();
		result = dutyRegistrationService.insertDuty(sendMap);
		logger.info("DutyRegistrationController.insert::" + "インサート終了");
		return result;
	}

	/**
	 * アップデート
	 * 
	 * @param topCustomerMod
	 * @return
	 */
	public boolean update(DutyRegistrationModel dutyRegistrationModel) {
		logger.info("DutyRegistrationController.update:" + "アップデート開始");
		boolean result = false;
		HashMap<String, Object> sendMap = dutyRegistrationModel.toHashMap();
		result = dutyRegistrationService.updateDutyRegistration(sendMap);
		logger.info("DutyRegistrationController.update:" + "アップデート終了");
		return result;
	}

	/**
	 * アップデート
	 * 
	 * @param topCustomerMod
	 * @return
	 */
	public boolean update(EmployeeWorkTimeModel employeeWorkTimeModel) {
		logger.info("DutyRegistrationController.update:" + "アップデート開始");
		boolean result = false;
		Map<String, Object> sendMap = employeeWorkTimeModel.toHashMap();
		result = dutyRegistrationService.updateDuty(sendMap);
		logger.info("DutyRegistrationController.update:" + "アップデート終了");
		return result;
	}
}
