package jp.co.lyc.cms.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import jp.co.lyc.cms.model.SalesSituationModel;
import jp.co.lyc.cms.util.StatusCodeToMsgMap;
import jp.co.lyc.cms.util.UtilsCheckMethod;

public class SalesSituationValidation implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return SalesSituationModel.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		SalesSituationModel p = (SalesSituationModel) obj;

		StackTraceElement elements[] = Thread.currentThread().getStackTrace();
		for (int i = 0; i < elements.length; i++) {
			StackTraceElement stackTraceElement = elements[i];
			String methodName = stackTraceElement.getMethodName();
			if (methodName.equals("updateSalesSituation")) {
				if (!UtilsCheckMethod.isNullOrEmpty(p.getHopeLowestPrice()) && !UtilsCheckMethod.isNullOrEmpty(p.getHopeHighestPrice())) {
					if (Integer.parseInt(p.getHopeHighestPrice()) < Integer.parseInt(p.getHopeLowestPrice())) {
						errors.rejectValue("hopeLowestPrice", "", StatusCodeToMsgMap.getErrMsgbyCode("MSG008"));
					}
				}
			} 
		}

	}

	
}
