package jp.co.lyc.cms.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import jp.co.lyc.cms.model.SituationChangesModel;
import jp.co.lyc.cms.service.SituationChangesService;

@Controller
@RequestMapping(value = "/SituationChange")
public class SituationChangeController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	SituationChangesService SituationChangesService;
	
	@RequestMapping(value = "/searchSituationChange", method = RequestMethod.POST)
	
	@ResponseBody
	public Map<String, Object> searchSituationChanges(@RequestBody SituationChangesModel situationInfo){
		String SocialInsurance="";
		String SocialInsuranceFront="";
		String ScheduleOfBonusAmount="";
		String employeeFormNameChange= "";
		List<SituationChangesModel> situationChangeList = new ArrayList<SituationChangesModel>();
		List<SituationChangesModel> situationChangeListFront = new ArrayList<SituationChangesModel>();
		Map<String, Object> sendMap = getDetailParam(situationInfo);
		
		if(situationInfo.getClassification().equals("3")||situationInfo.getClassification().equals("4")) {
			logger.info("PersonalSalesSearchController.searchEmpDetails:" + "入退社検索開始");
			situationChangeList = SituationChangesService.searchSituationIntoORretirement(sendMap);
			logger.info("PersonalSalesSearchController.searchEmpDetails:" + "入退社検索結束");
			for(int i =0 ;i<situationChangeList.size();i++) {
				if(situationChangeList.get(i).getSocialInsuranceFlag()!=null&&situationChangeList.get(i).getSocialInsuranceFlag().equals("1")) {
					SocialInsurance = "追加";
				}else {
					SocialInsurance = "なし";
				}	
				situationChangeList.get(i).setSocialInsuranceFlag(SocialInsurance);
			}	
			Map<String, Object> resultdata = new HashMap<>();
			resultdata.put("data", situationChangeList);
			return resultdata;
		}else {

		logger.info("PersonalSalesSearchController.searchEmpDetails:" + "検索開始");
		situationChangeList = SituationChangesService.searchSituationChanges(sendMap);
		logger.info("PersonalSalesSearchController.searchEmpDetails:" + "検索結束");
		for(int i=0;i<situationChangeList.size();i++) {
			if(situationChangeList.get(i).getSocialInsuranceFlag().equals("1")) {
				situationChangeList.get(i).setSocialInsuranceFlag("追加");
			}else {
				situationChangeList.get(i).setSocialInsuranceFlag("なし");
			}
		}
		
			
		List<String> getYandM = new ArrayList<String>();
		List<String> getEmpNo = new ArrayList<String>();
		for(int i =0 ;i<situationChangeList.size();i++) {
			getYandM.add(i, situationChangeList.get(i).getReflectYearAndMonth());
			getEmpNo.add(i, situationChangeList.get(i).getEmployeeNo());		
		}
		sendMap.put("getYandM", getYandM);
		sendMap.put("getEmpNo",getEmpNo);
		
		logger.info("PersonalSalesSearchController.searchEmpDetails:" + "二回目検索開始");
		situationChangeListFront = SituationChangesService.searchSituationChangesFront(sendMap);
		logger.info("PersonalSalesSearchController.searchEmpDetails:" + "二回目検索結束");
		
		for(int i=0;i<situationChangeListFront.size();i++) {
			if(situationChangeListFront.get(i).getSocialInsuranceFlag().equals("1")) {
				situationChangeListFront.get(i).setSocialInsuranceFlag("追加");
			}else {
				situationChangeListFront.get(i).setSocialInsuranceFlag("なし");
			}
		}
		
		String salaryChange = "";
		String socialInsuranceFlagChange = "";
		int startIndex =situationChangeListFront.size()-1;


		for(int i=situationChangeList.size()-1;i>=0;i--) {
			for(int j =startIndex;j>=0;j--) {
				String empNo =situationChangeList.get(i).getEmployeeNo();
				String empNoFront =situationChangeListFront.get(j).getEmployeeNo();
				
				//not入退职
				if(empNo.equals(empNoFront)) {
					//GONGZI合并
					if(situationChangeListFront.get(j).getSalary().equals(situationChangeList.get(i).getSalary())
							||situationChangeListFront.get(j).getSalary().equals("0")
							||situationChangeListFront.get(j).getSalary().equals("")
					) {
						salaryChange=situationChangeList.get(i).getSalary();
					}else {
						salaryChange= situationChangeListFront.get(j).getSalary()+"~"+situationChangeList.get(i).getSalary();
						situationChangeList.get(i).setSalaryFlag("1");
					}
					
					//BAOXIAN合并
					if(situationChangeListFront.get(j).getSocialInsuranceFlag().equals(situationChangeList.get(i).getSocialInsuranceFlag())) {
						socialInsuranceFlagChange="なし";
					}else {						
						socialInsuranceFlagChange=SocialInsuranceFront+"~"+SocialInsurance;
					}
					
					//BONUS合并
					if(situationChangeListFront.get(j).getScheduleOfBonusAmount().equals(situationChangeList.get(i).getScheduleOfBonusAmount())
							||situationChangeListFront.get(j).getScheduleOfBonusAmount()==null) {
						ScheduleOfBonusAmount=situationChangeList.get(i).getScheduleOfBonusAmount();
					}else {
						ScheduleOfBonusAmount=situationChangeListFront.get(j).getScheduleOfBonusAmount()+"~"+situationChangeList.get(i).getScheduleOfBonusAmount();
					}
					
					//SHEYUANXINGSHI合并
					if(situationChangeListFront.get(j).getEmployeeFormName().equals(situationChangeList.get(i).getEmployeeFormName())
							||situationChangeListFront.get(j).getEmployeeFormName()==null) {
						employeeFormNameChange =situationChangeList.get(i).getEmployeeFormName();
					}else if(situationChangeListFront.get(j).getEmployeeFormName().equals(situationChangeList.get(i).getEmployeeFormName())
							||situationChangeList.get(i).getEmployeeFormName()==null) {
						employeeFormNameChange =situationChangeListFront.get(j).getEmployeeFormName();
					}
					else {
						employeeFormNameChange=situationChangeListFront.get(j).getEmployeeFormName()+"~"+situationChangeList.get(i).getEmployeeFormName();
					}
					
					//setparam
					situationChangeList.get(i).setSalary(salaryChange);
					situationChangeList.get(i).setSocialInsuranceFlag(socialInsuranceFlagChange);
					situationChangeList.get(i).setScheduleOfBonusAmount(ScheduleOfBonusAmount);
					situationChangeList.get(i).setEmployeeFormName(employeeFormNameChange);
					startIndex--;
					break;
				}

			}
			
		}
//		for(int n =0;n<situationChangeList.size();n++) {
//			if(situationChangeList.get(n).getSalaryFlag().equals("1")&&!(situationChangeList.get(n).getSalaryFlag()==null)) {
//			situationChangeList= new ArrayList<SituationChangesModel>();
//			situationChangeList.add(n, situationChangeList.get(n));					
//			}
//		}
		Map<String, Object> resultdata = new HashMap<>();
		resultdata.put("data", situationChangeList);
		return resultdata;
	}
	}
	
	public Map<String, Object> getDetailParam(SituationChangesModel situationInfo) {
		Map<String, Object> sendMap = new HashMap<String, Object>();
		String startYandM = situationInfo.getStartYandM();
		String endYandM = situationInfo.getEndYandM();
		String classification = situationInfo.getClassification();
		sendMap.put("startYandM", startYandM);
		sendMap.put("endYandM", endYandM);
		sendMap.put("classification", classification);
		return sendMap;
		
	}
}
