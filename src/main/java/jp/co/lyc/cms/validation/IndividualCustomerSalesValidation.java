package jp.co.lyc.cms.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import jp.co.lyc.cms.model.IndividualCustomerSalesModel;
import jp.co.lyc.cms.util.StatusCodeToMsgMap;
import jp.co.lyc.cms.util.UtilsCheckMethod;



public class IndividualCustomerSalesValidation implements Validator {
	@Override
	public boolean supports(Class<?> clazz) {
		return IndividualCustomerSalesModel.class.equals(clazz);
	}
	
	@Override
	public void validate(Object obj, Errors errors) {
		// TODO Auto-generated method stub
		IndividualCustomerSalesModel psm =(IndividualCustomerSalesModel)obj;
		StackTraceElement elements[] = Thread.currentThread().getStackTrace();
		for (int i = 0; i < elements.length; i++) {
			StackTraceElement stackTraceElement = elements[i];
			String methodName = stackTraceElement.getMethodName();
			if(methodName.equals("searchCustomerSales")) {
				if(!UtilsCheckMethod.isNullOrEmpty(psm.getStartYear())&& !UtilsCheckMethod.isNullOrEmpty(psm.getEndYear()) && UtilsCheckMethod.isNullOrEmpty(psm.getFiscalYear())&&Integer.parseInt(psm.getStartYear())>Integer.parseInt(psm.getEndYear())) {
					errors.rejectValue("startYear", "年月範囲" ,StatusCodeToMsgMap.getErrMsgbyCodeReplace("MSG009","年月範囲"));
				}
			}
			
		}
	}
}
