package jp.co.lyc.cms.mapper;

import java.util.ArrayList;
import java.util.HashMap;
import jp.co.lyc.cms.model.BankInfoModel;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GetBankInfoMapper {

	public ArrayList<String> selectBankInfo();
	public ArrayList<HashMap<String, String>> getBankBranchInfo(HashMap<String, String> sendMap);
	public BankInfoModel selectAccountInfo(String employeeOrCustomerNo , String accountBelongsStatus);
	public void insertAccount(HashMap<String, String> sendMap);
	public void updateAccount(HashMap<String, String> sendMap);
}
