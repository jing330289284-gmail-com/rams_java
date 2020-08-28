package jp.co.lyc.cms.controller;

import java.util.HashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import jp.co.lyc.cms.mapper.TopCustomerInfoMapper;
import jp.co.lyc.cms.model.TopCustomerInfoModel;
import jp.co.lyc.cms.service.TopCustomerInfoService;

@Controller
@CrossOrigin(origins = "http://127.0.0.1:3000")
@RequestMapping(value = "/topCustomerInfo")
public class TopCustomerInfoController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	TopCustomerInfoService topCustomerInfoService;
	@Autowired
	TopCustomerInfoMapper topCustomerInfoMapper;
	/**
	 * 画面の初期化
	 * @param topCustomerMod
	 * @return
	 */
	@RequestMapping(value = "/init", method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String,Object> init(@RequestBody TopCustomerInfoModel topCustomerMod) {
		logger.info("TopCustomerInfoController.onloadPage:" + "初期化開始");
		HashMap<String,Object> resultMap = new HashMap<>();
		topCustomerMod = topCustomerInfoService.selectTopCustomerInfo(topCustomerMod.getTopCustomerNo());	
		resultMap.put("topCustomerMod", topCustomerMod);
		logger.info("TopCustomerInfoController.onloadPage:" + "初期化終了");
		return resultMap;
	}
	/**
	 * 登録ボタン
	 * @param topCustomerMod
	 * @return
	 */
	@RequestMapping(value = "/toroku", method = RequestMethod.POST)
	@ResponseBody
	public boolean toroku(@RequestBody TopCustomerInfoModel topCustomerMod) {
		logger.info("TopCustomerInfoController.onloadPage:" + "登録開始");
		boolean result = true;
		TopCustomerInfoModel checkMod = topCustomerInfoService.selectTopCustomerInfo(topCustomerMod.getTopCustomerNo());
		if (checkMod == null && topCustomerMod.getActionType().equals("insert")) {
			topCustomerInfoMapper.insertTopCustomerInfo(topCustomerInfoService.setSendMap(topCustomerMod));
		} else if (checkMod != null && (topCustomerMod.getActionType().equals("update"))) {
			topCustomerInfoMapper.updateTopCustomerInfo(topCustomerInfoService.setSendMap(topCustomerMod));
		}
		logger.info("TopCustomerInfoController.onloadPage:" + "登録終了");
		return result;
	}
}
