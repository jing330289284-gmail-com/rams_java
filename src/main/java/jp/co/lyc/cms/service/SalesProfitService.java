package jp.co.lyc.cms.service;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.lyc.cms.mapper.SalesProfitMapper;
import jp.co.lyc.cms.model.SalesProfitModel;

@Component
public class SalesProfitService {

	@Autowired
	SalesProfitMapper salesProfitMapper;


	public List<SalesProfitModel> getSalesProfitInfo(SalesProfitModel salesProfitModel) {

//		List<SalesPointSetModel> siteList = salesProfitMapper.getSalesProfitInfo(sendMap);
//		return siteList;
		//salesProfitModel.getEmployeeName(), salesProfitModel.getEmployeeStatus(), salesProfitModel.getStartDate(), salesProfitModel.getEndDate()

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		return salesProfitMapper.getSalesProfitInfo(salesProfitModel.getEmployeeName()
													, salesProfitModel.getEmployeeStatus()
													, dateFormat.format(salesProfitModel.getStartDate())
													, dateFormat.format(salesProfitModel.getEndDate()));
	}


}
