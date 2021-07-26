package jp.co.lyc.cms.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import jp.co.lyc.cms.model.SiteModel;
import jp.co.lyc.cms.util.StatusCodeToMsgMap;
import jp.co.lyc.cms.util.UtilsCheckMethod;

public class SiteInfoValidation implements Validator {
	// 日期 去“/”
	private String dateToString(String date) {
		String[] a = date.split("/");
		String b = a[0] + a[1] + a[2];
		return b;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return SiteModel.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		SiteModel p = (SiteModel) obj;
		StackTraceElement elements[] = Thread.currentThread().getStackTrace();
		// 入场年月不能为空
		if (UtilsCheckMethod.isNullOrEmpty(p.getAdmissionStartDate())) {
			errors.rejectValue("admissionStartDate", "", StatusCodeToMsgMap.getErrMsgbyCodeReplace("MSG001", "入場年月日"));
		}
		// 客户不能为空
		if (UtilsCheckMethod.isNullOrEmpty(p.getCustomerNo())) {
			errors.rejectValue("customerNo", "", StatusCodeToMsgMap.getErrMsgbyCodeReplace("MSG001", "お客様"));
		}
		// 单价不能为空
		if (UtilsCheckMethod.isNullOrEmpty(p.getUnitPrice())) {
			errors.rejectValue("unitPrice", "", StatusCodeToMsgMap.getErrMsgbyCodeReplace("MSG001", "単価"));
		}
		// 现场状态终了以及单金调整时退场年月不能为空
		if (!p.getWorkState().equals("0") && !p.getWorkState().equals("3") ) {
			if (UtilsCheckMethod.isNullOrEmpty(p.getAdmissionEndDate())) {
				errors.rejectValue("unitPrice", "", StatusCodeToMsgMap.getErrMsgbyCodeReplace("MSG001", "退場年月日"));
			}
		}
		// 精算范围后面小于前面
		if (!UtilsCheckMethod.isNullOrEmpty(p.getPayOffRange1())
				&& !UtilsCheckMethod.isNullOrEmpty(p.getPayOffRange2())) {
			if (Integer.parseInt(p.getPayOffRange1()) > Integer.parseInt(p.getPayOffRange2())) {
				errors.rejectValue("payOffRange1", "", StatusCodeToMsgMap.getErrMsgbyCodeReplace("MSG009", "精算"));
			}
		}
		// 入场时间大于退场时间
		if (!UtilsCheckMethod.isNullOrEmpty(p.getAdmissionEndDate())
				&& !UtilsCheckMethod.isNullOrEmpty(p.getAdmissionStartDate())) {
			if (Integer.parseInt(dateToString(p.getAdmissionStartDate())) > Integer
					.parseInt(dateToString(p.getAdmissionEndDate()))) {
				errors.rejectValue("admissionStartDate", "",
						StatusCodeToMsgMap.getErrMsgbyCodeReplace("MSG009", "入場年月日"));
			}
		}
		// 存在前回现场时判断
		if (p.getCheckDate() != "1") {
			// 前回现场没结束不能登陆
			if (UtilsCheckMethod.isNullOrEmpty(p.getCheckDate())) {
				errors.rejectValue("checkDate", "", StatusCodeToMsgMap.getErrMsgbyCode("MSG011"));
			}
			// 入场时间小于上次退场时间
			if (!UtilsCheckMethod.isNullOrEmpty(p.getCheckDate())
					&& !UtilsCheckMethod.isNullOrEmpty(p.getAdmissionStartDate())) {
				if (Integer.parseInt(dateToString(p.getAdmissionStartDate())) < Integer.parseInt(p.getCheckDate())) {
					errors.rejectValue("admissionStartDate", "", StatusCodeToMsgMap.getErrMsgbyCode("MSG010"));
				}
			}
		}
	}
}
