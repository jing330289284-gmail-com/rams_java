package jp.co.lyc.cms.model;

public class ExpensesInfoModel{
	
	String employeeNo;
	String expensesPeriod;//諸費用期間
	String expensesReflectYearAndMonth;
	String updateExpensesReflectYearAndMonth;
	String transportationExpenses;
	String otherAllowanceName;
	String otherAllowanceAmount;
	String leaderAllowanceAmount;
	String totalExpenses;
	String housingAllowance;
	String actionType;
	String updateUser;
	
	public String getTotalExpenses() {
		return totalExpenses;
	}
	public void setTotalExpenses(String totalExpenses) {
		this.totalExpenses = totalExpenses;
	}
	public String getExpensesPeriod() {
		return expensesPeriod;
	}
	public void setExpensesPeriod(String expensesPeriod) {
		this.expensesPeriod = expensesPeriod;
	}
	public String getActionType() {
		return actionType;
	}
	public String getEmployeeNo() {
		return employeeNo;
	}
	public String getExpensesReflectYearAndMonth() {
		return expensesReflectYearAndMonth;
	}
	public String getHousingAllowance() {
		return housingAllowance;
	}
	public String getLeaderAllowanceAmount() {
		return leaderAllowanceAmount;
	}
	public String getOtherAllowanceAmount() {
		return otherAllowanceAmount;
	}
	public String getOtherAllowanceName() {
		return otherAllowanceName;
	}
	public String getTransportationExpenses() {
		return transportationExpenses;
	}
	public String getUpdateExpensesReflectYearAndMonth() {
		return updateExpensesReflectYearAndMonth;
	}
	public String getUpdateUser() {
		return updateUser;
	}
	public void setActionType(String actionType) {
		this.actionType = actionType;
	}
	public void setEmployeeNo(String employeeNo) {
		this.employeeNo = employeeNo;
	}
	public void setExpensesReflectYearAndMonth(String expensesReflectYearAndMonth) {
		this.expensesReflectYearAndMonth = expensesReflectYearAndMonth;
	}
	public void setHousingAllowance(String housingAllowance) {
		this.housingAllowance = housingAllowance;
	}
	public void setLeaderAllowanceAmount(String leaderAllowanceAmount) {
		this.leaderAllowanceAmount = leaderAllowanceAmount;
	}
	public void setOtherAllowanceAmount(String otherAllowanceAmount) {
		this.otherAllowanceAmount = otherAllowanceAmount;
	}
	public void setOtherAllowanceName(String otherAllowanceName) {
		this.otherAllowanceName = otherAllowanceName;
	}
	public void setTransportationExpenses(String transportationExpenses) {
		this.transportationExpenses = transportationExpenses;
	}
	public void setUpdateExpensesReflectYearAndMonth(String updateExpensesReflectYearAndMonth) {
		this.updateExpensesReflectYearAndMonth = updateExpensesReflectYearAndMonth;
	}
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}	
}
