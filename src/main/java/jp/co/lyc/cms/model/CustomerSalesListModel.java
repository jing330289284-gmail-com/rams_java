package jp.co.lyc.cms.model;

import java.io.Serializable;
import java.util.List;

public class CustomerSalesListModel implements Serializable {

	private static final long serialVersionUID = 1L;
	String rowNo;
	String yearAndMonth;
	String customerName;
	String customerNo;
	String unitPrice;
	String bpUnitPrice;
	String DeductionsAndOvertimePay;
	String employeeNo;
	String employeeName;
	String admissionStartDate;
	String dailyCalculationStatus;
	String siteRoleName;
	String stationName;
	String totalUnitPrice;
	String averUnitPrice;
	int countPeo;
	int lastMonthCountPeo;
	List empDetail;
	String expectFee;
	String overTimeFee;
	String totalAmount;
	String grossProfit;
	int calPeoCount;
	int unitPTotal;
	int totalSales;
	int totalgrossProfit;
	double percent;

	public double getPercent() {
		return percent;
	}

	public void setPercent(double percent) {
		this.percent = percent;
	}

	public String getAdmissionStartDate() {
		return admissionStartDate;
	}

	public void setAdmissionStartDate(String admissionStartDate) {
		this.admissionStartDate = admissionStartDate;
	}

	public String getDailyCalculationStatus() {
		return dailyCalculationStatus;
	}

	public void setDailyCalculationStatus(String dailyCalculationStatus) {
		this.dailyCalculationStatus = dailyCalculationStatus;
	}

	public String getBpUnitPrice() {
		return bpUnitPrice;
	}

	public void setBpUnitPrice(String bpUnitPrice) {
		this.bpUnitPrice = bpUnitPrice;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getYearAndMonth() {
		return yearAndMonth;
	}

	public void setYearAndMonth(String yearAndMonth) {
		this.yearAndMonth = yearAndMonth;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerNo() {
		return customerNo;
	}

	public void setCustomerNo(String customerNo) {
		this.customerNo = customerNo;
	}

	public String getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getDeductionsAndOvertimePay() {
		return DeductionsAndOvertimePay;
	}

	public void setDeductionsAndOvertimePay(String deductionsAndOvertimePay) {
		DeductionsAndOvertimePay = deductionsAndOvertimePay;
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

	public String getSiteRoleName() {
		return siteRoleName;
	}

	public void setSiteRoleName(String siteRoleName) {
		this.siteRoleName = siteRoleName;
	}

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	public String getTotalUnitPrice() {
		return totalUnitPrice;
	}

	public void setTotalUnitPrice(String totalUnitPrice) {
		this.totalUnitPrice = totalUnitPrice;
	}

	public String getAverUnitPrice() {
		return averUnitPrice;
	}

	public void setAverUnitPrice(String averUnitPrice) {
		this.averUnitPrice = averUnitPrice;
	}

	public int getCountPeo() {
		return countPeo;
	}

	public void setCountPeo(int countPeo) {
		this.countPeo = countPeo;
	}

	public int getLastMonthCountPeo() {
		return lastMonthCountPeo;
	}

	public void setLastMonthCountPeo(int lastMonthCountPeo) {
		this.lastMonthCountPeo = lastMonthCountPeo;
	}

	public List getEmpDetail() {
		return empDetail;
	}

	public void setEmpDetail(List empDetail) {
		this.empDetail = empDetail;
	}

	public String getExpectFee() {
		return expectFee;
	}

	public void setExpectFee(String expectFee) {
		this.expectFee = expectFee;
	}

	public String getOverTimeFee() {
		return overTimeFee;
	}

	public void setOverTimeFee(String overTimeFee) {
		this.overTimeFee = overTimeFee;
	}

	public String getRowNo() {
		return rowNo;
	}

	public void setRowNo(String rowNo) {
		this.rowNo = rowNo;
	}

	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getGrossProfit() {
		return grossProfit;
	}

	public void setGrossProfit(String grossProfit) {
		this.grossProfit = grossProfit;
	}

	public int getCalPeoCount() {
		return calPeoCount;
	}

	public void setCalPeoCount(int calPeoCount) {
		this.calPeoCount = calPeoCount;
	}

	public int getUnitPTotal() {
		return unitPTotal;
	}

	public void setUnitPTotal(int unitPTotal) {
		this.unitPTotal = unitPTotal;
	}

	public int getTotalSales() {
		return totalSales;
	}

	public void setTotalSales(int totalSales) {
		this.totalSales = totalSales;
	}

	public int getTotalgrossProfit() {
		return totalgrossProfit;
	}

	public void setTotalgrossProfit(int totalgrossProfit) {
		this.totalgrossProfit = totalgrossProfit;
	}

}
