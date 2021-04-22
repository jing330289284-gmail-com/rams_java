package jp.co.lyc.cms.model;

import java.io.Serializable;

public class SendRepotsListName implements Serializable {

	private static final long serialVersionUID = -2028159323401651353L;

	String storageListName1;
	String oldStorageListName1;
	String storageListName2;
	String oldStorageListName2;
	String storageListName3;
	String oldStorageListName3;
	String UpdateUser;
	String storageListName;
	String oldStorageListName;
	String subChargeMailList;
	String candidateInChargeList;
	public String getUpdateUser() {
		return UpdateUser;
	}
	public String getCandidateInChargeList() {
		return candidateInChargeList;
	}


	public void setCandidateInChargeList(String candidateInChargeList) {
		this.candidateInChargeList = candidateInChargeList;
	}


	public void setUpdateUser(String updateUser) {
		UpdateUser = updateUser;
	}


	public String getStorageListName() {
		return storageListName;
	}


	public void setStorageListName(String storageListName) {
		this.storageListName = storageListName;
	}
	public String getStorageListName1() {
		return storageListName1;
	}


	public void setStorageListName1(String storageListName1) {
		this.storageListName1 = storageListName1;
	}


	public String getOldStorageListName1() {
		return oldStorageListName1;
	}


	public void setOldStorageListName(String oldStorageListName) {
		this.oldStorageListName = oldStorageListName;
	}
	public String getOldStorageListName() {
		return oldStorageListName;
	}


	public void setOldStorageListName1(String oldStorageListName1) {
		this.oldStorageListName1 = oldStorageListName1;
	}

	public String getStorageListName2() {
		return storageListName2;
	}


	public void setStorageListName2(String storageListName2) {
		this.storageListName2 = storageListName2;
	}


	public String getOldStorageListName2() {
		return oldStorageListName2;
	}


	public void setOldStorageListName2(String oldStorageListName2) {
		this.oldStorageListName2 = oldStorageListName2;
	}


	public String getStorageListName3() {
		return storageListName3;
	}


	public void setStorageListName3(String storageListName3) {
		this.storageListName3 = storageListName3;
	}


	public String getOldStorageListName3() {
		return oldStorageListName3;
	}


	public void setOldStorageListName3(String oldStorageListName3) {
		this.oldStorageListName3 = oldStorageListName3;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public String getSubChargeMailList() {
		return subChargeMailList;
	}


	public void setSubChargeMailList(String subChargeMailList) {
		this.subChargeMailList = subChargeMailList;
	}
}
