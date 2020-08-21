package jp.co.lyc.cms.mapper;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

import jp.co.lyc.cms.model.CustomerInfoModel;

@Mapper
public interface CustomerInfoSearchMapper {
	/**
	 * 検索
	 * @param sendMap
	 * @return
	 */
	public ArrayList<CustomerInfoModel> SelectCustomerInfo(HashMap<String, String> sendMap);
	/**
	 * お客様情報削除
	 * @param customerNo
	 */
	public void deleteCustomerInfo(String customerNo);
	
	/**
	 * お客様の部門削除
	 * @param customerNo
	 */
	public void deleteCustomerDepartmentInfo(String customerNo);
	
	/**
	 * 現場にお客様を使用確認
	 * @param customerNo
	 * @return
	 */
	public ArrayList<String> checkCustomerInSiteInfo(String customerNo);
	
	/**
	 * お客様の上位お客様番号検索
	 * @param customerNo
	 * @return
	 */
	public String getTopCustomerNoInCustomerInfo(String customerNo);
	
	/**
	 * お客様テーブルの上位お客様番号同じのお客様チェック
	 * @param customerNo
	 * @return
	 */
	public ArrayList<String> getCustomerNoWithSameTop(String topCustomerNo);
}
