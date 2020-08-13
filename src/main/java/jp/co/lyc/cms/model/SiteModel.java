package jp.co.lyc.cms.model;

import java.io.Serializable;

public class SiteModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2028159323401651353L;
	String employeeNo;
	String systemName;
	String location;
	String customerNo;
	String topCustomerNo;
	String developLanguageCode;
	String unitPrice;
	String developLanguage;
	String siteRoleCode;
	String siteManager;
	String admissionStartDate;
	String admissionEndDate;
	String payOffRange1;
	String payOffRange2;
	String workDate;
	String customerName;
	String topCustomerName;
	String siteRoleName;
	String related1Employees;
	String related2Employees;
	String related3Employees;
	String levelCode;
	String updateUser;
	
	public String getEmployeeNo() {
		return employeeNo;
	}
	public void setEmployeeNo(String employeeNo) {
		this.employeeNo = employeeNo;
	}
	public String getSystemName() {
		return systemName;
	}
	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getCustomerNo() {
		return customerNo;
	}
	public void setCustomerNo(String customerNo) {
		this.customerNo = customerNo;
	}
	public String getTopCustomerNo() {
		return topCustomerNo;
	}
	public void setTopCustomerNo(String topCustomerNo) {
		this.topCustomerNo = topCustomerNo;
	}
	public String getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
	}
	public String getDevelopLanguage() {
		return developLanguage;
	}
	public void setDevelopLanguage(String developLanguage) {
		this.developLanguage = developLanguage;
	}
	public String getSiteRoleCode() {
		return siteRoleCode;
	}
	public void setSiteRoleCode(String siteRoleCode) {
		this.siteRoleCode = siteRoleCode;
	}
	public String getSiteManager() {
		return siteManager;
	}
	public void setSiteManager(String siteManager) {
		this.siteManager = siteManager;
	}
	public String getAdmissionStartDate() {
		return admissionStartDate;
	}
	public void setAdmissionStartDate(String admissionStartDate) {
		this.admissionStartDate = admissionStartDate;
	}
	public String getAdmissionEndDate() {
		return admissionEndDate;
	}
	public void setAdmissionEndDate(String admissionEndDate) {
		this.admissionEndDate = admissionEndDate;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getWorkDate() {
		return workDate;
	}
	public void setWorkDate(String workDate) {
		this.workDate = workDate;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getTopCustomerName() {
		return topCustomerName;
	}
	public void setTopCustomerName(String topCustomerName) {
		this.topCustomerName = topCustomerName;
	}
	public String getSiteRoleName() {
		return siteRoleName;
	}
	public void setSiteRoleName(String siteRoleName) {
		this.siteRoleName = siteRoleName;
	}
	public String getLevelCode() {
		return levelCode;
	}
	public void setLevelCode(String levelCode) {
		this.levelCode = levelCode;
	}
	public String getPayOffRange1() {
		return payOffRange1;
	}
	public void setPayOffRange1(String payOffRange1) {
		this.payOffRange1 = payOffRange1;
	}
	public String getPayOffRange2() {
		return payOffRange2;
	}
	public void setPayOffRange2(String payOffRange2) {
		this.payOffRange2 = payOffRange2;
	}
	public String getDevelopLanguageCode() {
		return developLanguageCode;
	}
	public void setDevelopLanguageCode(String developLanguageCode) {
		this.developLanguageCode = developLanguageCode;
	}
	public String getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	public String getRelated1Employees() {
		return related1Employees;
	}
	public void setRelated1Employees(String related1Employees) {
		this.related1Employees = related1Employees;
	}
	public String getRelated2Employees() {
		return related2Employees;
	}
	public void setRelated2Employees(String related2Employees) {
		this.related2Employees = related2Employees;
	}
	public String getRelated3Employees() {
		return related3Employees;
	}
	public void setRelated3Employees(String related3Employees) {
		this.related3Employees = related3Employees;
	}
	

}
