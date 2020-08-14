package jp.co.lyc.cms.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import jp.co.lyc.cms.model.SalesSituationModel;

@Mapper
public interface SalesSituationMapper {

	/**
	 * ログイン
	 * @param sendMap
	 * @return
	 */
	
	public List<SalesSituationModel> getSalesSituationModel(String sysDate);
}
