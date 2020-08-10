package jp.co.lyc.cms.model;

import java.io.Serializable;

public class CustomerDepartmentTypeMasterModel implements Serializable {
	

	String customerDepartmentCode;//コード 
	String customerDepartmentName;//名称
	String customerDepartmenttypeRemark;//　備考
	String updateUser;//ログインの社員

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
	public String getCustomerDepartmenttypeRemark() {
		return customerDepartmenttypeRemark;
	}
	public void setCustomerDepartmenttypeRemark(String customerDepartmenttypeRemark) {
		this.customerDepartmenttypeRemark = customerDepartmenttypeRemark;
	}
	public String getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	private static final long serialVersionUID = -2028159323401651353L;
	
}
