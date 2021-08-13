package jp.co.lyc.cms.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import jp.co.lyc.cms.mapper.ProjectInfoMapper;
import jp.co.lyc.cms.model.ProjectInfoModel;
import jp.co.lyc.cms.util.UtilsCheckMethod;

@Component
public class ProjectInfoService {

	@Autowired
	ProjectInfoMapper projectInfoMapper;
	
	/**
	 * 責任者Dropの取得
	 * @return
	 */
	public ArrayList<ProjectInfoModel> getPersonInCharge(String customerNo) {
		ArrayList<ProjectInfoModel> resultList = new ArrayList<ProjectInfoModel>();
		ArrayList<ProjectInfoModel> infoList = projectInfoMapper.getPersonInCharge(customerNo);
		int i = 0;
		for(ProjectInfoModel p:infoList) {
			if(p != null && !(UtilsCheckMethod.isNullOrEmpty(p.getName()) && 
					UtilsCheckMethod.isNullOrEmpty(p.getMail()))) {
				p.setCode(Integer.toString(i));
				resultList.add(p);
				i++;
			}
		}
		return resultList;
	}
	
	/**
	 * 追加の場合
	 * @param wagesInfoModel
	 * @return
	 */
	@Transactional(rollbackFor = Exception.class)
	public boolean insert(HashMap<String, String> sendMap) {
		try {
			projectInfoMapper.insert(sendMap);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 更新の場合
	 * @param wagesInfoModel
	 * @return
	 */
	@Transactional(rollbackFor = Exception.class)
	public boolean update(HashMap<String, String> sendMap) {
		try {
			projectInfoMapper.update(sendMap);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 案件情報のsendmap作成
	 * @param projectInfoModel
	 * @return
	 */
	public HashMap<String, String> getSendMap(ProjectInfoModel projectInfoModel) {
		HashMap<String, String> sendMap = new HashMap<String, String>();
		sendMap.put("projectNo", projectInfoModel.getProjectNo());
		sendMap.put("projectName", projectInfoModel.getProjectName());
		sendMap.put("nationalityCode", projectInfoModel.getNationalityCode());
		sendMap.put("admissionPeriod", projectInfoModel.getAdmissionPeriod());
		sendMap.put("admissionMonthCode", projectInfoModel.getAdmissionMonthCode());
		sendMap.put("projectType", projectInfoModel.getProjectType());
		sendMap.put("successRate", projectInfoModel.getSuccessRate());
		sendMap.put("customerNo", projectInfoModel.getCustomerNo());
		sendMap.put("personInCharge", projectInfoModel.getPersonInCharge());
		sendMap.put("mail", projectInfoModel.getMail());
		sendMap.put("keyWordOfLanagurue1", projectInfoModel.getKeyWordOfLanagurue1());
		sendMap.put("keyWordOfLanagurue2", projectInfoModel.getKeyWordOfLanagurue2());
		sendMap.put("keyWordOfLanagurue3", projectInfoModel.getKeyWordOfLanagurue3());
		sendMap.put("projectInfoDetail", projectInfoModel.getProjectInfoDetail());
		sendMap.put("japaneaseConversationLevel", projectInfoModel.getJapaneaseConversationLevel());
		sendMap.put("unitPriceRangeLowest", projectInfoModel.getUnitPriceRangeLowest());
		sendMap.put("unitPriceRangeHighest", projectInfoModel.getUnitPriceRangeHighest());
		sendMap.put("ageClassificationCode", projectInfoModel.getAgeClassificationCode());
		sendMap.put("projectPhaseStart", projectInfoModel.getProjectPhaseStart());
		sendMap.put("projectPhaseEnd", projectInfoModel.getProjectPhaseEnd());
		sendMap.put("payOffRangeLowest", projectInfoModel.getPayOffRangeLowest());
		sendMap.put("payOffRangeHighest", projectInfoModel.getPayOffRangeHighest());
		sendMap.put("workStartPeriod", projectInfoModel.getWorkStartPeriod());
		sendMap.put("experienceYear", projectInfoModel.getExperienceYear());
		sendMap.put("noOfInterviewCode", projectInfoModel.getNoOfInterviewCode());
		sendMap.put("siteLocation", projectInfoModel.getSiteLocation());
		sendMap.put("requiredItem1", projectInfoModel.getRequiredItem1());
		sendMap.put("requiredItem2", projectInfoModel.getRequiredItem2());
		sendMap.put("salesStaff", projectInfoModel.getSalesStaff());
		sendMap.put("remark", projectInfoModel.getRemark());
		sendMap.put("recruitmentNumbers", projectInfoModel.getRecruitmentNumbers());
		sendMap.put("updateUser", projectInfoModel.getUpdateUser());
		return sendMap;
	}
}
