package jp.co.lyc.cms.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import jp.co.lyc.cms.model.CustomerSalesListModel;
@Mapper
public interface CustomerSalesListMapper {
	
	public  List<CustomerSalesListModel> getCustomerSalesList(Map<String, Object> sendMap);
	public  List<CustomerSalesListModel> getCustomerSalesListTwice(Map<String, Object> sendMap);
	public  List<CustomerSalesListModel> getCustomerSalesListThird(Map<String, Object> sendMap);
}
