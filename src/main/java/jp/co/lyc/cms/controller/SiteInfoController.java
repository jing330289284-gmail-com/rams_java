package jp.co.lyc.cms.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import jp.co.lyc.cms.model.SiteModel;
import jp.co.lyc.cms.service.GetSiteInfoService;

@Controller
@CrossOrigin(origins = "http://127.0.0.1:3000")
public class SiteInfoController {
	private String dateToString(String date) {
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
	}

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

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	GetSiteInfoService getSiteInfoService;

	@RequestMapping(value = "/insertSiteInfo")
	@ResponseBody
	public String insertSiteInfo(@RequestBody SiteModel siteModel, Model model) {

		Map<String, String> sendMap = new HashMap<String, String>();
//		String emplyeeNo = request.getSession().getAttribute("emplyeeNo").toString();
		try {
			String employeeNo = siteModel.getEmployeeNo();
			String customerNo = siteModel.getCustomerNo();
			String topCustomerNo = siteModel.getTopCustomerNo();
			String admissionStartDate = siteModel.getAdmissionStartDate();
			String location = siteModel.getLocation();
			String siteManager = siteModel.getSiteManager();
			String admissionEndDate = siteModel.getAdmissionEndDate();
			String unitPrice = siteModel.getUnitPrice();
			String siteRoleCode = siteModel.getSiteRoleCode();
			String payOffRange1 = siteModel.getPayOffRange1();
			String payOffRange2 = siteModel.getPayOffRange2();
			String systemName = siteModel.getSystemName();
			String developLanguageCode = siteModel.getDevelopLanguageCode();
			String relatedEmployees = toRelatedEmployees(siteModel.getRelated1Employees(),
					siteModel.getRelated2Employees(), siteModel.getRelated3Employees(),
					siteModel.getRelated4Employees());
			String levelCode = siteModel.getLevelCode();
			String remark = siteModel.getRemark();
			String updateUser = siteModel.getUpdateUser();

			if (employeeNo != null && employeeNo.length() != 0) {
				sendMap.put("employeeNo", employeeNo);
			} else {
				sendMap.put("employeeNo", "LYC001");
			}
			if (customerNo != null && customerNo.length() != 0) {
				sendMap.put("customerNo", customerNo);
			}
			if (topCustomerNo != null && topCustomerNo.length() != 0) {
				sendMap.put("topCustomerNo", topCustomerNo);
			}
			if (admissionStartDate != null && admissionStartDate.length() != 0) {
				sendMap.put("admissionStartDate", dateToString(admissionStartDate));
			}
			if (location != null && location.length() != 0) {
				sendMap.put("location", location);
			}
			if (siteManager != null && siteManager.length() != 0) {
				sendMap.put("siteManager", siteManager);
			}
			if (admissionEndDate != null && admissionEndDate.length() != 0) {
				sendMap.put("admissionEndDate", dateToString(admissionEndDate));
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
			if (updateUser != null && updateUser.length() != 0) {
				sendMap.put("updateUser", updateUser);
			}
			getSiteInfoService.insertSiteInfo(sendMap);

			return "true";
		} catch (Exception e) {
			return "error";
		}

	}

	@RequestMapping(value = "/getSiteInfo", method = RequestMethod.POST)
	@ResponseBody
	public List<SiteModel> getSiteInfo(@RequestBody Map employeeName) {
		List<SiteModel> siteList = new ArrayList<SiteModel>();

		try {
			siteList = getSiteInfoService.getSiteInfo(employeeName.get("employeeName").toString());
			for (int a = 0; a < siteList.size(); a++) {
				siteList.get(a).setWorkDate(
						dateToPeriod(siteList.get(a).getAdmissionStartDate(), siteList.get(a).getAdmissionEndDate()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("GetEmployeeInfoController.getEmployeeInfo:" + "検索結束");
		return siteList;
	}
}
