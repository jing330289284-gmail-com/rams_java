package jp.co.lyc.cms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.lyc.cms.mapper.SendLettersConfirmMapper;
import jp.co.lyc.cms.model.SendLettersConfirmModel;


@Component
public class SendLettersConfirmService {

	@Autowired
	SendLettersConfirmMapper sendLettersConfirmMapper;
	
	public List<SendLettersConfirmModel> getSalesEmps(String[] empNos){
		return sendLettersConfirmMapper.getSalesEmps(empNos);
	};
	
}
