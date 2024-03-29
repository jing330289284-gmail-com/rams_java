package jp.co.lyc.cms.model;

import java.util.ArrayList;

public class AccountInfoModel {
	
	String employeeOrCustomerNo;//社員番号かお客様番号
	String accountBelongsStatus;//ステータス
	String bankCode;//銀行コード
	String bankBranchCode;//支店コード
	String bankBranchName;
	String accountNo;//口座番号
	String accountName;//口座名義人
	String accountTypeStatus;//口座種類ステータス
	String actionType;//処理区分
	ArrayList<String> bankName;//銀行名
	String updateUser;//更新者
	
	
	public String getEmployeeOrCustomerNo() {
		return employeeOrCustomerNo;
	}
	public void setEmployeeOrCustomerNo(String employeeOrCustomerNo) {
		this.employeeOrCustomerNo = employeeOrCustomerNo;
	}
	public String getAccountBelongsStatus() {
		return accountBelongsStatus;
	}
	public void setAccountBelongsStatus(String accountBelongsStatus) {
		this.accountBelongsStatus = accountBelongsStatus;
	}
	public String getAccountTypeStatus() {
		return accountTypeStatus;
	}
	public void setAccountTypeStatus(String accountTypeStatus) {
		this.accountTypeStatus = accountTypeStatus;
	}
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	public String getUpdateUser() {
		return updateUser;
	}
	public String getBankBranchName() {
		return bankBranchName;
	}
	public void setBankBranchName(String bankBranchName) {
		this.bankBranchName = bankBranchName;
	}
	public ArrayList<String> getBankName() {
		return bankName;
	}
	public void setBankName(ArrayList<String> bankName) {
		this.bankName = bankName;
	}
	
	public String getActionType() {
		return actionType;
	}
	public void setActionType(String actionType) {
		this.actionType = actionType;
	}
	public String getBankCode() {
		return bankCode;
	}
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	public String getBankBranchCode() {
		return bankBranchCode;
	}
	public void setBankBranchCode(String bankBranchCode) {
		this.bankBranchCode = bankBranchCode;
	}
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	
}
