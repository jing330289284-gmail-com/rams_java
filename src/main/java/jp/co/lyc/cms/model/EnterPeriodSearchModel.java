package jp.co.lyc.cms.model;

import java.util.ArrayList;

public class EnterPeriodSearchModel {
	String rowNo;// 行番号
	String employeeNo;
	String yearAndMonth;// 年月
	String employeeName;// 社員名
	String enterPeriodKbn;// 区分
	String salary;// 給料
	String reflectYearAndMonth;// 反映年月
	String waitingCost;// 待機費
	String insuranceFeeAmount;// 保険総額
	String nextBonusMonth;// 次のボーナス月
	String scheduleOfBonusAmount;// ボーナス予定額
	String admissionStartDate;// 入場時間
	String nonSitePeriod;// 待機期間
	String unitPrice;// 単価
	String nonSiteMonths;// 非稼働月数
	ArrayList<EnterPeriodSearchModel> nonSitePeriodsList;
	String isRed;

	public String getIsRed() {
		return isRed;
	}

	public void setIsRed(String isRed) {
		this.isRed = isRed;
	}

	public String getRowNo() {
		return rowNo;
	}

	public void setRowNo(String rowNo) {
		this.rowNo = rowNo;
	}

	public ArrayList<EnterPeriodSearchModel> getNonSitePeriodsList() {
		return nonSitePeriodsList;
	}

	public void setNonSitePeriodsList(ArrayList<EnterPeriodSearchModel> nonSitePeriodsList) {
		this.nonSitePeriodsList = nonSitePeriodsList;
	}

	public String getNonSiteMonths() {
		return nonSiteMonths;
	}

	public void setNonSiteMonths(String nonSiteMonths) {
		this.nonSiteMonths = nonSiteMonths;
	}

	public String getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getNonSitePeriod() {
		return nonSitePeriod;
	}

	public void setNonSitePeriod(String nonSitePeriod) {
		this.nonSitePeriod = nonSitePeriod;
	}

	public String getEmployeeNo() {
		return employeeNo;
	}

	public void setEmployeeNo(String employeeNo) {
		this.employeeNo = employeeNo;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	public String getReflectYearAndMonth() {
		return reflectYearAndMonth;
	}

	public void setReflectYearAndMonth(String reflectYearAndMonth) {
		this.reflectYearAndMonth = reflectYearAndMonth;
	}

	public String getWaitingCost() {
		return waitingCost;
	}

	public void setWaitingCost(String waitingCost) {
		this.waitingCost = waitingCost;
	}

	public String getInsuranceFeeAmount() {
		return insuranceFeeAmount;
	}

	public void setInsuranceFeeAmount(String insuranceFeeAmount) {
		this.insuranceFeeAmount = insuranceFeeAmount;
	}

	public String getNextBonusMonth() {
		return nextBonusMonth;
	}

	public void setNextBonusMonth(String nextBonusMonth) {
		this.nextBonusMonth = nextBonusMonth;
	}

	public String getScheduleOfBonusAmount() {
		return scheduleOfBonusAmount;
	}

	public void setScheduleOfBonusAmount(String scheduleOfBonusAmount) {
		this.scheduleOfBonusAmount = scheduleOfBonusAmount;
	}

	public String getAdmissionStartDate() {
		return admissionStartDate;
	}

	public void setAdmissionStartDate(String admissionStartDate) {
		this.admissionStartDate = admissionStartDate;
	}

	public String getYearAndMonth() {
		return yearAndMonth;
	}

	public void setYearAndMonth(String yearAndMonth) {
		this.yearAndMonth = yearAndMonth;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getEnterPeriodKbn() {
		return enterPeriodKbn;
	}

	public void setEnterPeriodKbn(String enterPeriodKbn) {
		this.enterPeriodKbn = enterPeriodKbn;
	}
}
