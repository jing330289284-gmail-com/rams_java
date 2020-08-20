package jp.co.lyc.cms.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import jp.co.lyc.cms.mapper.CostInfoMapper;
import jp.co.lyc.cms.model.CostInfoModel;

@Component
public class CostInfoService {

	@Autowired
	CostInfoMapper getCostMapper;

	/**
	 * 画面データの検索
	 * @param sendMap
	 * @return
	 */
	
	public ArrayList<CostInfoModel> getEmployeeInfo(Map<String, String> sendMap) {
		return getCostMapper.selectCost(sendMap);
	}
	
	/**
	 * 稼働の判断
	 * @param employeeNo
	 * @return
	 */
	
	public HashMap<String,String> checkKado(String employeeNo) {
		return getCostMapper.checkKado(employeeNo);
	}
	
	/**
	 * インサート
	 * @param sendMap
	 */
	
	public boolean insert(Map<String, Object> sendMap) {
		try {
			getCostMapper.insertCost(sendMap);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * アップデート
	 * @param sendMap
	 */
	
	public boolean update(Map<String, Object> sendMap) {
		try {
			getCostMapper.updateCost(sendMap);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
