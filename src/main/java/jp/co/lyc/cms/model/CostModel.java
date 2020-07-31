package jp.co.lyc.cms.model;

import java.io.Serializable;

public class CostModel implements Serializable {

	private static final long serialVersionUID = -2028159323401651353L;
	
	public String employeeNo;//社員番号
	public String shoriKbn;//処理区分
	public int BonusFlag;//ボーナスフラグ
	public int SocialInsuranceFlag;//社会保険フラグ
	public String TransportationExpenses;//交通費
	public String remark;//備考
	public String salary;//給料
	public String WaitingCost;//非稼働費用
	public String NextBonusMonth;//次ボーナス月
	public String NextRaiseMonth;//次回昇給月
	public String updateUser;//更新者
	public String otherAllowance;//他の手当
	public String otherAllowanceAmount;//他の手当
	public String monthOfCompanyPay;//月に会社負担額
	public String welfarePensionAmount;//厚生年金料
	public String healthInsuranceAmount;//健康保険料
	public String InsuranceFeeAmount;//保険料総額
	public String lastTimeBonusAmount;//前回ボーナス金額
	public String scheduleOfBonusAmount;//ボーナス予定金額
	public String leaderAllowanceAmount;//リーダー手当
	public String totalAmount;//総額
	public String siteRoleCode;//役割コード
	
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
	public String getInsuranceFeeAmount() {
		return InsuranceFeeAmount;
	}
	public void setInsuranceFeeAmount(String insuranceFeeAmount) {
		InsuranceFeeAmount = insuranceFeeAmount;
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
	
	public String getNextBonusMonth() {
		return NextBonusMonth;
	}
	public void setNextBonusMonth(String nextBonusMonth) {
		NextBonusMonth = nextBonusMonth;
	}
	public String getNextRaiseMonth() {
		return NextRaiseMonth;
	}
	public void setNextRaiseMonth(String nextRaiseMonth) {
		NextRaiseMonth = nextRaiseMonth;
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
	public String getShoriKbn() {
		return shoriKbn;
	}
	public void setShoriKbn(String shoriKbn) {
		this.shoriKbn = shoriKbn;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public int getBonusFlag() {
		return BonusFlag;
	}
	public void setBonusFlag(int bonusFlag) {
		BonusFlag = bonusFlag;
	}
	public String getTransportationExpenses() {
		return TransportationExpenses;
	}
	public void setTransportationExpenses(String transportationExpenses) {
		TransportationExpenses = transportationExpenses;
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
	public String getWaitingCost() {
		return WaitingCost;
	}
	public void setWaitingCost(String waitingCost) {
		WaitingCost = waitingCost;
	}
	
	
}