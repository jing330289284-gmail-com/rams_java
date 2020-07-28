package jp.co.lyc.cms.model;

import java.io.Serializable;

public class IntoCompanyModel implements Serializable {

	private static final long serialVersionUID = -2028159323401651353L;

	String intoCompanyCode;//コード
	String intoCompanyName;//名前
	public String getIntoCompanyCode() {
		return intoCompanyCode;
	}
	public void setIntoCompanyCode(String intoCompanyCode) {
		this.intoCompanyCode = intoCompanyCode;
	}
	public String getIntoCompanyName() {
		return intoCompanyName;
	}
	public void setIntoCompanyName(String intoCompanyName) {
		this.intoCompanyName = intoCompanyName;
	}
	

	

}
