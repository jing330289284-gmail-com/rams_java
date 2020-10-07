package jp.co.lyc.cms.model;

public class LoginEmployeeModel {
	public String employeeNo;//社員番号
	public String password;//パスワード
	public String phoneNo;//电话号码
	public String passwordResetId;//パスワードリセットID
	
	public String getPasswordResetId() {
		return passwordResetId;
	}
	public void setPasswordResetId(String passwordResetId) {
		this.passwordResetId = passwordResetId;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getEmployeeNo() {
		return employeeNo;
	}
	public void setEmployeeNo(String employeeNo) {
		this.employeeNo = employeeNo;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
