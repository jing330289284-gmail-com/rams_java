package jp.co.lyc.cms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import jp.co.lyc.cms.model.ResumeModel;

@Mapper
public interface ResumeMapper {
	/**
	 * 画面情報検索
	 * @param TopCustomerNo
	 * @return
	 */
	public void selectCheckResume(ResumeModel resumeModel) ;
	public List<ResumeModel> selectResume(ResumeModel resumeModel) ;
	/**
	 *insert
	 * @param sendMap
	 */
	public void insertResume(ResumeModel resumeModel) ;	
}
