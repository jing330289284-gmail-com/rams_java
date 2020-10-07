package jp.co.lyc.cms.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import jp.co.lyc.cms.model.MonthlySalesSearchModel;
@Mapper
public interface MonthlySalesSearchMapper {
	public List<MonthlySalesSearchModel> getMonthlySalesInfo(Map<String, Object> sendMap);
}
