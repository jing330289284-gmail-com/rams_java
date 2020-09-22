package jp.co.lyc.cms.model;

import jp.co.lyc.cms.common.BaseController;

public class ExpensesInfoModel extends BaseController{
	
	String employeeNo;
	String expensesReflectYearAndMonth;
	String updateExpensesReflectYearAndMonth;
	String transportationExpenses;
	String otherAllowanceName;
	String otherAllowanceAmount;
	String leaderAllowanceAmount;
	String housingStatus;
	String housingAllowance;
	String actionType;
	String updateUser;
	
	public String getActionType() {
		return actionType;
	}
	public void setActionType(String actionType) {
		this.actionType = actionType;
	}
	public String getUpdateExpensesReflectYearAndMonth() {
		return updateExpensesReflectYearAndMonth;
	}
	public void setUpdateExpensesReflectYearAndMonth(String updateExpensesReflectYearAndMonth) {
		this.updateExpensesReflectYearAndMonth = updateExpensesReflectYearAndMonth;
	}
	public String getEmployeeNo() {
		return employeeNo;
	}
	public void setEmployeeNo(String employeeNo) {
		this.employeeNo = employeeNo;
	}
	public String getExpensesReflectYearAndMonth() {
		return expensesReflectYearAndMonth;
	}
	public void setExpensesReflectYearAndMonth(String expensesReflectYearAndMonth) {
		this.expensesReflectYearAndMonth = expensesReflectYearAndMonth;
	}
	public String getTransportationExpenses() {
		return transportationExpenses;
	}
	public void setTransportationExpenses(String transportationExpenses) {
		this.transportationExpenses = transportationExpenses;
	}
	public String getOtherAllowanceName() {
		return otherAllowanceName;
	}
	public void setOtherAllowanceName(String otherAllowanceName) {
		this.otherAllowanceName = otherAllowanceName;
	}
	public String getOtherAllowanceAmount() {
		return otherAllowanceAmount;
	}
	public void setOtherAllowanceAmount(String otherAllowanceAmount) {
		this.otherAllowanceAmount = otherAllowanceAmount;
	}
	public String getLeaderAllowanceAmount() {
		return leaderAllowanceAmount;
	}
	public void setLeaderAllowanceAmount(String leaderAllowanceAmount) {
		this.leaderAllowanceAmount = leaderAllowanceAmount;
	}
	public String getHousingStatus() {
		return housingStatus;
	}
	public void setHousingStatus(String housingStatus) {
		this.housingStatus = housingStatus;
	}
	public String getHousingAllowance() {
		return housingAllowance;
	}
	public void setHousingAllowance(String housingAllowance) {
		this.housingAllowance = housingAllowance;
	}
	public String getUpdateUser() {
		return getSession().getAttribute("employeeName").toString();
	}
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}	
}
