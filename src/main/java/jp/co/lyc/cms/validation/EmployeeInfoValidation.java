package jp.co.lyc.cms.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import jp.co.lyc.cms.model.EmployeeModel;
import jp.co.lyc.cms.util.StatusCodeToMsgMap;
import jp.co.lyc.cms.util.UtilsCheckMethod;

public class EmployeeInfoValidation implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return EmployeeModel.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		EmployeeModel p = (EmployeeModel) obj;

		StackTraceElement elements[] = Thread.currentThread().getStackTrace();
		for (int i = 0; i < elements.length; i++) {
			StackTraceElement stackTraceElement = elements[i];
			String methodName = stackTraceElement.getMethodName();
			if (methodName.equals("getEmployeeInfo")) {
				if (!UtilsCheckMethod.isNullOrEmpty(p.getAgeFrom()) && !UtilsCheckMethod.isNullOrEmpty(p.getAgeTo())) {
					if (Integer.parseInt(p.getAgeFrom()) < Integer.parseInt(p.getAgeTo())) {
						errors.rejectValue("ageFrom", "", StatusCodeToMsgMap.getErrMsgbyCode("MSG007"));
					}
				}
			} else if (methodName.equals("insertEmployee")||methodName.equals("updateEmployee")) {
				if (UtilsCheckMethod.isNullOrEmpty(p.getEmployeeFristName())) {
					errors.rejectValue("employeeFristName", "社員名", StatusCodeToMsgMap.getErrMsgbyCodeReplace("MSG001","社員名"));
				}
				if (UtilsCheckMethod.isNullOrEmpty(p.getEmployeeLastName())) {
					errors.rejectValue("employeeLastName", "社員氏", StatusCodeToMsgMap.getErrMsgbyCodeReplace("MSG001","社員氏"));
				}
				if (!UtilsCheckMethod.isNullOrEmpty(p.getPhoneNo())&&!UtilsCheckMethod.checkPhoneNo(p.getPhoneNo())) {
					errors.rejectValue("phoneNo", "電話番号", StatusCodeToMsgMap.getErrMsgbyCode("MSG0012"));
				}
				if (!UtilsCheckMethod.isNullOrEmpty(p.getCompanyMail())&&!UtilsCheckMethod.checkMail(p.getCompanyMail())) {
					errors.rejectValue("companyMail", "社内メール", StatusCodeToMsgMap.getErrMsgbyCode("MSG0011"));
				}
			} 
		}

	}

	
}
