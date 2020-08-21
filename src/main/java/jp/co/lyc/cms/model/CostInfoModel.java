package jp.co.lyc.cms.model;

import java.io.Serializable;

public class CostInfoModel implements Serializable {

	private static final long serialVersionUID = -2028159323401651353L;
	
	public String employeeNo;//社員番号
	public String actionType;//処理区分
	public int bonusFlag;//ボーナスフラグ
	public int SocialInsuranceFlag;//社会保険フラグ
	public String transportationExpenses;//交通費
	public String remark;//備考
	public String salary;//給料
	public String waitingCost;//非稼働費用
	public String nextBonusMonth;//次ボーナス月
	public String nextRaiseMonth;//次回昇給月
	public String updateUser;//更新者
	public String otherAllowance;//他の手当
	public String otherAllowanceAmount;//他の手当
	public String monthOfCompanyPay;//月に会社負担額
	public String welfarePensionAmount;//厚生年金料
	public String healthInsuranceAmount;//健康保険料
	public String insuranceFeeAmount;//保険料総額
	public String lastTimeBonusAmount;//前回ボーナス金額
	public String scheduleOfBonusAmount;//ボーナス予定金額
	public String leaderAllowanceAmount;//リーダー手当
	public String totalAmount;//総額
	public String siteRoleCode;//役割コード
	public String reflectYearAndMonth;//反映年月
	public String employeeFormCode;//社員形式
	public String employeeFormName;//社員形式名前
	public String housingAllowance;//住宅手当
	public String datePeriod;//時間段
	public String housingStatus;//住宅ステータス
	
	public String getHousingStatus() {
		return housingStatus;
	}
	public void setHousingStatus(String housingStatus) {
		this.housingStatus = housingStatus;
	}
	public String getEmployeeFormName() {
		return employeeFormName;
	}
	public void setEmployeeFormName(String employeeFormName) {
		this.employeeFormName = employeeFormName;
	}
	public String getDatePeriod() {
		return datePeriod;
	}
	public void setDatePeriod(String datePeriod) {
		this.datePeriod = datePeriod;
	}
	public String getSiteRoleCode() {
		return siteRoleCode;
	}
	public void setSiteRoleCode(String siteRoleCode) {
		this.siteRoleCode = siteRoleCode;
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
	public String getLeaderAllowanceAmount() {
		return leaderAllowanceAmount;
	}
	public void setLeaderAllowanceAmount(String leaderAllowanceAmount) {
		this.leaderAllowanceAmount = leaderAllowanceAmount;
	}
	public String getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}
	public String getMonthOfCompanyPay() {
		return monthOfCompanyPay;
	}
	public void setMonthOfCompanyPay(String monthOfCompanyPay) {
		this.monthOfCompanyPay = monthOfCompanyPay;
	}
	public String getOtherAllowance() {
		return otherAllowance;
	}
	public void setOtherAllowance(String otherAllowance) {
		this.otherAllowance = otherAllowance;
	}
	public String getOtherAllowanceAmount() {
		return otherAllowanceAmount;
	}
	public void setOtherAllowanceAmount(String otherAllowanceAmount) {
		this.otherAllowanceAmount = otherAllowanceAmount;
	}
	public String getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	public int getSocialInsuranceFlag() {
		return SocialInsuranceFlag;
	}
	public void setSocialInsuranceFlag(int socialInsuranceFlag) {
		SocialInsuranceFlag = socialInsuranceFlag;
	}
	public String getEmployeeNo() {
		return employeeNo;
	}
	public void setEmployeeNo(String employeeNo) {
		this.employeeNo = employeeNo;
	}
	
	public String getActionType() {
		return actionType;
	}
	public void setActionType(String actionType) {
		this.actionType = actionType;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getSalary() {
		return salary;
	}
	public void setSalary(String salary) {
		this.salary = salary;
	}
	public int getBonusFlag() {
		return bonusFlag;
	}
	public void setBonusFlag(int bonusFlag) {
		this.bonusFlag = bonusFlag;
	}
	public String getTransportationExpenses() {
		return transportationExpenses;
	}
	public void setTransportationExpenses(String transportationExpenses) {
		this.transportationExpenses = transportationExpenses;
	}
	public String getWaitingCost() {
		return waitingCost;
	}
	public void setWaitingCost(String waitingCost) {
		this.waitingCost = waitingCost;
	}
	public String getNextBonusMonth() {
		return nextBonusMonth;
	}
	public void setNextBonusMonth(String nextBonusMonth) {
		this.nextBonusMonth = nextBonusMonth;
	}
	public String getNextRaiseMonth() {
		return nextRaiseMonth;
	}
	public void setNextRaiseMonth(String nextRaiseMonth) {
		this.nextRaiseMonth = nextRaiseMonth;
	}
	public String getInsuranceFeeAmount() {
		return insuranceFeeAmount;
	}
	public void setInsuranceFeeAmount(String insuranceFeeAmount) {
		this.insuranceFeeAmount = insuranceFeeAmount;
	}
	public String getReflectYearAndMonth() {
		return reflectYearAndMonth;
	}
	public void setReflectYearAndMonth(String reflectYearAndMonth) {
		this.reflectYearAndMonth = reflectYearAndMonth;
	}
	public String getEmployeeFormCode() {
		return employeeFormCode;
	}
	public void setEmployeeFormCode(String employeeFormCode) {
		this.employeeFormCode = employeeFormCode;
	}
	public String getHousingAllowance() {
		return housingAllowance;
	}
	public void setHousingAllowance(String housingAllowance) {
		this.housingAllowance = housingAllowance;
	}
	
}