package jp.co.lyc.cms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.lyc.cms.mapper.SalesSituationMapper;
import jp.co.lyc.cms.model.SalesSituationModel;

@Component
public class SalesSituationService {

	@Autowired
	SalesSituationMapper salesSituationMapper;
	
	public List<SalesSituationModel> getSalesSituationModel(String sysDate){
		return salesSituationMapper.getSalesSituationModel(sysDate);
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

}
