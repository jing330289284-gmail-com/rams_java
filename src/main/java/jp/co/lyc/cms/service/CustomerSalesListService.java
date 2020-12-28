package jp.co.lyc.cms.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.lyc.cms.mapper.CustomerSalesListMapper;
import jp.co.lyc.cms.model.CustomerSalesListModel;


@Component
public class CustomerSalesListService {
	@Autowired
	CustomerSalesListMapper customerSalesListMapper;

	public List<CustomerSalesListModel>searchCustomerSalesList(Map<String, Object> sendMap){
		List<CustomerSalesListModel> customerSalesList = customerSalesListMapper.getCustomerSalesList(sendMap);
		return customerSalesList;	
	}
	
	public List<CustomerSalesListModel>searchCustomerSalesListTwice(Map<String, Object> sendMap){
		List<CustomerSalesListModel> customerSalesListTwice = customerSalesListMapper.getCustomerSalesListTwice(sendMap);
		return customerSalesListTwice;	
	}
	
	public List<CustomerSalesListModel>searchCustomerSalesListThird(Map<String, Object> sendMap){
		List<CustomerSalesListModel> customerSalesListThird = customerSalesListMapper.getCustomerSalesListThird(sendMap);
		return customerSalesListThird;	
	}
}
