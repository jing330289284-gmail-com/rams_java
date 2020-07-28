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
@CrossOrigin(origins = "http://localhost:3000")
public class SiteInfoController {
	private String dateToString(String date) {
		String[] a = date.split("/");
		String b = a[1];
		if (a[2].length() == 1) {
			b = b + "0" + a[2];
		} else {
			b = b + a[2];
		}
		if (a[3].length() == 1) {
			b = b + "0" + a[3];
		} else {
			b = b + a[3];
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
			String siteNo = siteModel.getSiteNo();
			String customerNo = siteModel.getCustomerNo();
			String topCustomerNo = siteModel.getTopCustomerNo();
			String AdmissionStartDate = siteModel.getAdmissionStartDate();
			String location = siteModel.getLocation();
			String siteManager = siteModel.getSiteManager();
			String AdmissionEndDate = siteModel.getAdmissionEndDate();
			String unitPrice = siteModel.getUnitPrice();
			String siteRoleCode = siteModel.getSiteRoleCode();
			String payOffRange = siteModel.getTime_1() + siteModel.getTime_2();
			String systemName = siteModel.getSystemName();
			String developlanguage = siteModel.getDeveloplanguage();
			if (employeeNo != null && employeeNo.length() != 0) {
				sendMap.put("employeeNo", employeeNo);
			} else {
				sendMap.put("employeeNo", "LYC001");
			}
			if (siteNo != null && siteNo.length() != 0) {
				sendMap.put("siteNo", siteNo);
			} else {
				sendMap.put("siteNo", "10");
			}
			if (customerNo != null && customerNo.length() != 0) {
				sendMap.put("customerNo", customerNo);
			}
			if (topCustomerNo != null && topCustomerNo.length() != 0) {
				sendMap.put("topCustomerNo", topCustomerNo);
			}
			if (AdmissionStartDate != null && AdmissionStartDate.length() != 0) {
				sendMap.put("AdmissionStartDate", dateToString(AdmissionStartDate));
			}
			if (location != null && location.length() != 0) {
				sendMap.put("location", location);
			}
			if (siteManager != null && siteManager.length() != 0) {
				sendMap.put("siteManager", siteManager);
			}
			if (AdmissionEndDate != null && AdmissionEndDate.length() != 0) {
				sendMap.put("AdmissionEndDate", dateToString(AdmissionEndDate));
			}
			if (unitPrice != null && unitPrice.length() != 0) {
				sendMap.put("unitPrice", unitPrice);
			}
			if (siteRoleCode != null && siteRoleCode.length() != 0) {
				sendMap.put("siteRoleCode", siteRoleCode);
			}
			if (payOffRange != null && payOffRange.length() != 0) {
				sendMap.put("payOffRange", payOffRange);
			}
			if (systemName != null && systemName.length() != 0) {
				sendMap.put("systemName", systemName);
			}
			if (developlanguage != null && developlanguage.length() != 0) {
				sendMap.put("developlanguage", developlanguage);
			}

			getSiteInfoService.insertSiteInfo(sendMap);

			return "true";
		} catch (Exception e) {
			return "error";
		}

	}

	@RequestMapping(value = "/getSiteInfo", method = RequestMethod.POST)
	@ResponseBody
	public List<SiteModel> getSiteInfo(@RequestBody Map employeeNo) {
		List<SiteModel> siteList = new ArrayList<SiteModel>();

		try {
			siteList = getSiteInfoService.getSiteInfo(employeeNo.get("employeeNo").toString());
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
