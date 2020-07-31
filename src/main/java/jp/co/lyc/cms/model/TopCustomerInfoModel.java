package jp.co.lyc.cms.model;

public class TopCustomerInfoModel {

	String topCustomerNo;//上位お客様番号
	String topCustomerName;//上位お客様名前
	String url;//URL
	String remark;//備考
	String updateUser;//更新者
	String shoriKbn;//処理区分
	
	public String getShoriKbn() {
		return shoriKbn;
	}
	public void setShoriKbn(String shoriKbn) {
		this.shoriKbn = shoriKbn;
	}
	public String getTopCustomerNo() {
		return topCustomerNo;
	}
	public void setTopCustomerNo(String topCustomerNo) {
		this.topCustomerNo = topCustomerNo;
	}
	public String getTopCustomerName() {
		return topCustomerName;
	}
	public void setTopCustomerName(String topCustomerName) {
		this.topCustomerName = topCustomerName;
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
	
	
}
