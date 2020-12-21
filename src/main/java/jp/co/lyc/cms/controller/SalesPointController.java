package jp.co.lyc.cms.controller;

import java.io.File;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
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

import jp.co.lyc.cms.common.BaseController;
import jp.co.lyc.cms.model.SalesInfoModel;
import jp.co.lyc.cms.model.SalesProfitModel;
import jp.co.lyc.cms.service.SalesProfitService;
import jp.co.lyc.cms.util.UtilsController;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

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
				if (pointInfo.get(i).getPoint() != null)
					employeePoint += Integer.parseInt(pointInfo.get(i).getPoint());
				if (pointInfo.get(i).getSpecialsalesPoint() != null)
					employeePoint += Integer.parseInt(pointInfo.get(i).getSpecialsalesPoint());
				if (pointInfo.get(i).getProfitAll() != null)
					employeeProfit += Integer.parseInt(pointInfo.get(i).getProfitAll().replaceAll(",", ""));
				if (pointInfo.get(i).getSiteRoleNameAll() != null)
					employeeGrossProfit += Integer.parseInt(pointInfo.get(i).getSiteRoleNameAll().replaceAll(",", ""));
			} else if (pointInfo.get(i).getEmployeeStatus().equals("1")) {
				bpCount++;
				if (pointInfo.get(i).getPoint() != null)
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
			parameters.put("employeePoint", formatString((float) employeePoint));
			parameters.put("bpCount", Integer.toString(bpCount));
			parameters.put("bpPoint", formatString((float) bpPoint));
			parameters.put("totalPoint", formatString((float) totalPoint));
			parameters.put("employeeProfit", formatString((float) employeeProfit));
			parameters.put("employeeGrossProfit", formatString((float) employeeGrossProfit));
			parameters.put("bpProfit", formatString((float) bpProfit));
			parameters.put("bpGrossProfit", formatString((float) bpGrossProfit));
			parameters.put("totalProfit", formatString((float) totalProfit));
			parameters.put("totalGrossProfit", formatString((float) totalGrossProfit));

			JasperReport report = JasperCompileManager.compileReport(inputFile.getAbsolutePath());
			JasperPrint print = JasperFillManager.fillReport(report, parameters, new JREmptyDataSource());
			JasperExportManager.exportReportToPdfFile(print, outputFile.getAbsolutePath());
		} catch (JRException e) {
			logger.error(e.getMessage());
		}
		logger.info("SalesPointController.downloadPDF:" + "終了");
		return outputFile.getAbsolutePath();
	}

	private String formatString(Float data) {
		DecimalFormat df = new DecimalFormat("#,###");
		return df.format(data);
	}
}
