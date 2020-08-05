package jp.co.lyc.cms.mapper;

import java.util.ArrayList;
import java.util.HashMap;
import jp.co.lyc.cms.model.CustomerInfoModel;
import jp.co.lyc.cms.model.TopCustomerInfoModel;
import org.apache.ibatis.annotations.Mapper;
import jp.co.lyc.cms.model.CustomerDepartmentInfoModel;

@Mapper
public interface CustomerInfoMapper {

	/**
	 * 上位お客様連想
	 * @return
	 */
	public ArrayList<TopCustomerInfoModel> selectTopCustomer(String topCustpmerName);
	/**
	 * 上位お客様存在チェック
	 * @return
	 */
	public String checkTopCustomer(String topCustpmerName);
	/**
	 * お客様情報検索
	 * @return
	 */
	public CustomerInfoModel selectCustomerInfo(String customerNo);
	/**
	 * 採番
	 * @return
	 */
	public String customerNoSaiBan();
	/**
	 * インサート
	 * @return
	 */
	public void insertCustomerInfo(HashMap<String, String> sendMap);
	/**
	 * 部門連想
	 * @return
	 */
	public ArrayList<CustomerDepartmentInfoModel> selectDepartmentMaster(String customerDepartmentName);
	/**
	 * 部門番号の検索
	 * @param customerDepartmentName
	 * @return
	 */
	public String selectDepartmentCode(String customerDepartmentName);
	/**
	 * 部門情報検索
	 * @param customerNo
	 * @return
	 */
	public ArrayList<CustomerDepartmentInfoModel> selectCustomerDepartmentInfo(HashMap<String, String> sendMapd);
	/**
	 * アップデート
	 * @return
	 */
	public void updateCustomerInfo(HashMap<String, String> sendMap);
	/**
	 * 部門インサート
	 * @return
	 */
	public void insertCustomerDepartment(HashMap<String, String> sendMap);
	
	/**
	 * 部門アップデート
	 * @return
	 */
	public void updateCustomerDepartment(HashMap<String, String> sendMap);
	
	/**
	 * 部門削除
	 * @return
	 */
	public void customerDepartmentdelect(HashMap<String, String> sendMap);
}
