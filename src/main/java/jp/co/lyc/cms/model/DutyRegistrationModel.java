package jp.co.lyc.cms.model;

import java.util.HashMap;
import java.util.Map;

public class DutyRegistrationModel {

	String breakTimeIsConst;
	String employeeNo;
	String breakTimeYearMonth;
	String breakTimeDayStart;
	String breakTimeDayEnd;
	String breakTimeDaybreakTimeHour;
	String breakTimeNightStart;
	String breakTimeNightEnd;
	String breakTimeNightbreakTimeHour;
	String breakTimeSumHour;
	String updateUser;

	public String getBreakTimeIsConst() {
		return breakTimeIsConst;
	}
	public void setBreakTimeIsConst(String breakTimeIsConst) {
		this.breakTimeIsConst = breakTimeIsConst;
	}
	public String getEmployeeNo() {
		return employeeNo;
	}
	public void setEmployeeNo(String employeeNo) {
		this.employeeNo = employeeNo;
	}
	public String getBreakTimeYearMonth() {
		return breakTimeYearMonth;
	}
	public void setBreakTimeYearMonth(String breakTimeYearMonth) {
		this.breakTimeYearMonth = breakTimeYearMonth;
	}
	public String getBreakTimeDayStart() {
		return breakTimeDayStart;
	}
	public void setBreakTimeDayStart(String breakTimeDayStart) {
		this.breakTimeDayStart = breakTimeDayStart;
	}
	public String getBreakTimeDayEnd() {
		return breakTimeDayEnd;
	}
	public void setBreakTimeDayEnd(String breakTimeDayEnd) {
		this.breakTimeDayEnd = breakTimeDayEnd;
	}
	public String getBreakTimeDaybreakTimeHour() {
		return breakTimeDaybreakTimeHour;
	}
	public void setBreakTimeDaybreakTimeHour(String breakTimeDaybreakTimeHour) {
		this.breakTimeDaybreakTimeHour = breakTimeDaybreakTimeHour;
	}
	public String getBreakTimeNightStart() {
		return breakTimeNightStart;
	}
	public void setBreakTimeNightStart(String breakTimeNightStart) {
		this.breakTimeNightStart = breakTimeNightStart;
	}
	public String getBreakTimeNightEnd() {
		return breakTimeNightEnd;
	}
	public void setBreakTimeNightEnd(String breakTimeNightEnd) {
		this.breakTimeNightEnd = breakTimeNightEnd;
	}
	public String getBreakTimeNightbreakTimeHour() {
		return breakTimeNightbreakTimeHour;
	}
	public void setBreakTimeNightbreakTimeHour(String breakTimeNightbreakTimeHour) {
		this.breakTimeNightbreakTimeHour = breakTimeNightbreakTimeHour;
	}
	public String getBreakTimeSumHour() {
		return breakTimeSumHour;
	}
	public void setBreakTimeSumHour(String breakTimeSumHour) {
		this.breakTimeSumHour = breakTimeSumHour;
	}
	public String getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	public HashMap<String, Object> toHashMap() {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("breakTimeIsConst", this.getBreakTimeIsConst());
		map.put("employeeNo", this.getEmployeeNo());
		map.put("breakTimeYearMonth", this.getBreakTimeYearMonth());
		map.put("breakTimeDayStart", this.getBreakTimeDayStart());
		map.put("breakTimeDayEnd", this.getBreakTimeDayEnd());
		map.put("breakTimeDaybreakTimeHour", this.getBreakTimeDaybreakTimeHour());
		map.put("breakTimeNightStart", this.getBreakTimeNightStart());
		map.put("breakTimeNightEnd", this.getBreakTimeNightEnd());
		map.put("breakTimeNightbreakTimeHour", this.getBreakTimeNightbreakTimeHour());
		map.put("breakTimeSumHour", this.getBreakTimeSumHour());
		map.put("updateUser", this.getUpdateUser());
		return map;
	}
	@Override
	public String toString() {
		return "DutyRegistrationModel [breakTimeIsConst=" + breakTimeIsConst + ", employeeNo=" + employeeNo
				+ ", breakTimeYearMonth=" + breakTimeYearMonth + ", breakTimeDayStart=" + breakTimeDayStart
				+ ", breakTimeDayEnd=" + breakTimeDayEnd + ", breakTimeDaybreakTimeHour=" + breakTimeDaybreakTimeHour
				+ ", breakTimeNightStart=" + breakTimeNightStart + ", breakTimeNightEnd=" + breakTimeNightEnd
				+ ", breakTimeNightbreakTimeHour=" + breakTimeNightbreakTimeHour + ", breakTimeSumHour="
				+ breakTimeSumHour + ", updateUser=" + updateUser + "]";
	}
}
