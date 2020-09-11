package jp.co.lyc.cms.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import jp.co.lyc.cms.model.LoginModel;
import jp.co.lyc.cms.util.StatusCodeToMsgMap;
import jp.co.lyc.cms.util.UtilsCheckMethod;

public class LoginValidation implements Validator{
	@Override
	public boolean supports(Class<?> clazz) {
		return LoginModel.class.equals(clazz);
	}
	
	@Override
	public void validate(Object obj, Errors errors) {
		LoginModel p = (LoginModel) obj;
		
		StackTraceElement elements[] = Thread.currentThread().getStackTrace();
		
		for (int i = 0; i < elements.length; i++) {
			StackTraceElement stackTraceElement = elements[i];
			String methodName = stackTraceElement.getMethodName();
			if(methodName.equals("sendVerificationCode")) {
				if(UtilsCheckMethod.isNullOrEmpty(p.employeeNo) && UtilsCheckMethod.isNullOrEmpty(p.password)) {
					errors.rejectValue("employeeNo", "", StatusCodeToMsgMap.getErrMsgbyCode("MSG002"));
				}
			}else if(methodName.equals("login")) {
				if(UtilsCheckMethod.isNullOrEmpty(p.employeeNo)) {
					errors.rejectValue("employeeNo", "", StatusCodeToMsgMap.getErrMsgbyCodeReplace("MSG001","社員番号"));
				}
				if(UtilsCheckMethod.isNullOrEmpty(p.password)) {
					errors.rejectValue("password", "", StatusCodeToMsgMap.getErrMsgbyCodeReplace("MSG001","パスワード"));
				}
				if(UtilsCheckMethod.isNullOrEmpty(p.getVerificationCode())) {
					errors.rejectValue("verificationCode", "", StatusCodeToMsgMap.getErrMsgbyCodeReplace("MSG001","認証番号"));
				}
			}
			
		}
	}
}
