package jp.co.lyc.cms.model;

import java.io.Serializable;

public class VisaModel implements Serializable {

	private static final long serialVersionUID = -2028159323401651353L;

	String visaCode;// コード
	String visaName;// 名前

	public String getVisaCode() {
		return visaCode;
	}

	public void setVisaCode(String visaCode) {
		this.visaCode = visaCode;
	}

	public String getVisaName() {
		return visaName;
	}

	public void setVisaName(String visaName) {
		this.visaName = visaName;
	}

}
