package jp.co.lyc.cms.model;

import org.springframework.web.multipart.MultipartFile;

public class ResumeModel {
	int rowNo;
	String employeeNo;
	String employeeName;
	String resumeInfo1;
	String resumeName1;
	String resumeInfo2;
	String resumeName2;
	String updateUser;
	String updateTime;
	MultipartFile resumeFile;
	public int getRowNo() {
		return rowNo;
	}
	public void setRowNo(int rowNo) {
		this.rowNo = rowNo;
	}
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
	public String getResumeInfo1() {
		return resumeInfo1;
	}
	public void setResumeInfo1(String resumeInfo1) {
		this.resumeInfo1 = resumeInfo1;
	}
	public String getResumeName1() {
		return resumeName1;
	}
	public void setResumeName1(String resumeName1) {
		this.resumeName1 = resumeName1;
	}
	public String getResumeInfo2() {
		return resumeInfo2;
	}
	public void setResumeInfo2(String resumeInfo2) {
		this.resumeInfo2 = resumeInfo2;
	}
	public String getResumeName2() {
		return resumeName2;
	}
	public void setResumeName2(String resumeName2) {
		this.resumeName2 = resumeName2;
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
	public MultipartFile getResumeFile() {
		return resumeFile;
	}
	public void setResumeFile(MultipartFile resumeFile) {
		this.resumeFile = resumeFile;
	}
	
}
