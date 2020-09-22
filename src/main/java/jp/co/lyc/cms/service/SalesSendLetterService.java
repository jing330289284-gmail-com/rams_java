package jp.co.lyc.cms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.lyc.cms.mapper.SalesSendLetterMapper;
import jp.co.lyc.cms.model.SalesSendLetterModel;


@Component
public class SalesSendLetterService {

	@Autowired
	SalesSendLetterMapper salesSendLetterMapper;
	
	public List<SalesSendLetterModel> getSalesCustomers(){
		return salesSendLetterMapper.getSalesCustomers();
	};
	
	public List<SalesSendLetterModel> getSalesPersons(String customerNo){
		return salesSendLetterMapper.getSalesPersons(customerNo);
	};
}
