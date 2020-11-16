package jp.co.lyc.cms.mapper;

import java.util.ArrayList;

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
}
