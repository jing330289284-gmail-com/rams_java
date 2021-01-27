package jp.co.lyc.cms.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;

import jp.co.lyc.cms.model.SalesContent;
import jp.co.lyc.cms.model.SalesSituationModel;

@Mapper
public interface SalesSituationMapper {

	/**
	 * ログイン
	 * @param sendMap
	 * @return
	 */
 	
	public List<SalesSituationModel> getSalesSituationModel(String sysDate, String curDate, String salesDate);
	
	public int insertSalesSituation(SalesSituationModel model);

	public int updateEmployeeSiteInfo(SalesSituationModel model);
	
	public int updateSalesSituation(SalesSituationModel model);
	
	public List<SalesSituationModel> getPersonalSalesInfo(String empNo);
	
	public List<SalesSituationModel> getPersonalSalesInfoFromT019(String empNo);
	
	public int updateEmployeeAddressInfo(SalesSituationModel model);
	
	public int updateSalesSentence(SalesContent model);
	
	public int getCount(String empNo);
	
	public int updateDataStatus(SalesSituationModel model);
	
	public int updateEMPInfo(SalesSituationModel model);
	
	public int updateBPEMPInfo(SalesSituationModel model);
}
