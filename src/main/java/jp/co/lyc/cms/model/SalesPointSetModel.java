package jp.co.lyc.cms.model;

public class SalesPointSetModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2028159323401651353L;
	String no;// 番号
	String employee;// 社員区分
	String newMember;// 新人区分
	String customerContract;// 契約区分
	String level;// お客様レベル
	String bpGrossProfit;// BP粗利
	String specialPoint;// 特別ポイント条件
	String point;// ポイント
	String specialPointNo; // 特別ポイント
	String remark;// 備考

	public String getSpecialPointNo() {
		return specialPointNo;
	}

	public void setSpecialPointNo(String specialPointNo) {
		this.specialPointNo = specialPointNo;
	}

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

	public String getBpGrossProfit() {
		return bpGrossProfit;
	}

	public void setBpGrossProfit(String bpGrossProfit) {
		this.bpGrossProfit = bpGrossProfit;
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
