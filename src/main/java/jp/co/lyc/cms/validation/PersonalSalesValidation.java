package jp.co.lyc.cms.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import jp.co.lyc.cms.model.PersonalSalesSearchModel;
import jp.co.lyc.cms.util.StatusCodeToMsgMap;
import jp.co.lyc.cms.util.UtilsCheckMethod;


public class PersonalSalesValidation implements Validator {
	@Override
	public boolean supports(Class<?> clazz) {
		return PersonalSalesSearchModel.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		// TODO Auto-generated method stub
		PersonalSalesSearchModel psm =(PersonalSalesSearchModel)obj;
		StackTraceElement elements[] = Thread.currentThread().getStackTrace();
		for (int i = 0; i < elements.length; i++) {
			StackTraceElement stackTraceElement = elements[i];
			String methodName = stackTraceElement.getMethodName();
			if(methodName.equals("searchEmpDetails")) {
				if(UtilsCheckMethod.isNullOrEmpty(psm.getEmployeeName())){
					errors.rejectValue("employeeNo","社員名",StatusCodeToMsgMap.getErrMsgbyCodeReplace("MSG001","社員名"));
				}
				if(UtilsCheckMethod.isNullOrEmpty(psm.getStartYearAndMonth())&&UtilsCheckMethod.isNullOrEmpty(psm.getEndYearAndMonth()) && UtilsCheckMethod.isNullOrEmpty(psm.getFiscalYear())){
					errors.rejectValue("StartYearAndMonth", "年度また年月", StatusCodeToMsgMap.getErrMsgbyCodeReplace("MSG001","年度また年月"));
				}
				if(!UtilsCheckMethod.isNullOrEmpty(psm.getStartYearAndMonth())&& !UtilsCheckMethod.isNullOrEmpty(psm.getEndYearAndMonth()) && UtilsCheckMethod.isNullOrEmpty(psm.getFiscalYear())&&Integer.parseInt(psm.getStartYearAndMonth())>Integer.parseInt(psm.getEndYearAndMonth())) {
					errors.rejectValue("StartYearAndMonth", "年月範囲" ,StatusCodeToMsgMap.getErrMsgbyCodeReplace("MSG009","年月範囲"));
				}

			}
			
		}
	}
}
