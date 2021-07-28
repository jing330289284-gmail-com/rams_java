package jp.co.lyc.cms.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.lyc.cms.mapper.BpInfoMapper;
import jp.co.lyc.cms.model.BpInfoModel;

@Component
public class BpInfoService {

	@Autowired
	BpInfoMapper bpInfoMapper;

	/**
	 * ログイン
	 * 
	 * @param sendMap
	 * @return
	 */

	public BpInfoModel getBpInfo(Map<String, Object> sendMap) {
		BpInfoModel bpInfoModel = bpInfoMapper.getBpInfo(sendMap);
		return bpInfoModel;
	}

	public List<BpInfoModel> getBpInfoList(Map<String, Object> sendMap) {
		return bpInfoMapper.getBpInfoList(sendMap);
	}
}