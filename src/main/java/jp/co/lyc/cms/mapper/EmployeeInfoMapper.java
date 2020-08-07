package jp.co.lyc.cms.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import jp.co.lyc.cms.model.EmployeeModel;

@Mapper
public interface EmployeeInfoMapper {

	/**
	 * 社員情報を取得
	 * 
	 * @param sendMap
	 * @return
	 */
	public List<EmployeeModel> getEmployeeInfo(Map<String, String> sendMap);

	/**
	 * 社員情報を追加
	 * 
	 * @param sendMap
	 */

	public void addEmployeeInfo(Map<String, String> sendMap);

	/**
	 * 社員情報詳細を追加
	 * 
	 */

	public void addEmployeeInfoDetail(Map<String, String> sendMap);

	/**
	 * 社員情報を削除
	 * 
	 * @param emp
	 */
	public void deleteEmployeeInfo(Map<String, String> sendMap);

	/**
	 * 社員情報詳細を削除
	 * 
	 * @param emp
	 */
	public void deleteEmployeeInfoDetail(Map<String, String> sendMap);

	/**
	 * 現場情報を削除
	 * 
	 * @param emp
	 */
	public void deleteEmployeeSiteInfo(Map<String, String> sendMap);

	/**
	 * 住所情報を削除
	 * 
	 * @param emp
	 */
	public void deleteAddressInfo(Map<String, String> sendMap);

	/**
	 * 諸費用を削除
	 * 
	 * @param emp
	 */
	public void deleteExpensesInfo(Map<String, String> sendMap);

}
