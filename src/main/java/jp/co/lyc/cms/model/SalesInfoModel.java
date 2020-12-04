package jp.co.lyc.cms.model;

import java.util.Date;

public class SalesInfoModel {

	/**
	 *
	 */
	private static final long serialVersionUID = -2028159323401651353L;
	String employeeFristName; // 氏名
	String employeeLastName; // 苗字
	String customerName; // 会社名
	String customerAbbreviation; // 会社略称
	String salary; // 給料
	String unitPrice; // 単価
	String admissionStartDate; // 現場開始年月
	String admissionEndDate; // 現場終了年月
	String employeeStatus; // 社員区分
	String employeeFrom; // 所属
	// データ処理
	String rowNo; // 自動採番
	String employeeNo; // 社員番号
	String startEndDate; // 現場開始から終了年月
	String employeeName; // 氏名
	String yearAndMonth; // 年月
	String workDate; // 入場期間
	String profit; // 売上
	String siteRoleName; // 粗利
	String bpBelongCustomerCode; // 所属会社
	String profitAll; // 売上合計
	String siteRoleNameAll; // 粗利合計
	String bpUnitPrice; // BP単価
	String reflectYearAndMonth; // 反映年月
	String customerNo; // 会社番号
	String intoCompanyName; // 会社番号
	String intoCompanyCode; // 会社番号
	String customerContractStatus; // 契约区分
	String salesProgressName; // 営業結果パターン
	String startTime; // 現場開始年月
	String endTime; // 現場終了年月
	String levelCode; // レベルコード
	String point; // ポイント
	Date startDate;// 現場開始時間
	Date endDate;// 現場終了時間

	public String getPoint() {
		return point;
	}

	public void setPoint(String point) {
		this.point = point;
	}

	public String getSalesProgressName() {
		return salesProgressName;
	}

	public void setSalesProgressName(String salesProgressName) {
		this.salesProgressName = salesProgressName;
	}

	public String getCustomerContractStatus() {
		return customerContractStatus;
	}

	public void setCustomerContractStatus(String customerContractStatus) {
		this.customerContractStatus = customerContractStatus;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getIntoCompanyName() {
		return intoCompanyName;
	}

	public void setIntoCompanyName(String intoCompanyName) {
		this.intoCompanyName = intoCompanyName;
	}

	public String getIntoCompanyCode() {
		return intoCompanyCode;
	}

	public void setIntoCompanyCode(String intoCompanyCode) {
		this.intoCompanyCode = intoCompanyCode;
	}

	public String getCustomerNo() {
		return customerNo;
	}

	public void setCustomerNo(String customerNo) {
		this.customerNo = customerNo;
	}

	public String getReflectYearAndMonth() {
		return reflectYearAndMonth;
	}

	public void setReflectYearAndMonth(String reflectYearAndMonth) {
		this.reflectYearAndMonth = reflectYearAndMonth;
	}

	public String getBpUnitPrice() {
		return bpUnitPrice;
	}

	public void setBpUnitPrice(String bpUnitPrice) {
		this.bpUnitPrice = bpUnitPrice;
	}

	public String getProfitAll() {
		return profitAll;
	}

	public void setProfitAll(String profitAll) {
		this.profitAll = profitAll;
	}

	public String getSiteRoleNameAll() {
		return siteRoleNameAll;
	}

	public void setSiteRoleNameAll(String siteRoleNameAll) {
		this.siteRoleNameAll = siteRoleNameAll;
	}

	public String getBpBelongCustomerCode() {
		return bpBelongCustomerCode;
	}

	public void setBpBelongCustomerCode(String bpBelongCustomerCode) {
		this.bpBelongCustomerCode = bpBelongCustomerCode;
	}

	public String getRowNo() {
		return rowNo;
	}

	public void setRowNo(String rowNo) {
		this.rowNo = rowNo;
	}

	public String getEmployeeNo() {
		return employeeNo;
	}

	public void setEmployeeNo(String employeeNo) {
		this.employeeNo = employeeNo;
	}

	public String getAdmissionStartDate() {
		return admissionStartDate;
	}

	public void setAdmissionStartDate(String admissionStartDate) {
		this.admissionStartDate = admissionStartDate;
	}

	public String getAdmissionEndDate() {
		return admissionEndDate;
	}

	public void setAdmissionEndDate(String admissionEndDate) {
		this.admissionEndDate = admissionEndDate;
	}

	public String getEmployeeFristName() {
		return employeeFristName;
	}

	public void setEmployeeFristName(String employeeFristName) {
		this.employeeFristName = employeeFristName;
	}

	public String getEmployeeLastName() {
		return employeeLastName;
	}

	public void setEmployeeLastName(String employeeLastName) {
		this.employeeLastName = employeeLastName;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerAbbreviation() {
		return customerAbbreviation;
	}

	public void setCustomerAbbreviation(String customerAbbreviation) {
		this.customerAbbreviation = customerAbbreviation;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	public String getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getStartEndDate() {
		return startEndDate;
	}

	public void setStartEndDate(String startEndDate) {
		this.startEndDate = startEndDate;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getYearAndMonth() {
		return yearAndMonth;
	}

	public void setYearAndMonth(String yearAndMonth) {
		this.yearAndMonth = yearAndMonth;
	}

	public String getEmployeeStatus() {
		return employeeStatus;
	}

	public void setEmployeeStatus(String employeeStatus) {
		this.employeeStatus = employeeStatus;
	}

	public String getEmployeeFrom() {
		return employeeFrom;
	}

	public void setEmployeeFrom(String employeeFrom) {
		this.employeeFrom = employeeFrom;
	}

	public String getWorkDate() {
		return workDate;
	}

	public void setWorkDate(String workDate) {
		this.workDate = workDate;
	}

	public String getProfit() {
		return profit;
	}

	public void setProfit(String profit) {
		this.profit = profit;
	}

	public String getSiteRoleName() {
		return siteRoleName;
	}

	public void setSiteRoleName(String siteRoleName) {
		this.siteRoleName = siteRoleName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
