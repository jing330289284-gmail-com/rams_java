package jp.co.lyc.cms.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.lyc.cms.mapper.PersonalSalesSearchMapper;
import jp.co.lyc.cms.model.PersonalSalesSearchModel;

@Component
public class PersonalSalesSearchService {
	
	@Autowired
	PersonalSalesSearchMapper personalSalesSearchMapper;
	
	public List<PersonalSalesSearchModel> searchEmpDetails(Map<String, Object> sendMap) {
		List<PersonalSalesSearchModel> personalList = personalSalesSearchMapper.getEmployeeSalesInfo(sendMap);
		return personalList;
	}
	
	public List<PersonalSalesSearchModel> searchEmpAllowance(Map<String, Object> sendMap) {
		List<PersonalSalesSearchModel> personalList = personalSalesSearchMapper.getEmpAllowance(sendMap);
		return personalList;
	}
}
