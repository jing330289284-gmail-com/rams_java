package jp.co.lyc.cms.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import jp.co.lyc.cms.model.TopCustomerInfoModel;
import jp.co.lyc.cms.mapper.TopCustomerInfoMapper;

@Component
public class TopCustomerInfoService {

	@Autowired
	TopCustomerInfoMapper topCustomerInfoMapper;
	
	/**
	 * 採番
	 * @return
	 */
	
	public String TopCustomerNoSaiBan() {
		String result = topCustomerInfoMapper.TopCustomerNoSaiBan();
		return result;
	}
	
	/**
	 * 画面情報検索
	 * @param TopCustomerNo
	 * @return
	 */
	
	public TopCustomerInfoModel selectTopCustomerInfo(String TopCustomerNo) {
		TopCustomerInfoModel resultMod = topCustomerInfoMapper.selectTopCustomerInfo(TopCustomerNo);
		return resultMod;
	}
	
	/**
	 * インサートとアップデートの値を設定
	 * @param topCustomerMod
	 * @return
	 */
	public HashMap<String, String> setSendMap(TopCustomerInfoModel topCustomerMod) {
		HashMap<String, String> sendMap = new HashMap<>();
		sendMap.put("topCustomerName", topCustomerMod.getTopCustomerName());
		sendMap.put("url", topCustomerMod.getUrl());
		sendMap.put("topCustomerAbbreviation", topCustomerMod.getTopCustomerAbbreviation());
		sendMap.put("remark", topCustomerMod.getRemark());
		sendMap.put("updateUser", topCustomerMod.getUpdateUser());
		sendMap.put("topCustomerNo", topCustomerMod.getTopCustomerNo());	
		return sendMap;	
	}
	
	/**
	 * お客様の部門削除
	 * @param customerNo
	 */
	
	public boolean deleteTopCustomerInfo(String topCustomerNo) {
		boolean result = true;
		try {
			topCustomerInfoMapper.deleteTopCustomerInfo(topCustomerNo);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return result = false;
		}
		return result;
	}
}
