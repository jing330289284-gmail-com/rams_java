package jp.co.lyc.cms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import jp.co.lyc.cms.model.WorkRepotModel;

@Mapper
public interface WorkRepotMapper {
	/**
	 * 画面情報検索
	 * @param TopCustomerNo
	 * @return
	 */
	public void selectCheckWorkRepot(WorkRepotModel workRepotModel) ;
	public List<WorkRepotModel> selectWorkRepot(WorkRepotModel workRepotModel) ;
	/**
	 * アップデート
	 * @param sendMap
	 */
	public void updateWorkRepot(WorkRepotModel workRepotModel) ;	
	/**
	 * ファイル名入力
	 * @param sendMap
	 */
	public void updateWorkRepotFile(WorkRepotModel workRepotModel) ;	
}
