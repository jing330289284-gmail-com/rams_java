package jp.co.lyc.cms.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import jp.co.lyc.cms.model.TopCustomerInfoModel;
import jp.co.lyc.cms.mapper.TopCustomerInfoMapper;

@Component
public class TopCustomerInfoService {

	@Autowired
	TopCustomerInfoMapper topCustomerInfoMapper;
	
	public String TopCustomerNoSaiBan() {
		String result = topCustomerInfoMapper.TopCustomerNoSaiBan();
		return result;
	}
	public TopCustomerInfoModel selectTopCustomerInfo(String TopCustomerNo) {
		TopCustomerInfoModel resultMod = topCustomerInfoMapper.selectTopCustomerInfo(TopCustomerNo);
		return resultMod;
	}
	public boolean insertTopCustomerInfo(HashMap<String, String> sendMap) {
		boolean result = true;
		try {
			topCustomerInfoMapper.insertTopCustomerInfo(sendMap);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return result = false;
		}
		return result;
	}
	public boolean updateTopCustomerInfo(HashMap<String, String> sendMap) {
		boolean result = true;
		try {
			topCustomerInfoMapper.updateTopCustomerInfo(sendMap);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return result = false;
		}
		return result;
	}
}
