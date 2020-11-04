package jp.co.lyc.cms.model;

import org.springframework.web.multipart.MultipartFile;

public class CostRegistrationModel {
	int rowNo;
	String employeeNo;
	String employeeName;
	String happendDate;
	int costClassificationCode;
	String dueDate;
	String detailedNameOrLine;
	int stationCode;
	int originCode;
	int transportationCode;
	int destinationCode;
	int cost;
	String remark;
	int roundCode;
	String costFile;
	String updateUser;
	String updateTime;
	String createTime;
	MultipartFile costFileFile;
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
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getHappendDate() {
		return happendDate;
	}
	public void setHappendDate(String happendDate) {
		this.happendDate = happendDate;
	}
	public int getCostClassificationCode() {
		return costClassificationCode;
	}
	public void setCostClassificationCode(int costClassificationCode) {
		this.costClassificationCode = costClassificationCode;
	}
	public String getDueDate() {
		return dueDate;
	}
	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}
	public String getDetailedNameOrLine() {
		return detailedNameOrLine;
	}
	public void setDetailedNameOrLine(String detailedNameOrLine) {
		this.detailedNameOrLine = detailedNameOrLine;
	}
	public int getStationCode() {
		return stationCode;
	}
	public void setStationCode(int stationCode) {
		this.stationCode = stationCode;
	}
	public int getOriginCode() {
		return originCode;
	}
	public void setOriginCode(int originCode) {
		this.originCode = originCode;
	}
	public int getTransportationCode() {
		return transportationCode;
	}
	public void setTransportationCode(int transportationCode) {
		this.transportationCode = transportationCode;
	}
	public int getDestinationCode() {
		return destinationCode;
	}
	public void setDestinationCode(int destinationCode) {
		this.destinationCode = destinationCode;
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public int getRoundCode() {
		return roundCode;
	}
	public void setRoundCode(int roundCode) {
		this.roundCode = roundCode;
	}
	public String getCostFile() {
		return costFile;
	}
	public void setCostFile(String costFile) {
		this.costFile = costFile;
	}
	public String getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public MultipartFile getCostFileFile() {
		return costFileFile;
	}
	public void setCostFileFile(MultipartFile costFileFile) {
		this.costFileFile = costFileFile;
	}
}
