package jp.co.lyc.cms.model;

import java.io.Serializable;
import java.util.List;

public class PersonalSalesSearchModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String status;
	String nodata;
	String errormessage;
	String onlyYandM;
	int workMonthCount;
	String employeeName;// 社員名
	String employeeNo;// 社員番号
	String fiscalYear;// 年度
	String startYearAndMonth;// 開始年月
	String endYearAndMonth;// 終了年月
	String admissionStartDate;// 年月
	String employeeFormName;// 社員形式
	String customerName;// 所属客様
	String unitPrice;// 単価
	String salary;// 基本支給
	String transportationExpenses;// 交通代
	String insuranceFeeAmount;// 社会保険
	String scheduleOfBonusAmount;
	String allowanceAmount;
	String grosProfits;// 粗利
	String paymentTotal;
	String dailyCalculationStatus;
	String admissionEndDate;
	String DeductionsAndOvertimePay;
	String DeductionsAndOvertimePayOfUnitPrice;
	String otherAllowanceName;
	String otherAllowanceAmount;
	String introductionAllowance;
	String leaderAllowanceAmount;
	String relatedEmployees;
	List<String> empNameList;
	String waitingCost;
	String nextBonusMonth;
	String bonusFee;

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getEmployeeNo() {
		return employeeNo;
	}

	public void setEmployeeNo(String employeeNo) {
		this.employeeNo = employeeNo;
	}

	public String getFiscalYear() {
		return fiscalYear;
	}

	public void setFiscalYear(String fiscalYear) {
		this.fiscalYear = fiscalYear;
	}

	public String getStartYearAndMonth() {
		return startYearAndMonth;
	}

	public void setStartYearAndMonth(String startYearAndMonth) {
		this.startYearAndMonth = startYearAndMonth;
	}

	public String getEndYearAndMonth() {
		return endYearAndMonth;
	}

	public void setEndYearAndMonth(String endYearAndMonth) {
		this.endYearAndMonth = endYearAndMonth;
	}

	public String getEmployeeFormName() {
		return employeeFormName;
	}

	public void setEmployeeFormName(String employeeFormName) {
		this.employeeFormName = employeeFormName;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	public String getTransportationExpenses() {
		return transportationExpenses;
	}

	public void setTransportationExpenses(String transportationExpenses) {
		this.transportationExpenses = transportationExpenses;
	}

	public String getInsuranceFeeAmount() {
		return insuranceFeeAmount;
	}

	public void setInsuranceFeeAmount(String insuranceFeeAmount) {
		this.insuranceFeeAmount = insuranceFeeAmount;
	}

	public String getScheduleOfBonusAmount() {
		return scheduleOfBonusAmount;
	}

	public void setScheduleOfBonusAmount(String scheduleOfBonusAmount) {
		this.scheduleOfBonusAmount = scheduleOfBonusAmount;
	}

	public String getAllowanceAmount() {
		return allowanceAmount;
	}

	public void setAllowanceAmount(String allowanceAmount) {
		this.allowanceAmount = allowanceAmount;
	}

	public String getGrosProfits() {
		return grosProfits;
	}

	public void setGrosProfits(String grosProfits) {
		this.grosProfits = grosProfits;
	}

	public String getAdmissionStartDate() {
		return admissionStartDate;
	}

	public void setAdmissionStartDate(String admissionStartDate) {
		this.admissionStartDate = admissionStartDate;
	}

	public String getOnlyYandM() {
		return onlyYandM;
	}

	public void setOnlyYandM(String onlyYandM) {
		this.onlyYandM = onlyYandM;
	}

	public int getWorkMonthCount() {
		return workMonthCount;
	}

	public void setWorkMonthCount(int workMonthCount) {
		this.workMonthCount = workMonthCount;
	}

	public String getPaymentTotal() {
		return paymentTotal;
	}

	public void setPaymentTotal(String paymentTotal) {
		this.paymentTotal = paymentTotal;
	}

	public String getErrormessage() {
		return errormessage;
	}

	public void setErrormessage(String errormessage) {
		this.errormessage = errormessage;
	}

	public String getNodata() {
		return nodata;
	}

	public void setNodata(String nodata) {
		this.nodata = nodata;
	}

	public String getDailyCalculationStatus() {
		return dailyCalculationStatus;
	}

	public void setDailyCalculationStatus(String dailyCalculationStatus) {
		this.dailyCalculationStatus = dailyCalculationStatus;
	}

	public String getAdmissionEndDate() {
		return admissionEndDate;
	}

	public void setAdmissionEndDate(String admissionEndDate) {
		this.admissionEndDate = admissionEndDate;
	}

	public String getDeductionsAndOvertimePay() {
		return DeductionsAndOvertimePay;
	}

	public void setDeductionsAndOvertimePay(String deductionsAndOvertimePay) {
		DeductionsAndOvertimePay = deductionsAndOvertimePay;
	}

	public String getOtherAllowanceName() {
		return otherAllowanceName;
	}

	public void setOtherAllowanceName(String otherAllowanceName) {
		this.otherAllowanceName = otherAllowanceName;
	}

	public String getOtherAllowanceAmount() {
		return otherAllowanceAmount;
	}

	public void setOtherAllowanceAmount(String otherAllowanceAmount) {
		this.otherAllowanceAmount = otherAllowanceAmount;
	}

	public String getIntroductionAllowance() {
		return introductionAllowance;
	}

	public void setIntroductionAllowance(String introductionAllowance) {
		this.introductionAllowance = introductionAllowance;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getLeaderAllowanceAmount() {
		return leaderAllowanceAmount;
	}

	public void setLeaderAllowanceAmount(String leaderAllowanceAmount) {
		this.leaderAllowanceAmount = leaderAllowanceAmount;
	}

	public String getRelatedEmployees() {
		return relatedEmployees;
	}

	public void setRelatedEmployees(String relatedEmployees) {
		this.relatedEmployees = relatedEmployees;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<String> getEmpNameList() {
		return empNameList;
	}

	public void setEmpNameList(List<String> empNameList) {
		this.empNameList = empNameList;
	}

	public String getNextBonusMonth() {
		return nextBonusMonth;
	}

	public void setNextBonusMonth(String nextBonusMonth) {
		this.nextBonusMonth = nextBonusMonth;
	}

	public String getBonusFee() {
		return bonusFee;
	}

	public void setBonusFee(String bonusFee) {
		this.bonusFee = bonusFee;
	}

	public String getWaitingCost() {
		return waitingCost;
	}

	public void setWaitingCost(String waitingCost) {
		this.waitingCost = waitingCost;
	}

	public String getDeductionsAndOvertimePayOfUnitPrice() {
		return DeductionsAndOvertimePayOfUnitPrice;
	}

	public void setDeductionsAndOvertimePayOfUnitPrice(String deductionsAndOvertimePayOfUnitPrice) {
		DeductionsAndOvertimePayOfUnitPrice = deductionsAndOvertimePayOfUnitPrice;
	}

}
