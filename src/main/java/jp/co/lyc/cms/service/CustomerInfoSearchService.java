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
	
	public ArrayList<CustomerInfoModel> SelectCustomerInfo(HashMap<String, String> sendMap) {
		return customerInfoSearchMapper.SelectCustomerInfo(sendMap);
	}
	public boolean delect(String customerNo) {
		boolean result = true;
		try {
			customerInfoSearchMapper.delect(customerNo);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return result = false;
		}
		return result;
	}
}
