package jp.co.lyc.cms.mapper;

import java.util.HashMap;
import java.util.List;

import jp.co.lyc.cms.model.WorkRepotModel;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WorkRepotMapper {
	/**
	 * 画面情報検索
	 * @param TopCustomerNo
	 * @return
	 */
	public List<WorkRepotModel> selectWorkRepot(WorkRepotModel workRepotModel) ;
	/**
	 * アップデート
	 * @param sendMap
	 */
	public void updateWorkRepot(WorkRepotModel workRepotModel) ;	
}
