package jp.co.lyc.cms.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import jp.co.lyc.cms.model.CustomerModel;
import jp.co.lyc.cms.model.EmployeeModel;
import jp.co.lyc.cms.model.IntoCompanyModel;
import jp.co.lyc.cms.model.JapaneseLevelModel;
import jp.co.lyc.cms.model.NationalityModel;
import jp.co.lyc.cms.model.StaffModel;
import jp.co.lyc.cms.model.TechnologyTypeModel;
import jp.co.lyc.cms.model.VisaModel;

@Mapper
public interface GetEmployeeInfoMapper {

	/**
	 * 社員情報を取得
	 * 
	 * @param sendMap
	 * @return
	 */
	public List<EmployeeModel> getEmployeeInfo(Map<String, String> sendMap);

	/**
	 * 国籍を取得
	 * 
	 * @return
	 */
	public List<NationalityModel> getNationalitys();

	/**
	 * 技術種別を取得
	 * 
	 * @return
	 */
	public List<String> getDevelopmentLanguageNos();

	/**
	 * 社員形式を取得
	 * 
	 * @return
	 */
	public List<StaffModel> getStaffForms();

	/**
	 * 在留資格を取得
	 * 
	 * @return
	 */
	public List<VisaModel> getVisa();

	/**
	 * お客様を取得
	 * 
	 * @return
	 */
	public List<CustomerModel> getCustomer();

	/**
	 * 技術種別を取得
	 * 
	 * @param sendMap
	 * 
	 * @return
	 */
	public List<TechnologyTypeModel> getTechnologyType(Map<String, String> sendMap);

	/**
	 * 日本語レベルを取得
	 * 
	 * @return
	 */
	public List<JapaneseLevelModel> getJapaneseLevel();

	/**
	 * 入社区分を取得
	 * 
	 * @return
	 */
	public List<IntoCompanyModel> getIntoCompany();

}
