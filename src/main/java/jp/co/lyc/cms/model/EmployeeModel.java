package jp.co.lyc.cms.model;

import java.io.Serializable;

public class EmployeeModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2028159323401651353L;
	int rowNo;
	String employeeNo;// 社員番号
	String employeeFristName;// 社員氏
	String employeeLastName;// 社員名
	String updateUser;// 更新者
	String password;// パースワード
	String authorityCode;// 権限コード
	String genderStatus;// 性別ステータス
	String birthday;// 年齢
	String japaneseCalendar;// 和暦
	String alphabetName;// ローマ字
	String alphabetName1;// ローマ字
	String alphabetName2;// ローマ字
	String alphabetName3;// ローマ字
	String furigana;// カタカナ
	String nationalityCode;// 出身地コード(国)
	String birthplace;// 出身地(県)
	String intoCompanyCode;// 入社区分
	String intoCompanyYearAndMonth;// 入社年月
	String employeeStatus;// 社員ステータス
	String retirementYearAndMonth;// 退職年月
	String myNumber;// マイナンバー
	String occupationCode;// 職種コード
	String employeeFormCode;// 社員形式
	String comeToJapanYearAndMonth;// 来日年月
	String graduationUniversity;// 卒業学校
	String graduationYearAndMonth;// 卒業年月
	String major;// 専門
	String residenceCode;// 在留資格
	String residenceCardNo;// 在留カード番号
	String stayPeriod;// 在留期間(まで)
	String japaneseLevelCode;// 日本語レベルコード
	String englishLevelCode;// 英語レベルコード
	String developLanguage1;// 技術语言1
	String developLanguage2;// 技術语言2
	String developLanguage3;// 技術语言3
	String developLanguage4;// 技術语言4
	String developLanguage5;// 技術语言5
	String certification1;// 資格1
	String certification2;// 資格2
	String resumeInfo1;// 履歴書情報1
	String resumeName1;// 履歴書備考1
	String resumeInfo2;// 履歴書情報2
	String resumeName2;// 履歴書備考2
	String residentCardInfo;// 在留カード情報
	String passportInfo;// 在留カード情報
	String passportNo;// パスポート
	String companyMail;// 社内メールアドレス
	String phoneNo;// 携帯電話
	String employmentInsuranceNo;// 雇用保険番号
	String departmentCode;// 部署コード
	String stationName;// 寄り駅
	String oldPassword;// 古いパスワード
	String customer;// お客様
	String customerNo;// お客様No
	String employeeName;
	String authorityName;
	String ageFrom;// 開始年齢
	String ageTo;// 終了年齢
	String salary;// 給料
	String salaryFrom;// 開始給料
	String salaryTo;// 終了給料
	String unitPriceFrom;// 単価範囲開始
	String unitPriceTo;// 単価範囲終了
	String rookieDivision;
	String developmentLanguageNo;// 技術
	String sortToggleSalary;// 給料ソート
	String siteRoleCode;// 役割コード
	String kadou;//
	String visaPeriod;// ビザ期間
	AccountInfoModel accountInfo;// 口座情報
	String yearsOfExperience; // 経験年数
	String admissionTime; // 入場年月
	String contractDeadline;// 契約期限(まで)

	BpInfoModel bpInfoModel;// PB情報

	String postcode;// 郵便番号
	String firstHalfAddress;// 住所前半
	String lastHalfAddress;// 住所後半
	String stationCode;//

	public String getAlphabetName3() {
		return alphabetName3;
	}

	public void setAlphabetName3(String alphabetName3) {
		this.alphabetName3 = alphabetName3;
	}

	public String getContractDeadline() {
		return contractDeadline;
	}

	public void setContractDeadline(String contractDeadline) {
		this.contractDeadline = contractDeadline;
	}

	public String getAdmissionTime() {
		return admissionTime;
	}

	public void setAdmissionTime(String admissionTime) {
		this.admissionTime = admissionTime;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	String intoCompanyYearAndMonthFrom;// 入社年月元
	String intoCompanyYearAndMonthTo;// 入社年月先
	String picInfo;// 写真

	String furigana1;// カタカナ1
	String furigana2;// カタカナ2

	public String getCustomerNo() {
		return customerNo;
	}

	public void setCustomerNo(String customerNo) {
		this.customerNo = customerNo;
	}

	public String getPassportInfo() {
		return passportInfo;
	}

	public void setPassportInfo(String passportInfo) {
		this.passportInfo = passportInfo;
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

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAuthorityCode() {
		return authorityCode;
	}

	public void setAuthorityCode(String authorityCode) {
		this.authorityCode = authorityCode;
	}

	public String getGenderStatus() {
		return genderStatus;
	}

	public void setGenderStatus(String genderStatus) {
		this.genderStatus = genderStatus;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getJapaneseCalendar() {
		return japaneseCalendar;
	}

	public void setJapaneseCalendar(String japaneseCalendar) {
		this.japaneseCalendar = japaneseCalendar;
	}

	public String getFurigana() {
		return furigana;
	}

	public void setFurigana(String furigana) {
		this.furigana = furigana;
	}

	public String getNationalityCode() {
		return nationalityCode;
	}

	public void setNationalityCode(String nationalityCode) {
		this.nationalityCode = nationalityCode;
	}

	public String getBirthplace() {
		return birthplace;
	}

	public void setBirthplace(String birthplace) {
		this.birthplace = birthplace;
	}

	public String getIntoCompanyCode() {
		return intoCompanyCode;
	}

	public void setIntoCompanyCode(String intoCompanyCode) {
		this.intoCompanyCode = intoCompanyCode;
	}

	public String getIntoCompanyYearAndMonth() {
		return intoCompanyYearAndMonth;
	}

	public void setIntoCompanyYearAndMonth(String intoCompanyYearAndMonth) {
		this.intoCompanyYearAndMonth = intoCompanyYearAndMonth;
	}

	public String getEmployeeStatus() {
		return employeeStatus;
	}

	public void setEmployeeStatus(String employeeStatus) {
		this.employeeStatus = employeeStatus;
	}

	public String getRetirementYearAndMonth() {
		return retirementYearAndMonth;
	}

	public void setRetirementYearAndMonth(String retirementYearAndMonth) {
		this.retirementYearAndMonth = retirementYearAndMonth;
	}

	public String getMyNumber() {
		return myNumber;
	}

	public void setMyNumber(String myNumber) {
		this.myNumber = myNumber;
	}

	public String getOccupationCode() {
		return occupationCode;
	}

	public void setOccupationCode(String occupationCode) {
		this.occupationCode = occupationCode;
	}

	public String getEmployeeFormCode() {
		return employeeFormCode;
	}

	public void setEmployeeFormCode(String employeeFormCode) {
		this.employeeFormCode = employeeFormCode;
	}

	public String getComeToJapanYearAndMonth() {
		return comeToJapanYearAndMonth;
	}

	public void setComeToJapanYearAndMonth(String comeToJapanYearAndMonth) {
		this.comeToJapanYearAndMonth = comeToJapanYearAndMonth;
	}

	public String getGraduationUniversity() {
		return graduationUniversity;
	}

	public void setGraduationUniversity(String graduationUniversity) {
		this.graduationUniversity = graduationUniversity;
	}

	public String getGraduationYearAndMonth() {
		return graduationYearAndMonth;
	}

	public void setGraduationYearAndMonth(String graduationYearAndMonth) {
		this.graduationYearAndMonth = graduationYearAndMonth;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getResidenceCode() {
		return residenceCode;
	}

	public void setResidenceCode(String residenceCode) {
		this.residenceCode = residenceCode;
	}

	public String getResidenceCardNo() {
		return residenceCardNo;
	}

	public void setResidenceCardNo(String residenceCardNo) {
		this.residenceCardNo = residenceCardNo;
	}

	public String getStayPeriod() {
		return stayPeriod;
	}

	public void setStayPeriod(String stayPeriod) {
		this.stayPeriod = stayPeriod;
	}

	public String getJapaneseLevelCode() {
		return japaneseLevelCode;
	}

	public void setJapaneseLevelCode(String japaneseLevelCode) {
		this.japaneseLevelCode = japaneseLevelCode;
	}

	public String getEnglishLevelCode() {
		return englishLevelCode;
	}

	public void setEnglishLevelCode(String englishLevelCode) {
		this.englishLevelCode = englishLevelCode;
	}

	public String getDevelopLanguage1() {
		return developLanguage1;
	}

	public void setDevelopLanguage1(String developLanguage1) {
		this.developLanguage1 = developLanguage1;
	}

	public String getDevelopLanguage2() {
		return developLanguage2;
	}

	public void setDevelopLanguage2(String developLanguage2) {
		this.developLanguage2 = developLanguage2;
	}

	public String getDevelopLanguage3() {
		return developLanguage3;
	}

	public void setDevelopLanguage3(String developLanguage3) {
		this.developLanguage3 = developLanguage3;
	}

	public String getDevelopLanguage4() {
		return developLanguage4;
	}

	public void setDevelopLanguage4(String developLanguage4) {
		this.developLanguage4 = developLanguage4;
	}

	public String getDevelopLanguage5() {
		return developLanguage5;
	}

	public void setDevelopLanguage5(String developLanguage5) {
		this.developLanguage5 = developLanguage5;
	}

	public String getCertification1() {
		return certification1;
	}

	public void setCertification1(String certification1) {
		this.certification1 = certification1;
	}

	public String getCertification2() {
		return certification2;
	}

	public void setCertification2(String certification2) {
		this.certification2 = certification2;
	}

	public String getResumeInfo1() {
		return resumeInfo1;
	}

	public void setResumeInfo1(String resumeInfo1) {
		this.resumeInfo1 = resumeInfo1;
	}

	public String getResumeName1() {
		return resumeName1;
	}

	public void setResumeName1(String resumeName1) {
		this.resumeName1 = resumeName1;
	}

	public String getResumeName2() {
		return resumeName2;
	}

	public void setResumeName2(String resumeName2) {
		this.resumeName2 = resumeName2;
	}

	public String getResumeInfo2() {
		return resumeInfo2;
	}

	public void setResumeInfo2(String resumeInfo2) {
		this.resumeInfo2 = resumeInfo2;
	}

	public String getResidentCardInfo() {
		return residentCardInfo;
	}

	public void setResidentCardInfo(String residentCardInfo) {
		this.residentCardInfo = residentCardInfo;
	}

	public String getPassportNo() {
		return passportNo;
	}

	public void setPassportNo(String passportNo) {
		this.passportNo = passportNo;
	}

	public String getCompanyMail() {
		return companyMail;
	}

	public void setCompanyMail(String companyMail) {
		this.companyMail = companyMail;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getEmploymentInsuranceNo() {
		return employmentInsuranceNo;
	}

	public void setEmploymentInsuranceNo(String employmentInsuranceNo) {
		this.employmentInsuranceNo = employmentInsuranceNo;
	}

	public String getDepartmentCode() {
		return departmentCode;
	}

	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getAuthorityName() {
		return authorityName;
	}

	public void setAuthorityName(String authorityName) {
		this.authorityName = authorityName;
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

	public String getRookieDivision() {
		return rookieDivision;
	}

	public void setRookieDivision(String rookieDivision) {
		this.rookieDivision = rookieDivision;
	}

	public String getDevelopmentLanguageNo() {
		return developmentLanguageNo;
	}

	public void setDevelopmentLanguageNo(String developmentLanguageNo) {
		this.developmentLanguageNo = developmentLanguageNo;
	}

	public String getSortToggleSalary() {
		return sortToggleSalary;
	}

	public void setSortToggleSalary(String sortToggleSalary) {
		this.sortToggleSalary = sortToggleSalary;
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

	public String getVisaPeriod() {
		return visaPeriod;
	}

	public void setVisaPeriod(String visaPeriod) {
		this.visaPeriod = visaPeriod;
	}

	public String getIntoCompanyYearAndMonthFrom() {
		return intoCompanyYearAndMonthFrom;
	}

	public void setIntoCompanyYearAndMonthFrom(String intoCompanyYearAndMonthFrom) {
		this.intoCompanyYearAndMonthFrom = intoCompanyYearAndMonthFrom;
	}

	public String getIntoCompanyYearAndMonthTo() {
		return intoCompanyYearAndMonthTo;
	}

	public void setIntoCompanyYearAndMonthTo(String intoCompanyYearAndMonthTo) {
		this.intoCompanyYearAndMonthTo = intoCompanyYearAndMonthTo;
	}

	public String getPicInfo() {
		return picInfo;
	}

	public void setPicInfo(String picInfo) {
		this.picInfo = picInfo;
	}

	public String getFurigana1() {
		return furigana1;
	}

	public void setFurigana1(String furigana1) {
		this.furigana1 = furigana1;
	}

	public String getFurigana2() {
		return furigana2;
	}

	public void setFurigana2(String furigana2) {
		this.furigana2 = furigana2;
	}

	public AccountInfoModel getAccountInfo() {
		return accountInfo;
	}

	public void setAccountInfo(AccountInfoModel accountInfo) {
		this.accountInfo = accountInfo;
	}

	public String getYearsOfExperience() {
		return yearsOfExperience;
	}

	public void setYearsOfExperience(String yearsOfExperience) {
		this.yearsOfExperience = yearsOfExperience;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getFirstHalfAddress() {
		return firstHalfAddress;
	}

	public void setFirstHalfAddress(String firstHalfAddress) {
		this.firstHalfAddress = firstHalfAddress;
	}

	public String getLastHalfAddress() {
		return lastHalfAddress;
	}

	public void setLastHalfAddress(String lastHalfAddress) {
		this.lastHalfAddress = lastHalfAddress;
	}

	public BpInfoModel getBpInfoModel() {
		return bpInfoModel;
	}

	public void setBpInfoModel(BpInfoModel bpInfoModel) {
		this.bpInfoModel = bpInfoModel;
	}

	public String getStationCode() {
		return stationCode;
	}

	public void setStationCode(String stationCode) {
		this.stationCode = stationCode;
	}

	public String getAlphabetName() {
		return alphabetName;
	}

	public void setAlphabetName(String alphabetName) {
		this.alphabetName = alphabetName;
	}

	public String getAlphabetName1() {
		return alphabetName1;
	}

	public void setAlphabetName1(String alphabetName1) {
		this.alphabetName1 = alphabetName1;
	}

	public String getAlphabetName2() {
		return alphabetName2;
	}

	public void setAlphabetName2(String alphabetName2) {
		this.alphabetName2 = alphabetName2;
	}

}
