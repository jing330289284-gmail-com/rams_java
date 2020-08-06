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
	 * お客様情報削除
	 * @param customerNo
	 */
	
	public boolean delectCustomerInfo(String customerNo) {
		boolean result = true;
		try {
			customerInfoSearchMapper.delectCustomerInfo(customerNo);
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
	
	public boolean delectCustomerDepartmentInfo(String customerNo) {
		boolean result = true;
		try {
			customerInfoSearchMapper.delectCustomerDepartmentInfo(customerNo);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return result = false;
		}
		return result;
	}
}
