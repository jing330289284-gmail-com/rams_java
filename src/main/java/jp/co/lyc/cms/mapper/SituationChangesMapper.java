package jp.co.lyc.cms.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import jp.co.lyc.cms.model.SituationChangesModel;

@Mapper
public interface SituationChangesMapper {

public List<SituationChangesModel>getSituationChanges(Map<String, Object> sendMap);

public List<SituationChangesModel>getSituationChangesFront(Map<String, Object> sendMap);

public List<SituationChangesModel>getSituationIntoORretirement(Map<String, Object> sendMap);

public List<SituationChangesModel>getSituationBonus(Map<String, Object> sendMap);

}
