package jp.co.lyc.cms.controller;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Comparator;
import java.util.Date;

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
import jp.co.lyc.cms.model.SalesInfoModel;
import jp.co.lyc.cms.model.SalesProfitModel;
import jp.co.lyc.cms.service.DutyRegistrationService;
import jp.co.lyc.cms.service.SalesProfitService;
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
@RequestMapping(value = "/SalesPointController")
public class SalesPointController extends BaseController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	SalesProfitController salesProfitController;

	@Autowired
	SalesProfitService salesProfitService;

	/**
	 * @throws ParseException
	 */
	@RequestMapping(value = "/downloadPDF", method = RequestMethod.POST)
	@ResponseBody
	public String downloadPDF(@RequestBody SalesProfitModel salesProfitModel) throws ParseException {
		logger.info("SalesPointController.downloadPDF:" + "開始");
		String employeeNo = salesProfitModel.getEmployeeName();
		List<SalesInfoModel> employeeNameToNo = salesProfitService.getEmployeeName();
		for (int i = 0; i < employeeNameToNo.size(); i++) {
			if (employeeNo.equals(employeeNameToNo.get(i).getEmployeeNo())) {
				employeeNo = employeeNameToNo.get(i).getEmployeeFristName()
						+ employeeNameToNo.get(i).getEmployeeLastName();
			}
		}
		String startTime = "";
		String endTime = "";
		String yearAndMonth = "";
		int newEmployeeCount = 0;
		int employeeCount = 0;
		int employeePoint = 0;
		int employeeProfit = 0;
		int employeeGrossProfit = 0;

		int bpCount = 0;
		int bpPoint = 0;
		int bpProfit = 0;
		int bpGrossProfit = 0;

		int totalPoint = 0;
		int totalProfit = 0;
		int totalGrossProfit = 0;

		if (salesProfitModel.getStartDate() != null && salesProfitModel.getEndDate() != null) {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMM");
			startTime = dateFormat.format(salesProfitModel.getStartDate()).toString();
			endTime = dateFormat.format(salesProfitModel.getEndDate()).toString();
			yearAndMonth = startTime.substring(0, 4) + "年" + startTime.substring(4, 6) + "月" + " - "
					+ endTime.substring(0, 4) + "年" + endTime.substring(4, 6) + "月";
		}

		List<SalesInfoModel> pointInfo = salesProfitController.getPointInfo(salesProfitModel);

		if (yearAndMonth.equals("")) {
			yearAndMonth = pointInfo.get(0).getYearAndMonth().substring(0, 4) + "年"
					+ pointInfo.get(0).getYearAndMonth().substring(4, 6) + "月" + " - "
					+ pointInfo.get(pointInfo.size() - 1).getYearAndMonth().substring(0, 4) + "年"
					+ pointInfo.get(pointInfo.size() - 1).getYearAndMonth().substring(4, 6) + "月";
		}

		for (int i = 0; i < pointInfo.size(); i++) {
			if (pointInfo.get(i).getEmployeeStatus().equals("0")) {
				if (pointInfo.get(i).getIntoCompanyCode().equals("0")) {
					newEmployeeCount++;
				} else {
					employeeCount++;
				}
				employeePoint += Integer.parseInt(pointInfo.get(i).getPoint());
				if (pointInfo.get(i).getSpecialsalesPoint() != null)
					employeePoint += Integer.parseInt(pointInfo.get(i).getSpecialsalesPoint());
				if (pointInfo.get(i).getProfitAll() != null)
					employeeProfit += Integer.parseInt(pointInfo.get(i).getProfitAll().replaceAll(",", ""));
				if (pointInfo.get(i).getSiteRoleNameAll() != null)
					employeeGrossProfit += Integer.parseInt(pointInfo.get(i).getSiteRoleNameAll().replaceAll(",", ""));
			} else if (pointInfo.get(i).getEmployeeStatus().equals("1")) {
				bpCount++;
				bpPoint += Integer.parseInt(pointInfo.get(i).getPoint());
				if (pointInfo.get(i).getSpecialsalesPoint() != null)
					bpPoint += Integer.parseInt(pointInfo.get(i).getSpecialsalesPoint());
				if (pointInfo.get(i).getProfitAll() != null)
					bpProfit += Integer.parseInt(pointInfo.get(i).getProfitAll().replaceAll(",", ""));
				if (pointInfo.get(i).getSiteRoleNameAll() != null)
					bpGrossProfit += Integer.parseInt(pointInfo.get(i).getSiteRoleNameAll().replaceAll(",", ""));
			}
		}
		totalPoint = employeePoint + bpPoint;
		totalProfit = employeeProfit + bpProfit;
		totalGrossProfit = employeeGrossProfit + bpGrossProfit;

		File nowFile = new File(".").getAbsoluteFile();
		File inputFile = new File(nowFile.getParentFile(), "src/main/resources/PDFTemplate/salesPoint.jrxml");
		File outputFile = new File(UtilsController.DOWNLOAD_PATH_BASE + "dutyReport/", employeeNo + ".pdf");
		outputFile.getParentFile().mkdirs();
		try {
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("employeeNo", employeeNo);
			parameters.put("yearAndMonth", yearAndMonth);
			parameters.put("newEmployeeCount", Integer.toString(newEmployeeCount));
			parameters.put("employeeCount", Integer.toString(employeeCount));
			parameters.put("employeePoint", Integer.toString(employeePoint));
			parameters.put("bpCount", Integer.toString(bpCount));
			parameters.put("bpPoint", Integer.toString(bpPoint));
			parameters.put("totalPoint", Integer.toString(totalPoint));
			parameters.put("employeeProfit", Integer.toString(employeeProfit));
			parameters.put("employeeGrossProfit", Integer.toString(employeeGrossProfit));
			parameters.put("bpProfit", Integer.toString(bpProfit));
			parameters.put("bpGrossProfit", Integer.toString(bpGrossProfit));
			parameters.put("totalProfit", Integer.toString(totalProfit));
			parameters.put("totalGrossProfit", Integer.toString(totalGrossProfit));

			JasperReport report = JasperCompileManager.compileReport(inputFile.getAbsolutePath());
			JasperPrint print = JasperFillManager.fillReport(report, parameters, new JREmptyDataSource());
			JasperExportManager.exportReportToPdfFile(print, outputFile.getAbsolutePath());
		} catch (JRException e) {
			logger.error(e.getMessage());
		}
		logger.info("SalesPointController.downloadPDF:" + "終了");
		return outputFile.getAbsolutePath();
	}
}
