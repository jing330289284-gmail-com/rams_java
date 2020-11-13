package jp.co.lyc.cms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import jp.co.lyc.cms.model.SalesInfoModel;
import jp.co.lyc.cms.model.SalesProfitModel;

@Mapper
public interface SalesProfitMapper {

	/**
	 * 営業個別売上情報取得
	 *
	 */
	public List<SalesProfitModel> getSalesProfitInfo(String eigyou, String intoCompanyCode, String startDate,
			String endDate);

	public List<SalesInfoModel> getSalesInfo(String eigyou, String intoCompanyCode, String startDate, String endDate);
}
