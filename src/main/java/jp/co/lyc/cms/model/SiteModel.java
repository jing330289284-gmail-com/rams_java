package jp.co.lyc.cms.model;

public class SiteModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2028159323401651353L;
	String employeeNo;// 社員番号
	String systemName;// システム名
	String location;// 場所
	String customerNo;// お客様コード
	String topCustomerNo;// トップお客様コード
	String developLanguageCode;// 開発言語コード
	String unitPrice;// 単価
	String developLanguageName;// 開発言語名称
	String siteRoleCode;// 役割コード
	String siteManager;// 現場責任者
	String admissionStartDate;// 入場日付
	String admissionEndDate;// 退場日付
	String payOffRange1;// 精算時間1
	String payOffRange2;// 精算時間2
	String workDate;// 現場期間
	String customerName;// お客様名称
	String topCustomerName;// トップお客様名称
	String siteRoleName;// 役割名称
	String relatedEmployees;// 関連社員1+2+3+4
	String related1Employees;// 関連社員1
	String related2Employees;// 関連社員2
	String related3Employees;// 関連社員3
	String related4Employees;// 関連社員4
	String levelCode;// 評価コード
	String stationCode;
	String remark;// 備考
	String updateUser;// 更新者
	String levelName;// 評価名称
	String typeOfIndustryName;// 業種名称
	String typeOfIndustryCode;// 業種コード
	String checkDate;// 前回現場の退場日付
	String dailyCalculationStatus;// 日割計算区分
	String workState;// 現場状態

	public String getStationCode() {
		return stationCode;
	}

	public void setStationCode(String stationCode) {
		this.stationCode = stationCode;
	}

	public String getDailyCalculationStatus() {
		return dailyCalculationStatus;
	}

	public void setDailyCalculationStatus(String dailyCalculationStatus) {
		this.dailyCalculationStatus = dailyCalculationStatus;
	}

	public String getWorkState() {
		return workState;
	}

	public void setWorkState(String workState) {
		this.workState = workState;
	}

	public String getCheckDate() {
		return checkDate;
	}

	public void setCheckDate(String checkDate) {
		this.checkDate = checkDate;
	}

	public String getTypeOfIndustryCode() {
		return typeOfIndustryCode;
	}

	public void setTypeOfIndustryCode(String typeOfIndustryCode) {
		this.typeOfIndustryCode = typeOfIndustryCode;
	}

	public String getEmployeeNo() {
		return employeeNo;
	}

	public void setEmployeeNo(String employeeNo) {
		this.employeeNo = employeeNo;
	}

	public String getSystemName() {
		return systemName;
	}

	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}

	public String getCustomerNo() {
		return customerNo;
	}

	public void setCustomerNo(String customerNo) {
		this.customerNo = customerNo;
	}

	public String getTopCustomerNo() {
		return topCustomerNo;
	}

	public void setTopCustomerNo(String topCustomerNo) {
		this.topCustomerNo = topCustomerNo;
	}

	public String getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getSiteRoleCode() {
		return siteRoleCode;
	}

	public void setSiteRoleCode(String siteRoleCode) {
		this.siteRoleCode = siteRoleCode;
	}

	public String getSiteManager() {
		return siteManager;
	}

	public void setSiteManager(String siteManager) {
		this.siteManager = siteManager;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getWorkDate() {
		return workDate;
	}

	public void setWorkDate(String workDate) {
		this.workDate = workDate;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getTopCustomerName() {
		return topCustomerName;
	}

	public void setTopCustomerName(String topCustomerName) {
		this.topCustomerName = topCustomerName;
	}

	public String getSiteRoleName() {
		return siteRoleName;
	}

	public void setSiteRoleName(String siteRoleName) {
		this.siteRoleName = siteRoleName;
	}

	public String getLevelCode() {
		return levelCode;
	}

	public void setLevelCode(String levelCode) {
		this.levelCode = levelCode;
	}

	public String getPayOffRange1() {
		return payOffRange1;
	}

	public void setPayOffRange1(String payOffRange1) {
		this.payOffRange1 = payOffRange1;
	}

	public String getPayOffRange2() {
		return payOffRange2;
	}

	public void setPayOffRange2(String payOffRange2) {
		this.payOffRange2 = payOffRange2;
	}

	public String getDevelopLanguageCode() {
		return developLanguageCode;
	}

	public void setDevelopLanguageCode(String developLanguageCode) {
		this.developLanguageCode = developLanguageCode;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public String getRelated1Employees() {
		return related1Employees;
	}

	public void setRelated1Employees(String related1Employees) {
		this.related1Employees = related1Employees;
	}

	public String getRelated2Employees() {
		return related2Employees;
	}

	public void setRelated2Employees(String related2Employees) {
		this.related2Employees = related2Employees;
	}

	public String getRelated3Employees() {
		return related3Employees;
	}

	public void setRelated3Employees(String related3Employees) {
		this.related3Employees = related3Employees;
	}

	public String getDevelopLanguageName() {
		return developLanguageName;
	}

	public void setDevelopLanguageName(String developLanguageName) {
		this.developLanguageName = developLanguageName;
	}

	public String getRelated4Employees() {
		return related4Employees;
	}

	public void setRelated4Employees(String related4Employees) {
		this.related4Employees = related4Employees;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRelatedEmployees() {
		return relatedEmployees;
	}

	public void setRelatedEmployees(String relatedEmployees) {
		this.relatedEmployees = relatedEmployees;
	}

	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	public String getTypeOfIndustryName() {
		return typeOfIndustryName;
	}

	public void setTypeOfIndustryName(String typeOfIndustryName) {
		this.typeOfIndustryName = typeOfIndustryName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

}
