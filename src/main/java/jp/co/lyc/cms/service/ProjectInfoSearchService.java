package jp.co.lyc.cms.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import jp.co.lyc.cms.mapper.ProjectInfoSearchMapper;
import jp.co.lyc.cms.model.ProjectInfoModel;

@Component
public class ProjectInfoSearchService {

	@Autowired
	ProjectInfoSearchMapper projectInfoSearchMapper; 
	@Autowired
	ProjectInfoService projectInfoService;
	
	/**
	 * 案件情報取得
	 * @param projectInfoModel
	 * @return
	 */
	public ArrayList<ProjectInfoModel> getProjectInfo(ProjectInfoModel projectInfoModel) {
		HashMap<String, String> sendMap = projectInfoService.getSendMap(projectInfoModel);
		return projectInfoSearchMapper.searchProjectInfo(sendMap);
	}
}
