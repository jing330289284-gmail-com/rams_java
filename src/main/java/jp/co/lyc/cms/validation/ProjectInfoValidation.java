package jp.co.lyc.cms.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import jp.co.lyc.cms.model.ProjectInfoModel;
import jp.co.lyc.cms.util.StatusCodeToMsgMap;
import jp.co.lyc.cms.util.UtilsCheckMethod;

public class ProjectInfoValidation implements Validator {
	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return ProjectInfoModel.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		// TODO Auto-generated method stub
		ProjectInfoModel p = (ProjectInfoModel) obj;

		StackTraceElement elements[] = Thread.currentThread().getStackTrace();
		for (int i = 0; i < elements.length; i++) {
			StackTraceElement stackTraceElement = elements[i];
			String methodName = stackTraceElement.getMethodName();
			if (methodName.equals("toroku")) {
				if (UtilsCheckMethod.isNullOrEmpty(p.getProjectName())) {
					errors.rejectValue("projectName", "", 
							StatusCodeToMsgMap.getErrMsgbyCodeReplace("MSG001", "案件名"));
				}
				if (!UtilsCheckMethod.isNullOrEmpty(p.getUnitPriceRangeLowest())
						&& !UtilsCheckMethod.isNullOrEmpty(p.getUnitPriceRangeHighest())) {
					if (Integer.parseInt(p.getUnitPriceRangeLowest()) > 
							Integer.parseInt(p.getUnitPriceRangeHighest())
							|| Integer.parseInt(p.getUnitPriceRangeLowest()) == 
								Integer.parseInt(p.getUnitPriceRangeHighest())) {
						errors.rejectValue("unitPriceRangeLowest", "",
								StatusCodeToMsgMap.getErrMsgbyCodeReplace("MSG009", "単価"));
					}
				}
				if(!UtilsCheckMethod.isNullOrEmpty(p.getPayOffRangeLowest())
						&& !UtilsCheckMethod.isNullOrEmpty(p.getPayOffRangeHighest())) {
					if (Integer.parseInt(p.getPayOffRangeLowest()) > 
					Integer.parseInt(p.getPayOffRangeHighest())) {
						errors.rejectValue("payOffRangeLowest", "",
								StatusCodeToMsgMap.getErrMsgbyCodeReplace("MSG009", "清算範囲"));
					}
				}
				if(!UtilsCheckMethod.isNullOrEmpty(p.getProjectPhaseStart())
						&& !UtilsCheckMethod.isNullOrEmpty(p.getProjectPhaseEnd())) {
					if (Integer.parseInt(p.getProjectPhaseStart()) > 
					Integer.parseInt(p.getProjectPhaseEnd())) {
						errors.rejectValue("projectPhaseEnd", "",
								StatusCodeToMsgMap.getErrMsgbyCodeReplace("MSG009", "作業工程"));
					}
				}
				if (!UtilsCheckMethod.isNullOrEmpty(p.getMail())) {
					if (!UtilsCheckMethod.checkMail(p.getMail())) {
						errors.rejectValue("payOffRangeLowest", "",
								StatusCodeToMsgMap.getErrMsgbyCodeReplace("MSG009", "メール"));
					}
				}
			}
		}
	}
}
