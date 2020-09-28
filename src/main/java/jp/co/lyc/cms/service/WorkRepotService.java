package jp.co.lyc.cms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.lyc.cms.mapper.WorkRepotMapper;
import jp.co.lyc.cms.model.WorkRepotModel;

@Component
public class WorkRepotService {

	@Autowired
	WorkRepotMapper workRepotMapper;
		
	/**
	 * 画面情報検索
	 * @param TopCustomerNo
	 * @return
	 */
	
	public List<WorkRepotModel> selectWorkRepot(WorkRepotModel workRepotModel)  {
		List<WorkRepotModel> resultMod = workRepotMapper.selectWorkRepot(workRepotModel);
		return resultMod;
	}
	/**
	 * アップデート
	 * @param sendMap
	 */
	
	public boolean updateDutyManagement(WorkRepotModel workRepotModel) {
		boolean result = true;
		try {
			workRepotMapper.updateWorkRepot(workRepotModel);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return result = false;
		}
		return result;
	}
}
