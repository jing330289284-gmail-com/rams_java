package jp.co.lyc.cms.model;

import java.util.Date;

public class PasswordResetModel {

	String passwordResetId;//パスワードリセットID
	String IdForEmployeeNo;//パスワードリセットID対応の社員番号
	Date idCreateTime;//パスワードリセットID
	String password;//新パスワード
	String updateUser;//更新者
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	public String getPasswordResetId() {
		return passwordResetId;
	}
	public void setPasswordResetId(String passwordResetId) {
		this.passwordResetId = passwordResetId;
	}
	public String getIdForEmployeeNo() {
		return IdForEmployeeNo;
	}
	public void setIdForEmployeeNo(String idForEmployeeNo) {
		IdForEmployeeNo = idForEmployeeNo;
	}
	public Date getIdCreateTime() {
		return idCreateTime;
	}
	public void setIdCreateTime(Date idCreateTime) {
		this.idCreateTime = idCreateTime;
	}
	
	
}
