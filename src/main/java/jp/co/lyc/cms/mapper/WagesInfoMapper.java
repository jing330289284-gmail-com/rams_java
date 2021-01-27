package jp.co.lyc.cms.mapper;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

import jp.co.lyc.cms.model.SiteModel;
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
	
	/**
	 * 稼働判断
	 * @return
	 */
	public ArrayList<SiteModel> kadouCheck(String employeeNo);
	
	/**
	 * 保険検索
	 * @param employeeNo
	 * @return
	 */
	public ArrayList<WagesInfoModel> hokenSearch(String employeeNo);
	
	/**
	 * 社員形式を取得
	 * @param employeeNo
	 * @return
	 */
	public WagesInfoModel getEmployeeForm(String employeeNo);
	
	/**
	 * 最近の非稼働月数
	 * @param employeeNo
	 * @return
	 */
	public ArrayList<String> getLastKadouPeriod(String employeeNo);
	
	/**
	 * 給料情報削除
	 * @param customerNo
	 */
	public void delete(HashMap<String, String> sendMap);
}
