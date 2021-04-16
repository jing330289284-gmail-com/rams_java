package jp.co.lyc.cms.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import jp.co.lyc.cms.mapper.SituationChangesMapper;
import jp.co.lyc.cms.model.SituationChangesModel;

@Component
public class SituationChangesService {

	@Autowired
	SituationChangesMapper SituationChangesMapper;

	public List<SituationChangesModel> searchSituationIntoORretirement(Map<String, Object> sendMap) {
		List<SituationChangesModel> SituationChangeList = SituationChangesMapper.getSituationIntoORretirement(sendMap);
		return SituationChangeList;
	}

	public List<SituationChangesModel> searchSituationChanges(Map<String, Object> sendMap) {
		List<SituationChangesModel> SituationChangeList = SituationChangesMapper.getSituationChanges(sendMap);
		return SituationChangeList;
	}

	public List<SituationChangesModel> searchSituationChangesFront(Map<String, Object> sendMap) {
		List<SituationChangesModel> SituationChangeList = SituationChangesMapper.getSituationChangesFront(sendMap);
		return SituationChangeList;
	}

	public List<SituationChangesModel> searchSitautionChangesBonus(Map<String, Object> sendMap) {
		List<SituationChangesModel> SituationChangeList = SituationChangesMapper.getSituationBonus(sendMap);
		return SituationChangeList;
	}
	
	public List<SituationChangesModel> getT005WagesInfoList(Map<String, Object> sendMap) {
		List<SituationChangesModel> T005WagesInfoList = SituationChangesMapper.getT005WagesInfoList(sendMap);
		return T005WagesInfoList;
	}
	public List<SituationChangesModel> searchIntoRetirement(Map<String, Object> sendMap) {
		List<SituationChangesModel> intoRetirementList = SituationChangesMapper.searchIntoRetirement(sendMap);
		return intoRetirementList;
	}
	
	public List<SituationChangesModel> searchscheduleOfBonus(Map<String, Object> sendMap) {
		List<SituationChangesModel> scheduleOfBonusAmountList = SituationChangesMapper.searchscheduleOfBonus(sendMap);
		return scheduleOfBonusAmountList;
	}
	
	public List<SituationChangesModel> getReflectYearAndMonth(Map<String, Object> sendMap) {
		List<SituationChangesModel> reflectYearAndMonthList = SituationChangesMapper.getReflectYearAndMonth(sendMap);
		return reflectYearAndMonthList;
	}
}
