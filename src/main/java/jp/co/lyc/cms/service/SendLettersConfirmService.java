package jp.co.lyc.cms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.lyc.cms.mapper.SendLettersConfirmMapper;
import jp.co.lyc.cms.model.EmployeeModel;
import jp.co.lyc.cms.model.SalesSituationModel;
import jp.co.lyc.cms.model.SendLettersConfirmModel;


@Component
public class SendLettersConfirmService {

	@Autowired
	SendLettersConfirmMapper sendLettersConfirmMapper;
	
	public List<SendLettersConfirmModel> getSalesEmps(String[] empNos){
		return sendLettersConfirmMapper.getSalesEmps(empNos);
	};
	
	public List<SalesSituationModel> getAllEmpsWithResume(){
		return sendLettersConfirmMapper.getAllEmpsWithResume();
	};
	
	public List<EmployeeModel> getLoginUserInfo(String lobinUserNo){
		return sendLettersConfirmMapper.getLoginUserInfo(lobinUserNo);
	};
	
	public List<EmployeeModel> getMail(){
		return sendLettersConfirmMapper.getMail();
	};
}
