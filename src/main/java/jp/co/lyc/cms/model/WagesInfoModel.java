package jp.co.lyc.cms.model;

import java.util.ArrayList;

import jp.co.lyc.cms.common.BaseController;

public class WagesInfoModel {
	String employeeNo;//社員名
	String reflectYearAndMonth;//反映年月
	String updatedReflectYearAndMonth;//反映年月
	String socialInsuranceFlag;//社会保険フラグ
	String salary;//給料
	String waitingCost;//非稼動費用
	String welfarePensionAmount;//厚生年金
	String healthInsuranceAmount;//健康保険
	String insuranceFeeAmount;//保険総額
	String lastTimeBonusAmount;//ボーナス前回額
	String scheduleOfBonusAmount;//ボーナス予定額
	String bonusFlag;//ボーナスフラグ
	String nextBonusMonth;//次ボーナス月
	String monthOfCompanyPay;//会社月負担額
	String nextRaiseMonth;//次回昇給月
	String totalAmount;//総額
	String employeeFormCode;//社員形式
	String remark;//備考
	String updateUser;//更新者
	ExpensesInfoModel expensesInfoModel;//諸費用画面データ
	String actionType;//処理区分
	ArrayList<ExpensesInfoModel> expensesInfoModels;
	String transportationExpenses;
	String leaderAllowanceAmount;
	String housingAllowance;
	String otherAllowanceAmount;
	
	public String getTransportationExpenses() {
		return transportationExpenses;
	}
	public void setTransportationExpenses(String transportationExpenses) {
		this.transportationExpenses = transportationExpenses;
	}
	public String getLeaderAllowanceAmount() {
		return leaderAllowanceAmount;
	}
	public void setLeaderAllowanceAmount(String leaderAllowanceAmount) {
		this.leaderAllowanceAmount = leaderAllowanceAmount;
	}
	public String getHousingAllowance() {
		return housingAllowance;
	}
	public void setHousingAllowance(String housingAllowance) {
		this.housingAllowance = housingAllowance;
	}
	public String getOtherAllowanceAmount() {
		return otherAllowanceAmount;
	}
	public void setOtherAllowanceAmount(String otherAllowanceAmount) {
		this.otherAllowanceAmount = otherAllowanceAmount;
	}
	public ArrayList<ExpensesInfoModel> getExpensesInfoModels() {
		return expensesInfoModels;
	}
	public void setExpensesInfoModels(ArrayList<ExpensesInfoModel> expensesInfoModels) {
		this.expensesInfoModels = expensesInfoModels;
	}
	public String getUpdatedReflectYearAndMonth() {
		return updatedReflectYearAndMonth;
	}
	public void setUpdatedReflectYearAndMonth(String updatedReflectYearAndMonth) {
		this.updatedReflectYearAndMonth = updatedReflectYearAndMonth;
	}
	public String getActionType() {
		return actionType;
	}
	public void setActionType(String actionType) {
		this.actionType = actionType;
	}
	public ExpensesInfoModel getExpensesInfoModel() {
		return expensesInfoModel;
	}
	public void setExpensesInfoModel(ExpensesInfoModel expensesInfoModel) {
		this.expensesInfoModel = expensesInfoModel;
	}
	public String getEmployeeNo() {
		return employeeNo;
	}
	public void setEmployeeNo(String employeeNo) {
		this.employeeNo = employeeNo;
	}
	public String getReflectYearAndMonth() {
		return reflectYearAndMonth;
	}
	public void setReflectYearAndMonth(String reflectYearAndMonth) {
		this.reflectYearAndMonth = reflectYearAndMonth;
	}
	public String getSocialInsuranceFlag() {
		return socialInsuranceFlag;
	}
	public void setSocialInsuranceFlag(String socialInsuranceFlag) {
		this.socialInsuranceFlag = socialInsuranceFlag;
	}
	public String getSalary() {
		return salary;
	}
	public void setSalary(String salary) {
		this.salary = salary;
	}
	public String getWaitingCost() {
		return waitingCost;
	}
	public void setWaitingCost(String waitingCost) {
		this.waitingCost = waitingCost;
	}
	public String getWelfarePensionAmount() {
		return welfarePensionAmount;
	}
	public void setWelfarePensionAmount(String welfarePensionAmount) {
		this.welfarePensionAmount = welfarePensionAmount;
	}
	public String getHealthInsuranceAmount() {
		return healthInsuranceAmount;
	}
	public void setHealthInsuranceAmount(String healthInsuranceAmount) {
		this.healthInsuranceAmount = healthInsuranceAmount;
	}
	public String getInsuranceFeeAmount() {
		return insuranceFeeAmount;
	}
	public void setInsuranceFeeAmount(String insuranceFeeAmount) {
		this.insuranceFeeAmount = insuranceFeeAmount;
	}
	public String getLastTimeBonusAmount() {
		return lastTimeBonusAmount;
	}
	public void setLastTimeBonusAmount(String lastTimeBonusAmount) {
		this.lastTimeBonusAmount = lastTimeBonusAmount;
	}
	public String getScheduleOfBonusAmount() {
		return scheduleOfBonusAmount;
	}
	public void setScheduleOfBonusAmount(String scheduleOfBonusAmount) {
		this.scheduleOfBonusAmount = scheduleOfBonusAmount;
	}
	public String getBonusFlag() {
		return bonusFlag;
	}
	public void setBonusFlag(String bonusFlag) {
		this.bonusFlag = bonusFlag;
	}
	public String getNextBonusMonth() {
		return nextBonusMonth;
	}
	public void setNextBonusMonth(String nextBonusMonth) {
		this.nextBonusMonth = nextBonusMonth;
	}
	public String getMonthOfCompanyPay() {
		return monthOfCompanyPay;
	}
	public void setMonthOfCompanyPay(String monthOfCompanyPay) {
		this.monthOfCompanyPay = monthOfCompanyPay;
	}
	public String getNextRaiseMonth() {
		return nextRaiseMonth;
	}
	public void setNextRaiseMonth(String nextRaiseMonth) {
		this.nextRaiseMonth = nextRaiseMonth;
	}
	public String getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}
	public String getEmployeeFormCode() {
		return employeeFormCode;
	}
	public void setEmployeeFormCode(String employeeFormCode) {
		this.employeeFormCode = employeeFormCode;
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
	
}
