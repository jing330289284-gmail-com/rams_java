package jp.co.lyc.cms.model;

import java.util.ArrayList;

public class CustomerInfoModel {

	String customerNo;// お客様番号
	String customerName;// お客様名
	String stationCode;// 本社場所コード
	String stationName;// 本社場所名前
	String establishmentDate;// 設立年月
	String businessStartDate;// 取引開始日
	String levelCode;// お客様ランキングコード
	String levelName;// お客様ランキング
	String listedCompanyFlag;// 上場会社フラグ
	String companyNatureCode;// 会社性質コード
	String url;// URL
	String remark;// 備考
	String updateUser;// 更新者
	String actionType;// 処理区分
	String topCustomerNo;// 上位客様番号
	String employeeName;// 社員番号（要員）
	ArrayList<String> employeeNameList;//
	String siteLocation;// 現場場所
	String siteManager;// 現場責任者
	String unitPrice;// 単価
	String customerRankingName;// お客様ランキング名前
	String companyNatureName;// 会社性質名前
	String topCustomerName;// 上位お客様名前
	String siteLocationCode;// 現場場所コード
	String location;// 現場場所
	String rowNo;// 行番号
	String purchasingManagers;// 購買担当
	String purchasingManagersMail;// メール
	String customerAbbreviation;// お客様略称
	String paymentsiteCode;// 支払サイト
	String capitalStock;// 資本金
	ArrayList<String> locationList;// 同社の現場場所リスト
	ArrayList<String> siteManagerList;// 同社の現場責任者リスト
	ArrayList<String> unitPriceList;// 単価リスト
	String representative;// 代表取締役
	ArrayList<CustomerDepartmentInfoModel> customerDepartmentList; // 部門リスト
	String resultCode;
	AccountInfoModel accountInfo;
	TopCustomerInfoModel topCustomerInfo;
	String admissionStartDate;// 入場期日
	String admissionEndDate;// 退場期日
	String traderPerson;// 取引総人月
	String paymentSiteName;
	String traderPersonFront;
	String traderPersonBack;
	String transactionStatus;
	String capitalStockFront;
	String capitalStockBack;
	String customerDepartmentCode;
	String positionCode;
	String basicContract;
	String response;

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public String getBasicContract() {
		return basicContract;
	}

	public void setBasicContract(String basicContract) {
		this.basicContract = basicContract;
	}

	public String getCustomerDepartmentCode() {
		return customerDepartmentCode;
	}

	public void setCustomerDepartmentCode(String customerDepartmentCode) {
		this.customerDepartmentCode = customerDepartmentCode;
	}

	public String getPositionCode() {
		return positionCode;
	}

	public void setPositionCode(String positionCode) {
		this.positionCode = positionCode;
	}

	public String getTraderPersonFront() {
		return traderPersonFront;
	}

	public void setTraderPersonFront(String traderPersonFront) {
		this.traderPersonFront = traderPersonFront;
	}

	public String getTraderPersonBack() {
		return traderPersonBack;
	}

	public void setTraderPersonBack(String traderPersonBack) {
		this.traderPersonBack = traderPersonBack;
	}

	public String getTransactionStatus() {
		return transactionStatus;
	}

	public void setTransactionStatus(String transactionStatus) {
		this.transactionStatus = transactionStatus;
	}

	public String getCapitalStockFront() {
		return capitalStockFront;
	}

	public void setCapitalStockFront(String capitalStockFront) {
		this.capitalStockFront = capitalStockFront;
	}

	public String getCapitalStockBack() {
		return capitalStockBack;
	}

	public void setCapitalStockBack(String capitalStockBack) {
		this.capitalStockBack = capitalStockBack;
	}

	public String getPaymentSiteName() {
		return paymentSiteName;
	}

	public void setPaymentSiteName(String paymentSiteName) {
		this.paymentSiteName = paymentSiteName;
	}

	public String getTraderPerson() {
		return traderPerson;
	}

	public void setTraderPerson(String traderPerson) {
		this.traderPerson = traderPerson;
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

	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	public String getCapitalStock() {
		return capitalStock;
	}

	public void setCapitalStock(String capitalStock) {
		this.capitalStock = capitalStock;
	}

	public String getSiteLocationCode() {
		return siteLocationCode;
	}

	public void setSiteLocationCode(String siteLocationCode) {
		this.siteLocationCode = siteLocationCode;
	}

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	public AccountInfoModel getAccountInfo() {
		return accountInfo;
	}

	public void setAccountInfo(AccountInfoModel accountInfo) {
		this.accountInfo = accountInfo;
	}

	public TopCustomerInfoModel getTopCustomerInfo() {
		return topCustomerInfo;
	}

	public void setTopCustomerInfo(TopCustomerInfoModel topCustomerInfo) {
		this.topCustomerInfo = topCustomerInfo;
	}

	public String getPaymentsiteCode() {
		return paymentsiteCode;
	}

	public void setPaymentsiteCode(String paymentsiteCode) {
		this.paymentsiteCode = paymentsiteCode;
	}

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public ArrayList<CustomerDepartmentInfoModel> getCustomerDepartmentList() {
		return customerDepartmentList;
	}

	public void setCustomerDepartmentList(ArrayList<CustomerDepartmentInfoModel> customerDepartmentList) {
		this.customerDepartmentList = customerDepartmentList;
	}

	public String getRepresentative() {
		return representative;
	}

	public void setRepresentative(String representative) {
		this.representative = representative;
	}

	public String getCustomerAbbreviation() {
		return customerAbbreviation;
	}

	public void setCustomerAbbreviation(String customerAbbreviation) {
		this.customerAbbreviation = customerAbbreviation;
	}

	public String getBusinessStartDate() {
		return businessStartDate;
	}

	public void setBusinessStartDate(String businessStartDate) {
		this.businessStartDate = businessStartDate;
	}

	public ArrayList<String> getLocationList() {
		return locationList;
	}

	public void setLocationList(ArrayList<String> locationList) {
		this.locationList = locationList;
	}

	public ArrayList<String> getSiteManagerList() {
		return siteManagerList;
	}

	public void setSiteManagerList(ArrayList<String> siteManagerList) {
		this.siteManagerList = siteManagerList;
	}

	public ArrayList<String> getUnitPriceList() {
		return unitPriceList;
	}

	public void setUnitPriceList(ArrayList<String> unitPriceList) {
		this.unitPriceList = unitPriceList;
	}

	public ArrayList<String> getEmployeeNameList() {
		return employeeNameList;
	}

	public void setEmployeeNameList(ArrayList<String> employeeNameList) {
		this.employeeNameList = employeeNameList;
	}

	public String getRowNo() {
		return rowNo;
	}

	public void setRowNo(String rowNo) {
		this.rowNo = rowNo;
	}

	public String getCustomerRankingName() {
		return customerRankingName;
	}

	public void setCustomerRankingName(String customerRankingName) {
		this.customerRankingName = customerRankingName;
	}

	public String getCompanyNatureName() {
		return companyNatureName;
	}

	public void setCompanyNatureName(String companyNatureName) {
		this.companyNatureName = companyNatureName;
	}

	public String getTopCustomerName() {
		return topCustomerName;
	}

	public void setTopCustomerName(String topCustomerName) {
		this.topCustomerName = topCustomerName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getTopCustomerNo() {
		return topCustomerNo;
	}

	public void setTopCustomerNo(String topCustomerNo) {
		this.topCustomerNo = topCustomerNo;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getSiteLocation() {
		return siteLocation;
	}

	public void setSiteLocation(String siteLocation) {
		this.siteLocation = siteLocation;
	}

	public String getSiteManager() {
		return siteManager;
	}

	public void setSiteManager(String siteManager) {
		this.siteManager = siteManager;
	}

	public String getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getEstablishmentDate() {
		return establishmentDate;
	}

	public void setEstablishmentDate(String establishmentDate) {
		this.establishmentDate = establishmentDate;
	}

	public String getActionType() {
		return actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	public String getCustomerNo() {
		return customerNo;
	}

	public void setCustomerNo(String customerNo) {
		this.customerNo = customerNo;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getStationCode() {
		return stationCode;
	}

	public void setStationCode(String stationCode) {
		this.stationCode = stationCode;
	}

	public String getCompanyNatureCode() {
		return companyNatureCode;
	}

	public void setCompanyNatureCode(String companyNatureCode) {
		this.companyNatureCode = companyNatureCode;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public String getLevelCode() {
		return levelCode;
	}

	public void setLevelCode(String levelCode) {
		this.levelCode = levelCode;
	}

	public String getListedCompanyFlag() {
		return listedCompanyFlag;
	}

	public void setListedCompanyFlag(String listedCompanyFlag) {
		this.listedCompanyFlag = listedCompanyFlag;
	}

	public String getPurchasingManagers() {
		return purchasingManagers;
	}

	public void setPurchasingManagers(String purchasingManagers) {
		this.purchasingManagers = purchasingManagers;
	}

	public String getPurchasingManagersMail() {
		return purchasingManagersMail;
	}

	public void setPurchasingManagersMail(String purchasingManagersMail) {
		this.purchasingManagersMail = purchasingManagersMail;
	}

}
