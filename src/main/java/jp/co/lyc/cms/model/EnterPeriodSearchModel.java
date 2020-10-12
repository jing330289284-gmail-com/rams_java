package jp.co.lyc.cms.model;

public class EnterPeriodSearchModel {
	String yearAndMonth;//年月
	String employeeName;//社員名
	String enterPeriodKbn;//区分
	
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
