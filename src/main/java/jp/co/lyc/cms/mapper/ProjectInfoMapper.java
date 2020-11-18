package jp.co.lyc.cms.mapper;

import java.util.ArrayList;
import java.util.HashMap;
import org.apache.ibatis.annotations.Mapper;
import jp.co.lyc.cms.model.ProjectInfoModel;

@Mapper
public interface ProjectInfoMapper {

	/**
	 * 採番用
	 * @return
	 */
	public ArrayList<String> saiban(String yearAndMonth);
	
	/**
	 * 責任者Drop 取得
	 * @param customerNo
	 * @return
	 */
	public ArrayList<ProjectInfoModel> getPersonInCharge(String customerNo);
	
	/**
	 * インサート
	 * @return
	 */
	public void insert(HashMap<String, String> sendMap);
	
	/**
	 * アップデート
	 * @return
	 */
	public void update(HashMap<String, String> sendMap);
	
	/**
	 * 案件情報の取得
	 * @return
	 */
	public ArrayList<ProjectInfoModel> getProjectInfo(HashMap<String, String> sendMap);
}
