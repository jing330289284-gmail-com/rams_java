package jp.co.lyc.cms.model;

import java.io.Serializable;

public class CustomerTopModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String topCustomerName;
	String topCustomerAbbreviation;
	String url;
	String updateUser;// ログインの社員
	
	
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
	public String getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	
	
}
