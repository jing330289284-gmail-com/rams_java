package jp.co.lyc.cms.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import jp.co.lyc.cms.model.TechnologyTypeModel;
import jp.co.lyc.cms.service.technologyTypeMasterService;

@Controller
@CrossOrigin(origins = "http://127.0.0.1:3000")
@RequestMapping(value = "/technologyTypeMaster")
public class technologyTypeMasterController {

	@Autowired
	technologyTypeMasterService technologyTypeMasterService;
	
	/**
	 * 画面の初期化
	 * @return
	 */
	@RequestMapping(value = "/onloadPage", method = RequestMethod.POST)
	@ResponseBody
	public String onloadPage() {
		return technologyTypeMasterService.getTechnologyCodeSaiban();
	}
	/**
	 * 登録ボタン
	 * @return
	 */
	@RequestMapping(value = "/toroku", method = RequestMethod.POST)
	@ResponseBody
	public boolean toroku(@RequestBody TechnologyTypeModel technologyTypeMod) {
		boolean result = checkHave(technologyTypeMod.getTechnologytypeName());
		if(result) {
			return insert(technologyTypeMod);
		}
		return false;
	}
	/**
	 * インサート
	 * @return
	 */
	public boolean insert(TechnologyTypeModel technologyTypeMod) {
		HashMap<String, String> sendMap = new HashMap<>();
		sendMap.put("technologytypeCode", technologyTypeMod.getTechnologytypeCode());
		sendMap.put("technologytypeName", technologyTypeMod.getTechnologytypeName());
		sendMap.put("updateUser", technologyTypeMod.getUpdateUser());
		return technologyTypeMasterService.insertTechnologyTypeMaster(sendMap);
		
	}
	/**
	 * あるかどうかのチェック
	 * @return
	 */
	public boolean checkHave(String technologytypeName) {
		return technologyTypeMasterService.checkHave(technologytypeName);
		
	}
	/**
	 * nullと空の判断
	 * @return
	 */
	public boolean isNullOrEmpty(String aString) {
		boolean result = true;
		if (aString == null || aString.isEmpty()) {
			return result;
		} else {
			return result = false;
		}
	}
}
