package jp.co.lyc.cms.model;

import java.io.Serializable;
import java.util.Date;

public class SalesContent implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2028159323401651353L;
	String employeeNo;
	public String getEmployeeNo() {
		return employeeNo;
	}

	public void setEmployeeNo(String employeeNo) {
		this.employeeNo = employeeNo;
	}

	String stationCode;
	String age;  
	String japaneaseConversationLevel;
	String englishConversationLevel;
	String yearsOfExperience;
	String projectPhaseCode;
	String developLanguageCode6;
	String developLanguageCode7;
	String developLanguageCode8;
	String developLanguageCode9;
	String developLanguageCode10; 
	String unitPrice;
	String remark;
	Date createTime;
	Date updateTime;
	String updateUser;
	
	public String getStationCode() {
		return stationCode;
	}

	public void setStationCode(String stationCode) {
		this.stationCode = stationCode;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getJapaneaseConversationLevel() {
		return japaneaseConversationLevel;
	}

	public void setJapaneaseConversationLevel(String japaneaseConversationLevel) {
		this.japaneaseConversationLevel = japaneaseConversationLevel;
	}

	public String getEnglishConversationLevel() {
		return englishConversationLevel;
	}

	public void setEnglishConversationLevel(String englishConversationLevel) {
		this.englishConversationLevel = englishConversationLevel;
	}

	public String getYearsOfExperience() {
		return yearsOfExperience;
	}

	public void setYearsOfExperience(String yearsOfExperience) {
		this.yearsOfExperience = yearsOfExperience;
	}

	public String getProjectPhaseCode() {
		return projectPhaseCode;
	}

	public void setProjectPhaseCode(String projectPhaseCode) {
		this.projectPhaseCode = projectPhaseCode;
	}

	public String getDevelopLanguageCode6() {
		return developLanguageCode6;
	}

	public void setDevelopLanguageCode6(String developLanguageCode6) {
		this.developLanguageCode6 = developLanguageCode6;
	}

	public String getDevelopLanguageCode7() {
		return developLanguageCode7;
	}

	public void setDevelopLanguageCode7(String developLanguageCode7) {
		this.developLanguageCode7 = developLanguageCode7;
	}

	public String getDevelopLanguageCode8() {
		return developLanguageCode8;
	}

	public void setDevelopLanguageCode8(String developLanguageCode8) {
		this.developLanguageCode8 = developLanguageCode8;
	}

	public String getDevelopLanguageCode9() {
		return developLanguageCode9;
	}

	public void setDevelopLanguageCode9(String developLanguageCode9) {
		this.developLanguageCode9 = developLanguageCode9;
	}

	public String getDevelopLanguageCode10() {
		return developLanguageCode10;
	}

	public void setDevelopLanguageCode10(String developLanguageCode10) {
		this.developLanguageCode10 = developLanguageCode10;
	}

	public String getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}