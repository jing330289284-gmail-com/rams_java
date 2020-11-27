package jp.co.lyc.cms.model;

import java.io.Serializable;

public class BranchModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String bankBranchCode;
	String bankBranchName;
	String bankCode;
	String updateUser;// ログインの社員
	public String getBankBranchCode() {
		return bankBranchCode;
	}
	public void setBankBranchCode(String bankBranchCode) {
		this.bankBranchCode = bankBranchCode;
	}
	public String getBankBranchName() {
		return bankBranchName;
	}
	public void setBankBranchName(String bankBranchName) {
		this.bankBranchName = bankBranchName;
	}
	public String getBankCode() {
		return bankCode;
	}
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	public String getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	
	
}
