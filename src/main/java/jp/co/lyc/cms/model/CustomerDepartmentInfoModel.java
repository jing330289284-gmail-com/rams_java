package jp.co.lyc.cms.model;

import jp.co.lyc.cms.common.BaseController;

public class CustomerDepartmentInfoModel{

	String customerNo;//お客様番号
	String customerDepartmentCode;//部門番号
	String customerDepartmentName;//部門名称
	String positionCode;//職位コード
	String positionName;//職位
	String responsiblePerson;//責任者
	String customerDepartmentMail;//メール
	String updateUser;//更新者
	String rowNo;//行番号
	String actionType;//処理区分
	String resultCode;//処理結果
	
	public String getPositionName() {
		return positionName;
	}
	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}
	public String getResultCode() {
		return resultCode;
	}
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
	public String getActionType() {
		return actionType;
	}
	public void setActionType(String actionType) {
		this.actionType = actionType;
	}
	public String getRowNo() {
		return rowNo;
	}
	public void setRowNo(String rowNo) {
		this.rowNo = rowNo;
	}
	public String getCustomerNo() {
		return customerNo;
	}
	public void setCustomerNo(String customerNo) {
		this.customerNo = customerNo;
	}
	public String getCustomerDepartmentCode() {
		return customerDepartmentCode;
	}
	public void setCustomerDepartmentCode(String customerDepartmentCode) {
		this.customerDepartmentCode = customerDepartmentCode;
	}
	public String getCustomerDepartmentName() {
		return customerDepartmentName;
	}
	public void setCustomerDepartmentName(String customerDepartmentName) {
		this.customerDepartmentName = customerDepartmentName;
	}
	public String getPositionCode() {
		return positionCode;
	}
	public void setPositionCode(String positionCode) {
		this.positionCode = positionCode;
	}
	public String getResponsiblePerson() {
		return responsiblePerson;
	}
	public void setResponsiblePerson(String responsiblePerson) {
		this.responsiblePerson = responsiblePerson;
	}
	public String getCustomerDepartmentMail() {
		return customerDepartmentMail;
	}
	public void setCustomerDepartmentMail(String customerDepartmentMail) {
		this.customerDepartmentMail = customerDepartmentMail;
	}
	public String getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	
}
