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

	public void insertEmployeeInfo(Map<String, String> sendMap);

	/**
	 * 社員情報詳細を追加
	 * 
	 */

	public void insertEmployeeInfoDetail(Map<String, String> sendMap);

	/**
	 * 社員情報を削除
	 * 
	 */
	public void deleteEmployeeInfo(Map<String, String> sendMap);

	/**
	 * 社員情報詳細を削除
	 * 
	 */
	public void deleteEmployeeInfoDetail(Map<String, String> sendMap);

	/**
	 * 現場情報を削除
	 * 
	 */
	public void deleteEmployeeSiteInfo(Map<String, String> sendMap);

	/**
	 * 住所情報を削除
	 * 
	 */
	public void deleteAddressInfo(Map<String, String> sendMap);

	/**
	 * 諸費用を削除
	 * 
	 */
	public void deleteExpensesInfo(Map<String, String> sendMap);

	/**
	 * EmployeeNoによると、社員情報を取得
	 */
	public EmployeeModel getEmployeeByEmployeeNo(Map<String, String> sendMap);

	/**
	 * 社員情報を修正
	 * 
	 * @param emp
	 */
	public void updateEmployeeInfo(Map<String, String> sendMap);

	/**
	 * 社員情報詳細を修正
	 * 
	 * @param emp
	 */
	public void updateEmployeeInfoDetail(Map<String, String> sendMap);

}
