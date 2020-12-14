package jp.co.lyc.cms.model;

import java.io.Serializable;
import java.util.List;

public class IndividualCustomerSalesModel implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String customerName;
	String fiscalYear;
	String startYear;
	String endYear;
	String unitPrice;
	String DeductionsAndOvertimePay;
	String employeeName;
	String siteRoleName;
	String yearAndMonth;
	String maxUnitPrice;
	String minUnitPrice;
	String averUnitPrice;
	String totalUnitPrice;
	String workPeoSum;
	String overTimeFee;
	String expectFee;
	String totalAmount;
	String employeeNo;
	String grossProfit;
	String totalExpenses;
	String stationName;
	List empDetail;
	int totalworkPeoSum;
	int totaluPrice;
	int overTimeOrExpectFee;
	int totalgrossProfit;
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getFiscalYear() {
		return fiscalYear;
	}
	public void setFiscalYear(String fiscalYear) {
		this.fiscalYear = fiscalYear;
	}
	public String getStartYear() {
		return startYear;
	}
	public void setStartYear(String startYear) {
		this.startYear = startYear;
	}
	public String getEndYear() {
		return endYear;
	}
	public void setEndYear(String endYear) {
		this.endYear = endYear;
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
	public String getYearAndMonth() {
		return yearAndMonth;
	}
	public void setYearAndMonth(String yearAndMonth) {
		this.yearAndMonth = yearAndMonth;
	}
	public String getMaxUnitPrice() {
		return maxUnitPrice;
	}
	public void setMaxUnitPrice(String maxUnitPrice) {
		this.maxUnitPrice = maxUnitPrice;
	}
	public String getMinUnitPrice() {
		return minUnitPrice;
	}
	public void setMinUnitPrice(String minUnitPrice) {
		this.minUnitPrice = minUnitPrice;
	}
	public String getAverUnitPrice() {
		return averUnitPrice;
	}
	public void setAverUnitPrice(String averUnitPrice) {
		this.averUnitPrice = averUnitPrice;
	}
	public String getTotalUnitPrice() {
		return totalUnitPrice;
	}
	public void setTotalUnitPrice(String totalUnitPrice) {
		this.totalUnitPrice = totalUnitPrice;
	}
	public String getWorkPeoSum() {
		return workPeoSum;
	}
	public void setWorkPeoSum(String workPeoSum) {
		this.workPeoSum = workPeoSum;
	}
	public String getOverTimeFee() {
		return overTimeFee;
	}
	public void setOverTimeFee(String overTimeFee) {
		this.overTimeFee = overTimeFee;
	}
	public String getExpectFee() {
		return expectFee;
	}
	public void setExpectFee(String expectFee) {
		this.expectFee = expectFee;
	}
	public String getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}
	public String getEmployeeNo() {
		return employeeNo;
	}
	public void setEmployeeNo(String employeeNo) {
		this.employeeNo = employeeNo;
	}
	public String getGrossProfit() {
		return grossProfit;
	}
	public void setGrossProfit(String grossProfit) {
		this.grossProfit = grossProfit;
	}
	public String getTotalExpenses() {
		return totalExpenses;
	}
	public void setTotalExpenses(String totalExpenses) {
		this.totalExpenses = totalExpenses;
	}
	public String getStationName() {
		return stationName;
	}
	public void setStationName(String stationName) {
		this.stationName = stationName;
	}
	public List getEmpDetail() {
		return empDetail;
	}
	public void setEmpDetail(List empDetail) {
		this.empDetail = empDetail;
	}
	public int getTotalworkPeoSum() {
		return totalworkPeoSum;
	}
	public void setTotalworkPeoSum(int totalworkPeoSum) {
		this.totalworkPeoSum = totalworkPeoSum;
	}
	public int getTotaluPrice() {
		return totaluPrice;
	}
	public void setTotaluPrice(int totaluPrice) {
		this.totaluPrice = totaluPrice;
	}
	public int getOverTimeOrExpectFee() {
		return overTimeOrExpectFee;
	}
	public void setOverTimeOrExpectFee(int overTimeOrExpectFee) {
		this.overTimeOrExpectFee = overTimeOrExpectFee;
	}
	public int getTotalgrossProfit() {
		return totalgrossProfit;
	}
	public void setTotalgrossProfit(int totalgrossProfit) {
		this.totalgrossProfit = totalgrossProfit;
	}



}
