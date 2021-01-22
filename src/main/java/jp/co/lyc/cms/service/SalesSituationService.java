package jp.co.lyc.cms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.lyc.cms.mapper.SalesSituationMapper;
import jp.co.lyc.cms.model.SalesContent;
import jp.co.lyc.cms.model.SalesSituationModel;

@Component
public class SalesSituationService {

	@Autowired
	SalesSituationMapper salesSituationMapper;
	
	public List<SalesSituationModel> getSalesSituationModel(String sysDate, String curDate, String salesDate){
		return salesSituationMapper.getSalesSituationModel(sysDate, curDate, salesDate);
	}
	
	public int insertSalesSituation(SalesSituationModel model){
		return salesSituationMapper.insertSalesSituation(model);
	}

	public int updateEmployeeSiteInfo(SalesSituationModel model) {
		return salesSituationMapper.updateEmployeeSiteInfo(model);
	}

	public int updateSalesSituation(SalesSituationModel model){
		return salesSituationMapper.updateSalesSituation(model);
	}
	
	public List<SalesSituationModel> getPersonalSalesInfo(String empNo){
		return salesSituationMapper.getPersonalSalesInfo(empNo);
	}
	
	public List<SalesSituationModel> getPersonalSalesInfoFromT019(String empNo){
		return salesSituationMapper.getPersonalSalesInfoFromT019(empNo);
	}
	
	public int updateEmployeeAddressInfo(SalesSituationModel model){
		return salesSituationMapper.updateEmployeeAddressInfo(model);
	}
	
	public int updateSalesSentence(SalesContent model){
		return salesSituationMapper.updateSalesSentence(model);
	}
	
	public int getCount(String empNo){
		return salesSituationMapper.getCount(empNo);
	}
	
	public int updateDataStatus(SalesSituationModel model){
		return salesSituationMapper.updateDataStatus(model);
	}
}
