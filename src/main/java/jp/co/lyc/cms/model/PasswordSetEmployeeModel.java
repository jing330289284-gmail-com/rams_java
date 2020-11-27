package jp.co.lyc.cms.model;

public class PasswordSetEmployeeModel {

	String oldPassword;//既存パスワード
	String newPassword;//新しいパスワード
	String passwordCheck;//パスワード再確認
	
	public String getPasswordCheck() {
		return passwordCheck;
	}
	public void setPasswordCheck(String passwordCheck) {
		this.passwordCheck = passwordCheck;
	}
	public String getOldPassword() {
		return oldPassword;
	}
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	
	
}
