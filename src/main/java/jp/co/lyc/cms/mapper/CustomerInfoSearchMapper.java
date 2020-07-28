package jp.co.lyc.cms.mapper;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

import jp.co.lyc.cms.model.CustomerInfoModel;

@Mapper
public interface CustomerInfoSearchMapper {
	public ArrayList<CustomerInfoModel> SelectCustomerInfo(HashMap<String, String> sendMap);
	public void delect(String customerNo);
}
