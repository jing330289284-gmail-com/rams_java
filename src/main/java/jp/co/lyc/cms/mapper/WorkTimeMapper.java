package jp.co.lyc.cms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import jp.co.lyc.cms.model.WorkTimeModel;

@Mapper
public interface WorkTimeMapper {
	/**
	 * 画面情報検索
	 * @param TopCustomerNo
	 * @return
	 */
	public List<WorkTimeModel> selectWorkTime(WorkTimeModel workTimeModel) ;
}
