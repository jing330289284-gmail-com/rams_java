package jp.co.lyc.cms.model;

import java.io.Serializable;
import java.util.Date;

public class SalesSituationModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2028159323401651353L;
	
	String employeeNo;
	String salesYearAndMonth;
	String interviewDate1;
	String stationCode1;
	String interviewCustomer1;
	String interviewDate2;
	String stationCode2;
	String interviewCustomer2;
	String hopeLowestPrice;
	String hopeHighestPrice;
	String salesProgressCode;
	String remark;
	String salesStaff;
	Date updateTime;
	Date createTime;
	String updateUser;
	
	String employeeName;
	String nearestStation;
	String developLanguage; 
	String siteRoleCode;
	String unitPrice;
	String salesPriorityStatus;
	String costumer;
	
	public String getCostumer() {
		return costumer;
	}
	public void setCostumer(String costumer) {
		this.costumer = costumer;
	}
	
	
	public String getSalesPriorityStatus() {
		return salesPriorityStatus;
	}
	public void setSalesPriorityStatus(String salesPriorityStatus) {
		this.salesPriorityStatus = salesPriorityStatus;
	}
	
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getNearestStation() {
		return nearestStation;
	}
	public void setNearestStation(String nearestStation) {
		this.nearestStation = nearestStation;
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
	public String getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
	}
	public String getEmployeeNo() {
		return employeeNo;
	}
	public void setEmployeeNo(String employeeNo) {
		this.employeeNo = employeeNo;
	}
	public String getSalesYearAndMonth() {
		return salesYearAndMonth;
	}
	public void setSalesYearAndMonth(String salesYearAndMonth) {
		this.salesYearAndMonth = salesYearAndMonth;
	}
	public String getInterviewDate1() {
		return interviewDate1;
	}
	public void setInterviewDate1(String interviewDate1) {
		this.interviewDate1 = interviewDate1;
	}
	public String getStationCode1() {
		return stationCode1;
	}
	public void setStationCode1(String stationCode1) {
		this.stationCode1 = stationCode1;
	}
	public String getInterviewCustomer1() {
		return interviewCustomer1;
	}
	public void setInterviewCustomer1(String interviewCustomer1) {
		this.interviewCustomer1 = interviewCustomer1;
	}
	public String getInterviewDate2() {
		return interviewDate2;
	}
	public void setInterviewDate2(String interviewDate2) {
		this.interviewDate2 = interviewDate2;
	}
	public String getStationCode2() {
		return stationCode2;
	}
	public void setStationCode2(String stationCode2) {
		this.stationCode2 = stationCode2;
	}
	public String getInterviewCustomer2() {
		return interviewCustomer2;
	}
	public void setInterviewCustomer2(String interviewCustomer2) {
		this.interviewCustomer2 = interviewCustomer2;
	}
	public String getHopeLowestPrice() {
		return hopeLowestPrice;
	}
	public void setHopeLowestPrice(String hopeLowestPrice) {
		this.hopeLowestPrice = hopeLowestPrice;
	}
	public String getHopeHighestPrice() {
		return hopeHighestPrice;
	}
	public void setHopeHighestPrice(String hopeHighestPrice) {
		this.hopeHighestPrice = hopeHighestPrice;
	}
	public String getSalesProgressCode() {
		return salesProgressCode;
	}
	public void setSalesProgressCode(String salesProgressCode) {
		this.salesProgressCode = salesProgressCode;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getSalesStaff() {
		return salesStaff;
	}
	public void setSalesStaff(String salesStaff) {
		this.salesStaff = salesStaff;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
