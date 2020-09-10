package jp.co.lyc.cms.controller;

import java.util.ArrayList;
import java.util.Calendar;
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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import jp.co.lyc.cms.model.SiteSearchModel;
import jp.co.lyc.cms.service.SiteSearchService;
import jp.co.lyc.cms.validation.SiteSearchValidation;

@Controller
@CrossOrigin(origins = "http://127.0.0.1:3000")
public class SiteSearchController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	// 日期 去“/”
	private String dateToString(String date) {
		String[] a = date.split("/");
		String b = a[0] + a[1] + a[2];
		return b;
	}

	// 时间段表示
	private String dateToPeriod(String beginDate, String endDate) {
		StringBuffer stringBuilder1;
		StringBuffer stringBuilder2;
		String period;
		if (beginDate != null) {
			stringBuilder1 = new StringBuffer(beginDate);
			stringBuilder1.insert(6, ".");
			stringBuilder1.insert(4, ".");
			period = stringBuilder1 + "〜";
		} else {
			period = "未定";
			return period;
		}
		if (endDate != null) {
			stringBuilder2 = new StringBuffer(endDate);
			stringBuilder2.insert(6, ".");
			stringBuilder2.insert(4, ".");
			period = period + stringBuilder2;
		}
		return period;
	}

	// 期间计算
	private String timeCalculate(String admissionStartDate, String admissionEndDate) {
		int beginYear = 0;
		int beginMonth = 0;
		int beginDay = 0;
		int endYear = 0;
		int endMonth = 0;
		int endDay = 0;
		if (admissionStartDate != null) {
			beginYear = Integer.parseInt(admissionStartDate.substring(0, 4));
			beginMonth = Integer.parseInt(admissionStartDate.substring(4, 6));
			beginDay = Integer.parseInt(admissionStartDate.substring(6));
		}
		if (admissionEndDate != null) {
			endYear = Integer.parseInt(admissionEndDate.substring(0, 4));
			endMonth = Integer.parseInt(admissionEndDate.substring(4, 6));
			endDay = Integer.parseInt(admissionEndDate.substring(6));
		} else {
			endYear = Calendar.getInstance().get(Calendar.YEAR);
			endMonth = Calendar.getInstance().get(Calendar.MONTH) + 1;
			endDay = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
		}
		int year = 0;
		int month = 0;
		if (Math.abs(endDay - beginDay) >= 15) {
			if ((endMonth - beginMonth) >= 0) {
				year = endYear - beginYear;
				month = endMonth - beginMonth + 1;
			} else if ((endMonth - beginMonth) < 0) {
				year = endYear - beginYear - 1;
				month = endMonth - beginMonth + 13;
			}
		} else if (Math.abs(endDay - beginDay) < 15) {
			if ((endMonth - beginMonth) >= 0) {
				year = endYear - beginYear;
				month = endMonth - beginMonth;
			} else if ((endMonth - beginMonth) < 0) {
				year = endYear - beginYear - 1;
				month = endMonth - beginMonth + 12;
			}
		}
		if (month == 12) {
			year = year + 1;
			month = 0;
		}
		if (year == 0) {
			return month + "月";
		} else if (month == 0) {
			return year + "年";
		} else
			return year + "年" + month + "月";
	}

	@Autowired
	SiteSearchService SiteSearchService;
	String errorsMessage = "";

	@RequestMapping(value = "/getSiteSearchInfo", method = RequestMethod.POST)
	@ResponseBody
	// 现场信息查询
	public Map<String, Object> getSiteSearchInfo(@RequestBody SiteSearchModel siteSearchModel) {
		List<SiteSearchModel> siteList = new ArrayList<SiteSearchModel>();
		Map<String, Object> sendMap = new HashMap<String, Object>();
		DataBinder binder = new DataBinder(siteSearchModel);
		binder.setValidator(new SiteSearchValidation());
		binder.validate();
		BindingResult results = binder.getBindingResult();
		Map<String, Object> result = new HashMap<>();
		if (results.hasErrors()) {
			results.getAllErrors().forEach(o -> {
				FieldError error = (FieldError) o;
				errorsMessage += error.getDefaultMessage();// エラーメッセージ
			});
			result.put("errorsMessage", errorsMessage);// エラーメッセージ
			return result;
		}
		try {
			// 取得前端送过来的值
			String employeeName = siteSearchModel.getEmployeeName();
			String employeeStatus = siteSearchModel.getEmployeeStatus();
			String employeeForm = siteSearchModel.getEmployeeForm();
			String siteRoleCode = siteSearchModel.getSiteRoleCode();
			String customerNo = siteSearchModel.getCustomerNo();
			String topCustomerNo = siteSearchModel.getTopCustomerNo();
			String bpCustomerNo = siteSearchModel.getBpCustomerNo();
			String stationCode = siteSearchModel.getStationCode();
			String typeOfIndustryCode = siteSearchModel.getTypeOfIndustryCode();
			String payOffRange1 = siteSearchModel.getPayOffRange1();
			String payOffRange2 = siteSearchModel.getPayOffRange2();
			String unitPrice1 = siteSearchModel.getUnitPrice1();
			String unitPrice2 = siteSearchModel.getUnitPrice2();
			String developLanguageCode = siteSearchModel.getDevelopLanguageCode();
			String admissionStartDate = siteSearchModel.getAdmissionStartDate();
			String admissionEndDate = siteSearchModel.getAdmissionEndDate();
			String dataAcquisitionPeriod = siteSearchModel.getDataAcquisitionPeriod();
			// 存入map 传入后台查询用
			if (employeeName != null && employeeName.length() != 0) {
				sendMap.put("employeeName", employeeName);
			}
			if (employeeStatus != null && employeeStatus.length() != 0) {
				sendMap.put("employeeStatus", employeeStatus);
			}
			if (employeeForm != null && employeeForm.length() != 0) {
				sendMap.put("employeeForm", employeeForm);
			}
			if (siteRoleCode != null && siteRoleCode.length() != 0) {
				sendMap.put("siteRoleCode", siteRoleCode);
			}
			if (customerNo != null && customerNo.length() != 0) {
				sendMap.put("customerNo", customerNo);
			}
			if (topCustomerNo != null && topCustomerNo.length() != 0) {
				sendMap.put("topCustomerNo", topCustomerNo);
			}
			if (bpCustomerNo != null && bpCustomerNo.length() != 0) {
				sendMap.put("bpCustomerNo", bpCustomerNo);
			}
			if (stationCode != null && stationCode.length() != 0) {
				sendMap.put("stationCode", stationCode);
			}
			if (typeOfIndustryCode != null && typeOfIndustryCode.length() != 0) {
				sendMap.put("typeOfIndustryCode", typeOfIndustryCode);
			}
			if (payOffRange1 != null && payOffRange1.length() != 0) {
				sendMap.put("payOffRange1", payOffRange1);
			}
			if (payOffRange2 != null && payOffRange2.length() != 0) {
				sendMap.put("payOffRange2", payOffRange2);
			}
			if (unitPrice1 != null && unitPrice1.length() != 0) {
				sendMap.put("unitPrice1", unitPrice1);
			}
			if (unitPrice2 != null && unitPrice2.length() != 0) {
				sendMap.put("unitPrice2", unitPrice2);
			}
			if (developLanguageCode != null && developLanguageCode.length() != 0) {
				sendMap.put("developLanguageCode", developLanguageCode);
			}
			if (admissionStartDate != null && admissionStartDate.length() != 0) {
				sendMap.put("admissionStartDate", dateToString(admissionStartDate));
			}
			if (admissionEndDate != null && admissionEndDate.length() != 0) {
				sendMap.put("admissionEndDate", dateToString(admissionEndDate));
			}
			if (dataAcquisitionPeriod != null && dataAcquisitionPeriod.length() != 0) {
				sendMap.put("dataAcquisitionPeriod", dataAcquisitionPeriod + "00");
			}
			siteList = SiteSearchService.getSiteInfo(sendMap);
			for (int a = 0; a < siteList.size(); a++) {
				// 行番号设定
				siteList.get(a).setRowNo((a + 1) + "");
				// 勤務期間设定
				siteList.get(a).setWorkDate(
						dateToPeriod(siteList.get(a).getAdmissionStartDate(), siteList.get(a).getAdmissionEndDate()));
				// 社员形式设定
				if (siteList.get(a).getEmployeeFrom() != null && siteList.get(a).getEmployeeFrom().length() != 0) {
					siteList.get(a).setEmployeeFrom("BP(" + siteList.get(a).getEmployeeFrom() + ")");
				} else {
					siteList.get(a).setEmployeeFrom("社員");
				}
				// 勤務時間设定
				siteList.get(a).setWorkTime(
						timeCalculate(siteList.get(a).getAdmissionStartDate(), siteList.get(a).getAdmissionEndDate()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("GetEmployeeInfoController.getEmployeeInfo:" + "検索結束");
		result.put("data", siteList);
		return result;
	}

}
