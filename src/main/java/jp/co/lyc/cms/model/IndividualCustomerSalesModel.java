package jp.co.lyc.cms.model;

import java.io.Serializable;

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
	String occupationName;
	String yearAndMonth;
	String maxUnitPrice;
	String minUnitPrice;
	String averUnitPrice;
	String totalUnitPrice;
	String workPeoSum;
	String overTimeFee;
	String expectFee;
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
	public String getOccupationName() {
		return occupationName;
	}
	public void setOccupationName(String occupationName) {
		this.occupationName = occupationName;
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
	


}
