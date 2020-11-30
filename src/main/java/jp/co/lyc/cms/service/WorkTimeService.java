package jp.co.lyc.cms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.lyc.cms.mapper.WorkTimeMapper;
import jp.co.lyc.cms.model.WorkTimeModel;

@Component
public class WorkTimeService {

	@Autowired
	WorkTimeMapper workTimeMapper;
	/**
	 * 画面情報検索
	 * @param TopCustomerNo
	 * @return
	 */
	public List<WorkTimeModel> selectWorkTime(WorkTimeModel workTimeModel)  {
		List<WorkTimeModel> resultMod = workTimeMapper.selectWorkTime(workTimeModel);
		return resultMod;
	}
}
