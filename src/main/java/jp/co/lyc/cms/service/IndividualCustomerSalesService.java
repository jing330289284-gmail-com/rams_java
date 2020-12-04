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

}
