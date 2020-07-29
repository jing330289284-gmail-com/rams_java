package jp.co.lyc.cms.mapper;

import java.util.ArrayList;
import java.util.HashMap;
import jp.co.lyc.cms.model.CustomerInfoModel;
import jp.co.lyc.cms.model.TopCustomerInfoModel;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CustomerInfoMapper {

	//画面下拉框
	public ArrayList<HashMap<String, String>> selectCustomerRanking();
	public ArrayList<HashMap<String, String>> selectCompanyNature();
	public ArrayList<TopCustomerInfoModel> selectTopCustomer(String topCustpmerName);
	//上位客户是否存在
	public String checkTopCustomer(String topCustpmerName);
	//查询客户信息
	public CustomerInfoModel selectCustomerInfo(String customerNo);
	//客户番号采番
	public String customerNoSaiBan();
	//客户信息插入
	public void insertCustomerInfo(HashMap<String, String> sendMap);
	//客户信息更新
	public void updateCustomerInfo(HashMap<String, String> sendMap);
	//插入部门信息
	public void insertCustomerDepartment(HashMap<String, String> sendMap);
	//更新部门信息
	public void updateCustomerDepartment(HashMap<String, String> sendMap);
}
