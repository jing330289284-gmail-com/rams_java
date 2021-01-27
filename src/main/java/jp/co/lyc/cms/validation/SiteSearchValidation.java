package jp.co.lyc.cms.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import jp.co.lyc.cms.model.SiteSearchModel;
import jp.co.lyc.cms.util.StatusCodeToMsgMap;
import jp.co.lyc.cms.util.UtilsCheckMethod;

public class SiteSearchValidation implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return SiteSearchModel.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		SiteSearchModel p = (SiteSearchModel) obj;
		if (!UtilsCheckMethod.isNullOrEmpty(p.getPayOffRange1())
				&& !UtilsCheckMethod.isNullOrEmpty(p.getPayOffRange2())) {
			if (Integer.parseInt(p.getPayOffRange1()) > Integer.parseInt(p.getPayOffRange2())) {
				errors.rejectValue("payOffRange1", "", StatusCodeToMsgMap.getErrMsgbyCodeReplace("MSG009", "精算"));
			}
		}
		if (!UtilsCheckMethod.isNullOrEmpty(p.getUnitPrice1()) && !UtilsCheckMethod.isNullOrEmpty(p.getUnitPrice2())) {
			if (Integer.parseInt(p.getUnitPrice1()) > Integer.parseInt(p.getUnitPrice2())) {
				errors.rejectValue("unitPrice1", "", StatusCodeToMsgMap.getErrMsgbyCodeReplace("MSG009", "単価"));
			}
		}
		if (!UtilsCheckMethod.isNullOrEmpty(p.getAdmissionStartDate())
				&& !UtilsCheckMethod.isNullOrEmpty(p.getAdmissionEndDate())) {
			if (Integer.parseInt(p.getAdmissionStartDate()) > Integer
					.parseInt(p.getAdmissionEndDate())) {
				errors.rejectValue("admissionStartDate", "",
						StatusCodeToMsgMap.getErrMsgbyCodeReplace("MSG009", "入場年月日"));
			}
		}
	}
}
