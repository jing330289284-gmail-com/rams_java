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
	 * ��T����ȡ��
	 * 
	 * @param sendMap
	 * @return
	 */
	public List<EmployeeModel> getEmployeeInfo(Map<String, String> sendMap);

	/**
	 * ������ȡ��
	 * 
	 * @return
	 */
	public List<NationalityModel> getNationalitys();

	/**
	 * ���g�N�e��ȡ��
	 * 
	 * @return
	 */
	public List<String> getDevelopmentLanguageNos();

	/**
	 * ��T��ʽ��ȡ��
	 * 
	 * @return
	 */
	public List<StaffModel> getStaffForms();

	/**
	 * �����Y���ȡ��
	 * 
	 * @return
	 */
	public List<VisaModel> getVisa();

	/**
	 * ���͘���ȡ��
	 * 
	 * @return
	 */
	public List<CustomerModel> getCustomer();

	/**
	 * ���g�N�e��ȡ��
	 * 
	 * @param sendMap
	 * 
	 * @return
	 */
	public List<TechnologyTypeModel> getTechnologyType(Map<String, String> sendMap);

	/**
	 * �ձ��Z��٥��ȡ��
	 * 
	 * @return
	 */
	public List<JapaneseLevelModel> getJapaneseLevel();

	/**
	 * �������֤�ȡ��
	 * 
	 * @return
	 */
	public List<IntoCompanyModel> getIntoCompany();

}
