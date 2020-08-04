package jp.co.lyc.cms.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import jp.co.lyc.cms.model.TopCustomerInfoModel;
import jp.co.lyc.cms.mapper.CustomerInfoMapper;
import jp.co.lyc.cms.model.CustomerDepartmentInfoModel;
import jp.co.lyc.cms.model.CustomerInfoModel;

@Component
public class CustomerInfoService {

	//客户信息检索画面service
	@Autowired
	CustomerInfoMapper customerInfoMapper;

	/**
	 * 上位お客様連想
	 * @param topCustpmerName
	 * @return
	 */
	public ArrayList<TopCustomerInfoModel> selectTopCustomer(String topCustpmerName) {
		ArrayList<TopCustomerInfoModel> resultList = customerInfoMapper.selectTopCustomer(topCustpmerName);
		return resultList;
	}
	/**
	 * 部門連想
	 * @param customerDepartmentName
	 * @return
	 */
	public ArrayList<CustomerDepartmentInfoModel> selectDepartmentMaster(String customerDepartmentName) {
		ArrayList<CustomerDepartmentInfoModel> resultList = customerInfoMapper.selectDepartmentMaster(customerDepartmentName);
		return resultList;
	}
	/**
	 * 部門番号検索
	 * @param customerDepartmentName
	 * @return
	 */
	public String selectDepartmentCode(String customerDepartmentName) {
		return customerInfoMapper.selectDepartmentCode(customerDepartmentName);
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
	 * 部門情報検索
	 * @param customerNo
	 * @return
	 */
	public ArrayList<CustomerDepartmentInfoModel> selectCustomerDepartmentInfo(HashMap<String, String> sendMapd) {
		ArrayList<CustomerDepartmentInfoModel> resultList = customerInfoMapper.selectCustomerDepartmentInfo(sendMapd);
		return resultList;
	}
	/**
	 * お客様情報検索
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
