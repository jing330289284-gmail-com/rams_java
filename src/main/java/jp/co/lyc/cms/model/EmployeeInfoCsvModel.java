package jp.co.lyc.cms.model;

import java.io.Serializable;

public class EmployeeInfoCsvModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2028159323401651353L;
	String employeeNo;// 社員番号
	String nationality;// 出身地コード(国)
	String employeeName;// 社員氏名
	String alphabetName;// ローマ字
	String furigana;// カタカナ
	String genderStatus;// 性別ステータス
	String companyMail;// 社内メールアドレス
	String birthday;// 年齢
	String japaneseCalendar;// 和暦
	String age;// 年齢
	String intoCompanyYearAndMonth;// 入社年月
	String intoCompanyYears;// 入社年数
	String contractDeadline;// 契約期限(まで)
	String phoneNo;// 携帯電話
	String residenceName;// 在留資格
	String stayPeriodYears;// 在留期間
	String stayPeriod;// 在留期限
	String residenceCardNo;// 在留カ－ド番号
	String passportNo;// パスポート
	String passportStayPeriod; // パスポート期間
	String myNumber;// マイナンバー
	String employmentInsuranceStatus;// 雇用保険加入
	String postcode;// 郵便番号
	String address;// 住所
	String accountInfo;// 口座情報

	public String getEmployeeNo() {
		return employeeNo;
	}

	public void setEmployeeNo(String employeeNo) {
		this.employeeNo = employeeNo;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getAlphabetName() {
		return alphabetName;
	}

	public void setAlphabetName(String alphabetName) {
		this.alphabetName = alphabetName;
	}

	public String getFurigana() {
		return furigana;
	}

	public void setFurigana(String furigana) {
		this.furigana = furigana;
	}

	public String getGenderStatus() {
		return genderStatus;
	}

	public void setGenderStatus(String genderStatus) {
		this.genderStatus = genderStatus;
	}

	public String getCompanyMail() {
		return companyMail;
	}

	public void setCompanyMail(String companyMail) {
		this.companyMail = companyMail;
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

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getIntoCompanyYearAndMonth() {
		return intoCompanyYearAndMonth;
	}

	public void setIntoCompanyYearAndMonth(String intoCompanyYearAndMonth) {
		this.intoCompanyYearAndMonth = intoCompanyYearAndMonth;
	}

	public String getIntoCompanyYears() {
		return intoCompanyYears;
	}

	public void setIntoCompanyYears(String intoCompanyYears) {
		this.intoCompanyYears = intoCompanyYears;
	}

	public String getContractDeadline() {
		return contractDeadline;
	}

	public void setContractDeadline(String contractDeadline) {
		this.contractDeadline = contractDeadline;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getResidenceName() {
		return residenceName;
	}

	public void setResidenceName(String residenceName) {
		this.residenceName = residenceName;
	}

	public String getStayPeriodYears() {
		return stayPeriodYears;
	}

	public void setStayPeriodYears(String stayPeriodYears) {
		this.stayPeriodYears = stayPeriodYears;
	}

	public String getStayPeriod() {
		return stayPeriod;
	}

	public void setStayPeriod(String stayPeriod) {
		this.stayPeriod = stayPeriod;
	}

	public String getResidenceCardNo() {
		return residenceCardNo;
	}

	public void setResidenceCardNo(String residenceCardNo) {
		this.residenceCardNo = residenceCardNo;
	}

	public String getPassportNo() {
		return passportNo;
	}

	public void setPassportNo(String passportNo) {
		this.passportNo = passportNo;
	}

	public String getPassportStayPeriod() {
		return passportStayPeriod;
	}

	public void setPassportStayPeriod(String passportStayPeriod) {
		this.passportStayPeriod = passportStayPeriod;
	}

	public String getMyNumber() {
		return myNumber;
	}

	public void setMyNumber(String myNumber) {
		this.myNumber = myNumber;
	}

	public String getEmploymentInsuranceStatus() {
		return employmentInsuranceStatus;
	}

	public void setEmploymentInsuranceStatus(String employmentInsuranceStatus) {
		this.employmentInsuranceStatus = employmentInsuranceStatus;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAccountInfo() {
		return accountInfo;
	}

	public void setAccountInfo(String accountInfo) {
		this.accountInfo = accountInfo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
