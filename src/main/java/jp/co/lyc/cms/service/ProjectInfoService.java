package jp.co.lyc.cms.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.lyc.cms.mapper.ProjectInfoMapper;
import jp.co.lyc.cms.model.ProjectInfoModel;
import jp.co.lyc.cms.util.UtilsCheckMethod;

@Component
public class ProjectInfoService {

	@Autowired
	ProjectInfoMapper projectInfoMapper;
	
	/**
	 * 責任者Dropの取得
	 * @return
	 */
	public ArrayList<ProjectInfoModel> getPersonInCharge(String customerNo) {
		ArrayList<ProjectInfoModel> resultList = new ArrayList<ProjectInfoModel>();
		ArrayList<ProjectInfoModel> infoList = projectInfoMapper.getPersonInCharge(customerNo);
		int i = 0;
		for(ProjectInfoModel p:infoList) {
			if(p != null && !(UtilsCheckMethod.isNullOrEmpty(p.getName()) && 
					UtilsCheckMethod.isNullOrEmpty(p.getMail()))) {
				p.setCode(Integer.toString(i));
				resultList.add(p);
				i++;
			}
		}
		return resultList;
	}
}
