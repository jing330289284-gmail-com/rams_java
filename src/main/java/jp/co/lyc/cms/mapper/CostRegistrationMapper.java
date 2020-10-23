package jp.co.lyc.cms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import jp.co.lyc.cms.model.CostRegistrationModel;

@Mapper
public interface CostRegistrationMapper {
	/**
	 * 画面情報検索
	 * @param TopCustomerNo
	 * @return
	 */
	public List<CostRegistrationModel> selectCostRegistration(CostRegistrationModel costRegistrationModel) ;
	/**
	 * アップデート
	 * @param sendMap
	 */
	public void updateCostRegistration(CostRegistrationModel costRegistrationModel) ;
	/**
	 * インサート
	 * @param sendMap
	 */
	public void insertCostRegistration(CostRegistrationModel costRegistrationModel) ;
	public void deletetCostRegistration(CostRegistrationModel costRegistrationModel) ;
}
