package jp.co.lyc.cms.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import jp.co.lyc.cms.model.LoginEmployeeModel;
import jp.co.lyc.cms.util.StatusCodeToMsgMap;
import jp.co.lyc.cms.util.UtilsCheckMethod;

public class LoginEmployeeValidation implements Validator{
	@Override
	public boolean supports(Class<?> clazz) {
		return LoginEmployeeModel.class.equals(clazz);
	}
	
	@Override
	public void validate(Object obj, Errors errors) {
		LoginEmployeeModel p = (LoginEmployeeModel) obj;
		
		StackTraceElement elements[] = Thread.currentThread().getStackTrace();
		
		for (int i = 0; i < elements.length; i++) {
			StackTraceElement stackTraceElement = elements[i];
			String methodName = stackTraceElement.getMethodName();
			if(methodName.equals("sendMail")) {
				if(UtilsCheckMethod.isNullOrEmpty(p.getEmployeeNo())) {
					errors.rejectValue("employeeNo", "", StatusCodeToMsgMap.getErrMsgbyCodeReplace("MSG001","社員番号"));
				}
			}else if(methodName.equals("login")) {
				if(UtilsCheckMethod.isNullOrEmpty(p.employeeNo)) {
					errors.rejectValue("employeeNo", "", StatusCodeToMsgMap.getErrMsgbyCodeReplace("MSG001","社員番号"));
				}
				if(UtilsCheckMethod.isNullOrEmpty(p.password)) {
					errors.rejectValue("password", "", StatusCodeToMsgMap.getErrMsgbyCodeReplace("MSG001","パスワード"));
				}
			}
		}
	}
}
