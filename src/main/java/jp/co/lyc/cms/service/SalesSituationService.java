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


}
