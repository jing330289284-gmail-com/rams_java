package jp.co.lyc.cms.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import jp.co.lyc.cms.model.WorkRepotModel;
import jp.co.lyc.cms.mapper.WorkRepotMapper;

@Component
public class WorkRepotService {

	@Autowired
	WorkRepotMapper workRepotMapper;
		
	/**
	 * 画面情報検索
	 * @param TopCustomerNo
	 * @return
	 */
	
	public List<WorkRepotModel> selectWorkRepot()  {
		List<WorkRepotModel> resultMod = workRepotMapper.selectWorkRepot();
		return resultMod;
	}
	/**
	 * アップデート
	 * @param sendMap
	 */
	
	public boolean updateDutyManagement(HashMap<String, String> sendMap) {
		boolean result = true;
		try {
			workRepotMapper.updateWorkRepot(sendMap);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return result = false;
		}
		return result;
	}
}
