package jp.co.lyc.cms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import jp.co.lyc.cms.model.SalesEmployeeModel;
import jp.co.lyc.cms.model.SalesInfoModel;
import jp.co.lyc.cms.model.SalesPointModel;

@Mapper
public interface SalesProfitMapper {

	/**
	 * 営業個別売上情報取得
	 *
	 */
	public List<SalesInfoModel> getPointInfo(String eigyou, String startDate, String endDate);

	public List<SalesInfoModel> getSalesInfo(String eigyou, String intoCompanyCode, String startDate, String endDate);

	public List<SalesEmployeeModel> getCustomerName();

	public List<SalesInfoModel> getEmployeeNoSalary();

	public List<SalesInfoModel> getEmployeeName();

	public List<SalesEmployeeModel> getEmployeeSiteInfo();

	public List<SalesPointModel> getSalesPointInfo();

	public List<SalesInfoModel> getSalesInfoByemployeeNoList(List<String> employeeNoList);
}
