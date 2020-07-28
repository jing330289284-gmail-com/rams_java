package jp.co.lyc.cms.mapper;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import jp.co.lyc.cms.model.CostModel;

@Mapper
public interface GetCostMapper {

	
	public CostModel selectCost(Map<String, String> sendMap);
	public void insertCost(Map<String, Object> sendMap);
	public void updateCost(Map<String, Object> sendMap);
	public HashMap<String, String> checkKado(String employeeNo);
}
