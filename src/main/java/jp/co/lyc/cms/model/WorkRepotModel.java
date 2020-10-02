package jp.co.lyc.cms.model;

import org.springframework.web.multipart.MultipartFile;

public class WorkRepotModel {

	String employeeNo;
	String employeeName;
	String attendanceYearAndMonth;
	String workingTimeReport;
	String sumWorkTime;
	String updateUser;
	String updateTime;
	String approvalStatus;
	MultipartFile workRepotFile;

	public MultipartFile getWorkRepotFile() {
		return workRepotFile;
	}
	public void setWorkRepotFile(MultipartFile workRepotFile) {
		this.workRepotFile = workRepotFile;
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
	public String getAttendanceYearAndMonth() {
		return attendanceYearAndMonth;
	}
	public void setAttendanceYearAndMonth(String attendanceYearAndMonth) {
		this.attendanceYearAndMonth = attendanceYearAndMonth;
	}
	public String getWorkingTimeReport() {
		return workingTimeReport;
	}
	public void setWorkingTimeReport(String workingTimeReport) {
		this.workingTimeReport = workingTimeReport;
	}
	public String getSumWorkTime() {
		return sumWorkTime;
	}
	public void setSumWorkTime(String sumWorkTime) {
		this.sumWorkTime = sumWorkTime;
	}
	public String getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public String getApprovalStatus() {
		return approvalStatus;
	}
	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}
	
}
