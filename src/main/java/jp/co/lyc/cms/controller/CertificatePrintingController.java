package jp.co.lyc.cms.controller;

import java.io.File;
import java.util.HashMap;
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
import jp.co.lyc.cms.model.CertificatePrintingModel;
import jp.co.lyc.cms.service.DutyRegistrationService;
import jp.co.lyc.cms.util.UtilsController;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

@Controller
@RequestMapping(value = "/certificatePrinting")
public class CertificatePrintingController extends BaseController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	DutyRegistrationService dutyRegistrationService;

	/**
	 */
	@RequestMapping(value = "/downloadPDF", method = RequestMethod.POST)
	@ResponseBody
	public String downloadPDF(@RequestBody CertificatePrintingModel certificatePrintingModel) {
		logger.info("DutyRegistrationController.downloadPDF:" + "開始");
		File nowFile = new File(".").getAbsoluteFile();
		File inputFile = new File(nowFile.getParentFile(),
				"src/main/resources/PDFTemplate/certificateIncumbency.jrxml");
		File outputFile = new File(UtilsController.DOWNLOAD_PATH_BASE + "dutyReport/", "test" + ".pdf");
		outputFile.getParentFile().mkdirs();
		try {
			Map<String, Object> parameters = new HashMap<String, Object>();

			parameters.put("certificate", certificatePrintingModel.getCertificate());
			parameters.put("employeeName", certificatePrintingModel.getEmployeeName());
			parameters.put("address", certificatePrintingModel.getAddress());
			parameters.put("birthday", certificatePrintingModel.getBirthday());
			parameters.put("intoCompanyYearAndMonth", certificatePrintingModel.getIntoCompanyYearAndMonth());
			parameters.put("nowYearAndMonth", certificatePrintingModel.getNowYearAndMonth());
			parameters.put("workingTime", certificatePrintingModel.getWorkingTime());
			parameters.put("lastDayofYearAndMonth", certificatePrintingModel.getLastDayofYearAndMonth());
			parameters.put("retirementYearAndMonth", certificatePrintingModel.getRetirementYearAndMonth());
			parameters.put("occupationCode", certificatePrintingModel.getOccupationCode());
			parameters.put("remark", certificatePrintingModel.getRemark());

			JasperReport report = JasperCompileManager.compileReport(inputFile.getAbsolutePath());
			JasperPrint print = JasperFillManager.fillReport(report, parameters, new JREmptyDataSource());
			JasperExportManager.exportReportToPdfFile(print, outputFile.getAbsolutePath());
		} catch (JRException e) {
			logger.error(e.getMessage());
		}
		logger.info("DutyRegistrationController.downloadPDF:" + "終了");
		return outputFile.getAbsolutePath();
	}
}
