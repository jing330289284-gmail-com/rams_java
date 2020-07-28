package jp.co.lyc.cms.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.lyc.cms.mapper.GetEmployeeInfoMapper;
import jp.co.lyc.cms.model.CustomerModel;
import jp.co.lyc.cms.model.EmployeeModel;
import jp.co.lyc.cms.model.IntoCompanyModel;
import jp.co.lyc.cms.model.JapaneseLevelModel;
import jp.co.lyc.cms.model.NationalityModel;
import jp.co.lyc.cms.model.StaffModel;
import jp.co.lyc.cms.model.TechnologyTypeModel;
import jp.co.lyc.cms.model.VisaModel;

@Component
public class GetEmployeeInfoService {

	@Autowired
	GetEmployeeInfoMapper getEmployeeInfoMapper;

	/**
	 * ��T����ȡ��
	 * 
	 * @param sendMap
	 * @return
	 */
	public List<EmployeeModel> getEmployeeInfo(Map<String, String> sendMap) {
		List<EmployeeModel> employeeList = getEmployeeInfoMapper.getEmployeeInfo(sendMap);
		return employeeList;
	}

	/**
	 * ������ȡ��
	 * 
	 * @return
	 */
	public List<NationalityModel> getNationalitys() {
		List<NationalityModel> nationalitysList = getEmployeeInfoMapper.getNationalitys();
		return nationalitysList;
	}



	/**
	 * ��T��ʽ��ȡ��
	 * 
	 * @return
	 */
	public List<StaffModel> getStaffForms() {
		List<StaffModel> emploryeeFormsList = getEmployeeInfoMapper.getStaffForms();
		return emploryeeFormsList;
	}

	/**
	 * �����Y���ȡ��
	 * 
	 * @return
	 */
	public List<VisaModel> getVisa() {
		List<VisaModel> visaList = getEmployeeInfoMapper.getVisa();
		return visaList;
	}

	
	/**
	 * ���g�N�e��ȡ��
	 * @param sendMap 
	 * 
	 * @return
	 */
	public List<TechnologyTypeModel> getTechnologyType(Map<String, String> sendMap) {
		List<TechnologyTypeModel> technologyTypeList = getEmployeeInfoMapper.getTechnologyType(sendMap);
		return technologyTypeList;
	}
	
	/**
	 * ���͘���ȡ��
	 * 
	 * @return
	 */
	public List<CustomerModel> getCustomer() {
		List<CustomerModel> customeList = getEmployeeInfoMapper.getCustomer();
		return customeList;
	}
	
	
	/**
	 * �ձ��Z��٥��ȡ��
	 * 
	 * @return
	 */
	public List<JapaneseLevelModel> getJapaneseLevel() {
		List<JapaneseLevelModel> japaneseLevelList = getEmployeeInfoMapper.getJapaneseLevel();
		return japaneseLevelList;
	}
	
	
	/**
	 * �������֤�ȡ��
	 * 
	 * @return
	 */
	public List<IntoCompanyModel> getIntoCompany() {
		List<IntoCompanyModel> intoCompanyList = getEmployeeInfoMapper.getIntoCompany();
		return intoCompanyList;
	}

}
