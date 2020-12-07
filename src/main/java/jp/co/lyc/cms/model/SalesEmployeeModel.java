package jp.co.lyc.cms.model;

public class SalesEmployeeModel {

	/**
	 *
	 */
	private static final long serialVersionUID = -2028159323401651353L;

	String employeeNo; // 社員番号
	String startTime; // 現場開始年月
	String endTime; // 現場終了年月
	String customerNo; // お客様番号
	String customerName; // お客様
	String levelCode; // お客様レベル

	public String getEmployeeNo() {
		return employeeNo;
	}

	public void setEmployeeNo(String employeeNo) {
		this.employeeNo = employeeNo;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getCustomerNo() {
		return customerNo;
	}

	public void setCustomerNo(String customerNo) {
		this.customerNo = customerNo;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getLevelCode() {
		return levelCode;
	}

	public void setLevelCode(String levelCode) {
		this.levelCode = levelCode;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
