package jp.co.lyc.cms.model;

public class CustomerDepartmentInfoModel {

	String customerNo;//お客様番号
	String customerDepartmentCode;//部門番号
	String customerDepartmentName;//部門名称
	String position;//職位
	String responsiblePerson;//責任者
	String mail;//メール
	String updateuser;//更新者
	String rowNo;//行番号
	
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
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getResponsiblePerson() {
		return responsiblePerson;
	}
	public void setResponsiblePerson(String responsiblePerson) {
		this.responsiblePerson = responsiblePerson;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getUpdateuser() {
		return updateuser;
	}
	public void setUpdateuser(String updateuser) {
		this.updateuser = updateuser;
	}
	
	
}
