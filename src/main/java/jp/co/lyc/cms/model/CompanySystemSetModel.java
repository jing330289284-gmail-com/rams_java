package jp.co.lyc.cms.model;

import java.io.Serializable;

public class CompanySystemSetModel implements Serializable {

	private static final long serialVersionUID = -2028159323401651353L;

	String companyName;// 名前
	String companyLogo;// logo
	String backgroundColor;// 背景色
	String empNoHead;// 社員番号の頭

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyLogo() {
		return companyLogo;
	}

	public void setCompanyLogo(String companyLogo) {
		this.companyLogo = companyLogo;
	}

	public String getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(String backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	public String getEmpNoHead() {
		return empNoHead;
	}

	public void setEmpNoHead(String empNoHead) {
		this.empNoHead = empNoHead;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
