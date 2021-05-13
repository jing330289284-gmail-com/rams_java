package jp.co.lyc.cms.model;

public class EmployeeInformationModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2028159323401651353L;
	int rowNo;// 番号
	int stayPeriodDate;// 在留期間
	int passportStayPeriodDate;// パスポート期限
	int contractDeadlineDate;// 契約期限
	int birthdayDate; // 誕生日
	String employeeNo;// 社員・BP番号
	String employeeName;// 社員・BP名
	String birthday;// 誕生日
	String stayPeriod;// 在留期間
	String passportStayPeriod;// パスポート期限
	String contractDeadline;// 契約期限
	String dealDistinctioCode;// 処理区分
	String[] employeeNos;// 社員・BP番号
	String[] dealDistinctioCodes;// 処理区分

	public int getStayPeriodDate() {
		return stayPeriodDate;
	}

	public void setStayPeriodDate(int stayPeriodDate) {
		this.stayPeriodDate = stayPeriodDate;
	}

	public int getPassportStayPeriodDate() {
		return passportStayPeriodDate;
	}

	public void setPassportStayPeriodDate(int passportStayPeriodDate) {
		this.passportStayPeriodDate = passportStayPeriodDate;
	}

	public int getContractDeadlineDate() {
		return contractDeadlineDate;
	}

	public void setContractDeadlineDate(int contractDeadlineDate) {
		this.contractDeadlineDate = contractDeadlineDate;
	}

	public int getBirthdayDate() {
		return birthdayDate;
	}

	public void setBirthdayDate(int birthdayDate) {
		this.birthdayDate = birthdayDate;
	}

	public String[] getEmployeeNos() {
		return employeeNos;
	}

	public void setEmployeeNos(String[] employeeNos) {
		this.employeeNos = employeeNos;
	}

	public String[] getDealDistinctioCodes() {
		return dealDistinctioCodes;
	}

	public void setDealDistinctioCodes(String[] dealDistinctioCodes) {
		this.dealDistinctioCodes = dealDistinctioCodes;
	}

	public int getRowNo() {
		return rowNo;
	}

	public void setRowNo(int rowNo) {
		this.rowNo = rowNo;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getEmployeeNo() {
		return employeeNo;
	}

	public void setEmployeeNo(String employeeNo) {
		this.employeeNo = employeeNo;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getStayPeriod() {
		return stayPeriod;
	}

	public void setStayPeriod(String stayPeriod) {
		this.stayPeriod = stayPeriod;
	}

	public String getPassportStayPeriod() {
		return passportStayPeriod;
	}

	public void setPassportStayPeriod(String passportStayPeriod) {
		this.passportStayPeriod = passportStayPeriod;
	}

	public String getContractDeadline() {
		return contractDeadline;
	}

	public void setContractDeadline(String contractDeadline) {
		this.contractDeadline = contractDeadline;
	}

	public String getDealDistinctioCode() {
		return dealDistinctioCode;
	}

	public void setDealDistinctioCode(String dealDistinctioCode) {
		this.dealDistinctioCode = dealDistinctioCode;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
