package jp.co.lyc.cms.mapper;

import java.util.HashMap;
import jp.co.lyc.cms.model.TopCustomerInfoModel;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TopCustomerInfoMapper {

	public String TopCustomerNoSaiBan();
	public void insertTopCustomerInfo(HashMap<String, String> sendMap);
	public void updateTopCustomerInfo(HashMap<String, String> sendMap);
	public TopCustomerInfoModel selectTopCustomerInfo(String TopCustomerNo);
}
