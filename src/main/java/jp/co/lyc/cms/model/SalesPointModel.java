package jp.co.lyc.cms.model;

public class SalesPointModel {

	/**
	 *
	 */
	private static final long serialVersionUID = -2028159323401651353L;

	String employeeStatus; // 社員区分
	String intoCompanyCode; // 新人区分
	String customerContractStatus; // 契約区分
	String levelCode; // お客様レベル
	String salesProgressCode; // 営業結果パタンー
	String salesPoint; // ポイント
	String specialPointConditionCode; // 特別ポイント条件
	String specialsalesPoint; // 特別ポイント

	public String getEmployeeStatus() {
		return employeeStatus;
	}

	public void setEmployeeStatus(String employeeStatus) {
		this.employeeStatus = employeeStatus;
	}

	public String getIntoCompanyCode() {
		return intoCompanyCode;
	}

	public void setIntoCompanyCode(String intoCompanyCode) {
		this.intoCompanyCode = intoCompanyCode;
	}

	public String getCustomerContractStatus() {
		return customerContractStatus;
	}

	public void setCustomerContractStatus(String customerContractStatus) {
		this.customerContractStatus = customerContractStatus;
	}

	public String getLevelCode() {
		return levelCode;
	}

	public void setLevelCode(String levelCode) {
		this.levelCode = levelCode;
	}

	public String getSalesProgressCode() {
		return salesProgressCode;
	}

	public void setSalesProgressCode(String salesProgressCode) {
		this.salesProgressCode = salesProgressCode;
	}

	public String getSalesPoint() {
		return salesPoint;
	}

	public void setSalesPoint(String salesPoint) {
		this.salesPoint = salesPoint;
	}

	public String getSpecialPointConditionCode() {
		return specialPointConditionCode;
	}

	public void setSpecialPointConditionCode(String specialPointConditionCode) {
		this.specialPointConditionCode = specialPointConditionCode;
	}

	public String getSpecialsalesPoint() {
		return specialsalesPoint;
	}

	public void setSpecialsalesPoint(String specialsalesPoint) {
		this.specialsalesPoint = specialsalesPoint;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
