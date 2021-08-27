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
				(certificatePrintingModel.getStamp().equals("0")
						? "src/main/resources/PDFTemplate/certificateIncumbencyStamp.jrxml"
						: "src/main/resources/PDFTemplate/certificateIncumbencyNoStamp.jrxml"));
		File outputFile = new File(UtilsController.DOWNLOAD_PATH_BASE + "certificate/", "証明書" + ".pdf");
		outputFile.getParentFile().mkdirs();
		try {
			Map<String, Object> parameters = new HashMap<String, Object>();

			parameters.put("certificate", certificatePrintingModel.getCertificate());
			parameters.put("employeeName", certificatePrintingModel.getEmployeeName());
			parameters.put("furigana", certificatePrintingModel.getFurigana());
			parameters.put("address", certificatePrintingModel.getAddress());
			parameters.put("postcode", certificatePrintingModel.getPostcode());
			parameters.put("firstHalfAddress", certificatePrintingModel.getFirstHalfAddress());
			parameters.put("lastHalfAddress", certificatePrintingModel.getLastHalfAddress());
			parameters.put("birthday", dateChange(certificatePrintingModel.getBirthday()));
			parameters.put("intoCompanyYearAndMonth",
					dateChange(certificatePrintingModel.getIntoCompanyYearAndMonth()));
			parameters.put("nowYearAndMonth", dateChange(certificatePrintingModel.getNowYearAndMonth()));
			parameters.put("workingTime", certificatePrintingModel.getWorkingTime());
			parameters.put("lastDayofYearAndMonth", dateChange(certificatePrintingModel.getLastDayofYearAndMonth()));
			parameters.put("retirementYearAndMonth", dateChange(certificatePrintingModel.getRetirementYearAndMonth()));
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

	private String dateChange(String date) {
		if (date == null || date.length() < 8) {
			return "";
		} else {
			return date.substring(0, 4) + "年" + date.substring(4, 6) + "月" + date.substring(6, 8) + "日";
		}
	}
}
