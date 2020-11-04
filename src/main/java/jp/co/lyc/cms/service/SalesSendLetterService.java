package jp.co.lyc.cms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.lyc.cms.mapper.SalesSendLetterMapper;
import jp.co.lyc.cms.model.SalesSendLettersListName;
import jp.co.lyc.cms.model.SendLettersConfirmModel;
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
	
	public int creatList(SalesSendLetterModel model){
		return salesSendLetterMapper.creatList(model);
	};
	
	public List<SalesSendLetterModel> getLists(){
		return salesSendLetterMapper.getLists();
	};
	
	public int listNameUpdate(SalesSendLettersListName model){
		return salesSendLetterMapper.listNameUpdate(model);
	};
	
	public List<SalesSendLetterModel> getSalesCustomersByNos(String[] ctmNos){
		return salesSendLetterMapper.getSalesCustomersByNos(ctmNos);
	};
	
	public int deleteList(String storageListName){
		return salesSendLetterMapper.deleteList(storageListName);
	};
}
