package jp.co.lyc.cms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.lyc.cms.mapper.ResumeMapper;
import jp.co.lyc.cms.model.ResumeModel;

@Component
public class ResumeService {

	@Autowired
	ResumeMapper resumeMapper;
	/**
	 * 画面情報検索 本月
	 * @param TopCustomerNo
	 * @return
	 */
	
	public void selectCheckResume(ResumeModel resumeModel)  {
		resumeMapper.selectCheckResume(resumeModel);
	}
	/**
	 * 画面情報検索
	 * @param TopCustomerNo
	 * @return
	 */
	
	public List<ResumeModel> selectResume(ResumeModel resumeModel)  {
		List<ResumeModel> resultMod = resumeMapper.selectResume(resumeModel);
		return resultMod;
	}
	public String selectEmployeeName(String employeeNo)  {
		String employeeName = resumeMapper.selectEmployeeName(employeeNo);
		return employeeName;
	}
	/**
	 * アップデート
	 * @param sendMap
	 */
	
	public boolean insertResume(ResumeModel resumeModel) {
		boolean result = true;
		try {
			resumeMapper.insertResume(resumeModel);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return result = false;
		}
		return result;
	}
}
