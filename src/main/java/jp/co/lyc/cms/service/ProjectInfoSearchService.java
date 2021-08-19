package jp.co.lyc.cms.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import jp.co.lyc.cms.mapper.ProjectInfoSearchMapper;
import jp.co.lyc.cms.model.ProjectInfoModel;
import jp.co.lyc.cms.util.UtilsCheckMethod;

@Component
public class ProjectInfoSearchService {

	@Autowired
	ProjectInfoSearchMapper projectInfoSearchMapper;
	@Autowired
	ProjectInfoService projectInfoService;

	/**
	 * 案件情報取得
	 * 
	 * @param projectInfoModel
	 * @return
	 */
	public ArrayList<ProjectInfoModel> getProjectInfo(ProjectInfoModel projectInfoModel) {
		ArrayList<ProjectInfoModel> resultList = new ArrayList<ProjectInfoModel>();
		if (projectInfoModel.getTheSelectProjectperiodStatus().equals("1")) {
			// 選択期間は未来日の場合
			Calendar cal = Calendar.getInstance();
			String yearAndMonth = Integer.toString(cal.get(Calendar.YEAR));
			int month = cal.get(Calendar.MONTH) + 1;
			yearAndMonth += month > 10 ? month : "0" + month;
			projectInfoModel.setYearAndMonth(yearAndMonth);
			resultList = projectInfoSearchMapper.searchProjectInfo(getSearchMap(projectInfoModel));
		} else {
			resultList = projectInfoSearchMapper.searchProjectInfo(getSearchMap(projectInfoModel));
		}
		resultList = resetRowNo(resultList);
		return resultList;
	}

	/**
	 * rowNo のリセット
	 * 
	 * @param projectInfoModel
	 * @return
	 */
	public ArrayList<ProjectInfoModel> resetRowNo(ArrayList<ProjectInfoModel> projectInfoModelList) {
		int rowNo = 1;
		for (ProjectInfoModel projectInfoModel : projectInfoModelList) {
			projectInfoModel.setRowNo(Integer.toString(rowNo));
			rowNo++;
		}
		return projectInfoModelList;
	}

	/**
	 * 案件情報の削除
	 * 
	 * @param projectInfoModel
	 */
	@Transactional(rollbackFor = Exception.class)
	public boolean delete(ProjectInfoModel projectInfoModel) {
		try {
			projectInfoSearchMapper.delete(projectInfoModel.getProjectNo());
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 案件情報の終了フラグ修正
	 * 
	 * @param projectInfoModel
	 */
	@Transactional(rollbackFor = Exception.class)
	public boolean endFlagChange(ProjectInfoModel projectInfoModel) {
		try {
			projectInfoSearchMapper.endFlagChange(projectInfoModel.getProjectNo());
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 検索のマップを作成
	 * 
	 * @param projectInfoModel
	 * @return
	 */
	public HashMap<String, String> getSearchMap(ProjectInfoModel projectInfoModel) {
		HashMap<String, String> sendMap = new HashMap<String, String>();
		if (!UtilsCheckMethod.isNullOrEmpty(projectInfoModel.getProjectNo())) {
			sendMap.put("projectNo", projectInfoModel.getProjectNo());
		}
		if (!UtilsCheckMethod.isNullOrEmpty(projectInfoModel.getYearAndMonth())) {
			sendMap.put("yearAndMonth", projectInfoModel.getYearAndMonth());
		}
		if (!UtilsCheckMethod.isNullOrEmpty(projectInfoModel.getKeyWordOfLanagurue1())) {
			sendMap.put("keyWordOfLanagurue1", projectInfoModel.getKeyWordOfLanagurue1());
		}
		if (!UtilsCheckMethod.isNullOrEmpty(projectInfoModel.getKeyWordOfLanagurue2())) {
			sendMap.put("keyWordOfLanagurue2", projectInfoModel.getKeyWordOfLanagurue2());
		}
		if (!UtilsCheckMethod.isNullOrEmpty(projectInfoModel.getKeyWordOfLanagurue3())) {
			sendMap.put("keyWordOfLanagurue3", projectInfoModel.getKeyWordOfLanagurue3());
		}
		if (!UtilsCheckMethod.isNullOrEmpty(projectInfoModel.getProjectType())) {
			sendMap.put("projectType", projectInfoModel.getProjectType());
		}
		if (!UtilsCheckMethod.isNullOrEmpty(projectInfoModel.getAdmissionPeriod())) {
			sendMap.put("admissionPeriod", projectInfoModel.getAdmissionPeriod());
		}
		if (!UtilsCheckMethod.isNullOrEmpty(projectInfoModel.getUnitPriceRangeLowest())) {
			sendMap.put("unitPriceRangeLowest", projectInfoModel.getUnitPriceRangeLowest());
		}
		if (!UtilsCheckMethod.isNullOrEmpty(projectInfoModel.getUnitPriceRangeHighest())) {
			sendMap.put("unitPriceRangeHighest", projectInfoModel.getUnitPriceRangeHighest());
		}
		if (!UtilsCheckMethod.isNullOrEmpty(projectInfoModel.getNationalityCode())) {
			sendMap.put("nationalityCode", projectInfoModel.getNationalityCode());
		}
		if (!UtilsCheckMethod.isNullOrEmpty(projectInfoModel.getJapaneaseConversationLevel())) {
			sendMap.put("japaneaseConversationLevel", projectInfoModel.getJapaneaseConversationLevel());
		}
		if (!UtilsCheckMethod.isNullOrEmpty(projectInfoModel.getExperienceYear())) {
			sendMap.put("experienceYear", projectInfoModel.getExperienceYear());
		}
		if (!UtilsCheckMethod.isNullOrEmpty(projectInfoModel.getProjectPhaseStart())) {
			sendMap.put("projectPhaseStart", projectInfoModel.getProjectPhaseStart());
		}
		if (!UtilsCheckMethod.isNullOrEmpty(projectInfoModel.getNoOfInterviewCode())) {
			sendMap.put("noOfInterviewCode", projectInfoModel.getNoOfInterviewCode());
		}
		if (!UtilsCheckMethod.isNullOrEmpty(projectInfoModel.getSuccessRate())) {
			sendMap.put("successRate", projectInfoModel.getSuccessRate());
		}
		return sendMap;
	}
}
