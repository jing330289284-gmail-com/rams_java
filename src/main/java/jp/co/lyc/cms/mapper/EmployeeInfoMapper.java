package jp.co.lyc.cms.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import jp.co.lyc.cms.model.EmployeeModel;

@Mapper
public interface EmployeeInfoMapper {

	/**
	 * ログイン
	 * 
	 * @param sendMap
	 * @return
	 */

	public EmployeeModel getEmployeeModel(Map<String, String> sendMap);

	/**
	 * 社員情報を取得
	 * 
	 * @param sendMap
	 * @return
	 */
	public List<EmployeeModel> getEmployeeInfo(Map<String, Object> sendMap);

	public List<EmployeeModel> getEmployeesInfo(Map<String, Object> sendMap);

	public List<String> getEmployeeWithAdmission(Map<String, Object> sendMap);

	public List<EmployeeModel> getEmployeesDevelopLanguage();

	public List<EmployeeModel> getcustomerNo();
	
	public List<EmployeeModel> getAdmissionStartDate();
	/**
	 * 社員情報を確認
	 * 
	 * @param sendMap
	 * @return
	 */
	public List<String> verificationEmployeeInfo();

	/**
	 * 社員情報を追加
	 * 
	 * @param sendMap
	 */

	public void insertEmployeeInfo(Map<String, Object> sendMap);

	/**
	 * 社員情報詳細を追加
	 * 
	 */

	public void insertEmployeeInfoDetail(Map<String, Object> sendMap);

	/**
	 * 社員情報を削除
	 * 
	 */
	public void deleteEmployeeInfo(Map<String, Object> sendMap);

	/**
	 * 社員情報詳細を削除
	 * 
	 */
	public void deleteEmployeeInfoDetail(Map<String, Object> sendMap);

	/**
	 * 住所情報を削除
	 * 
	 */
	public void deleteAddressInfo(Map<String, Object> sendMap);

	/**
	 * EmployeeNoによると、社員情報を取得
	 */
	public EmployeeModel getEmployeeByEmployeeNo(Map<String, Object> sendMap);

	/**
	 * 社員情報を修正
	 * 
	 * @param emp
	 */
	public void updateEmployeeInfo(Map<String, Object> sendMap);

	/**
	 * 社員情報詳細を修正
	 * 
	 * @param emp
	 */
	public void updateEmployeeInfoDetail(Map<String, Object> sendMap);

	/**
	 * アドレスを追加
	 * 
	 * @param emp
	 */
	public void insertAddressInfo(Map<String, Object> sendMap);

	/**
	 * アドレスを修正
	 * 
	 * @param emp
	 */
	public void updateAddressInfo(Map<String, Object> sendMap);

	/**
	 * ログイン認証番号の電話番号存在チェック
	 * 
	 * @param employeeNo
	 * @return
	 */
	public String getEmployeePhoneNo(String employeeNo);

	/**
	 * 履歴書を追加
	 * 
	 * @param sendMap
	 */
	public void insertResumeManagement(HashMap<String, Object> sendMap);

	/**
	 * 履歴書を修正
	 * 
	 * @param sendMap
	 */
	public void updateResumeManagement(Map<String, Object> sendMap);

	/**
	 * 履歴書を削除
	 * 
	 * @param sendMap
	 */
	public void deleteResumeManagement(Map<String, Object> sendMap);
}