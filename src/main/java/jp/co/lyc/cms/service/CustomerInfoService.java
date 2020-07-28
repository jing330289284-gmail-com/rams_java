package jp.co.lyc.cms.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import jp.co.lyc.cms.model.TopCustomerInfoModel;
import jp.co.lyc.cms.mapper.CustomerInfoMapper;
import jp.co.lyc.cms.model.CustomerInfoModel;

@Component
public class CustomerInfoService {

	@Autowired
	CustomerInfoMapper customerInfoMapper;
	
	public ArrayList<HashMap<String, String>> selectCustomerRanking() {
		ArrayList<HashMap<String, String>> resultList = customerInfoMapper.selectCustomerRanking();
		return resultList;
	}
	public ArrayList<TopCustomerInfoModel> selectTopCustomer(String topCustpmerName) {
		ArrayList<TopCustomerInfoModel> resultList = customerInfoMapper.selectTopCustomer(topCustpmerName);
		return resultList;
	}
	public String checkTopCustomer(String topCustpmerName) {
		String result = customerInfoMapper.checkTopCustomer(topCustpmerName);
		return result;
	}
	public ArrayList<HashMap<String, String>> selectCompanyNature() {
		ArrayList<HashMap<String, String>> resultList = customerInfoMapper.selectCompanyNature();
		return resultList;
	}
	public CustomerInfoModel selectCustomerInfo(String customerNo) {
		CustomerInfoModel resultMod = customerInfoMapper.selectCustomerInfo(customerNo);
		return resultMod;
	}
	public String customerNoSaiBan() {
		String result = customerInfoMapper.customerNoSaiBan();
		return result;
	}
	public boolean insertCustomerInfo(HashMap<String, String> sendMap) {
		boolean result = true;
		try {
			customerInfoMapper.insertCustomerInfo(sendMap);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return result = false;
		}
		return result;
	}
	public boolean updateCustomerInfo(HashMap<String, String> sendMap) {
		boolean result = true;
		try {
			customerInfoMapper.updateCustomerInfo(sendMap);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return result = false;
		}
		return result;
	}
}
