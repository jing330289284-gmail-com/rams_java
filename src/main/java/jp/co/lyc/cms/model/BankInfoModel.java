package jp.co.lyc.cms.model;

import java.util.ArrayList;

public class BankInfoModel {
	
	String employeeOrCustomerNo;
	String accountBelongsStatus;
	String bankCode;
	String bankBranchCode;
	String bankBranchName;
	String accountNo;
	String accountName;
	String accountTypeStatus;
	String shoriKbn;
	ArrayList<String> bankName;
	String updateUser;
	
	
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
	public void setUpdateuser(String updateUser) {
		this.updateUser = updateUser;
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
	public String getShoriKbn() {
		return shoriKbn;
	}
	public void setShoriKbn(String shoriKbn) {
		this.shoriKbn = shoriKbn;
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
