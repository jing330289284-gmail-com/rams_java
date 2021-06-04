package jp.co.lyc.cms.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.dao.DataAccessResourceFailureException;

public class SalesSituationModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2028159323401651353L;

	/* 現在の日付 */
	Date curDate;
	/* 社員営業され日付 */
	String salesDate;
	/* 面談情報更新処理用 */
	String salesDateUpdate;

	String employeeNo;
	String salesYearAndMonth;
	String interviewDate1;
	String stationCode;
	String interviewCustomer1;
	String interviewDate2;
	String stationCode1;
	String stationCode2;
	String interviewCustomer2;
	String hopeLowestPrice;
	String hopeHighestPrice;
	String salesProgressCode;
	String remark;
	String remark1;
	String remark2;
	String salesStaff;
	Date updateTime;
	Date createTime;
	String updateUser;

	String employeeName;
	String nearestStation;
	String developLanguageCode;
	String developLanguageName;

	String developLanguage;
	String siteRoleCode;
	String siteRoleName;
	String unitPrice;
	String price;
	String salesPriorityStatus;
	String customer;
	String admissionStartDate;
	String customerNo;
	String admissionEndDate;
	String scheduledEndDate;
	String resumeInfo1;// 履歴書情報1
	String resumeInfo2;// 履歴書情報2
	String resumeName1;// 履歴書情報1
	String resumeName2;// 履歴書情報2
	int rowNo;//
	String nowCustomer;
	String customerContractStatus;
	String purchasingManagers2;
	String positionCode2;
	String purchasingManagersMail2;
	String employeeFullName;
	String genderStatus;
	String nationalityName;
	String employeeStatus;
	String birthday;
	String yearsOfExperience;
	String projectPhase;
	String japaneseLevelCode;
	String englishLevelCode;
	String age;
	String japaneaseConversationLevel;
	String englishConversationLevel;
	String developLanguage1;
	String developLanguage2;
	String developLanguage3;
	String developLanguage4;
	String developLanguage5;

	String confirmPrice;
	String confirmCustomer;
	ArrayList<String> employeeNoList;
	ArrayList<String> resumeInfoList;
	ArrayList<String> resumeInfo1List;
	ArrayList<String> resumeInfo2List;
	String text;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getResumeName1() {
		return resumeName1;
	}

	public void setResumeName1(String resumeName1) {
		this.resumeName1 = resumeName1;
	}

	public String getResumeName2() {
		return resumeName2;
	}

	public void setResumeName2(String resumeName2) {
		this.resumeName2 = resumeName2;
	}

	public ArrayList<String> getResumeInfoList() {
		return resumeInfoList;
	}

	public void setResumeInfoList(ArrayList<String> resumeInfoList) {
		this.resumeInfoList = resumeInfoList;
	}

	public String getSiteRoleName() {
		return siteRoleName;
	}

	public void setSiteRoleName(String siteRoleName) {
		this.siteRoleName = siteRoleName;
	}

	public ArrayList<String> getResumeInfo1List() {
		return resumeInfo1List;
	}

	public void setResumeInfo1List(ArrayList<String> resumeInfo1List) {
		this.resumeInfo1List = resumeInfo1List;
	}

	public ArrayList<String> getResumeInfo2List() {
		return resumeInfo2List;
	}

	public void setResumeInfo2List(ArrayList<String> resumeInfo2List) {
		this.resumeInfo2List = resumeInfo2List;
	}

	public ArrayList<String> getEmployeeNoList() {
		return employeeNoList;
	}

	public void setEmployeeNoList(ArrayList<String> employeeNoList) {
		this.employeeNoList = employeeNoList;
	}

	public String getConfirmPrice() {
		return confirmPrice;
	}

	public void setConfirmPrice(String confirmPrice) {
		this.confirmPrice = confirmPrice;
	}

	public String getConfirmCustomer() {
		return confirmCustomer;
	}

	public void setConfirmCustomer(String confirmCustomer) {
		this.confirmCustomer = confirmCustomer;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getSalesDate() {
		return salesDate;
	}

	public void setSalesDate(String salesDate) {
		this.salesDate = salesDate;
	}

	public Date getCurDate() {
		return curDate;
	}

	public void setCurDate(Date curDate) {
		this.curDate = curDate;
	}

	public String getSalesDateUpdate() {
		return salesDateUpdate;
	}

	public void setSalesDateUpdate(String salesDateUpdate) {
		this.salesDateUpdate = salesDateUpdate;
	}

	/* 2020/12/09 START 張棟 */
	/* 稼働開始 */
	String theMonthOfStartWork;

	public String getTheMonthOfStartWork() {
		return theMonthOfStartWork;
	}

	public void setTheMonthOfStartWork(String theMonthOfStartWork) {
		this.theMonthOfStartWork = theMonthOfStartWork;
	}

	/* 2020/12/09 END 張棟 */

	public String getStationCode1() {
		return stationCode1;
	}

	public void setStationCode1(String stationCode1) {
		this.stationCode1 = stationCode1;
	}

	public String getDevelopLanguage1() {
		return developLanguage1;
	}

	public void setDevelopLanguage1(String developLanguage1) {
		this.developLanguage1 = developLanguage1;
	}

	public String getDevelopLanguage2() {
		return developLanguage2;
	}

	public void setDevelopLanguage2(String developLanguage2) {
		this.developLanguage2 = developLanguage2;
	}

	public String getDevelopLanguage3() {
		return developLanguage3;
	}

	public void setDevelopLanguage3(String developLanguage3) {
		this.developLanguage3 = developLanguage3;
	}

	public String getDevelopLanguage4() {
		return developLanguage4;
	}

	public void setDevelopLanguage4(String developLanguage4) {
		this.developLanguage4 = developLanguage4;
	}

	public String getDevelopLanguage5() {
		return developLanguage5;
	}

	public void setDevelopLanguage5(String developLanguage5) {
		this.developLanguage5 = developLanguage5;
	}

	public String getJapaneaseConversationLevel() {
		return japaneaseConversationLevel;
	}

	public void setJapaneaseConversationLevel(String japaneaseConversationLevel) {
		this.japaneaseConversationLevel = japaneaseConversationLevel;
	}

	public String getEnglishConversationLevel() {
		return englishConversationLevel;
	}

	public void setEnglishConversationLevel(String englishConversationLevel) {
		this.englishConversationLevel = englishConversationLevel;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getJapaneseLevelCode() {
		return japaneseLevelCode;
	}

	public void setJapaneseLevelCode(String japaneseLevelCode) {
		this.japaneseLevelCode = japaneseLevelCode;
	}

	public String getEnglishLevelCode() {
		return englishLevelCode;
	}

	public void setEnglishLevelCode(String englishLevelCode) {
		this.englishLevelCode = englishLevelCode;
	}

	public String getEmployeeFullName() {
		return employeeFullName;
	}

	public void setEmployeeFullName(String employeeFullName) {
		this.employeeFullName = employeeFullName;
	}

	public String getGenderStatus() {
		return genderStatus;
	}

	public void setGenderStatus(String genderStatus) {
		this.genderStatus = genderStatus;
	}

	public String getNationalityName() {
		return nationalityName;
	}

	public void setNationalityName(String nationalityName) {
		this.nationalityName = nationalityName;
	}

	public String getEmployeeStatus() {
		return employeeStatus;
	}

	public void setEmployeeStatus(String employeeStatus) {
		this.employeeStatus = employeeStatus;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getYearsOfExperience() {
		return yearsOfExperience;
	}

	public void setYearsOfExperience(String yearsOfExperience) {
		this.yearsOfExperience = yearsOfExperience;
	}

	public String getProjectPhase() {
		return projectPhase;
	}

	public void setProjectPhase(String projectPhase) {
		this.projectPhase = projectPhase;
	}

	public String getPurchasingManagers2() {
		return purchasingManagers2;
	}

	public void setPurchasingManagers2(String purchasingManagers2) {
		this.purchasingManagers2 = purchasingManagers2;
	}

	public String getPositionCode2() {
		return positionCode2;
	}

	public void setPositionCode2(String positionCode2) {
		this.positionCode2 = positionCode2;
	}

	public String getPurchasingManagersMail2() {
		return purchasingManagersMail2;
	}

	public void setPurchasingManagersMail2(String purchasingManagersMail2) {
		this.purchasingManagersMail2 = purchasingManagersMail2;
	}

	public String getCustomerContractStatus() {
		return customerContractStatus;
	}

	public void setCustomerContractStatus(String customerContractStatus) {
		this.customerContractStatus = customerContractStatus;
	}

	public String getNowCustomer() {
		return nowCustomer;
	}

	public void setNowCustomer(String nowCustomer) {
		this.nowCustomer = nowCustomer;
	}

	public int getRowNo() {
		return rowNo;
	}

	public void setRowNo(int rowNo) {
		this.rowNo = rowNo;
	}

	public String getResumeInfo1() {
		return resumeInfo1;
	}

	public void setResumeInfo1(String resumeInfo1) {
		this.resumeInfo1 = resumeInfo1;
	}

	public String getResumeInfo2() {
		return resumeInfo2;
	}

	public void setResumeInfo2(String resumeInfo2) {
		this.resumeInfo2 = resumeInfo2;
	}

	public String getAdmissionEndDate() {
		return admissionEndDate;
	}

	public void setAdmissionEndDate(String admissionEndDate) {
		this.admissionEndDate = admissionEndDate;
	}

	public String getCustomerNo() {
		return customerNo;
	}

	public void setCustomerNo(String customerNo) {
		this.customerNo = customerNo;
	}

	public String getAdmissionStartDate() {
		return admissionStartDate;
	}

	public void setAdmissionStartDate(String admissionStartDate) {
		this.admissionStartDate = admissionStartDate;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getSalesPriorityStatus() {
		return salesPriorityStatus;
	}

	public void setSalesPriorityStatus(String salesPriorityStatus) {
		this.salesPriorityStatus = salesPriorityStatus;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getNearestStation() {
		return nearestStation;
	}

	public void setNearestStation(String nearestStation) {
		this.nearestStation = nearestStation;
	}

	public String getDevelopLanguage() {
		return developLanguage;
	}

	public void setDevelopLanguage(String developLanguage) {
		this.developLanguage = developLanguage;
	}

	public String getSiteRoleCode() {
		return siteRoleCode;
	}

	public void setSiteRoleCode(String siteRoleCode) {
		this.siteRoleCode = siteRoleCode;
	}

	public String getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getEmployeeNo() {
		return employeeNo;
	}

	public void setEmployeeNo(String employeeNo) {
		this.employeeNo = employeeNo;
	}

	public String getSalesYearAndMonth() {
		return salesYearAndMonth;
	}

	public void setSalesYearAndMonth(String salesYearAndMonth) {
		this.salesYearAndMonth = salesYearAndMonth;
	}

	public String getInterviewDate1() {
		return interviewDate1;
	}

	public void setInterviewDate1(String interviewDate1) {
		this.interviewDate1 = interviewDate1;
	}

	public String getStationCode() {
		return stationCode;
	}

	public void setStationCode(String stationCode) {
		this.stationCode = stationCode;
	}

	public String getInterviewCustomer1() {
		return interviewCustomer1;
	}

	public void setInterviewCustomer1(String interviewCustomer1) {
		this.interviewCustomer1 = interviewCustomer1;
	}

	public String getInterviewDate2() {
		return interviewDate2;
	}

	public void setInterviewDate2(String interviewDate2) {
		this.interviewDate2 = interviewDate2;
	}

	public String getStationCode2() {
		return stationCode2;
	}

	public void setStationCode2(String stationCode2) {
		this.stationCode2 = stationCode2;
	}

	public String getInterviewCustomer2() {
		return interviewCustomer2;
	}

	public void setInterviewCustomer2(String interviewCustomer2) {
		this.interviewCustomer2 = interviewCustomer2;
	}

	public String getHopeLowestPrice() {
		return hopeLowestPrice;
	}

	public void setHopeLowestPrice(String hopeLowestPrice) {
		this.hopeLowestPrice = hopeLowestPrice;
	}

	public String getHopeHighestPrice() {
		return hopeHighestPrice;
	}

	public void setHopeHighestPrice(String hopeHighestPrice) {
		this.hopeHighestPrice = hopeHighestPrice;
	}

	public String getSalesProgressCode() {
		return salesProgressCode;
	}

	public void setSalesProgressCode(String salesProgressCode) {
		this.salesProgressCode = salesProgressCode;
	}

	public String getRemark1() {
		return remark1;
	}

	public void setRemark1(String remark1) {
		this.remark1 = remark1;
	}

	public String getRemark2() {
		return remark2;
	}

	public void setRemark2(String remark2) {
		this.remark2 = remark2;
	}

	public String getSalesStaff() {
		return salesStaff;
	}

	public void setSalesStaff(String salesStaff) {
		this.salesStaff = salesStaff;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public String getDevelopLanguageCode() {
		return developLanguageCode;
	}

	public void setDevelopLanguageCode(String developLanguageCode) {
		this.developLanguageCode = developLanguageCode;
	}

	public String getDevelopLanguageName() {
		return developLanguageName;
	}

	public void setDevelopLanguageName(String developLanguageName) {
		this.developLanguageName = developLanguageName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getScheduledEndDate() {
		return scheduledEndDate;
	}

	public void setScheduledEndDate(String scheduledEndDate) {
		this.scheduledEndDate = scheduledEndDate;
	}

}
