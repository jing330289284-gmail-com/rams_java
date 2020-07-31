package jp.co.lyc.cms.model;

import java.util.ArrayList;
import java.util.HashMap;

public class CustomerInfoSelectModel {
	
	ArrayList<HashMap<String, String>> customerRanking;//お客様ランキング選択肢
	ArrayList<HashMap<String, String>> companyNature;//お客様性質選択肢
	ArrayList<HashMap<String, String>> position;//職位選択肢

	public ArrayList<HashMap<String, String>> getPosition() {
		return position;
	}

	public void setPosition(ArrayList<HashMap<String, String>> position) {
		this.position = position;
	}

	public ArrayList<HashMap<String, String>> getCustomerRanking() {
		return customerRanking;
	}

	public void setCustomerRanking(ArrayList<HashMap<String, String>> customerRanking) {
		this.customerRanking = customerRanking;
	}

	public ArrayList<HashMap<String, String>> getCompanyNature() {
		return companyNature;
	}

	public void setCompanyNature(ArrayList<HashMap<String, String>> companyNature) {
		this.companyNature = companyNature;
	}
}
