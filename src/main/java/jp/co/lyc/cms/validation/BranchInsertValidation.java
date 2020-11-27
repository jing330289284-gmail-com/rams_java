package jp.co.lyc.cms.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import jp.co.lyc.cms.model.BranchModel;
import jp.co.lyc.cms.util.StatusCodeToMsgMap;
import jp.co.lyc.cms.util.UtilsCheckMethod;



public class BranchInsertValidation implements Validator {
	@Override
	public boolean supports(Class<?> clazz) {
		return BranchModel.class.equals(clazz);
	}
	@Override
	public void validate(Object obj, Errors errors) {
		// TODO Auto-generated method stub
		BranchModel bm =(BranchModel)obj;
		StackTraceElement elements[] = Thread.currentThread().getStackTrace();
		for (int i = 0; i < elements.length; i++) {
			StackTraceElement stackTraceElement = elements[i];
			String methodName = stackTraceElement.getMethodName();
			if(methodName.equals("toroku")) {
				if(UtilsCheckMethod.isNullOrEmpty(bm.getBankCode())) {
					errors.rejectValue("BankCode","銀行名",StatusCodeToMsgMap.getErrMsgbyCodeReplace("MSG001","銀行名"));
				}
				if(UtilsCheckMethod.isNullOrEmpty(bm.getBankBranchName())){
					errors.rejectValue("BankBranchName", "支店名", StatusCodeToMsgMap.getErrMsgbyCodeReplace("MSG001","支店名"));
				}
				if(UtilsCheckMethod.isNullOrEmpty(bm.getBankBranchCode())){
					errors.rejectValue("BankBranchCode","支店番号",StatusCodeToMsgMap.getErrMsgbyCodeReplace("MSG001","支店番号"));
				}



			}
			
		}
	}
}
