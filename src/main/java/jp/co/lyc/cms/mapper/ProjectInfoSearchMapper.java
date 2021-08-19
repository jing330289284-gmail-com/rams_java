package jp.co.lyc.cms.mapper;

import java.util.ArrayList;
import java.util.HashMap;
import org.apache.ibatis.annotations.Mapper;
import jp.co.lyc.cms.model.ProjectInfoModel;

@Mapper
public interface ProjectInfoSearchMapper {

	/**
	 *  案件情報取得
	 * @param sendMap
	 * @return
	 */
	public ArrayList<ProjectInfoModel> searchProjectInfo(HashMap<String, String> sendMap);
	
	/**
	 * 案件情報の削除
	 * @param projectNo
	 */
	public void delete(String projectNo);
	
	/**
	 * 案件情報の終了フラグ修正
	 * @param projectNo
	 */
	public void endFlagChange(String projectNo);
}
