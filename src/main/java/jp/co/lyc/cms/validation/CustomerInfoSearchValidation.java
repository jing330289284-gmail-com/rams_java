package jp.co.lyc.cms.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import jp.co.lyc.cms.model.CustomerInfoModel;
import jp.co.lyc.cms.util.StatusCodeToMsgMap;
import jp.co.lyc.cms.util.UtilsCheckMethod;

public class CustomerInfoSearchValidation implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return CustomerInfoModel.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		// TODO Auto-generated method stub
		CustomerInfoModel p = (CustomerInfoModel) obj;

		StackTraceElement elements[] = Thread.currentThread().getStackTrace();
		for (int i = 0; i < elements.length; i++) {
			StackTraceElement stackTraceElement = elements[i];
			String methodName = stackTraceElement.getMethodName();
			if (methodName.equals("customerSearch")) {
				if (!UtilsCheckMethod.isNullOrEmpty(p.getCapitalStockBack()) &&
						!UtilsCheckMethod.isNullOrEmpty(p.getCapitalStockFront())) {
					if(Integer.parseInt(p.getCapitalStockBack()) < 
						Integer.parseInt(p.getCapitalStockFront())) {
						errors.rejectValue("capitalStock", "", 
								StatusCodeToMsgMap.getErrMsgbyCodeReplace("MSG005","資本金範囲"));
					}
				}
				if (!UtilsCheckMethod.isNullOrEmpty(p.getTraderPersonBack()) &&
						!UtilsCheckMethod.isNullOrEmpty(p.getTraderPersonFront())) {
					if(Integer.parseInt(p.getTraderPersonBack()) < 
						Integer.parseInt(p.getTraderPersonFront())) {
						errors.rejectValue("traderPerson", "", 
								StatusCodeToMsgMap.getErrMsgbyCodeReplace("MSG005","取引人月範囲"));
					}
				}
			}
		}
	}
}
