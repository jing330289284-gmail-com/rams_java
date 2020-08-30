package jp.co.lyc.cms.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SessionEmployeeModel implements Serializable {

	private static final long serialVersionUID = -7670202209084458561L;
	String sessionEmpid;
	String sessionEmpNo;
	String sessionEmpName;
	String sessionPassword;
	String sessionAuthority;
	
	public String getSessionEmpid() {
		return sessionEmpid;
	}
	public void setSessionEmpid(String sessionEmpid) {
		this.sessionEmpid = sessionEmpid;
	}
	public String getSessionEmpNo() {
		return sessionEmpNo;
	}
	public void setSessionEmpNo(String sessionEmpNo) {
		this.sessionEmpNo = sessionEmpNo;
	}
	public String getSessionEmpName() {
		return sessionEmpName;
	}
	public void setSessionEmpName(String sessionEmpName) {
		this.sessionEmpName = sessionEmpName;
	}
	public String getSessionPassword() {
		return sessionPassword;
	}
	public void setSessionPassword(String sessionPassword) {
		this.sessionPassword = sessionPassword;
	}
	public String getSessionAuthority() {
		return sessionAuthority;
	}
	public void setSessionAuthority(String sessionAuthority) {
		this.sessionAuthority = sessionAuthority;
	}
	
}
