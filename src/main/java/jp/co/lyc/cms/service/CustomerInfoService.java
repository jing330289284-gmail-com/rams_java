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

	//客户信息检索画面service
	@Autowired
	CustomerInfoMapper customerInfoMapper;
	/**
	 * 画面三个下拉框
	 * @return
	 */
	public ArrayList<HashMap<String, String>> selectCustomerRanking() {
		ArrayList<HashMap<String, String>> resultList = customerInfoMapper.selectCustomerRanking();
		return resultList;
	}
	public ArrayList<TopCustomerInfoModel> selectTopCustomer(String topCustpmerName) {
		ArrayList<TopCustomerInfoModel> resultList = customerInfoMapper.selectTopCustomer(topCustpmerName);
		return resultList;
	}
	public ArrayList<HashMap<String, String>> selectCompanyNature() {
		ArrayList<HashMap<String, String>> resultList = customerInfoMapper.selectCompanyNature();
		return resultList;
	}
	/**
	 * 上位客户是否存在
	 * @param topCustpmerName
	 * @return
	 */
	public String checkTopCustomer(String topCustpmerName) {
		String result = customerInfoMapper.checkTopCustomer(topCustpmerName);
		return result;
	}
	/**
	 * 查询客户信息
	 * @param customerNo
	 * @return
	 */
	public CustomerInfoModel selectCustomerInfo(String customerNo) {
		CustomerInfoModel resultMod = customerInfoMapper.selectCustomerInfo(customerNo);
		return resultMod;
	}
	/**
	 * 客户番号采番
	 * @return
	 */
	public String customerNoSaiBan() {
		String result = customerInfoMapper.customerNoSaiBan();
		return result;
	}
	/**
	 * 插入客户信息
	 * @param sendMap
	 * @return
	 */
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
	/**
	 * 更新客户信息
	 * @param sendMap
	 * @return
	 */
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
	/**
	 * 插入部门信息
	 * @param sendMap
	 * @return
	 */
	public boolean insertCustomerDepartment(HashMap<String, String> sendMap) {
		boolean result = true;
		try {
			customerInfoMapper.insertCustomerDepartment(sendMap);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return result = false;
		}
		return result;
	}
	/**
	 * 更新部门信息
	 * @param sendMap
	 * @return
	 */
	public boolean updateCustomerDepartment(HashMap<String, String> sendMap) {
		boolean result = true;
		try {
			customerInfoMapper.updateCustomerDepartment(sendMap);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return result = false;
		}
		return result;
	}
}
