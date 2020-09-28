package jp.co.lyc.cms.model;

import java.io.Serializable;

public class SendLettersConfirmModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2028159323401651353L;
	
	String employeeNo;
	String employeeName;
	String salesYearAndMonth;
	String hopeHighestPrice;
	String salesProgressCode;
	String remark;
	String updateUser;
	String employeeStatus;
	String stationName;
	String nearestStation;
	String developLanguage; 
	String genderStatus;
	String nationalityName;
	String birthday;
	String yearsOfExperience;
	String japaneseLevelName;
	String beginMonth;
	String[] employeeNos;

	int rowNo;// 
	
	
	public String[] getEmployeeNos() {
		return employeeNos;
	}

	public void setEmployeeNos(String[] employeeNos) {
		this.employeeNos = employeeNos;
	}


	public String getEmployeeNo() {
		return employeeNo;
	}

	public void setEmployeeNo(String employeeNo) {
		this.employeeNo = employeeNo;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getSalesYearAndMonth() {
		return salesYearAndMonth;
	}

	public void setSalesYearAndMonth(String salesYearAndMonth) {
		this.salesYearAndMonth = salesYearAndMonth;
	}

	public String getHopeHighestPrice() {
		return hopeHighestPrice;
	}

	public void setHopeHighestPrice(String hopeHighestPrice) {
		this.hopeHighestPrice = hopeHighestPrice;
	}

	public String getSalesProgressCode() {
		return salesProgressCode;
	}

	public void setSalesProgressCode(String salesProgressCode) {
		this.salesProgressCode = salesProgressCode;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public String getEmployeeStatus() {
		return employeeStatus;
	}

	public void setEmployeeStatus(String employeeStatus) {
		this.employeeStatus = employeeStatus;
	}

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	public String getNearestStation() {
		return nearestStation;
	}

	public void setNearestStation(String nearestStation) {
		this.nearestStation = nearestStation;
	}

	public String getDevelopLanguage() {
		return developLanguage;
	}

	public void setDevelopLanguage(String developLanguage) {
		this.developLanguage = developLanguage;
	}

	public String getGenderStatus() {
		return genderStatus;
	}

	public void setGenderStatus(String genderStatus) {
		this.genderStatus = genderStatus;
	}

	public String getNationalityName() {
		return nationalityName;
	}

	public void setNationalityName(String nationalityName) {
		this.nationalityName = nationalityName;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getYearsOfExperience() {
		return yearsOfExperience;
	}

	public void setYearsOfExperience(String yearsOfExperience) {
		this.yearsOfExperience = yearsOfExperience;
	}

	public String getJapaneseLevelName() {
		return japaneseLevelName;
	}

	public void setJapaneseLevelName(String japaneseLevelName) {
		this.japaneseLevelName = japaneseLevelName;
	}

	public String getBeginMonth() {
		return beginMonth;
	}

	public void setBeginMonth(String beginMonth) {
		this.beginMonth = beginMonth;
	}

	public int getRowNo() {
		return rowNo;
	}

	public void setRowNo(int rowNo) {
		this.rowNo = rowNo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	
}
