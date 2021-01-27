package jp.co.lyc.cms.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import jp.co.lyc.cms.model.AccountInfoModel;
import jp.co.lyc.cms.util.UtilsCheckMethod;

public class AccountInfoValidation implements Validator{
	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return AccountInfoModel.class.equals(clazz);
	}

	/**
	 *
	 */
	@Override
	public void validate(Object obj, Errors errors) {
		// TODO Auto-generated method stub
		AccountInfoModel p = (AccountInfoModel) obj;
		if(UtilsCheckMethod.isNullOrEmpty(p.getAccountName())) {
			
		}
	}
}
