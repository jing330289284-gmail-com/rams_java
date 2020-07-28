package jp.co.lyc.cms.model;

import java.io.Serializable;

public class StaffModel implements Serializable {

	private static final long serialVersionUID = -2028159323401651353L;

	String staffFormCode;//社員形式コード
	String staffFormName;//社員形式名前

	public String getStaffFormCode() {
		return staffFormCode;
	}

	public void setStaffFormCode(String staffFormCode) {
		this.staffFormCode = staffFormCode;
	}

	public String getStaffFormName() {
		return staffFormName;
	}

	public void setStaffFormName(String staffFormName) {
		this.staffFormName = staffFormName;
	}

}
