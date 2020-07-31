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
	 * 削除ボタン
	 * @param customerNo
	 */
	public void delect(String customerNo);
}
