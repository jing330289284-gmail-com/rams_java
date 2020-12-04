package jp.co.lyc.cms.service;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.lyc.cms.mapper.SalesProfitMapper;
import jp.co.lyc.cms.model.SalesInfoModel;
import jp.co.lyc.cms.model.SalesProfitModel;

@Component
public class SalesProfitService {

	@Autowired
	SalesProfitMapper salesProfitMapper;

	public List<SalesProfitModel> getPointInfo(SalesProfitModel salesProfitModel) {

//		List<SalesPointSetModel> siteList = salesProfitMapper.getSalesProfitInfo(sendMap);
//		return siteList;
		// salesProfitModel.getEmployeeName(), salesProfitModel.getEmployeeStatus(),
		// salesProfitModel.getStartDate(), salesProfitModel.getEndDate()

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMM");
		// String startTime =
		// dateFormat.format(salesProfitModel.getStartDate()).toString();
		// String endTime = dateFormat.format(salesProfitModel.getEndDate()).toString();
		String startTime = null;
		String endTime = null;
		return salesProfitMapper.getPointInfo(salesProfitModel.getEmployeeName(), startTime, endTime);
	}

	public List<SalesInfoModel> getSalesInfo(SalesProfitModel salesProfitModel) {

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMM");
		String startTime = dateFormat.format(salesProfitModel.getStartDate()).toString() + "00";
		String endTime = dateFormat.format(salesProfitModel.getEndDate()).toString() + "99";

		return salesProfitMapper.getSalesInfo(salesProfitModel.getEmployeeName(), salesProfitModel.getEmployeeStatus(),
				startTime, endTime);
	}

	public List<SalesInfoModel> getCustomerName() {

		return salesProfitMapper.getCustomerName();
	}

	public List<SalesInfoModel> getEmployeeNoSalary() {

		return salesProfitMapper.getEmployeeNoSalary();
	}

	public List<SalesInfoModel> getEmployeeName() {

		return salesProfitMapper.getEmployeeName();
	}
}
