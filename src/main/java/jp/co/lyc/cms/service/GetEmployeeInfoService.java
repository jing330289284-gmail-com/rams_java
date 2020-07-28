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
	 * 社員情報を取得
	 * 
	 * @param sendMap
	 * @return
	 */
	public List<EmployeeModel> getEmployeeInfo(Map<String, String> sendMap) {
		List<EmployeeModel> employeeList = getEmployeeInfoMapper.getEmployeeInfo(sendMap);
		return employeeList;
	}

	/**
	 * 国籍を取得
	 * 
	 * @return
	 */
	public List<NationalityModel> getNationalitys() {
		List<NationalityModel> nationalitysList = getEmployeeInfoMapper.getNationalitys();
		return nationalitysList;
	}



	/**
	 * 社員形式を取得
	 * 
	 * @return
	 */
	public List<StaffModel> getStaffForms() {
		List<StaffModel> emploryeeFormsList = getEmployeeInfoMapper.getStaffForms();
		return emploryeeFormsList;
	}

	/**
	 * 在留資格を取得
	 * 
	 * @return
	 */
	public List<VisaModel> getVisa() {
		List<VisaModel> visaList = getEmployeeInfoMapper.getVisa();
		return visaList;
	}

	
	/**
	 * 技術種別を取得
	 * @param sendMap 
	 * 
	 * @return
	 */
	public List<TechnologyTypeModel> getTechnologyType(Map<String, String> sendMap) {
		List<TechnologyTypeModel> technologyTypeList = getEmployeeInfoMapper.getTechnologyType(sendMap);
		return technologyTypeList;
	}
	
	/**
	 * お客様を取得
	 * 
	 * @return
	 */
	public List<CustomerModel> getCustomer() {
		List<CustomerModel> customeList = getEmployeeInfoMapper.getCustomer();
		return customeList;
	}
	
	
	/**
	 * 日本語レベルを取得
	 * 
	 * @return
	 */
	public List<JapaneseLevelModel> getJapaneseLevel() {
		List<JapaneseLevelModel> japaneseLevelList = getEmployeeInfoMapper.getJapaneseLevel();
		return japaneseLevelList;
	}
	
	
	/**
	 * 入社区分を取得
	 * 
	 * @return
	 */
	public List<IntoCompanyModel> getIntoCompany() {
		List<IntoCompanyModel> intoCompanyList = getEmployeeInfoMapper.getIntoCompany();
		return intoCompanyList;
	}

}
