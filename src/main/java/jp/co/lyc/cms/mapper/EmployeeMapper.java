package jp.co.lyc.cms.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import jp.co.lyc.cms.model.EmployeeModel;

@Mapper
public interface EmployeeMapper {

//	public EmployeeModel selectByNo(String no);
	
//	public void addEmployee(EmployeeModel emp);
	
	public EmployeeModel getEmployeeModel(Map<String, String> sendMap);
}
