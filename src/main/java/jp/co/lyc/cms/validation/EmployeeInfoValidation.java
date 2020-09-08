package jp.co.lyc.cms.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import jp.co.lyc.cms.model.EmployeeModel;
import jp.co.lyc.cms.util.StatusCodeToMsgMap;

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
			String className = stackTraceElement.getClassName();
			String methodName = stackTraceElement.getMethodName();
			String fileName = stackTraceElement.getFileName();
			int lineNumber = stackTraceElement.getLineNumber();
//			if(methodName.equals("getEmployeeInfo")) {
//				System.out.print("这是getEmployeeInfo方法  "+ methodName);
//			}
//			if(methodName.equals("deleteEmployeeInfo")) {
//				System.out.print("这是deleteEmployeeInfo方法  "+ methodName);
//			}
			 System.out.println("StackTraceElement数组下标 i="+i+",fileName="
	                    +fileName+",className="+className+",methodName="+methodName+",lineNumber="+lineNumber);
		}
		if (!isNullOrEmpty(p.getAgeFrom()) && !isNullOrEmpty(p.getAgeTo())) {
			
		}
		if (!isNullOrEmpty(p.getAgeFrom()) && !isNullOrEmpty(p.getAgeTo())) {
			if (Integer.parseInt(p.getAgeFrom()) < Integer.parseInt(p.getAgeTo())) {
				errors.rejectValue("ageFrom", "", StatusCodeToMsgMap.getErrMsgbyCode("MSG007"));
			}
		}

	}

	public boolean isNullOrEmpty(String aString) {
		boolean result = true;
		if (aString == null || aString.isEmpty()) {
			return result;
		} else {
			return result = false;
		}
	}
}
