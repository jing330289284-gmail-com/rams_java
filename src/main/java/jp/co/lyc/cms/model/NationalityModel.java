package jp.co.lyc.cms.model;

import java.io.Serializable;

public class NationalityModel implements Serializable {

	private static final long serialVersionUID = -2028159323401651353L;

	String nationalityCode;// コード
	String nationalityName;// 名前

	public String getNationalityCode() {
		return nationalityCode;
	}

	public void setNationalityCode(String nationalityCode) {
		this.nationalityCode = nationalityCode;
	}

	public String getNationalityName() {
		return nationalityName;
	}

	public void setNationalityName(String nationalityName) {
		this.nationalityName = nationalityName;
	}

}
