package jp.co.lyc.cms.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import jp.co.lyc.cms.model.PasswordSetEmployeeModel;
import jp.co.lyc.cms.util.StatusCodeToMsgMap;
import jp.co.lyc.cms.util.UtilsCheckMethod;

public class PasswordSetEmployeeValidation implements Validator{
	@Override
	public boolean supports(Class<?> clazz) {
		return PasswordSetEmployeeModel.class.equals(clazz);
	}
	
	@Override
	public void validate(Object obj, Errors errors) {
		PasswordSetEmployeeModel p = (PasswordSetEmployeeModel) obj;
		
		StackTraceElement elements[] = Thread.currentThread().getStackTrace();
		
		for (int i = 0; i < elements.length; i++) {
			StackTraceElement stackTraceElement = elements[i];
			String methodName = stackTraceElement.getMethodName();
			if(methodName.equals("passwordReset")) {
				if(UtilsCheckMethod.isNullOrEmpty(p.getOldPassword())) {
					errors.rejectValue("oldPassword", "", 
							StatusCodeToMsgMap.getErrMsgbyCodeReplace("MSG001", "既存パスワード"));
				}
				if(UtilsCheckMethod.isNullOrEmpty(p.getNewPassword())) {
					errors.rejectValue("newPassword", "", 
							StatusCodeToMsgMap.getErrMsgbyCodeReplace("MSG001", "新しいパスワード"));
				}else {
					if(p.getNewPassword().length() < 8) {
						errors.rejectValue("newPassword", "", 
								StatusCodeToMsgMap.getErrMsgbyCodeReplace("MSG005", "パスワードの枠"));
					}
					if(!UtilsCheckMethod.passwordCheck(p.getNewPassword())) {
						errors.rejectValue("newPassword", "", 
								StatusCodeToMsgMap.getErrMsgbyCodeReplace("MSG007", "パスワード"));
					}
				}
				if(UtilsCheckMethod.isNullOrEmpty(p.getPasswordCheck())) {
					errors.rejectValue("newPassword", "", 
							StatusCodeToMsgMap.getErrMsgbyCodeReplace("MSG001", "パスワード再確認"));
				}
				if(!p.getPasswordCheck().equals(p.getNewPassword())) {
					errors.rejectValue("newPassword", "", 
							StatusCodeToMsgMap.getErrMsgbyCodeReplace("MSG003", "パスワード再確認"));
				}
			}
		}
	}
}

