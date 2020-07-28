package jp.co.lyc.cms.model;

import java.io.Serializable;

public class JapaneseLevelModel implements Serializable {

	private static final long serialVersionUID = -2028159323401651353L;

	String japaneseLevelCode;//コード
	String japaneseLevelName;//名前
	public String getJapaneseLevelCode() {
		return japaneseLevelCode;
	}
	public void setJapaneseLevelCode(String japaneseLevelCode) {
		this.japaneseLevelCode = japaneseLevelCode;
	}
	public String getJapaneseLevelName() {
		return japaneseLevelName;
	}
	public void setJapaneseLevelName(String japaneseLevelName) {
		this.japaneseLevelName = japaneseLevelName;
	}

	

}
