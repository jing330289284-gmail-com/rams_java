package jp.co.lyc.cms.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import jp.co.lyc.cms.model.SituationChangesModel;
import jp.co.lyc.cms.util.StatusCodeToMsgMap;
import jp.co.lyc.cms.util.UtilsCheckMethod;

public class SituationChangesValidation implements Validator{
	@Override
	public boolean supports(Class<?> clazz) {
		return SituationChangesModel.class.equals(clazz);
	}
	
	@Override
	public void validate(Object obj, Errors errors) {
		// TODO Auto-generated method stub
		SituationChangesModel scm =(SituationChangesModel)obj;
		StackTraceElement elements[] = Thread.currentThread().getStackTrace();
		for (int i = 0; i < elements.length; i++) {
			StackTraceElement stackTraceElement = elements[i];
			String methodName = stackTraceElement.getMethodName();
			if(methodName.equals("searchSituationChanges")) {
				if(!UtilsCheckMethod.isNullOrEmpty(scm.getStartYandM())&&!UtilsCheckMethod.isNullOrEmpty(scm.getEndYandM())&& Integer.parseInt(scm.getEndYandM())<Integer.parseInt(scm.getStartYandM())){
					errors.rejectValue("endYandM", "年月範囲", StatusCodeToMsgMap.getErrMsgbyCodeReplace("MSG009","年月範囲"));
				}
			}
			}

	}
}