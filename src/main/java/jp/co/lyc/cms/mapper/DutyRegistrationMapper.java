package jp.co.lyc.cms.mapper;

import java.util.HashMap;
import jp.co.lyc.cms.model.DutyRegistrationModel;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DutyRegistrationMapper {

	/**
	 * インサート
	 * @param sendMap
	 */
	public void insertDutyRegistration(HashMap<String, String> sendMap);
	/**
	 * 画面情報検索
	 * @param TopCustomerNo
	 * @return
	 */
	public DutyRegistrationModel selectDutyRegistration(HashMap<String, String> sendMap);
	/**
	 * アップデート
	 * @param sendMap
	 */
	public void updateDutyRegistration(HashMap<String, String> sendMap);	
	/**
	 * 上位お客様削除
	 * @param customerNo
	 */
	public void deleteDutyRegistration(String dutyRegistrationNo);
}
