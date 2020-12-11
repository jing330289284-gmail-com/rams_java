package jp.co.lyc.cms.service;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.lyc.cms.mapper.SalesProfitMapper;
import jp.co.lyc.cms.model.SalesEmployeeModel;
import jp.co.lyc.cms.model.SalesInfoModel;
import jp.co.lyc.cms.model.SalesPointModel;
import jp.co.lyc.cms.model.SalesProfitModel;

@Component
public class SalesProfitService {

	@Autowired
	SalesProfitMapper salesProfitMapper;

	public List<SalesInfoModel> getPointInfo(SalesProfitModel salesProfitModel) {

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMM");
		String startTime = null;
		String endTime = null;
		if (salesProfitModel.getStartDate() != null && salesProfitModel.getEndDate() != null) {
			startTime = dateFormat.format(salesProfitModel.getStartDate()).toString();
			endTime = dateFormat.format(salesProfitModel.getEndDate()).toString();
		}
		return salesProfitMapper.getPointInfo(salesProfitModel.getEmployeeName(), startTime, endTime);
	}

	public List<SalesInfoModel> getSalesInfo(SalesProfitModel salesProfitModel) {

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMM");
		String startTime = dateFormat.format(salesProfitModel.getStartDate()).toString() + "00";
		String endTime = dateFormat.format(salesProfitModel.getEndDate()).toString() + "99";

		return salesProfitMapper.getSalesInfo(salesProfitModel.getEmployeeName(), salesProfitModel.getEmployeeStatus(),
				startTime, endTime);
	}

	public List<SalesEmployeeModel> getCustomerName() {

		return salesProfitMapper.getCustomerName();
	}

	public List<SalesInfoModel> getEmployeeNoSalary() {

		return salesProfitMapper.getEmployeeNoSalary();
	}

	public List<SalesInfoModel> getEmployeeName() {

		return salesProfitMapper.getEmployeeName();
	}

	public List<SalesEmployeeModel> getEmployeeSiteInfo() {

		return salesProfitMapper.getEmployeeSiteInfo();
	}

	public List<SalesPointModel> getSalesPointInfo() {
		return salesProfitMapper.getSalesPointInfo();
	}
}
