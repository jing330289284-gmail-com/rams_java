package jp.co.lyc.cms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.lyc.cms.mapper.CostRegistrationMapper;
import jp.co.lyc.cms.model.CostRegistrationModel;

@Component
public class CostRegistrationService {

	@Autowired
	CostRegistrationMapper costRegistrationMapper;
	/**
	 * 画面情報検索 本月
	 * @param TopCustomerNo
	 * @return
	 */
	
	public void selectCheckCostRegistration(CostRegistrationModel costRegistrationModel)  {
		costRegistrationMapper.selectCostRegistration(costRegistrationModel);
	}
	/**
	 * 画面情報検索
	 * @param TopCustomerNo
	 * @return
	 */
	
	public List<CostRegistrationModel> selectCostRegistration(CostRegistrationModel costRegistrationModel)  {
		List<CostRegistrationModel> resultMod = costRegistrationMapper.selectCostRegistration(costRegistrationModel);
		return resultMod;
	}
	/**
	 * アップデート
	 * @param sendMap
	 */
	
	public boolean updateCostRegistration(CostRegistrationModel costRegistrationModel) {
		boolean result = true;
		try {
			costRegistrationMapper.updateCostRegistration(costRegistrationModel);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return result = false;
		}
		return result;
	}
	public boolean insertCostRegistration(CostRegistrationModel costRegistrationModel) {
		boolean result = true;
		try {
			costRegistrationMapper.insertCostRegistration(costRegistrationModel);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return result = false;
		}
		return result;
	}
	public boolean deletetCostRegistration(CostRegistrationModel costRegistrationModel) {
		boolean result = true;
		try {
			costRegistrationMapper.insertCostRegistration(costRegistrationModel);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return result = false;
		}
		return result;
	}
}
