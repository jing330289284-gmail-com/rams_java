package jp.co.lyc.cms.controller;

import java.awt.geom.Area;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.aspectj.weaver.ast.And;
import org.exolab.castor.xml.Marshaller.NilObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.amazonaws.services.s3.AmazonS3;

import ch.qos.logback.core.property.FileExistsPropertyDefiner;
import jp.co.lyc.cms.model.EmployeeInformationModel;
import jp.co.lyc.cms.service.EmployeeInformationService;
import net.sf.jasperreports.components.barbecue.BarcodeProviders.ShipmentIdentificationNumberProvider;

@Controller
@RequestMapping(value = "/EmployeeInformation")
public class EmployeeInformationController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	String errorsMessage = "";
	@Autowired
	EmployeeInformationService employeeInformationService;

	@RequestMapping(value = "/getEmployeeInformation", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getEmployeeInformation() {
		logger.info("GetEmployeeInfoController.getEmployeeInfo:" + "検索開始");

		List<EmployeeInformationModel> employeeList = new ArrayList<EmployeeInformationModel>();
		employeeList = employeeInformationService.getEmployeeInformation();
		Date date = new Date();
		// 日数計算
		for (int i = 0; i < employeeList.size(); i++) {
			if (employeeList.get(i).getStayPeriod() == null || employeeList.get(i).getStayPeriod().equals("")) {
				employeeList.get(i).setStayPeriod("");
			} else {
				employeeList.get(i)
						.setStayPeriod(Integer.toString(dateDiff(date, employeeList.get(i).getStayPeriod())));
			}

			if (employeeList.get(i).getPassportStayPeriod() == null
					|| employeeList.get(i).getPassportStayPeriod().equals("")) {
				employeeList.get(i).setPassportStayPeriod("");
			} else {
				employeeList.get(i).setPassportStayPeriod(
						Integer.toString(dateDiff(date, employeeList.get(i).getPassportStayPeriod())));
			}

			if (employeeList.get(i).getContractDeadline() == null
					|| employeeList.get(i).getContractDeadline().equals("")) {
				employeeList.get(i).setContractDeadline("");
			} else {
				employeeList.get(i).setContractDeadline(
						Integer.toString(dateDiff(date, employeeList.get(i).getContractDeadline())));
			}

			String nowTime = Integer.toString(date.getMonth() + 1) + Integer.toString(date.getDate());
			nowTime = String.format("%0" + 4 + "d", Integer.parseInt(nowTime));
			if (employeeList.get(i).getBirthday() == null || employeeList.get(i).getBirthday().equals("")) {
				employeeList.get(i).setBirthday("");
			} else {
				if (Integer.parseInt(nowTime) > Integer.parseInt(employeeList.get(i).getBirthday())) {
					String year = Integer.toString(date.getYear() + 1901);
					employeeList.get(i)
							.setBirthday(Integer.toString(dateDiff(date, year + employeeList.get(i).getBirthday())));
				} else if (Integer.parseInt(nowTime) < Integer.parseInt(employeeList.get(i).getBirthday())) {
					String year = Integer.toString(date.getYear() + 1900);
					employeeList.get(i)
							.setBirthday(Integer.toString(dateDiff(date, year + employeeList.get(i).getBirthday())));
				} else {
					employeeList.get(i).setBirthday("0");
				}
			}
		}

		// 排序
		List<EmployeeInformationModel> newEmployeeList = new ArrayList<EmployeeInformationModel>();
		for (int i = 0; i < employeeList.size(); i++) {
			if (!employeeList.get(i).getDealDistinctioCode().equals("2")
					&& ((employeeList.get(i).getStayPeriod().equals("") ? false
							: Integer.parseInt(employeeList.get(i).getStayPeriod()) <= 90)
							|| (employeeList.get(i).getBirthday().equals("") ? false
									: Integer.parseInt(employeeList.get(i).getBirthday()) <= 7)
							|| (employeeList.get(i).getContractDeadline().equals("") ? false
									: Integer.parseInt(employeeList.get(i).getContractDeadline()) <= 60))) {
				newEmployeeList.add(employeeList.get(i));
				employeeList.remove(i);
				i--;
			}
		}

		for (int i = 0; i < employeeList.size(); i++) {
			newEmployeeList.add(employeeList.get(i));
		}

		for (int i = 0; i < newEmployeeList.size(); i++) {
			newEmployeeList.get(i).setRowNo(i + 1);
		}
		Map<String, Object> result = new HashMap<>();
		result.put("data", newEmployeeList);
		logger.info("GetEmployeeInfoController.getEmployeeInfo:" + "検索結束");
		return result;
	}

	@RequestMapping(value = "/updateEmployeeInformation", method = RequestMethod.POST)
	@ResponseBody
	public void updateEmployeeInformation(@RequestBody EmployeeInformationModel model) {
		List<EmployeeInformationModel> list = new ArrayList<EmployeeInformationModel>();
		for (int i = 0; i < model.getEmployeeNos().length; i++) {
			EmployeeInformationModel tempModel = new EmployeeInformationModel();
			tempModel.setEmployeeNo(model.getEmployeeNos()[i]);
			tempModel.setDealDistinctioCode(
					model.getDealDistinctioCodes()[i].equals("") ? "0" : model.getDealDistinctioCodes()[i]);
			list.add(tempModel);
		}
		employeeInformationService.updateEmployeeInformation(list);
	}

	public static int test(Date dateFrom, String dateToString) {
		List<EmployeeInformationModel> array = new ArrayList<EmployeeInformationModel>();
		array.add(null);
		array.clear();
		array.add(null);
		EmployeeInformationModel.getSerialversionuid();
		return -1;
	}

	public static int dateDiff(Date dateFrom, String dateToString) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date dateTo = null;

		// Date型に変換
		try {
			dateTo = sdf.parse(dateToString);
		} catch (java.text.ParseException e) {
			e.printStackTrace();
		}

		// 差分の日数を計算する
		long dateTimeTo = dateTo.getTime();
		long dateTimeFrom = dateFrom.getTime();
		long dayDiff = (dateTimeTo - dateTimeFrom) / (1000 * 60 * 60 * 24);

		// System.out.println("差分日数 : " + dayDiff);
		return (int) dayDiff;
	}
}
