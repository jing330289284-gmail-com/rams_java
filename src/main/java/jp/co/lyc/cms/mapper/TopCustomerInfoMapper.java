package jp.co.lyc.cms.mapper;

import java.util.HashMap;
import jp.co.lyc.cms.model.TopCustomerInfoModel;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TopCustomerInfoMapper {

	/**
	 * 採番
	 * @return
	 */
	public String TopCustomerNoSaiBan();
	/**
	 * インサート
	 * @param sendMap
	 */
	public void insertTopCustomerInfo(HashMap<String, String> sendMap);
	/**
	 * アップデート
	 * @param sendMap
	 */
	public void updateTopCustomerInfo(HashMap<String, String> sendMap);
	/**
	 * 画面情報検索
	 * @param TopCustomerNo
	 * @return
	 */
	public TopCustomerInfoModel selectTopCustomerInfo(String TopCustomerNo);
	
	/**
	 * 上位お客様削除
	 * @param customerNo
	 */
	public void deleteTopCustomerInfo(String topCustomerNo);
}
