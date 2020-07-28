package jp.co.lyc.cms.mapper;

import java.util.ArrayList;
import java.util.HashMap;
import jp.co.lyc.cms.model.CustomerInfoModel;
import jp.co.lyc.cms.model.TopCustomerInfoModel;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CustomerInfoMapper {

	public ArrayList<HashMap<String, String>> selectCustomerRanking();
	public ArrayList<HashMap<String, String>> selectCompanyNature();
	public ArrayList<TopCustomerInfoModel> selectTopCustomer(String topCustpmerName);
	public String checkTopCustomer(String topCustpmerName);
	public CustomerInfoModel selectCustomerInfo(String customerNo);
	public String customerNoSaiBan();
	public void insertCustomerInfo(HashMap<String, String> sendMap);
	public void updateCustomerInfo(HashMap<String, String> sendMap);
}
