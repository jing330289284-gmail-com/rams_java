package jp.co.lyc.cms.mapper;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

import jp.co.lyc.cms.model.WagesInfoModel;

@Mapper
public interface WagesInfoMapper {
	
	/**
	 * 給料情報の取得
	 * @return
	 */
	public ArrayList<WagesInfoModel> getWagesInfo(HashMap<String, String> sendMap);
	
	/**
	 * インサート
	 * @return
	 */
	public void insert(HashMap<String, String> sendMap);
	
	/**
	 * アップデート
	 * @return
	 */
	public void update(HashMap<String, String> sendMap);
}
