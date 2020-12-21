package jp.co.lyc.cms.model;

/**
 * 要員情報<br>
 * <br>
 * 要員送信確認画面の要員情報<br>
 * */
public class AllEmployName {
	
	/** 要員名前*/
	public String employeeName;

	/** 要員所属*/
	public String employeeNo;
	
	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getEmployeeNo() {
		return employeeNo;
	}

	public void setEmployeeNo(String employeeNo) {
		this.employeeNo = employeeNo;
	}
}
