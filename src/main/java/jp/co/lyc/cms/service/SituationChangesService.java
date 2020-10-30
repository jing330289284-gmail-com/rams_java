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
	
	public List<SituationChangesModel>searchSituationIntoORretirement(Map<String, Object> sendMap){
		List<SituationChangesModel>SituationChangeList =SituationChangesMapper.getSituationIntoORretirement(sendMap);
		return SituationChangeList;
	}
	
	public List<SituationChangesModel>searchSituationChanges(Map<String, Object> sendMap){
		List<SituationChangesModel>SituationChangeList =SituationChangesMapper.getSituationChanges(sendMap);
		return SituationChangeList;
	}
	
	public List<SituationChangesModel>searchSituationChangesFront(Map<String, Object> sendMap){
		List<SituationChangesModel>SituationChangeList =SituationChangesMapper.getSituationChangesFront(sendMap);
		return SituationChangeList;
	}
}
