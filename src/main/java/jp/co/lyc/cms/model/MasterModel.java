package jp.co.lyc.cms.model;

import java.io.Serializable;

public class MasterModel implements Serializable {

	private static final long serialVersionUID = -2028159323401651353L;

	String master;// マスターテーブル名
	String data;// データ
	String code;// 番号
	String columnCode;// code名
	String columnName;// name名
	String bankBranchCode;
	String bankBranchName;
	String newBankBranchCode;
	String newBankBranchName;
	String bankCode;
	String topCustomerNo;
	String topCustomerName;
	String topCustomerAbbreviation;
	String url;
	String updateUser;// ログインの社員
	int row;
	int codeNo;

	public int getCodeNo() {
		return codeNo;
	}

	public void setCodeNo(int codeNo) {
		this.codeNo = codeNo;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getTopCustomerNo() {
		return topCustomerNo;
	}

	public void setTopCustomerNo(String topCustomerNo) {
		this.topCustomerNo = topCustomerNo;
	}

	public String getTopCustomerName() {
		return topCustomerName;
	}

	public void setTopCustomerName(String topCustomerName) {
		this.topCustomerName = topCustomerName;
	}

	public String getTopCustomerAbbreviation() {
		return topCustomerAbbreviation;
	}

	public void setTopCustomerAbbreviation(String topCustomerAbbreviation) {
		this.topCustomerAbbreviation = topCustomerAbbreviation;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getNewBankBranchCode() {
		return newBankBranchCode;
	}

	public void setNewBankBranchCode(String newBankBranchCode) {
		this.newBankBranchCode = newBankBranchCode;
	}

	public String getNewBankBranchName() {
		return newBankBranchName;
	}

	public void setNewBankBranchName(String newBankBranchName) {
		this.newBankBranchName = newBankBranchName;
	}

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

	public String getMaster() {
		return master;
	}

	public void setMaster(String master) {
		this.master = master;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getColumnCode() {
		return columnCode;
	}

	public void setColumnCode(String columnCode) {
		this.columnCode = columnCode;
	}

}
