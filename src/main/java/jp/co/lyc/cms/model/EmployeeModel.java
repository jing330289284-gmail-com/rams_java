package jp.co.lyc.cms.model;

import java.io.Serializable;

public class EmployeeModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2028159323401651353L;
	int rowNo;
	String employeeNo;// 社員番号
	String employeeFristName;// 社員名前
	String furigana;// カタカナ
	String emploryeeForm;// 社員形式
	String joinCompanyOfYearandMonth;
	String nearestStation;// 寄り駅
	String customer;//
	String intoCompanyCode;
	String birthplaceOfcontroy;
	String password;
	String employeeName;
	String authorityNo;
	String authorityProperty;
	String genderCode;// 性別
	String age;// 年齢
	String ageFrom;// 開始年齢
	String ageTo;// 終了年齢
	String salary;// 給料
	String salaryFrom;// 開始給料
	String salaryTo;// 終了給料
	String unitPriceFrom;// 単価範囲開始
	String unitPriceTo;// 単価範囲終了
	String birthPlace;
	String statusOfResidence;
	String rookieDivision;
	String japanease;// 日本語レベル
	String developmentLanguageNo;// 技術
	String phoneNo;// 電話番号
	String sortToggleSalary;// 給料ソート
	String developmentLanguageNo1;// 開発言語1
	String developmentLanguageNo2;// 開発言語2
	String developmentLanguageNo3;// 開発言語3
	String siteRoleCode;//
	String kadou;//
	String alphabetOfName;// ローマ名前
	String visaPeriod;// ビザ期間

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

	public String getFurigana() {
		return furigana;
	}

	public void setFurigana(String furigana) {
		this.furigana = furigana;
	}

	public String getEmploryeeForm() {
		return emploryeeForm;
	}

	public void setEmploryeeForm(String emploryeeForm) {
		this.emploryeeForm = emploryeeForm;
	}

	public String getJoinCompanyOfYearandMonth() {
		return joinCompanyOfYearandMonth;
	}

	public void setJoinCompanyOfYearandMonth(String joinCompanyOfYearandMonth) {
		this.joinCompanyOfYearandMonth = joinCompanyOfYearandMonth;
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

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
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

	public String getBirthPlace() {
		return birthPlace;
	}

	public void setBirthPlace(String birthPlace) {
		this.birthPlace = birthPlace;
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

	public String getDevelopmentLanguageNo1() {
		return developmentLanguageNo1;
	}

	public void setDevelopmentLanguageNo1(String developmentLanguageNo1) {
		this.developmentLanguageNo1 = developmentLanguageNo1;
	}

	public String getDevelopmentLanguageNo2() {
		return developmentLanguageNo2;
	}

	public void setDevelopmentLanguageNo2(String developmentLanguageNo2) {
		this.developmentLanguageNo2 = developmentLanguageNo2;
	}

	public String getDevelopmentLanguageNo3() {
		return developmentLanguageNo3;
	}

	public void setDevelopmentLanguageNo3(String developmentLanguageNo3) {
		this.developmentLanguageNo3 = developmentLanguageNo3;
	}

	public String getSiteRoleCode() {
		return siteRoleCode;
	}

	public void setSiteRoleCode(String siteRoleCode) {
		this.siteRoleCode = siteRoleCode;
	}

	public String getKadou() {
		return kadou;
	}

	public void setKadou(String kadou) {
		this.kadou = kadou;
	}

	public String getAlphabetOfName() {
		return alphabetOfName;
	}

	public void setAlphabetOfName(String alphabetOfName) {
		this.alphabetOfName = alphabetOfName;
	}

	public String getVisaPeriod() {
		return visaPeriod;
	}

	public void setVisaPeriod(String visaPeriod) {
		this.visaPeriod = visaPeriod;
	}

}
