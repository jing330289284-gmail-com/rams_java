package jp.co.lyc.cms.model;

import java.io.Serializable;

public class PersonalSalesSearchModel implements Serializable  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String employeeFirstName;//社員名
	String employeeLastName;
	String employeeNo;//社員番号
	String fiscalYear;//年度
	String startYearAndMonth;//開始年月
	String endYearAndMonth;//終了年月
	String reflectYearAndMonth;//年月
	String employeeFormName;//社員形式
	String customerName;//所属客様
	String unitPrice;//単価
	String salary;//基本支給
	String transportationExpenses;//交通代
	String insuranceFeeAmount;//社会保険
	String scheduleOfBonusAmount;
	String leaderAllowanceAmount;//リーダー手当
	String otherAllowanceAmount;//他の手当
	String grosProfits;//粗利
	public String getEmployeeFirstName() {
		return employeeFirstName;
	}
	public void setEmployeeFirstName(String employeeFirstName) {
		this.employeeFirstName = employeeFirstName;
	}
	public String getEmployeeLastName() {
		return employeeLastName;
	}
	public void setEmployeeLastName(String employeeLastName) {
		this.employeeLastName = employeeLastName;
	}
	public String getEmployeeNo() {
		return employeeNo;
	}
	public void setEmployeeNo(String employeeNo) {
		this.employeeNo = employeeNo;
	}
	public String getFiscalYear() {
		return fiscalYear;
	}
	public void setFiscalYear(String fiscalYear) {
		this.fiscalYear = fiscalYear;
	}
	public String getStartYearAndMonth() {
		return startYearAndMonth;
	}
	public void setStartYearAndMonth(String startYearAndMonth) {
		this.startYearAndMonth = startYearAndMonth;
	}
	public String getEndYearAndMonth() {
		return endYearAndMonth;
	}
	public void setEndYearAndMonth(String endYearAndMonth) {
		this.endYearAndMonth = endYearAndMonth;
	}
	public String getReflectYearAndMonth() {
		return reflectYearAndMonth;
	}
	public void setReflectYearAndMonth(String reflectYearAndMonth) {
		this.reflectYearAndMonth = reflectYearAndMonth;
	}
	public String getEmployeeFormName() {
		return employeeFormName;
	}
	public void setEmployeeFormName(String employeeFormName) {
		this.employeeFormName = employeeFormName;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
	}
	public String getSalary() {
		return salary;
	}
	public void setSalary(String salary) {
		this.salary = salary;
	}
	public String getTransportationExpenses() {
		return transportationExpenses;
	}
	public void setTransportationExpenses(String transportationExpenses) {
		this.transportationExpenses = transportationExpenses;
	}
	public String getInsuranceFeeAmount() {
		return insuranceFeeAmount;
	}
	public void setInsuranceFeeAmount(String insuranceFeeAmount) {
		this.insuranceFeeAmount = insuranceFeeAmount;
	}
	public String getScheduleOfBonusAmount() {
		return scheduleOfBonusAmount;
	}
	public void setScheduleOfBonusAmount(String scheduleOfBonusAmount) {
		this.scheduleOfBonusAmount = scheduleOfBonusAmount;
	}
	public String getLeaderAllowanceAmount() {
		return leaderAllowanceAmount;
	}
	public void setLeaderAllowanceAmount(String leaderAllowanceAmount) {
		this.leaderAllowanceAmount = leaderAllowanceAmount;
	}
	public String getOtherAllowanceAmount() {
		return otherAllowanceAmount;
	}
	public void setOtherAllowanceAmount(String otherAllowanceAmount) {
		this.otherAllowanceAmount = otherAllowanceAmount;
	}
	public String getGrosProfits() {
		return grosProfits;
	}
	public void setGrosProfits(String grosProfits) {
		this.grosProfits = grosProfits;
	}
	

	

}
