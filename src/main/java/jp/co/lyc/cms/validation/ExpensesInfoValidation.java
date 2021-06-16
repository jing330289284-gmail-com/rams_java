package jp.co.lyc.cms.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import jp.co.lyc.cms.model.ExpensesInfoModel;
import jp.co.lyc.cms.util.StatusCodeToMsgMap;
import jp.co.lyc.cms.util.UtilsCheckMethod;

public class ExpensesInfoValidation implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return ExpensesInfoModel.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		ExpensesInfoModel p = (ExpensesInfoModel) obj;

		StackTraceElement elements[] = Thread.currentThread().getStackTrace();
		for (int i = 0; i < elements.length; i++) {
			StackTraceElement stackTraceElement = elements[i];
			String methodName = stackTraceElement.getMethodName();
			
			if (methodName.equals("toroku")) {
				if(UtilsCheckMethod.isNullOrEmpty(p.getEmployeeNo())) {
					errors.rejectValue("employeeNo", "", StatusCodeToMsgMap.getErrMsgbyCodeReplace("MSG001","社員名"));
				}
				if(UtilsCheckMethod.isNullOrEmpty(p.getExpensesReflectYearAndMonth())) {
					errors.rejectValue("expensesReflectYearAndMonth", "", StatusCodeToMsgMap.getErrMsgbyCodeReplace("MSG001","開始年月"));
				}
				if(!UtilsCheckMethod.isNullOrEmpty(p.getTransportationExpenses())) {
					if(!UtilsCheckMethod.numberFormat(p.getTransportationExpenses())) {
						errors.rejectValue("transportationExpenses", "", StatusCodeToMsgMap.getErrMsgbyCodeReplace("MSG007","交通費"));
					}
				}
				if(!UtilsCheckMethod.isNullOrEmpty(p.getLeaderAllowanceAmount())) {
					if(!UtilsCheckMethod.numberFormat(p.getLeaderAllowanceAmount())) {
						errors.rejectValue("leaderAllowanceAmount", "", StatusCodeToMsgMap.getErrMsgbyCodeReplace("MSG007","リーダー手当"));
					}
				}
				if(!UtilsCheckMethod.isNullOrEmpty(p.getOtherAllowanceAmount())) {
					if(UtilsCheckMethod.isNullOrEmpty(p.getOtherAllowanceName())) {
						errors.rejectValue("otherAllowanceAmount", "", StatusCodeToMsgMap.getErrMsgbyCodeReplace("MSG001","他の手当"));
					}
				}
				if(!UtilsCheckMethod.isNullOrEmpty(p.getOtherAllowanceAmount())) {
					if(!UtilsCheckMethod.numberFormat(p.getOtherAllowanceAmount())) {
						errors.rejectValue("otherAllowanceAmount", "", StatusCodeToMsgMap.getErrMsgbyCodeReplace("MSG007","他の手当の費用"));
					}
				}
				if(!UtilsCheckMethod.isNullOrEmpty(p.getSpecialAllowance())) {
					if(!UtilsCheckMethod.numberFormat(p.getSpecialAllowance())) {
						errors.rejectValue("specialAllowance", "", StatusCodeToMsgMap.getErrMsgbyCodeReplace("MSG007","特別手当"));
					}
				}
			}
		}
		}
}
