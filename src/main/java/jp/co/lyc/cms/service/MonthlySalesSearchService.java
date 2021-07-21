package jp.co.lyc.cms.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.lyc.cms.mapper.MonthlySalesSearchMapper;
import jp.co.lyc.cms.model.MonthlySalesSearchModel;

@Component
public class MonthlySalesSearchService {

	@Autowired
	MonthlySalesSearchMapper MonthlySalesSearchMapper;

	public List<MonthlySalesSearchModel> searchMonthlySales(Map<String, Object> sendMap) {
		List<MonthlySalesSearchModel> monthlySalesList = MonthlySalesSearchMapper.getMonthlySalesInfo(sendMap);
		return monthlySalesList;
	}

	public List<MonthlySalesSearchModel> searchBpMonthlySales(Map<String, Object> sendMap) {
		List<MonthlySalesSearchModel> monthlySalesList = MonthlySalesSearchMapper.getBpMonthlySalesInfo(sendMap);
		return monthlySalesList;
	}
}
