package jp.co.lyc.cms.model;

import java.io.Serializable;

public class ModelClass implements Serializable {

	private static final long serialVersionUID = -2028159323401651353L;

	String code;
	String name;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
