package jp.co.lyc.cms.model;

import java.io.Serializable;

public class CustomerModel implements Serializable {

	private static final long serialVersionUID = -2028159323401651353L;

	String customerRankingCode;// コード
	String customerRankingName;// 名前

	public String getCustomerRankingCode() {
		return customerRankingCode;
	}

	public void setCustomerRankingCode(String customerRankingCode) {
		this.customerRankingCode = customerRankingCode;
	}

	public String getCustomerRankingName() {
		return customerRankingName;
	}

	public void setCustomerRankingName(String customerRankingName) {
		this.customerRankingName = customerRankingName;
	}

}
