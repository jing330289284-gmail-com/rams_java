package jp.co.lyc.cms.mapper;

import java.util.HashMap;
import jp.co.lyc.cms.model.AccountInfoModel;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccountInfoMapper {

	/**
	 * 口座情報の検索
	 * @param employeeOrCustomerNo
	 * @param accountBelongsStatus
	 * @return
	 */
	public AccountInfoModel selectAccountInfo(String employeeOrCustomerNo);
	/**
	 * インサート
	 * @param sendMap
	 */
	public void insertAccount(HashMap<String, String> sendMap);
	/**
	 * アップデート
	 * @param sendMap
	 */
	public void updateAccount(HashMap<String, String> sendMap);
}
