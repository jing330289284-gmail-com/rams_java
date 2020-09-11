package jp.co.lyc.cms.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import jp.co.lyc.cms.model.PersonalSalesSearchModel;

@Mapper
public interface PersonalSalesSearchMapper{

	
	public List<PersonalSalesSearchModel> getEmployeeSalesInfo(Map<String, Object> sendMap);

}
