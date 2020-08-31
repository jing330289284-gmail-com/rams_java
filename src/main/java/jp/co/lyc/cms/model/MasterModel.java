package jp.co.lyc.cms.model;

import java.io.Serializable;

public class MasterModel implements Serializable {

	private static final long serialVersionUID = -2028159323401651353L;

	String master;// マスターテーブル名
	String data;// データ
	String code;// 番号
	String columnCode;// code名
	String columnName;// name名
	String updateUser;// ログインの社員

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
