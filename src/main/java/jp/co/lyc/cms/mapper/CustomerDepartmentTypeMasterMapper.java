package jp.co.lyc.cms.mapper;

import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

import jp.co.lyc.cms.model.CustomerDepartmentTypeMasterModel;

@Mapper
public interface CustomerDepartmentTypeMasterMapper {
	
	/**
	 * 有無判断
	 * @param CustomerDepartmenttypeName
	 * @return
	 */
	public CustomerDepartmentTypeMasterModel checkHave(String CustomerDepartmenttypeName);
	/**
	 * インサート
	 * @param sendMap
	 */
	public void insertCustomerDepartmentTypeMaster(HashMap<String, String> sendMap);
}
