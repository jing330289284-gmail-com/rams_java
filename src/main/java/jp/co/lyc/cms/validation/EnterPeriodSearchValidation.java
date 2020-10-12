package jp.co.lyc.cms.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import jp.co.lyc.cms.model.EnterPeriodSearchModel;

public class EnterPeriodSearchValidation implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return EnterPeriodSearchModel.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		EnterPeriodSearchModel p = (EnterPeriodSearchModel) obj;
		StackTraceElement elements[] = Thread.currentThread().getStackTrace();

		for (int i = 0; i < elements.length; i++) {
			StackTraceElement stackTraceElement = elements[i];
			String methodName = stackTraceElement.getMethodName();
			if (methodName.equals("passwordReset")) {
			}
		}
	}
}
