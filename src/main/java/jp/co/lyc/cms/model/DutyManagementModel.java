package jp.co.lyc.cms.model;

import java.util.HashMap;
import java.util.Map;

public class DutyManagementModel {


	String employeeNo;
	String employeeName;
	String customerName;
	String stationName;
	String payOffRange;
	Integer workTime;
	String workTotalTimeHaveData;
	String employeeWorkTimeHaveData;
	Integer approvalStatus;
	String updateUser;
	public String getEmployeeNo() {
		return employeeNo;
	}
	public void setEmployeeNo(String employeeNo) {
		this.employeeNo = employeeNo;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getStationName() {
		return stationName;
	}
	public void setStationName(String stationName) {
		this.stationName = stationName;
	}
	public String getPayOffRange() {
		return payOffRange;
	}
	public void setPayOffRange(String payOffRange) {
		this.payOffRange = payOffRange;
	}
	public Integer getWorkTime() {
		return workTime;
	}
	public void setWorkTime(Integer workTime) {
		this.workTime = workTime;
	}
	public String getWorkTotalTimeHaveData() {
		return workTotalTimeHaveData;
	}
	public void setWorkTotalTimeHaveData(String workTotalTimeHaveData) {
		this.workTotalTimeHaveData = workTotalTimeHaveData;
	}
	public String getEmployeeWorkTimeHaveData() {
		return employeeWorkTimeHaveData;
	}
	public void setEmployeeWorkTimeHaveData(String employeeWorkTimeHaveData) {
		this.employeeWorkTimeHaveData = employeeWorkTimeHaveData;
	}
	public Integer getApprovalStatus() {
		return approvalStatus;
	}
	public void setApprovalStatus(Integer approvalStatus) {
		this.approvalStatus = approvalStatus;
	}
	public String getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
}
