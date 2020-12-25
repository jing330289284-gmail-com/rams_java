package jp.co.lyc.cms.model;

import java.io.Serializable;

public class CustomerEmployeeDetail implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String employeeName;
	String siteRoleName;
	String unitPrice;
	String stationName;
	String employeeNo;
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getSiteRoleName() {
		return siteRoleName;
	}
	public void setSiteRoleName(String siteRoleName) {
		this.siteRoleName = siteRoleName;
	}
	public String getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
	}
	public String getStationName() {
		return stationName;
	}
	public void setStationName(String stationName) {
		this.stationName = stationName;
	}
	public String getEmployeeNo() {
		return employeeNo;
	}
	public void setEmployeeNo(String employeeNo) {
		this.employeeNo = employeeNo;
	}
	
}
