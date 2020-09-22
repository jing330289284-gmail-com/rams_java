package jp.co.lyc.cms.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import jp.co.lyc.cms.model.SalesSendLetterModel;

@Mapper
public interface SalesSendLetterMapper {

	/**
	 * ログイン
	 * @param sendMap
	 * @return
	 */
	
	public List<SalesSendLetterModel> getSalesCustomers();
	public List<SalesSendLetterModel> getSalesPersons(String customerNo);
	
	
	
}
