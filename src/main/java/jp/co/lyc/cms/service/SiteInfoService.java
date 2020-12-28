package jp.co.lyc.cms.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import jp.co.lyc.cms.mapper.SiteInfoMapper;
import jp.co.lyc.cms.model.SiteModel;

@Component
public class SiteInfoService {

	@Autowired
	SiteInfoMapper siteInfoMapper;

	@Transactional(rollbackFor = Exception.class)
	public boolean insertSiteInfo(Map<String, Object> sendMap) {
		try {
			siteInfoMapper.siteInsert(sendMap);
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Transactional(rollbackFor = Exception.class)
	public boolean updateSiteInfo(Map<String, Object> sendMap) {
		try {
			if(sendMap.get("workState").equals("2")) {
				List<SiteModel> lastData = siteInfoMapper.getSiteInfo((String) sendMap.get("employeeNo"));
				String unitPriceLast = lastData.get(lastData.size() -1).getUnitPrice();
				String admissionEndDate = (String) sendMap.get("admissionEndDate");
				int year = Integer.parseInt(admissionEndDate.substring(0,4));
				int month = Integer.parseInt(admissionEndDate.substring(4,6));
				month += 1;
				if(month > 12) {
					month -= 12;
					year += 1;
				}
				admissionEndDate = year + "" + (month > 10 ? month : "0" + month) + "01"; 
				String unitPriceNow = (String) sendMap.get("unitPrice");
				sendMap.replace("unitPrice", unitPriceLast);
				siteInfoMapper.siteUpdate(sendMap);
				sendMap.replace("workState", "0");
				sendMap.replace("unitPrice", unitPriceNow);
				sendMap.replace("admissionStartDate", admissionEndDate);
				sendMap.replace("admissionEndDate", null);
				siteInfoMapper.siteInsert(sendMap);
			}else {
				siteInfoMapper.siteUpdate(sendMap);
			}
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public List<SiteModel> getSiteInfo(String employeeNo) {

		List<SiteModel> siteList = siteInfoMapper.getSiteInfo(employeeNo);
		return siteList;
	}


	
	/**
	 * 現場情報を削除
	 * 
	 * @param emp
	 * @return boolean
	 */
	@Transactional(rollbackFor = Exception.class)
	public boolean deleteSiteInfo(Map<String, Object> sendMap) {
		boolean result = true;
		try {
			siteInfoMapper.deleteSiteInfo(sendMap);
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
			return result = false;
		}
		return result;
	}

}
