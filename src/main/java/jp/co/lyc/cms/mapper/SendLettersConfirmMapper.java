package jp.co.lyc.cms.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;

import jp.co.lyc.cms.model.SalesSituationModel;
import jp.co.lyc.cms.model.SendLettersConfirmModel;
import jp.co.lyc.cms.model.AllEmployName;
import jp.co.lyc.cms.model.EmployeeModel;

@Mapper
public interface SendLettersConfirmMapper {

	/**
	 * ログイン
	 * @param sendMap
	 * @return
	 */
	
	public List<SendLettersConfirmModel> getSalesEmps(String[] empNos);
	
	public List<SalesSituationModel> getAllEmpsWithResume();
	
	public List<EmployeeModel> getLoginUserInfo(String lobinUserNo);
	
	public List<EmployeeModel> getMail();
	
	public List<AllEmployName> getAllEmployInfoName();
}
