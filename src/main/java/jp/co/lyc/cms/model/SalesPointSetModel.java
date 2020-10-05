package jp.co.lyc.cms.model;

public class SalesPointSetModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2028159323401651353L;
	String no;//番号
	String employee;//社員区分
	String newMember;//新人区分
	String customerContract;//契約区分
	String level;//お客様レベル
	String salesPuttern;//営業結果パターン
	String specialPoint;//特別ポイント条件
	String point;//ポイント
	String remark;//備考
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getEmployee() {
		return employee;
	}
	public void setEmployee(String employee) {
		this.employee = employee;
	}
	public String getNewMember() {
		return newMember;
	}
	public void setNewMember(String newMember) {
		this.newMember = newMember;
	}
	public String getCustomerContract() {
		return customerContract;
	}
	public void setCustomerContract(String customerContract) {
		this.customerContract = customerContract;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getSalesPuttern() {
		return salesPuttern;
	}
	public void setSalesPuttern(String salesPuttern) {
		this.salesPuttern = salesPuttern;
	}
	public String getSpecialPoint() {
		return specialPoint;
	}
	public void setSpecialPoint(String specialPoint) {
		this.specialPoint = specialPoint;
	}
	public String getPoint() {
		return point;
	}
	public void setPoint(String point) {
		this.point = point;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}