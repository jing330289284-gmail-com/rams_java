package jp.co.lyc.cms.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.lyc.cms.mapper.IndividualCustomerSalesMapper;
import jp.co.lyc.cms.model.IndividualCustomerSalesModel;

@Component
public class IndividualCustomerSalesService {
	
@Autowired
IndividualCustomerSalesMapper individualCustomerSalesMapper;

public List<IndividualCustomerSalesModel>searchCustomerSales(Map<String, Object> sendMap){
	List<IndividualCustomerSalesModel> customerSalesList = individualCustomerSalesMapper.getCustomerSalesInfo(sendMap);
	return customerSalesList;
	
}


public List<IndividualCustomerSalesModel>searchCustomerSalestwice(Map<String, Object> sendMap){
	List<IndividualCustomerSalesModel> searchCustomerSalestwice = individualCustomerSalesMapper.getCustomerSalesInfoTwice(sendMap);
	return searchCustomerSalestwice;
	
}

public List<IndividualCustomerSalesModel>searchCustomerSalesthird(Map<String, Object> sendMap){
	List<IndividualCustomerSalesModel> searchCustomerSalesthird = individualCustomerSalesMapper.getCustomerSalesInfoThird(sendMap);
	return searchCustomerSalesthird;
	
}

}
