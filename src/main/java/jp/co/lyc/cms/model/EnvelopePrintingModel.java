package jp.co.lyc.cms.model;

import java.util.Date;

public class EnvelopePrintingModel {

	/**
	 *
	 */
	private static final long serialVersionUID = -2028159323401651353L;

	String companyData; // 会社情報
	String employeeName; // 社員名前
	String employeeNo; // 社員番号
	String postcode; // 郵便番号
	String firstHalfAddress; // 前半住所
	String lastHalfAddress; // 後半住所

	public String getCompanyData() {
		return companyData;
	}

	public void setCompanyData(String companyData) {
		this.companyData = companyData;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
