package jp.co.lyc.cms.validation;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import jp.co.lyc.cms.model.MonthlySalesSearchModel;
import jp.co.lyc.cms.util.StatusCodeToMsgMap;
import jp.co.lyc.cms.util.UtilsCheckMethod;

public class MonthlySalesSearchValidation  implements Validator{
	@Override
	public boolean supports(Class<?> clazz) {
		return MonthlySalesSearchModel.class.equals(clazz);
	}
	
	@Override
	public void validate(Object obj, Errors errors) {
		// TODO Auto-generated method stub
		MonthlySalesSearchModel psm =(MonthlySalesSearchModel)obj;
		StackTraceElement elements[] = Thread.currentThread().getStackTrace();
		Date day = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyyMM");
		String sysTime = df.format(day);
		for (int i = 0; i < elements.length; i++) {
			StackTraceElement stackTraceElement = elements[i];
			String methodName = stackTraceElement.getMethodName();
			if(methodName.equals("searchMonthlySales")) {
//				if(UtilsCheckMethod.isNullOrEmpty(psm.getNowYandM())){
//					errors.rejectValue("nowYandM","年月",StatusCodeToMsgMap.getErrMsgbyCodeReplace("MSG001","年月"));
//				}
				if(UtilsCheckMethod.isNullOrEmpty(psm.getStartYandM())&&UtilsCheckMethod.isNullOrEmpty(psm.getEndYandM())&&UtilsCheckMethod.isNullOrEmpty(psm.getFiscalYear())){
					errors.rejectValue("startYandM", "年度および年月", StatusCodeToMsgMap.getErrMsgbyCodeReplace("MSG001","年度および年月"));
				}
				if(!UtilsCheckMethod.isNullOrEmpty(psm.getStartYandM())&&UtilsCheckMethod.isNullOrEmpty(psm.getEndYandM())&& Integer.parseInt(psm.getStartYandM())-Integer.parseInt(sysTime)>0){
					errors.rejectValue("startYandM", "年月範囲", StatusCodeToMsgMap.getErrMsgbyCodeReplace("MSG009","年月範囲"));
				}
				
				if(UtilsCheckMethod.isNullOrEmpty(psm.getStartYandM())&&!UtilsCheckMethod.isNullOrEmpty(psm.getEndYandM())&& Integer.parseInt(psm.getEndYandM())-Integer.parseInt(sysTime)>0){
					errors.rejectValue("endYandM", "年月範囲", StatusCodeToMsgMap.getErrMsgbyCodeReplace("MSG009","年月範囲"));
				}
				if(!UtilsCheckMethod.isNullOrEmpty(psm.getStartYandM())&&!UtilsCheckMethod.isNullOrEmpty(psm.getEndYandM())&& Integer.parseInt(psm.getEndYandM())<Integer.parseInt(psm.getStartYandM())){
					errors.rejectValue("endYandM", "年月範囲", StatusCodeToMsgMap.getErrMsgbyCodeReplace("MSG009","年月範囲"));
				}
				
				if(!UtilsCheckMethod.isNullOrEmpty(psm.getUtilPricefront())&& !UtilsCheckMethod.isNullOrEmpty(psm.getUtilPriceback())&&Integer.parseInt(psm.getUtilPricefront())>Integer.parseInt(psm.getUtilPriceback())) {
					errors.rejectValue("utilPricefront", "単価範囲" ,StatusCodeToMsgMap.getErrMsgbyCodeReplace("MSG009","単価範囲"));
				}
				if(!UtilsCheckMethod.isNullOrEmpty(psm.getSalaryfront())&& !UtilsCheckMethod.isNullOrEmpty(psm.getSalaryback())&&Integer.parseInt(psm.getSalaryfront())>Integer.parseInt(psm.getSalaryback())) {
					errors.rejectValue("utilPricefront", "給料範囲" ,StatusCodeToMsgMap.getErrMsgbyCodeReplace("MSG009","給料範囲"));
				}
				if(!UtilsCheckMethod.isNullOrEmpty(psm.getGrossProfitFront())&& !UtilsCheckMethod.isNullOrEmpty(psm.getGrossProfitBack())&&Integer.parseInt(psm.getGrossProfitFront())>Integer.parseInt(psm.getGrossProfitBack())) {
					errors.rejectValue("utilPricefront", "粗利範囲" ,StatusCodeToMsgMap.getErrMsgbyCodeReplace("MSG009","粗利範囲"));
				}

			}
			
		}
	}
}

