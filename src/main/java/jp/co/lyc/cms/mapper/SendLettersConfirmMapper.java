package jp.co.lyc.cms.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import jp.co.lyc.cms.model.SendLettersConfirmModel;

@Mapper
public interface SendLettersConfirmMapper {

	/**
	 * ログイン
	 * @param sendMap
	 * @return
	 */
	
	public List<SendLettersConfirmModel> getSalesEmps(String[] empNos);
	
	
	
}
