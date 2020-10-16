package jp.co.lyc.cms.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.lyc.cms.mapper.EnterPeriodSearchMapper;
import jp.co.lyc.cms.model.EnterPeriodSearchModel;
import jp.co.lyc.cms.util.UtilsCheckMethod;
import jp.co.lyc.cms.util.UtilsController;

@Component
public class EnterPeriodSearchService {

	@Autowired
	EnterPeriodSearchMapper enterPeriodSearchMapper;
	
	/**
	 * 区分は入社の場合
	 * @param sendMap
	 * @return
	 */
	public ArrayList<EnterPeriodSearchModel> selectEnterPeriodData(HashMap<String, String> sendMap) {
		//社員最初の入場期日を取得
		ArrayList<EnterPeriodSearchModel> dateList = 
				enterPeriodSearchMapper.selectAdmissionStartDate(sendMap);
		//画面の年月
		String yearAndMonth = sendMap.get("yearAndMonth");
		ArrayList<String> fullYearPeople = new ArrayList<String>();
		for(EnterPeriodSearchModel epsModel : dateList) {
			//最初の入場年
			int yearAd = Integer.parseInt(epsModel.getAdmissionStartDate().substring(0,4));
			//最初の入場月
			int monthAd = Integer.parseInt(epsModel.getAdmissionStartDate().substring(4,6));
			//画面の年
			int yearPage = Integer.parseInt(yearAndMonth.substring(0,4));
			//画面の月
			int monthPage = Integer.parseInt(yearAndMonth.substring(4));
			//待機開始年月
			//待機月数
			int nonSiteMonths = Integer.parseInt(epsModel.getNonSiteMonths());
			int year = yearPage - yearAd;
			int month = monthAd - monthPage;
			if(month == 0) {
				month = year*12 - nonSiteMonths;
			}else{
				month = year*12 + month - nonSiteMonths;
			}
			if(month != 0 && month%12 == 0) {
				fullYearPeople.add(epsModel.getEmployeeNo());
			}
		}
		if(fullYearPeople.size() == 0) {
			return null;
		}
		HashMap<String, Object> resultSendMap = new HashMap<String, Object>();
		resultSendMap.put("fullYearPeople", fullYearPeople);
		//非稼働期間以外のデータ
		ArrayList<EnterPeriodSearchModel> resultList = 
				enterPeriodSearchMapper.selectEnterPeriodData(resultSendMap);
		//非稼働期間
		ArrayList<EnterPeriodSearchModel> periodList = 
				enterPeriodSearchMapper.selectNonSitePeriod(resultSendMap);
		for(EnterPeriodSearchModel eps:resultList) {
			String employeeNo = eps.getEmployeeNo();
			ArrayList<EnterPeriodSearchModel> periodsList = new ArrayList<EnterPeriodSearchModel>();
			for(EnterPeriodSearchModel pl : periodList) {
				if(!UtilsCheckMethod.isNullOrEmpty(pl.getNonSiteMonths()) &&
						!UtilsCheckMethod.isNullOrEmpty(pl.getNonSitePeriod()) &&
						pl.getEmployeeNo().equals(employeeNo)) {
					periodsList.add(pl);
				}
			}
			eps.setNonSitePeriodsList(periodsList);
		}
		return resultList;
	}
	
	/**
	 * sendMapの作成
	 * @param enterPeriodSearchModel
	 * @return
	 */
	public HashMap<String, String> getSendMap( EnterPeriodSearchModel enterPeriodSearchModel) {
		HashMap<String, String> sendMap = new HashMap<String, String>();
		sendMap.put("employeeNo", enterPeriodSearchModel.getEmployeeNo());
		sendMap.put("admissionStartDate", enterPeriodSearchModel.getAdmissionStartDate());
		sendMap.put("enterPeriodKbn", enterPeriodSearchModel.getEmployeeName());
		sendMap.put("enterPeriodKbn", enterPeriodSearchModel.getEnterPeriodKbn());
		sendMap.put("insuranceFeeAmount", enterPeriodSearchModel.getInsuranceFeeAmount());
		sendMap.put("nextBonusMonth", enterPeriodSearchModel.getNextBonusMonth());
		sendMap.put("reflectYearAndMonth", enterPeriodSearchModel.getReflectYearAndMonth());
		sendMap.put("salary", enterPeriodSearchModel.getSalary());
		sendMap.put("scheduleOfBonusAmount", enterPeriodSearchModel.getScheduleOfBonusAmount());
		sendMap.put("waitingCost", enterPeriodSearchModel.getWaitingCost());
		sendMap.put("yearAndMonth", enterPeriodSearchModel.getYearAndMonth());
		sendMap.put("NonSiteCode", enterPeriodSearchModel.getNonSitePeriod());
		sendMap.put("unitPrice", enterPeriodSearchModel.getUnitPrice());
		sendMap.put("nonSiteMonths", enterPeriodSearchModel.getNonSiteMonths());
		return sendMap;
	}
}
