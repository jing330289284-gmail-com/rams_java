package jp.co.lyc.cms.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import jp.co.lyc.cms.model.MasterModel;
import jp.co.lyc.cms.service.MasterUpdateService;

@Controller
@CrossOrigin(origins = "http://127.0.0.1:3000")
@RequestMapping(value = "/masterUpdate")
public class MasterUpdateController {

	@Autowired
	MasterUpdateService masterUpdateService;

	@RequestMapping(value = "/getMasterInfo", method = RequestMethod.POST)
	@ResponseBody
	public List<MasterModel> getMasterInfo(@RequestBody Map master) {
		List<MasterModel> masterList = new ArrayList<MasterModel>();

		try {
			HashMap<String, String> sendMap = new HashMap<>();
			sendMap.put("master", master.get("master").toString());
			sendMap.put("columnName", master.get("master").toString().substring(4) + "name");
			sendMap.put("columnCode", master.get("master").toString().substring(4) + "code");
			masterList = masterUpdateService.getMasterInfo(sendMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return masterList;
	}

	/**
	 * 修正ボタン
	 * 
	 * @return
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public boolean toroku(@RequestBody MasterModel masterModel) {
		boolean result = checkHave(masterModel);
		if (result) {
			return update(masterModel);
		}
		return false;
	}

	/**
	 * インサート
	 * 
	 * @return
	 */
	public boolean update(MasterModel masterModel) {
		HashMap<String, String> sendMap = new HashMap<>();
		sendMap.put("master", masterModel.getMaster());
		sendMap.put("code", masterModel.getCode());
		sendMap.put("data", masterModel.getData());
		sendMap.put("columnName", masterModel.getMaster().substring(4) + "name");
		sendMap.put("columnCode", masterModel.getMaster().toString().substring(4) + "code");
		sendMap.put("updateUser", "LYC001");
		return masterUpdateService.updateMaster(sendMap);

	}

	/**
	 * あるかどうかのチェック
	 * 
	 * @return
	 */
	public boolean checkHave(MasterModel masterModel) {
		masterModel.setColumnName(masterModel.getMaster().substring(4) + "name");
		return masterUpdateService.checkHave(masterModel);

	}

	/**
	 * nullと空の判断
	 * 
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
