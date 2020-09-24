package jp.co.lyc.cms.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import jp.co.lyc.cms.controller.WagesInfoController;
import jp.co.lyc.cms.model.WagesInfoModel;
import jp.co.lyc.cms.util.StatusCodeToMsgMap;
import jp.co.lyc.cms.util.UtilsCheckMethod;

public class WagesInfoValidation implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return WagesInfoModel.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		WagesInfoModel p = (WagesInfoModel) obj;

		StackTraceElement elements[] = Thread.currentThread().getStackTrace();
		for (int i = 0; i < elements.length; i++) {
			StackTraceElement stackTraceElement = elements[i];
			String methodName = stackTraceElement.getMethodName();
			
			if (methodName.equals("toroku")) {
				if(UtilsCheckMethod.isNullOrEmpty(p.getEmployeeNo())) {
					errors.rejectValue("employeeNo", "", StatusCodeToMsgMap.getErrMsgbyCodeReplace("MSG001","社員名"));
				}else if(!p.getEmployeeNo().substring(0,3).equals("LYC")) {
					errors.rejectValue("employeeNo", "", StatusCodeToMsgMap.getErrMsgbyCode("MSG0012"));
				}
				if(UtilsCheckMethod.isNullOrEmpty(p.getSalary()) && UtilsCheckMethod.isNullOrEmpty(p.getWaitingCost())) {
					errors.rejectValue("salary", "", StatusCodeToMsgMap.getErrMsgbyCodeReplace("MSG001","給料または非稼働費用"));
				}
				if(UtilsCheckMethod.isNullOrEmpty(p.getReflectYearAndMonth())) {
					errors.rejectValue("reflectYearAndMonth", "", StatusCodeToMsgMap.getErrMsgbyCodeReplace("MSG001","反映年月"));
				}
				if(UtilsCheckMethod.isNullOrEmpty(p.getTotalAmount())) {
					errors.rejectValue("totalAmount", "", StatusCodeToMsgMap.getErrMsgbyCodeReplace("MSG003","入力した金額"));
				}
				if(p.getBonusFlag().equals("1")) {
					if(!UtilsCheckMethod.isNullOrEmpty(p.getScheduleOfBonusAmount()) && 
							UtilsCheckMethod.isNullOrEmpty(p.getNextBonusMonth())) {
						errors.rejectValue("nextBonusMonth", "", StatusCodeToMsgMap.getErrMsgbyCodeReplace("MSG001","次のボーナス月"));
					}
					if(UtilsCheckMethod.isNullOrEmpty(p.getScheduleOfBonusAmount()) && 
							!UtilsCheckMethod.isNullOrEmpty(p.getNextBonusMonth())) {
						errors.rejectValue("scheduleOfBonusAmount", "", StatusCodeToMsgMap.getErrMsgbyCodeReplace("MSG001","ボーナス予定額"));
					}
				}
			}
		}
		
	}
}
