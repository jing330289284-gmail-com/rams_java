package jp.co.lyc.cms.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import jp.co.lyc.cms.model.EmployeeModel;

@Mapper
public interface EmployeeMapper {

	/**
	 * ログイン
	 * @param sendMap
	 * @return
	 */
	
	public EmployeeModel getEmployeeModel(Map<String, String> sendMap);
}
