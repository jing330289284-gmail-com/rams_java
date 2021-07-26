package jp.co.lyc.cms.controller;

import java.io.IOException;
import java.text.DecimalFormat;
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
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import jp.co.lyc.cms.model.MonthlySalesSearchModel;
import jp.co.lyc.cms.service.MonthlySalesSearchService;
import jp.co.lyc.cms.util.UtilsCheckMethod;
import jp.co.lyc.cms.validation.MonthlySalesSearchValidation;

@Controller
@RequestMapping(value = "/monthlySales")
public class MonthlySalesSearchController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	String errorsMessage = "";
	@Autowired
	MonthlySalesSearchService MonthlySalesSearchService;

	@RequestMapping(value = "/searchMonthlySales", method = RequestMethod.POST)

	@ResponseBody
	public Map<String, Object> searchMonthlySales(@RequestBody MonthlySalesSearchModel monthlyInfo) throws IOException {
		List<MonthlySalesSearchModel> MonthlySalesModelList = new ArrayList<MonthlySalesSearchModel>();
		logger.info("MonthlySalesSearchController.searchMonthlySales:" + "検索開始");
		errorsMessage = "";
		Date day = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyyMM");
		String sysTime = df.format(day);
		DataBinder binder = new DataBinder(monthlyInfo);
		binder.setValidator(new MonthlySalesSearchValidation());
		binder.validate();
		BindingResult results = binder.getBindingResult();
		Map<String, Object> resulterr = new HashMap<>();
		if (results.hasErrors()) {
			results.getAllErrors().forEach(o -> {
				FieldError error = (FieldError) o;
				errorsMessage += error.getDefaultMessage();// エラーメッセージ
			});

			resulterr.put("errorsMessage", errorsMessage);// エラーメッセージ
			return resulterr;
		} else {
			List<String> getYandM = new ArrayList<String>();
			String startYandM = monthlyInfo.getStartYandM();
			String endYandM = monthlyInfo.getEndYandM();
			if (startYandM != "" && endYandM == "") {
				endYandM = sysTime;
			}
			if (startYandM == "" && endYandM != "") {

				startYandM = "201901";
			}

			if (startYandM != "0" && startYandM != null && endYandM != "0" && endYandM != null) {
				int startY = Integer.parseInt(startYandM.substring(0, 4));
				int startM = Integer.parseInt(startYandM.substring(4, 6));
				int endY = Integer.parseInt(endYandM.substring(0, 4));
				int endM = Integer.parseInt(endYandM.substring(4, 6));
				int count = 0;
				for (int y = startY; y <= endY; y++) {
					if (y == startY && y == endY) {
						for (int m = startM; m <= endM; m++) {
							String monthStr = Integer.toString(m);
							if (m < 10) {
								monthStr = "0" + Integer.toString(m);
							}
							getYandM.add(count, Integer.toString(startY) + monthStr);
							count++;
						}
					} else if (y == startY) {
						for (int m = startM; m <= 12; m++) {

							String monthStr = Integer.toString(m);
							if (m < 10) {
								monthStr = "0" + Integer.toString(m);
							}
							getYandM.add(count, Integer.toString(startY) + monthStr);
							count++;
						}
					} else if (y != startY && y != endY) {
						for (int m = 1; m <= 12; m++) {

							String monthStr = Integer.toString(m);
							if (m < 10) {
								monthStr = "0" + Integer.toString(m);
							}
							getYandM.add(count, Integer.toString(y) + monthStr);
							count++;
						}
					} else if (y == endY) {
						for (int m = 1; m <= endM; m++) {

							String monthStr = Integer.toString(m);
							if (m < 10) {
								monthStr = "0" + Integer.toString(m);
							}
							getYandM.add(count, Integer.toString(endY) + monthStr);
							count++;
						}
					}

				}
			}
			
			if (UtilsCheckMethod.isNullOrEmpty(monthlyInfo.getEmployeeClassification())) {
				monthlyInfo.setEmployeeClassification("");
			}
			
			Map<String, Object> sendMap = getDetailParam(monthlyInfo);
			sendMap.put("getYandM", getYandM);

			MonthlySalesModelList = MonthlySalesSearchService.searchMonthlySales(sendMap);
			logger.info("MonthlySalesSearchController.searchMonthlySales:" + "検索結束");
			for (int i = 0; i < MonthlySalesModelList.size(); i++) {
				if (UtilsCheckMethod.isNullOrEmpty(MonthlySalesModelList.get(i).getEmployeeName())) {
					MonthlySalesModelList.get(i).setEmployeeName("");
				}

				if (!monthlyInfo.getEmployeeClassification().equals("1")) {
					if (!UtilsCheckMethod.isNullOrEmpty(MonthlySalesModelList.get(i).getEmployeeNo())) {
						if (MonthlySalesModelList.get(i).getEmployeeNo().substring(0, 2).equals("BP")) {
							MonthlySalesModelList.get(i)
									.setEmployeeName(MonthlySalesModelList.get(i).getEmployeeName() + "(BP)");
						}
					}
				}
			}

			for (int i = 0; i < MonthlySalesModelList.size(); i++) {
				if (MonthlySalesModelList.get(i).getEmployeeName() == null
						|| MonthlySalesModelList.get(i).getEmployeeName().equals("")) {
					MonthlySalesModelList.remove(i);
					i--;
				}
			}

			if (monthlyInfo.getEmployeeClassification().equals("")
					|| monthlyInfo.getEmployeeClassification().equals("1")) {
				List<MonthlySalesSearchModel> bpMonthlySalesModelList = new ArrayList<MonthlySalesSearchModel>();
				bpMonthlySalesModelList = MonthlySalesSearchService.searchBpMonthlySales(sendMap);
				for (int i = 0; i < bpMonthlySalesModelList.size(); i++) {
					MonthlySalesModelList.add(bpMonthlySalesModelList.get(i));
				}
			}

			for (int i = 0; i < MonthlySalesModelList.size(); i++) {
				MonthlySalesModelList.get(i).setRowNo(String.valueOf(i + 1));
			}

			if (MonthlySalesModelList.size() == 0) {
				String noData = "";
				noData = "条件に該当する結果が存在しない";
				resulterr.put("noData", noData);
				return resulterr;
			} else {
				Map<String, Object> resultdata = new HashMap<>();
				resultdata.put("data", MonthlySalesModelList);
				return resultdata;
			}
		}
	}

	public Map<String, Object> getDetailParam(MonthlySalesSearchModel monthlyInfo) {
		Map<String, Object> sendMap = new HashMap<String, Object>();
		String employeeClassification = monthlyInfo.getEmployeeClassification();
		String employeeForms = monthlyInfo.getEmployeeForms();
		String employeeOccupation = monthlyInfo.getEmployeeOccupation();
		String kadou = monthlyInfo.getKadou();
		String utilPricefront = monthlyInfo.getUtilPricefront();
		String utilPriceback = monthlyInfo.getUtilPriceback();
		String salaryfront = monthlyInfo.getSalaryfront();
		String salaryback = monthlyInfo.getSalaryback();
		String grossProfitFront = monthlyInfo.getGrossProfitFront();
		String grossProfitBack = monthlyInfo.getGrossProfitBack();
		String startYandM = monthlyInfo.getStartYandM();
		String endYandM = monthlyInfo.getEndYandM();
		if (employeeClassification != null && employeeClassification.length() != 0) {
			sendMap.put("employeeClassification", employeeClassification);
		}
		if (employeeForms != null && employeeForms.length() != 0) {
			sendMap.put("employeeForms", employeeForms);
		}
		if (employeeOccupation != null && employeeOccupation.length() != 0) {
			sendMap.put("employeeOccupation", employeeOccupation);
		}
		if (kadou != null && kadou.length() != 0) {
			sendMap.put("kadou", kadou);
		}
		if (utilPricefront != null && utilPricefront.length() != 0) {
			sendMap.put("utilPricefront", utilPricefront);
		}
		if (utilPriceback != null && utilPriceback.length() != 0) {
			sendMap.put("utilPriceback", utilPriceback);
		}
		if (salaryfront != null && salaryfront.length() != 0) {
			sendMap.put("salaryFront", salaryfront);
		}
		if (salaryback != null && salaryback.length() != 0) {
			sendMap.put("salaryBack", salaryback);
		}
		if (grossProfitFront != null && grossProfitFront.length() != 0) {
			sendMap.put("grossProfitFront", grossProfitFront);
		}
		if (grossProfitBack != null && grossProfitBack.length() != 0) {
			sendMap.put("grossProfitBack", grossProfitBack);
		}
		if (startYandM != null && startYandM.length() != 0) {
			sendMap.put("startYandM", startYandM);
		}
		if (endYandM != null && endYandM.length() != 0) {
			sendMap.put("endYandM", endYandM);
		}
		return sendMap;
	}

}
