package jp.co.lyc.cms.model;

import java.util.HashMap;
import java.util.Map;

public class EmployeeWorkTimeModel {

	String employeeNo;
	String yearAndMonth;
	String day;
	String week;
	String morningTime;
	String afternoonTime;
	String holidayFlag;
	String workTime;
	String confirmFlag;
	String siteCustomer;
	String customer;
	String siteResponsiblePerson;
	String systemName;
	String createTime;
	String updateTime;
	String updateUser;

	public String getEmployeeNo() {
		return employeeNo;
	}
	public void setEmployeeNo(String employeeNo) {
		this.employeeNo = employeeNo;
	}
	public String getYearAndMonth() {
		return yearAndMonth;
	}
	public void setYearAndMonth(String yearAndMonth) {
		this.yearAndMonth = yearAndMonth;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public String getWeek() {
		return week;
	}
	public void setWeek(String week) {
		this.week = week;
	}
	public String getMorningTime() {
		return morningTime;
	}
	public void setMorningTime(String morningTime) {
		this.morningTime = morningTime.replace(":", "");
	}
	public String getAfternoonTime() {
		return afternoonTime;
	}
	public void setAfternoonTime(String afternoonTime) {
		this.afternoonTime = afternoonTime.replace(":", "");
	}
	public String getHolidayFlag() {
		return holidayFlag;
	}
	public void setHolidayFlag(String holidayFlag) {
		this.holidayFlag = holidayFlag;
	}
	public String getWorkTime() {
		return workTime;
	}
	public void setWorkTime(String workTime) {
		this.workTime = workTime;
	}
	public String getConfirmFlag() {
		return confirmFlag;
	}
	public void setConfirmFlag(String confirmFlag) {
		this.confirmFlag = confirmFlag;
	}
	public String getSiteCustomer() {
		return siteCustomer;
	}
	public void setSiteCustomer(String siteCustomer) {
		this.siteCustomer = siteCustomer;
	}
	public String getCustomer() {
		return customer;
	}
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	public String getSiteResponsiblePerson() {
		return siteResponsiblePerson;
	}
	public void setSiteResponsiblePerson(String siteResponsiblePerson) {
		this.siteResponsiblePerson = siteResponsiblePerson;
	}
	public String getSystemName() {
		return systemName;
	}
	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public String getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public Map<String, Object> toHashMap() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("employeeNo", this.getEmployeeNo());
		map.put("yearAndMonth", this.getYearAndMonth());
		map.put("day", this.getDay());
		map.put("week", this.getWeek());
		map.put("morningTime", this.getMorningTime());
		map.put("afternoonTime", this.getAfternoonTime());
		map.put("holidayFlag", this.getHolidayFlag());
		map.put("workTime", this.getWorkTime());
		map.put("confirmFlag", this.getConfirmFlag());
		map.put("siteCustomer", this.getSiteCustomer());
		map.put("customer", this.getCustomer());
		map.put("siteResponsiblePerson", this.getSiteResponsiblePerson());
		map.put("systemName", this.getSystemName());
		map.put("createTime", this.getCreateTime());
		map.put("updateTime", this.getUpdateTime());
		map.put("updateUser", this.getUpdateUser());
		return map;
	}
	public static EmployeeWorkTimeModel fromHashMap (Map<String, Object> map)	{
		EmployeeWorkTimeModel employeeWorkTimeModel = new EmployeeWorkTimeModel();
		employeeWorkTimeModel.setEmployeeNo(String.valueOf(map.get("employeeNo")));
		employeeWorkTimeModel.setYearAndMonth(String.valueOf(map.get("yearAndMonth")));
		employeeWorkTimeModel.setDay(String.valueOf(map.get("day")));
		employeeWorkTimeModel.setWeek(String.valueOf(map.get("week")));
		employeeWorkTimeModel.setMorningTime(String.valueOf(map.get("morningTime")));
		employeeWorkTimeModel.setAfternoonTime(String.valueOf(map.get("afternoonTime")));
		employeeWorkTimeModel.setHolidayFlag(String.valueOf(map.get("holidayFlag")));
		try {
			Integer.valueOf(String.valueOf(map.get("workTime")));
			employeeWorkTimeModel.setWorkTime(String.valueOf(map.get("workTime")));
		} catch (Exception e)	{
			employeeWorkTimeModel.setWorkTime("0");
		}
		try {
			Integer.valueOf(String.valueOf(map.get("confirmFlag")));
			employeeWorkTimeModel.setConfirmFlag(String.valueOf(map.get("confirmFlag")));
		} catch (Exception e)	{
			employeeWorkTimeModel.setConfirmFlag("0");
		}
		employeeWorkTimeModel.setSiteCustomer(String.valueOf(map.get("siteCustomer")));
		employeeWorkTimeModel.setCustomer(String.valueOf(map.get("customer")));
		employeeWorkTimeModel.setSiteResponsiblePerson(String.valueOf(map.get("siteResponsiblePerson")));
		employeeWorkTimeModel.setSystemName(String.valueOf(map.get("systemName")));
		employeeWorkTimeModel.setCreateTime(String.valueOf(map.get("createTime")));
		employeeWorkTimeModel.setUpdateTime(String.valueOf(map.get("updateTime")));
		employeeWorkTimeModel.setUpdateUser(String.valueOf(map.get("updateUser")));
		return employeeWorkTimeModel;
	}
	
	@Override
	public String toString() {
		return "EmployeeWorkTimeModel [employeeNo=" + employeeNo + ", yearAndMonth=" + yearAndMonth + ", day=" + day
				+ ", week=" + week + ", morningTime=" + morningTime + ", afternoonTime=" + afternoonTime
				+ ", holidayFlag=" + holidayFlag + ", workTime=" + workTime + ", confirmFlag=" + confirmFlag
				+ ", siteCustomer=" + siteCustomer + ", customer=" + customer + ", siteResponsiblePerson="
				+ siteResponsiblePerson + ", systemName=" + systemName + ", createTime=" + createTime + ", updateTime="
				+ updateTime + ", updateUser=" + updateUser + "]";
	}
}
