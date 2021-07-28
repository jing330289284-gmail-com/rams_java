package jp.co.lyc.cms.model;

public class BpInfoModel {

	/**
	 * 
	 */
	public String rowNo;

	public String bpEmployeeNo;// 社員番号

	public String oldUnitPriceStartMonth;// 社員番号

	public String actionType;// 処理区分

	public String bpBelongCustomerCode;// BP所属

	public String bpUnitPrice;// BP単価

	public String bpSalesProgressCode;// 営業状況

	public String bpRemark;// 備考

	public String unitPriceStartMonth;// 単価開始年月

	public String bpOtherCompanyAdmissionEndDate;// 所属現場終年月

	public String updateUser;// 更新者

	public String getOldUnitPriceStartMonth() {
		return oldUnitPriceStartMonth;
	}

	public void setOldUnitPriceStartMonth(String oldUnitPriceStartMonth) {
		this.oldUnitPriceStartMonth = oldUnitPriceStartMonth;
	}

	public String getRowNo() {
		return rowNo;
	}

	public void setRowNo(String rowNo) {
		this.rowNo = rowNo;
	}

	public String getBpEmployeeNo() {
		return bpEmployeeNo;
	}

	public String getUnitPriceStartMonth() {
		return unitPriceStartMonth;
	}

	public void setUnitPriceStartMonth(String unitPriceStartMonth) {
		this.unitPriceStartMonth = unitPriceStartMonth;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public void setBpEmployeeNo(String bpEmployeeNo) {
		this.bpEmployeeNo = bpEmployeeNo;
	}

	public String getActionType() {
		return actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	public String getBpBelongCustomerCode() {
		return bpBelongCustomerCode;
	}

	public void setBpBelongCustomerCode(String bpBelongCustomerCode) {
		this.bpBelongCustomerCode = bpBelongCustomerCode;
	}

	public String getBpUnitPrice() {
		return bpUnitPrice;
	}

	public void setBpUnitPrice(String bpUnitPrice) {
		this.bpUnitPrice = bpUnitPrice;
	}

	public String getBpSalesProgressCode() {
		return bpSalesProgressCode;
	}

	public void setBpSalesProgressCode(String bpSalesProgressCode) {
		this.bpSalesProgressCode = bpSalesProgressCode;
	}

	public String getBpRemark() {
		return bpRemark;
	}

	public void setBpRemark(String bpRemark) {
		this.bpRemark = bpRemark;
	}

	public String getBpOtherCompanyAdmissionEndDate() {
		return bpOtherCompanyAdmissionEndDate;
	}

	public void setBpOtherCompanyAdmissionEndDate(String bpOtherCompanyAdmissionEndDate) {
		this.bpOtherCompanyAdmissionEndDate = bpOtherCompanyAdmissionEndDate;
	}

	/*
	 * public String getUpdateUser() { return
	 * getSession().getAttribute("employeeName").toString(); }
	 * 
	 * public void setUpdateUser(String updateUser) { this.updateUser = updateUser;
	 * }
	 */

	/*
	 * public String getCreateTime() { return createTime; }
	 * 
	 * public void setCreateTime(String createTime) { this.createTime = createTime;
	 * }
	 * 
	 * public String getUpdateTime() { return updateTime; }
	 * 
	 * public void setUpdateTime(String updateTime) { this.updateTime = updateTime;
	 * }
	 */
}