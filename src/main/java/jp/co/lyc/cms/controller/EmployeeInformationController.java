package jp.co.lyc.cms.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

import jp.co.lyc.cms.model.EmployeeInformationModel;
import jp.co.lyc.cms.service.EmployeeInformationService;

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
			if (!(employeeList.get(i).getStayPeriod() == null || employeeList.get(i).getStayPeriod().equals(""))) {
				employeeList.get(i).setStayPeriodDate(dateDiff(date, employeeList.get(i).getStayPeriod()));
			}
			if (!(employeeList.get(i).getPassportStayPeriod() == null
					|| employeeList.get(i).getPassportStayPeriod().equals(""))) {
				employeeList.get(i)
						.setPassportStayPeriodDate(dateDiff(date, employeeList.get(i).getPassportStayPeriod()));
			}

			if (!(employeeList.get(i).getContractDeadline() == null
					|| employeeList.get(i).getContractDeadline().equals(""))) {
				employeeList.get(i).setContractDeadlineDate(dateDiff(date, employeeList.get(i).getContractDeadline()));
			}

			String nowTime = Integer.toString(date.getMonth() + 1) + Integer.toString(date.getDate());
			nowTime = String.format("%0" + 4 + "d", Integer.parseInt(nowTime));
			if (!(employeeList.get(i).getBirthday() == null || employeeList.get(i).getBirthday().equals(""))) {
				if (Integer.parseInt(nowTime) > Integer.parseInt(employeeList.get(i).getBirthday())) {
					String year = Integer.toString(date.getYear() + 1901);
					employeeList.get(i).setBirthdayDate(dateDiff(date, year + employeeList.get(i).getBirthday()));
				} else if (Integer.parseInt(nowTime) < Integer.parseInt(employeeList.get(i).getBirthday())) {
					String year = Integer.toString(date.getYear() + 1900);
					employeeList.get(i).setBirthdayDate(dateDiff(date, year + employeeList.get(i).getBirthday()));
				}
			}
		}

		// 排序
		List<EmployeeInformationModel> newEmployeeList = new ArrayList<EmployeeInformationModel>();
		List<EmployeeInformationModel> TempList = new ArrayList<EmployeeInformationModel>();
		/*
		 * for (int i = 0; i < employeeList.size(); i++) { if
		 * (!employeeList.get(i).getDealDistinctioCode().equals("2") &&
		 * ((employeeList.get(i).getStayPeriod().equals("") ? false :
		 * Integer.parseInt(employeeList.get(i).getStayPeriod()) <= 90) ||
		 * (employeeList.get(i).getBirthday().equals("") ? false :
		 * Integer.parseInt(employeeList.get(i).getBirthday()) <= 7) ||
		 * (employeeList.get(i).getContractDeadline().equals("") ? false :
		 * Integer.parseInt(employeeList.get(i).getContractDeadline()) <= 60))) {
		 * newEmployeeList.add(employeeList.get(i)); employeeList.remove(i); i--; } }
		 */

		// 在留カード
		for (int i = 0; i < employeeList.size(); i++) {
			if (!employeeList.get(i).getDealDistinctioCode().equals("2")
					&& (employeeList.get(i).getStayPeriod() == null || employeeList.get(i).getStayPeriod().equals("")
							? false
							: Integer.parseInt(employeeList.get(i).getStayPeriod()) <= 90)) {
				TempList.add(employeeList.get(i));
				employeeList.remove(i);
				i--;
			}
		}
		
		for (int x = 0; x < TempList.size() - 1; x++) {
			for (int y = x + 1; y < TempList.size(); y++) {
				if (Integer.parseInt(TempList.get(x).getStayPeriod()) > Integer
						.parseInt(TempList.get(y).getStayPeriod())) {
					EmployeeInformationModel temp = TempList.get(x);
					TempList.set(x, TempList.get(y));
					TempList.set(y, temp);
				}
			}
		}
		
		for (int i = 0; i < TempList.size(); i++) {
			newEmployeeList.add(TempList.get(i));
		}
		TempList.clear();

		// 誕生日
		for (int i = 0; i < employeeList.size(); i++) {
			if (!employeeList.get(i).getDealDistinctioCode().equals("2")
					&& (employeeList.get(i).getBirthday().equals("") ? false
							: Integer.parseInt(employeeList.get(i).getBirthday()) <= 7)) {
				TempList.add(employeeList.get(i));
				employeeList.remove(i);
				i--;
			}
		}
		for (int x = 0; x < TempList.size() - 1; x++) {
			for (int y = x + 1; y < TempList.size(); y++) {
				if (Integer.parseInt(TempList.get(x).getBirthday()) > Integer.parseInt(TempList.get(y).getBirthday())) {
					EmployeeInformationModel temp = TempList.get(x);
					TempList.set(x, TempList.get(y));
					TempList.set(y, temp);
				}
			}
		}
		for (int i = 0; i < TempList.size(); i++) {
			newEmployeeList.add(TempList.get(i));
		}
		TempList.clear();

		// 契約
		for (int i = 0; i < employeeList.size(); i++) {
			if (!employeeList.get(i).getDealDistinctioCode().equals("2")
					&& (employeeList.get(i).getContractDeadline() == null || employeeList.get(i).getContractDeadline().equals("") ? false
							: Integer.parseInt(employeeList.get(i).getContractDeadline()) <= 60)) {
				TempList.add(employeeList.get(i));
				employeeList.remove(i);
				i--;
			}
		}
		for (int x = 0; x < TempList.size() - 1; x++) {
			for (int y = x + 1; y < TempList.size(); y++) {
				if (Integer.parseInt(TempList.get(x).getContractDeadline()) > Integer
						.parseInt(TempList.get(y).getContractDeadline())) {
					EmployeeInformationModel temp = TempList.get(x);
					TempList.set(x, TempList.get(y));
					TempList.set(y, temp);
				}
			}
		}
		for (int i = 0; i < TempList.size(); i++) {
			newEmployeeList.add(TempList.get(i));
		}
		TempList.clear();

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
