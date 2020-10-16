package jp.co.lyc.cms.mapper;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

import jp.co.lyc.cms.model.EnterPeriodSearchModel;

@Mapper
public interface EnterPeriodSearchMapper {
	
	/**
	 * 最初の入場期日を取得
	 * @return
	 */
	public ArrayList<EnterPeriodSearchModel> selectAdmissionStartDate(HashMap<String, String> sendMap);
	
	/**
	 * 結果を出ます
	 * @param sendMap
	 * @return
	 */
	public ArrayList<EnterPeriodSearchModel> selectEnterPeriodData(HashMap<String, Object> sendMap);
	
	/**
	 * 社員の非稼働期間検索
	 * @param employeeNo
	 * @return
	 */
	public ArrayList<EnterPeriodSearchModel> selectNonSitePeriod(HashMap<String, Object> fullYearPeople);
	
}
