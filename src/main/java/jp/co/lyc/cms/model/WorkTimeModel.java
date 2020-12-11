package jp.co.lyc.cms.model;
public class WorkTimeModel {
	int rowNo;
	String employeeNo;
	String attendanceYearAndMonth;
	String systemName;//システム名
	String stationName;//場所
	String payOffRange;//精算時間
	String attendanceDays;//出勤日数
	String sumWorkTime;//出勤時間
	String averageSumWorkTime;//会社平均稼働
	String workTimeRank;//社内稼動ランキング
	Integer carCost;//交通費用
	Integer otherCost;//他の費用
	String yearAndMonth1;//検索用
	String yearAndMonth2;//検索用
	public int getRowNo() {
		return rowNo;
	}
	public void setRowNo(int rowNo) {
		this.rowNo = rowNo;
	}
	public String getEmployeeNo() {
		return employeeNo;
	}
	public void setEmployeeNo(String employeeNo) {
		this.employeeNo = employeeNo;
	}
	public String getAttendanceYearAndMonth() {
		return attendanceYearAndMonth;
	}
	public void setAttendanceYearAndMonth(String attendanceYearAndMonth) {
		this.attendanceYearAndMonth = attendanceYearAndMonth;
	}
	public String getSystemName() {
		return systemName;
	}
	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}
	public String getStationName() {
		return stationName;
	}
	public void setStationName(String stationName) {
		this.stationName = stationName;
	}
	public String getPayOffRange() {
		return payOffRange;
	}
	public void setPayOffRange(String payOffRange) {
		this.payOffRange = payOffRange;
	}
	public String getAttendanceDays() {
		return attendanceDays;
	}
	public void setAttendanceDays(String attendanceDays) {
		this.attendanceDays = attendanceDays;
	}
	public String getSumWorkTime() {
		return sumWorkTime;
	}
	public void setSumWorkTime(String sumWorkTime) {
		this.sumWorkTime = sumWorkTime;
	}
	public String getAverageSumWorkTime() {
		return averageSumWorkTime;
	}
	public void setAverageSumWorkTime(String averageSumWorkTime) {
		this.averageSumWorkTime = averageSumWorkTime;
	}
	public String getWorkTimeRank() {
		return workTimeRank;
	}
	public void setWorkTimeRank(String workTimeRank) {
		this.workTimeRank = workTimeRank;
	}
	public Integer getCarCost() {
		return carCost;
	}
	public void setCarCost(Integer carCost) {
		this.carCost = carCost;
	}
	public Integer getOtherCost() {
		return otherCost;
	}
	public void setOtherCost(Integer otherCost) {
		this.otherCost = otherCost;
	}
	public String getYearAndMonth1() {
		return yearAndMonth1;
	}
	public void setYearAndMonth1(String yearAndMonth1) {
		this.yearAndMonth1 = yearAndMonth1;
	}
	public String getYearAndMonth2() {
		return yearAndMonth2;
	}
	public void setYearAndMonth2(String yearAndMonth2) {
		this.yearAndMonth2 = yearAndMonth2;
	}
}
