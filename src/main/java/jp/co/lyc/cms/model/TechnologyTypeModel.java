package jp.co.lyc.cms.model;

import java.io.Serializable;

public class TechnologyTypeModel implements Serializable {

	private static final long serialVersionUID = -2028159323401651353L;

	String technologytypeCode;//コード
	String technologytypeName;//名前
	String updateUser;//ログインの社員
	public String getTechnologytypeCode() {
		return technologytypeCode;
	}
	public void setTechnologytypeCode(String technologytypeCode) {
		this.technologytypeCode = technologytypeCode;
	}
	public String getTechnologytypeName() {
		return technologytypeName;
	}
	public void setTechnologytypeName(String technologytypeName) {
		this.technologytypeName = technologytypeName;
	}
	public String getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	
	
}
