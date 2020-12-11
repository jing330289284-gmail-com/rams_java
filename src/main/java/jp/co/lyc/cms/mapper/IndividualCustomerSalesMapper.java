package jp.co.lyc.cms.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import jp.co.lyc.cms.model.IndividualCustomerSalesModel;

@Mapper
public interface IndividualCustomerSalesMapper {
	public List<IndividualCustomerSalesModel> getCustomerSalesInfo(Map<String, Object> sendMap);
	public List<IndividualCustomerSalesModel> getCustomerSalesInfoTwice(Map<String, Object> sendMap);
	public List<IndividualCustomerSalesModel> getCustomerSalesInfoThird(Map<String, Object> sendMap);
}

