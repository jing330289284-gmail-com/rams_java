package jp.co.lyc.cms.model;

import java.io.Serializable;

public class EmployeeModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2028159323401651353L;
	int rowNo;
	String employeeNo;//��T����
	String employeeFristName;//��T��ǰ
	String employeeLastName;//��������
	String emploryeeForm;//��T��ʽ
	String joinCompanyOfYear;//������
	String joinCompanyOfMonth;//������
	String nearestStation;//�Ĥ��k
	String customer;//
	String intoCompanyCode;
	String birthplaceOfcontroy;
	String password;
	String employeeName;
	String authorityNo;
	String authorityProperty;
	String genderCode;//�Ԅe
	String age;//���h
	String ageFrom;//�_ʼ���h
	String ageTo;//�K�����h
	String salary;//�o��
	String salaryFrom;//�_ʼ�o��
	String salaryTo;//�K�˽o��
	String unitPriceFrom;//�g�������_ʼ
	String unitPriceTo;//�g������K��
	String birthPlace;
	String statusOfResidence;
	String rookieDivision;
	String japanease;//�ձ��Z��٥�
	String developmentLanguageNo;//���g
	String phoneNo;//�Ԓ����
	String sortToggleSalary;//�o�ϥ��`��

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	public String getEmploryeeForm() {
		return emploryeeForm;
	}

	public void setEmploryeeForm(String emploryeeForm) {
		this.emploryeeForm = emploryeeForm;
	}

	public String getAgeFrom() {
		return ageFrom;
	}

	public void setAgeFrom(String ageFrom) {
		this.ageFrom = ageFrom;
	}

	public String getAgeTo() {
		return ageTo;
	}

	public void setAgeTo(String ageTo) {
		this.ageTo = ageTo;
	}

	public int getRowNo() {
		return rowNo;
	}

	public void setRowNo(int rowNo) {
		this.rowNo = rowNo;
	}

	public String getEmployeeNo() {
		return employeeNo;
	}

	public void setEmployeeNo(String employeeNo) {
		this.employeeNo = employeeNo;
	}

	public String getEmployeeFristName() {
		return employeeFristName;
	}

	public void setEmployeeFristName(String employeeFristName) {
		this.employeeFristName = employeeFristName;
	}

	public String getEmployeeLastName() {
		return employeeLastName;
	}

	public void setEmployeeLastName(String employeeLastName) {
		this.employeeLastName = employeeLastName;
	}

	public String getJoinCompanyOfYear() {
		return joinCompanyOfYear;
	}

	public void setJoinCompanyOfYear(String joinCompanyOfYear) {
		this.joinCompanyOfYear = joinCompanyOfYear;
	}

	public String getJoinCompanyOfMonth() {
		return joinCompanyOfMonth;
	}

	public void setJoinCompanyOfMonth(String joinCompanyOfMonth) {
		this.joinCompanyOfMonth = joinCompanyOfMonth;
	}

	public String getNearestStation() {
		return nearestStation;
	}

	public void setNearestStation(String nearestStation) {
		this.nearestStation = nearestStation;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}


	public String getIntoCompanyCode() {
		return intoCompanyCode;
	}

	public void setIntoCompanyCode(String intoCompanyCode) {
		this.intoCompanyCode = intoCompanyCode;
	}

	public String getBirthplaceOfcontroy() {
		return birthplaceOfcontroy;
	}

	public void setBirthplaceOfcontroy(String birthplaceOfcontroy) {
		this.birthplaceOfcontroy = birthplaceOfcontroy;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getAuthorityNo() {
		return authorityNo;
	}

	public void setAuthorityNo(String authorityNo) {
		this.authorityNo = authorityNo;
	}

	public String getAuthorityProperty() {
		return authorityProperty;
	}

	public void setAuthorityProperty(String authorityProperty) {
		this.authorityProperty = authorityProperty;
	}

	public String getGenderCode() {
		return genderCode;
	}

	public void setGenderCode(String genderCode) {
		this.genderCode = genderCode;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getBirthPlace() {
		return birthPlace;
	}

	public void setBirthPlace(String birthPlace) {
		this.birthPlace = birthPlace;
	}

	public String getSalaryFrom() {
		return salaryFrom;
	}

	public void setSalaryFrom(String salaryFrom) {
		this.salaryFrom = salaryFrom;
	}

	public String getSalaryTo() {
		return salaryTo;
	}

	public void setSalaryTo(String salaryTo) {
		this.salaryTo = salaryTo;
	}

	public String getUnitPriceFrom() {
		return unitPriceFrom;
	}

	public void setUnitPriceFrom(String unitPriceFrom) {
		this.unitPriceFrom = unitPriceFrom;
	}

	public String getUnitPriceTo() {
		return unitPriceTo;
	}

	public void setUnitPriceTo(String unitPriceTo) {
		this.unitPriceTo = unitPriceTo;
	}

	public String getStatusOfResidence() {
		return statusOfResidence;
	}

	public void setStatusOfResidence(String statusOfResidence) {
		this.statusOfResidence = statusOfResidence;
	}

	public String getRookieDivision() {
		return rookieDivision;
	}

	public void setRookieDivision(String rookieDivision) {
		this.rookieDivision = rookieDivision;
	}

	public String getJapanease() {
		return japanease;
	}

	public void setJapanease(String japanease) {
		this.japanease = japanease;
	}

	public String getDevelopmentLanguageNo() {
		return developmentLanguageNo;
	}

	public void setDevelopmentLanguageNo(String developmentLanguageNo) {
		this.developmentLanguageNo = developmentLanguageNo;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getSortToggleSalary() {
		return sortToggleSalary;
	}

	public void setSortToggleSalary(String sortToggleSalary) {
		this.sortToggleSalary = sortToggleSalary;
	}

}
