package jp.co.lyc.cms.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

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

import jp.co.lyc.cms.common.BaseController;
import jp.co.lyc.cms.mapper.SiteInfoMapper;
import jp.co.lyc.cms.mapper.SiteSearchMapper;
import jp.co.lyc.cms.model.SiteModel;
import jp.co.lyc.cms.model.SiteSearchModel;
import jp.co.lyc.cms.service.SiteInfoService;
import jp.co.lyc.cms.util.StatusCodeToMsgMap;
import jp.co.lyc.cms.util.UtilsCheckMethod;
import jp.co.lyc.cms.validation.SiteInfoValidation;

@Controller
public class SiteInfoController extends BaseController {
	private String dateToString(String date) {
		if (date != null && date.length() != 0) {
			String[] a = date.split("/");
			String b = a[0];
			if (a[1].length() == 1) {
				b = b + "0" + a[1];
			} else {
				b = b + a[1];
			}
			if (a[2].length() == 1) {
				b = b + "0" + a[2];
			} else {
				b = b + a[2];
			}
			return b;
		} else
			return "";
	}

	private String dateToPeriod(String beginDate, String endDate) {
		StringBuffer stringBuilder1;
		StringBuffer stringBuilder2;
		String period;
		if (beginDate != null) {
			stringBuilder1 = new StringBuffer(beginDate);

			period = stringBuilder1 + "〜";
		} else {
			period = "未定";
			return period;
		}
		if (endDate != null) {
			stringBuilder2 = new StringBuffer(endDate);

			period = period + stringBuilder2;
		}

		return period;
	}

	private String toRelatedEmployees(String related1Employees, String related2Employees, String related3Employees,
			String related4Employees) {
		String relatedEmployees = related1Employees;
		if (related2Employees != null && related2Employees.length() != 0) {
			relatedEmployees = relatedEmployees + "," + related2Employees;
		}
		if (related3Employees != null && related3Employees.length() != 0) {
			relatedEmployees = relatedEmployees + "," + related3Employees;
		}
		if (related4Employees != null && related4Employees.length() != 0) {
			relatedEmployees = relatedEmployees + "," + related4Employees;
		}
		return relatedEmployees;
	}

	// 计算待机月
	private String timeCalculate(String checkDate, String admissionStartDate) {
		if (checkDate.equals("1")) {
			return "";
		} else {
			int beginYear = 0;
			int beginMonth = 0;
			int beginDay = 0;
			int endYear = 0;
			int endMonth = 0;
			int endDay = 0;
			if (checkDate != null) {
				beginYear = Integer.parseInt(checkDate.substring(0, 4));
				beginMonth = Integer.parseInt(checkDate.substring(4, 6));
				beginDay = Integer.parseInt(checkDate.substring(6));
			}
			if (admissionStartDate != null) {
				endYear = Integer.parseInt(admissionStartDate.substring(0, 4));
				endMonth = Integer.parseInt(admissionStartDate.substring(4, 6));
				endDay = Integer.parseInt(admissionStartDate.substring(6));
			}
			int year = 0;
			int month = 0;
			if (endDay - beginDay >= 15) {
				if ((endMonth - beginMonth) >= 0) {
					year = endYear - beginYear;
					month = endMonth - beginMonth + 1;
				} else if ((endMonth - beginMonth) < 0) {
					year = endYear - beginYear - 1;
					month = endMonth - beginMonth + 13;
				}
			} else if (endDay - beginDay <= -15) {
				if ((endMonth - beginMonth) >= 0) {
					year = endYear - beginYear;
					month = endMonth - beginMonth - 1;
				} else if ((endMonth - beginMonth) < 0) {
					year = endYear - beginYear - 1;
					month = endMonth - beginMonth + 11;
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
			month = month + year * 12;
			if (month == 0) {
				return "";
			} else {
				return month + "";
			}
		}
	}

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	SiteInfoService siteInfoService;
	@Autowired
	SiteSearchMapper siteSearchMapper;
	@Autowired
	SiteInfoMapper siteInfoMapper;
	String errorsMessage = "";

	@RequestMapping(value = "/insertSiteInfo")
	@ResponseBody
	public Map<String, Object> insertSiteInfo(@RequestBody SiteModel siteModel) {
		Map<String, Object> result = new HashMap<>();
		errorsMessage = "";
		DataBinder binder = new DataBinder(siteModel);
		binder.setValidator(new SiteInfoValidation());
		binder.validate();
		BindingResult results = binder.getBindingResult();
		if (siteModel.getWorkState().equals("2")) {
			result.put("errorsMessage", StatusCodeToMsgMap.getErrMsgbyCode("MSG012"));// エラーメッセージ
			return result;
		} else if (results.hasErrors()) {
			results.getAllErrors().forEach(o -> {
				FieldError error = (FieldError) o;
				errorsMessage += error.getDefaultMessage();// エラーメッセージ
			});
			result.put("errorsMessage", errorsMessage);// エラーメッセージ
			return result;
		}
		List<SiteModel> checkList = siteInfoMapper.getSiteInfo(siteModel.getEmployeeNo());
		if (checkList.size() != 0) {
			SiteModel checkModel = checkList.get(checkList.size() - 1);
			if (UtilsCheckMethod.isNullOrEmpty(checkModel.getAdmissionEndDate())) {
				errorsMessage += "終了してない現場があるため、新規できない";
				result.put("errorsMessage", errorsMessage);// エラーメッセージ
				return result;
			} else {
				int lastEnd = Integer.parseInt(checkModel.getAdmissionEndDate());
				int nowStart = Integer.parseInt(dateToString(siteModel.getAdmissionStartDate()));
				if (nowStart < lastEnd) {
					errorsMessage += "新規現場の入場期日は既存データの終了期日以降にしてください";
					result.put("errorsMessage", errorsMessage);// エラーメッセージ
					return result;
				}
			}
		}
		// 登陆处理
		if (insert(putData(siteModel))) {
			result.put("result", true);
		} else {
			result.put("result", false);
		}
		logger.info("SiteInfoController.insertSiteInfo:" + "追加結束");
		return result;

	}

	@RequestMapping(value = "/updateSiteInfo")
	@ResponseBody
	public Map<String, Object> updateSiteInfo(@RequestBody SiteModel siteModel) {
		Map<String, Object> result = new HashMap<>();
		errorsMessage = "";
		DataBinder binder = new DataBinder(siteModel);
		binder.setValidator(new SiteInfoValidation());
		binder.validate();
		BindingResult results = binder.getBindingResult();
		if (results.hasErrors()) {
			results.getAllErrors().forEach(o -> {
				FieldError error = (FieldError) o;
				errorsMessage += error.getDefaultMessage();// エラーメッセージ
			});
			result.put("errorsMessage", errorsMessage);// エラーメッセージ
			return result;
		}
		List<SiteModel> checkList = siteInfoMapper.getSiteInfo(siteModel.getEmployeeNo());
		if (checkList.size() != 0 && checkList.size() > 1) {
			SiteModel checkModel = checkList.get(checkList.size() - 2);
			int lastEnd = Integer.parseInt(checkModel.getAdmissionEndDate());
			int nowStart = Integer.parseInt(dateToString(siteModel.getAdmissionStartDate()));
			if (nowStart < lastEnd) {
				errorsMessage += "入場期日は既存データの終了期日以降にしてください";
				result.put("errorsMessage", errorsMessage);// エラーメッセージ
				return result;
			}
		}
		// 登陆处理
		if (update(putData(siteModel))) {
			result.put("result", true);
		} else {
			result.put("result", false);
		}
		logger.info("SiteInfoController.updateSiteInfo:" + "追加結束");
		return result;

	}

	/**
	 * insert
	 * 
	 * @return
	 */

	public boolean insert(Map<String, Object> sendMap) {
		return siteInfoService.insertSiteInfo(sendMap);
	}

	/**
	 * update
	 * 
	 * @return
	 */

	public boolean update(Map<String, Object> sendMap) {
		return siteInfoService.updateSiteInfo(sendMap);
	}

	/**
	 * データ整理
	 * 
	 * @return
	 */
	public Map<String, Object> putData(SiteModel siteModel) {
		HttpSession loginSession = getSession();
		Map<String, Object> sendMap = new HashMap<String, Object>();
		String employeeNo = siteModel.getEmployeeNo();
		String customerNo = siteModel.getCustomerNo();
		String topCustomerNo = siteModel.getTopCustomerNo();
		String admissionStartDate = dateToString(siteModel.getAdmissionStartDate());
		String location = siteModel.getLocation();
		String siteManager = siteModel.getSiteManager();
		String admissionEndDate = dateToString(siteModel.getAdmissionEndDate());
		String unitPrice = siteModel.getUnitPrice();
		String siteRoleCode = siteModel.getSiteRoleCode();
		String payOffRange1 = siteModel.getPayOffRange1();
		String payOffRange2 = siteModel.getPayOffRange2();
		String systemName = siteModel.getSystemName();
		String developLanguageCode = siteModel.getDevelopLanguageCode();
		String relatedEmployees = toRelatedEmployees(siteModel.getRelated1Employees(), siteModel.getRelated2Employees(),
				siteModel.getRelated3Employees(), siteModel.getRelated4Employees());
		String levelCode = siteModel.getLevelCode();
		String typeOfIndustryCode = siteModel.getTypeOfIndustryCode();
		String remark = siteModel.getRemark();
		String workDate = siteModel.getWorkDate();
		String checkDate = siteModel.getCheckDate();
		String nonSiteMonths = timeCalculate(checkDate, admissionStartDate);
		String workState = siteModel.getWorkState();
		String dailyCalculationStatus = siteModel.getDailyCalculationStatus();
		String scheduledEndDate = siteModel.getScheduledEndDate();
		sendMap.put("nonSiteMonths", nonSiteMonths);
		if (nonSiteMonths != "") {
			sendMap.put("nonSitePeriod", checkDate + "〜" + admissionStartDate);
		} else {
			sendMap.put("nonSitePeriod", "");
		}
		if (employeeNo != null && employeeNo.length() != 0) {
			sendMap.put("employeeNo", employeeNo);
		}
		if (customerNo != null && customerNo.length() != 0) {
			sendMap.put("customerNo", customerNo);
		}
		if (topCustomerNo != null && topCustomerNo.length() != 0) {
			sendMap.put("topCustomerNo", topCustomerNo);
		}
		if (admissionStartDate != null && admissionStartDate.length() != 0) {
			sendMap.put("admissionStartDate", admissionStartDate);
		}
		if (location != null && location.length() != 0) {
			sendMap.put("location", location);
		}
		if (siteManager != null && siteManager.length() != 0) {
			sendMap.put("siteManager", siteManager);
		}
		if (admissionEndDate != null && admissionEndDate.length() != 0) {
			sendMap.put("admissionEndDate", admissionEndDate);
		}
		if (unitPrice != null && unitPrice.length() != 0) {
			sendMap.put("unitPrice", unitPrice);
		}
		if (siteRoleCode != null && siteRoleCode.length() != 0) {
			sendMap.put("siteRoleCode", siteRoleCode);
		}
		if (payOffRange1 != null && payOffRange1.length() != 0) {
			sendMap.put("payOffRange1", payOffRange1);
		}
		if (payOffRange2 != null && payOffRange2.length() != 0) {
			sendMap.put("payOffRange2", payOffRange2);
		}
		if (systemName != null && systemName.length() != 0) {
			sendMap.put("systemName", systemName);
		}
		if (developLanguageCode != null && developLanguageCode.length() != 0) {
			sendMap.put("developLanguageCode", developLanguageCode);
		}
		if (relatedEmployees != null && relatedEmployees.length() != 0) {
			sendMap.put("relatedEmployees", relatedEmployees);
		}
		if (levelCode != null && levelCode.length() != 0) {
			sendMap.put("levelCode", levelCode);
		}
		if (remark != null && remark.length() != 0) {
			sendMap.put("remark", remark);
		}
		if (scheduledEndDate != null && scheduledEndDate.length() != 0) {
			sendMap.put("scheduledEndDate", scheduledEndDate);
		}
		if (typeOfIndustryCode != null && typeOfIndustryCode.length() != 0) {
			sendMap.put("typeOfIndustryCode", typeOfIndustryCode);
		}
		if (workDate != null && workDate.length() != 0) {
			sendMap.put("workDate", workDate);
		}
		if (workState != null && workState.length() != 0) {
			sendMap.put("workState", workState);
		}
		if (dailyCalculationStatus != null && dailyCalculationStatus.length() != 0) {
			sendMap.put("dailyCalculationStatus", "0");
		} else {
			sendMap.put("dailyCalculationStatus", "1");
		}
		sendMap.put("updateUser", loginSession.getAttribute("employeeName"));
		return sendMap;
	}

	@RequestMapping(value = "/getSiteInfo", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getSiteInfo(@RequestBody Map employeeName) {
		List<SiteModel> siteList = new ArrayList<SiteModel>();
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			if (employeeName.get("employeeName") != null) {
				siteList = siteInfoService.getSiteInfo(employeeName.get("employeeName").toString());
			}
			for (int a = 0; a < siteList.size(); a++) {
				siteList.get(a).setWorkDate(
						dateToPeriod(siteList.get(a).getAdmissionStartDate(), siteList.get(a).getAdmissionEndDate()));
				String[] relatedEmployees;
				if (siteList.get(a).getRelatedEmployees() != "" && siteList.get(a).getRelatedEmployees() != null) {
					relatedEmployees = siteList.get(a).getRelatedEmployees().split(",");
					if (relatedEmployees.length > 0) {
						if (relatedEmployees[0] != "") {
							siteList.get(a).setRelated1Employees(relatedEmployees[0]);
						}
					}
					if (relatedEmployees.length > 1) {
						if (relatedEmployees[1] != "") {
							siteList.get(a).setRelated2Employees(relatedEmployees[1]);
						}
					}
					if (relatedEmployees.length > 2) {
						if (relatedEmployees[2] != "") {
							siteList.get(a).setRelated3Employees(relatedEmployees[2]);
						}
					}
					if (relatedEmployees.length > 3) {
						if (relatedEmployees[3] != "") {
							siteList.get(a).setRelated4Employees(relatedEmployees[3]);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("GetEmployeeInfoController.getEmployeeInfo:" + "検索結束");
		if (siteList.size() != 0) {
			result.put("siteList", siteList);
		} else {
			result.put("errorsMessage", "該当データなし");
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/deleteSiteInfo", method = RequestMethod.POST)
	@ResponseBody
	public boolean deleteSiteInfo(@RequestBody Map map) throws Exception {
		logger.info("SiteInfoController.deleteSiteInfo:" + "削除開始");
		boolean result = true;
		SiteSearchModel deleteModel = (SiteSearchModel) siteSearchMapper.getSiteInfo(map).get(0);
		if (!UtilsCheckMethod.isNullOrEmpty(deleteModel.getAdmissionEndDate())) {
			return false;
		}
		result = siteInfoService.deleteSiteInfo(map);
		logger.info("SiteInfoController.deleteSiteInfo:" + "削除結束");
		return result;
	}
}
