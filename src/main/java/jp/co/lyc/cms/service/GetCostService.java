package jp.co.lyc.cms.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import jp.co.lyc.cms.mapper.GetCostMapper;
import jp.co.lyc.cms.model.CostModel;

@Component
public class GetCostService {

	@Autowired
	GetCostMapper getCostMapper;

	public CostModel getEmployeeInfo(Map<String, String> sendMap) {
		return getCostMapper.selectCost(sendMap);
	}
	public HashMap<String,String> checkKado(String employeeNo) {
		return getCostMapper.checkKado(employeeNo);
	}
	
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
