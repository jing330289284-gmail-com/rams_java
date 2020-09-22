package jp.co.lyc.cms.model;

import java.util.HashMap;
import java.util.Map;

public class BreakTimeModel {

	String employeeNo;
	String yearMonth;
	String breakTimeFixedStatus;
	String lunchBreakStartTime;
	String lunchBreakFinshTime;
	String lunchBreakTime;
	String nightBreakStartTime;
	String nightBreakfinshTime;
	String nightBreakTime;
	String totalBreakTime;

	public String getEmployeeNo() {
		return employeeNo;
	}
	public void setEmployeeNo(String employeeNo) {
		this.employeeNo = employeeNo;
	}
	public String getYearMonth() {
		return yearMonth;
	}
	public void setYearMonth(String yearMonth) {
		this.yearMonth = yearMonth;
	}
	public String getBreakTimeFixedStatus() {
		return breakTimeFixedStatus;
	}
	public void setBreakTimeFixedStatus(String breakTimeFixedStatus) {
		this.breakTimeFixedStatus = breakTimeFixedStatus;
	}
	public String getLunchBreakStartTime() {
		return lunchBreakStartTime;
	}
	public void setLunchBreakStartTime(String lunchBreakStartTime) {
		this.lunchBreakStartTime = lunchBreakStartTime;
	}
	public String getLunchBreakFinshTime() {
		return lunchBreakFinshTime;
	}
	public void setLunchBreakFinshTime(String lunchBreakFinshTime) {
		this.lunchBreakFinshTime = lunchBreakFinshTime;
	}
	public String getLunchBreakTime() {
		return lunchBreakTime;
	}
	public void setLunchBreakTime(String lunchBreakTime) {
		this.lunchBreakTime = lunchBreakTime;
	}
	public String getNightBreakStartTime() {
		return nightBreakStartTime;
	}
	public void setNightBreakStartTime(String nightBreakStartTime) {
		this.nightBreakStartTime = nightBreakStartTime;
	}
	public String getNightBreakfinshTime() {
		return nightBreakfinshTime;
	}
	public void setNightBreakfinshTime(String nightBreakfinshTime) {
		this.nightBreakfinshTime = nightBreakfinshTime;
	}
	public String getNightBreakTime() {
		return nightBreakTime;
	}
	public void setNightBreakTime(String nightBreakTime) {
		this.nightBreakTime = nightBreakTime;
	}
	public String getTotalBreakTime() {
		return totalBreakTime;
	}
	public void setTotalBreakTime(String totalBreakTime) {
		this.totalBreakTime = totalBreakTime;
	}
	public Map<String, Object> toHashMap() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("employeeNo", this.getEmployeeNo());
		map.put("yearMonth", this.getYearMonth());
		map.put("breakTimeFixedStatus", this.getBreakTimeFixedStatus());
		map.put("lunchBreakStartTime", this.getLunchBreakStartTime());
		map.put("lunchBreakFinshTime", this.getLunchBreakFinshTime());
		map.put("lunchBreakTime", this.getLunchBreakTime());
		map.put("nightBreakStartTime", this.getNightBreakStartTime());
		map.put("nightBreakfinshTime", this.getNightBreakfinshTime());
		map.put("nightBreakTime", this.getNightBreakTime());
		map.put("totalBreakTime", this.getTotalBreakTime());
		return map;
	}
	@Override
	public String toString() {
		return "breakTimeModel [employeeNo=" + employeeNo + ", yearMonth=" + yearMonth + ", breakTimeFixedStatus="
				+ breakTimeFixedStatus + ", lunchBreakStartTime=" + lunchBreakStartTime + ", lunchBreakFinshTime="
				+ lunchBreakFinshTime + ", lunchBreakTime=" + lunchBreakTime + ", nightBreakStartTime="
				+ nightBreakStartTime + ", nightBreakfinshTime=" + nightBreakfinshTime + ", nightBreakTime="
				+ nightBreakTime + ", totalBreakTime=" + totalBreakTime + "]";
	}
}
