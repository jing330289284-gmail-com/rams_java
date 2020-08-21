package jp.co.lyc.cms.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.lyc.cms.mapper.CustomerInfoMapper;
import jp.co.lyc.cms.mapper.CustomerInfoSearchMapper;
import jp.co.lyc.cms.model.CustomerInfoModel;

@Component
public class CustomerInfoSearchService {

	@Autowired
	CustomerInfoSearchMapper customerInfoSearchMapper;
	
	/**
	 * 検索
	 * @param sendMap
	 * @return
	 */
	
	public ArrayList<CustomerInfoModel> SelectCustomerInfo(HashMap<String, String> sendMap) {
		return customerInfoSearchMapper.SelectCustomerInfo(sendMap);
	}
	
	/**
	 * 現場にお客様を使用確認
	 * @param customerNo
	 * @return
	 */
	
	public ArrayList<String> checkCustomerInSiteInfo(String customerNo) {
		return customerInfoSearchMapper.checkCustomerInSiteInfo(customerNo);
	}
	
	/**
	 * お客様の上位お客様番号検索
	 * @param customerNo
	 * @return
	 */
	
	public ArrayList<String> getCustomerNoWithSameTop(String topCustomerNo) {
		return customerInfoSearchMapper.getCustomerNoWithSameTop(topCustomerNo);
	}
	
	/**
	 * お客様の上位お客様番号検索
	 * @param customerNo
	 * @return
	 */
	
	public String getTopCustomerNoInCustomerInfo(String customerNo) {
		return customerInfoSearchMapper.getTopCustomerNoInCustomerInfo(customerNo);
	}

	/**
	 * お客様情報削除
	 * @param customerNo
	 */
	
	public boolean deleteCustomerInfo(String customerNo) {
		boolean result = true;
		try {
			customerInfoSearchMapper.deleteCustomerInfo(customerNo);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return result = false;
		}
		return result;
	}
	
	/**
	 * お客様の部門削除
	 * @param customerNo
	 */
	
	public boolean deleteCustomerDepartmentInfo(String customerNo) {
		boolean result = true;
		try {
			customerInfoSearchMapper.deleteCustomerDepartmentInfo(customerNo);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return result = false;
		}
		return result;
	}
}
